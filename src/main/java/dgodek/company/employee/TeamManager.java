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
    private Predicate<Employee> predicate;

    public TeamManager(Builder builder) {
        super(builder);
        this.maxSize = builder.maxSize;
        this.predicate = builder.predicate;
        this.employees = new ArrayList<>();
    }

    @Override
    public boolean canHire(Employee employee) {
        return maxSize > employees.size() &&
                predicate.test(employee);
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
        amountOfWork += task.getunitsOfWork();
    }


    public void setPredicate(Predicate<Employee> predicate) {
        this.predicate = predicate;
    }

    @Override
    public Report reportWork() {
        List<Report> reports = new ArrayList<>();
        employees.forEach((e) -> e.reportWork(reports));
        reports.add(new WorkerReport(this));

        return new ManagerReport(this, reports);
    }

    public List<Report> reportWork(List<Report> reports) {
        employees.forEach((e) -> e.reportWork(reports));
        reports.add(new WorkerReport(this));

        return reports;
    }

    public static class Builder extends AbstractEmployee.Builder {
        private final int maxSize;
        private Predicate<Employee> predicate = (o) -> true;

        public Builder(String name, String surname, String email, int maxSize, String nationality) {
            super(name, surname, email, nationality);
            role(Role.MANAGER);
            this.maxSize = maxSize;
        }

        public static Builder getHiringOnlyMan(String name, String surname, String email, int maxSize,
                                               String nationality) {

            return new Builder(name, surname, email,maxSize,nationality)
                    .predicate((o) -> o.getSex() == Sex.MALE);
        }

        public static Builder getHiringOnlyAGH(String name, String surname, String email, int maxSize,
                                               String nationality) {

            return new Builder(name, surname, email, maxSize, nationality)
                    .predicate((o) -> o.getAcademy().equals("AGH"));
        }

        public static Builder getHiringOnlyFromPoland(String name, String surname, String email, int maxSize,
                                               String nationality) {

            return new Builder(name, surname, email, maxSize, nationality)
                    .predicate((o) -> o.getNationality().equals("Poland"));
        }

        public static Builder getHiringOnlyWithGmailMail(String name, String surname, String email, int maxSize,
                                                      String nationality) {

            return new Builder(name, surname, email, maxSize, nationality)
                    .predicate((o) -> Pattern
                            .matches("[a-zA-Z0-9._]+@gmail\\.com",
                                    o.getEmail()));
        }

        public Builder predicate(Predicate<Employee> predicate) {
            this.predicate = predicate;
            return this;
        }

        @Override
        public TeamManager build() {
            return new TeamManager(this);
        }

    }

}
