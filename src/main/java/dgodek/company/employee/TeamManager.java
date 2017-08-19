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
    private Predicate<Employee> hireStrategy;

    TeamManager(Builder builder) {
        super(builder);
        this.maxSize = builder.maxSize;
        this.hireStrategy = builder.predicate;
        this.employees = new ArrayList<>();
    }

    @Override
    public boolean canHire(Employee employee) {
        return maxSize > employees.size() &&
                hireStrategy.test(employee);
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
    public void assign(Task task) {
        Employee employee = getWorkerWithLowestAmountOfWork();
        System.out.println(this.toString() + " | Assigning " + task.toString() + "to employee " + employee.toString());
        employee.assign(task);
        tasks.add(task);
        amountOfWork += task.getUnitsOfWork();
    }


    public void setHireStrategy(Predicate<Employee> predicate) {
        this.hireStrategy = predicate;
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
        private Predicate<Employee> predicate = (o) -> true;

        public Builder(String name, String surname, String email, int maxSize, String nationality) {
            super(name, surname, email, nationality);
            super.role(Role.MANAGER);
            this.maxSize = maxSize;
        }

        public Builder predicate(Predicate<Employee> predicate) {
            this.predicate = predicate;
            return this;
        }

        public TeamManager build() {
            return new TeamManager(this);
        }

        public TeamManager buildHiringOnlyMan() {
            this.predicate = (o) -> o.getSex() == Sex.MALE;

            return new TeamManager(this);
        }

        public TeamManager buildHiringOnlyAGH() {
            this.predicate = (o) -> o.getAcademy().equals("AGH");

            return new TeamManager(this);
        }

        public TeamManager buildHiringOnlyFromPoland() {
            this.predicate = (o) -> o.getNationality().equals("Poland");

            return new TeamManager(this);
        }

        public TeamManager buildHiringOnlyWithGmailMail() {
            this.predicate =(o) -> Pattern
                            .matches("[a-zA-Z0-9._]+@gmail\\.com",
                            o.getEmail());

            return new TeamManager(this);
        }


    }

}
