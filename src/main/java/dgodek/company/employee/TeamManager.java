package dgodek.company.employee;

import dgodek.company.exceptions.NoHiredEmployeesException;
import dgodek.company.exceptions.NoSuchHiredEmployeeException;
import dgodek.company.Task;
import dgodek.company.exceptions.CantHireException;
import dgodek.company.report.ManagerReport;
import dgodek.company.report.Report;
import dgodek.company.report.WorkerReport;

import java.util.*;
import java.util.function.Predicate;
import java.util.regex.Pattern;

/**
 * Created by matematyk60 on 23.07.17.
 */
public class TeamManager extends AbstractEmployee implements Manager {
    private final  List<Employee> employees;
    private int maxSize;
    private HiringStrategy hiringStrategy;

    TeamManager(Builder builder) {
        super(builder);
        this.maxSize = builder.maxSize;
        this.hiringStrategy = builder.hiringStrategy;
        this.employees = new ArrayList<>();
    }

    @Override
    public boolean canHire(Employee employee) {
        return isNotFull() &&
                hiringStrategy.getPredicate().test(employee);
    }

    @Override
    public boolean isNotFull() {
        return maxSize > employees.size();
    }

    @Override
    public void hire(Employee employee) {
        if (canHire(employee)) {
            employees.add(employee);
        } else {
            throw new CantHireException();
        }
    }

    @Override
    public void fire(Employee employee) {
        if (!employees.remove(employee)) {
            throw new NoSuchHiredEmployeeException();
        }
    }

    @Override
    public Employee getWorkerWithLowestAmountOfWork() {
        Optional<Employee> employeeWithLowestAmountOfWork = employees
                .stream()
                .min(Comparator.comparing(Employee::getAmountOfWork));

        return employeeWithLowestAmountOfWork
                .orElseThrow(NoHiredEmployeesException::new);
    }

    @Override
    public Integer getAmountOfWork() {
        if(employees.isEmpty()) {
            return Integer.MAX_VALUE;
        }
        if(
            employees.stream().anyMatch((e) -> e.getAmountOfWork().equals(Integer.MAX_VALUE))
        ) {
           return Integer.MAX_VALUE;
        }
        return amountOfWork;
    }

    public HiringStrategy getHiringStrategy() {
        return hiringStrategy;
    }

    @Override
    public void assign(Task task) {
        Employee employee = getWorkerWithLowestAmountOfWork();
        System.out.println(this.toString() + " | Assigning " + task.toString() + "to employee " + employee.toString());
        employee.assign(task);
        tasks.add(task);
        amountOfWork += task.getUnitsOfWork();
    }


    @Override
    public Report reportWork() {
        List<Report> reports = new ArrayList<>();
        employees.forEach((e) -> e.reportWork(reports));

        return new ManagerReport(this, reports);
    }

    public List<Report> reportWork(List<Report> reports) {
        employees.forEach((e) -> e.reportWork(reports));
        reports.add(new WorkerReport(this));

        return reports;
    }

    public static class Builder extends AbstractEmployee.Builder<Builder> {
        private final int maxSize;
        private HiringStrategy hiringStrategy = HiringStrategy.NONE;

        public Builder(String name, String surname, String email, int maxSize, String nationality) {
            super(name, surname, email, nationality);
            super.role(Role.MANAGER);
            this.maxSize = maxSize;
        }

        public Builder hiringStategy(HiringStrategy hiringStrategy) {
            this.hiringStrategy = hiringStrategy;
            return this;
        }

        public TeamManager build() {
            return new TeamManager(this);
        }

    }

    public enum HiringStrategy {
        HIRING_ONLY_WITH_GMAIL_MAIL((o) -> Pattern
                .matches("[a-zA-Z0-9._]+@gmail\\.com",
                        o.getEmail()), "Hiring only with gmail mail"),
        HIRING_ONLY_FROM_POLAND((o) -> o.getNationality().equals("Poland"), "Hiring only from Poland"),
        HIRING_ONLY_FROM_AGH((o) -> o.getAcademy().equals("AGH"), "Hiring only from AGH "),
        HIRING_ONLY_MAN((o) -> o.getSex() == Sex.MALE, "Hiring only man"),
        NONE((o) -> true, "None");

        private Predicate<Employee> predicate;
        private String stringRepresentation;

        HiringStrategy(Predicate<Employee> predicate, String stringRepresentation) {
            this.predicate = predicate;
            this.stringRepresentation = stringRepresentation;
        }

        public Predicate<Employee> getPredicate() {
            return predicate;
        }

        public String getStringRepresentation() {
            return stringRepresentation;
        }


        @Override
        public String toString() {
            return stringRepresentation;
        }
    }

}
