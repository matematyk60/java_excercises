package dgodek.company.employee;

import dgodek.company.exceptions.NoHiredEmployeesException;
import dgodek.company.exceptions.NoSuchHiredEmployeeException;
import dgodek.company.Task;
import dgodek.company.exceptions.TooManyEmployeesException;
import dgodek.company.report.ManagerReport;
import dgodek.company.report.Report;

import java.util.*;
import java.util.function.Predicate;
import java.util.stream.Collectors;

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
        return maxSize > employees.size() && predicate.test(employee);
    }

    @Override
    public void hire(Employee employee) {
        if (canHire(employee)) {
            employees.add(employee);
        } else {
            throw new TooManyEmployeesException();
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
        setAmountOfWork(getAmountOfWork() + task.getunitsOfWork());
    }


    public void setPredicate(Predicate<Employee> predicate) {
        this.predicate = predicate;
    }

    @Override
    public Report reportWork() {
        List<Report> subWorkersReports = employees.stream()
                .map((r)->reportWork())
                .collect(Collectors.toList());

        return new ManagerReport(this, subWorkersReports);
    }

    public static class Builder extends AbstractEmployee.Builder {
        private final int maxSize;
        private Predicate<Employee> predicate;

        public Builder(String name, String email, int maxSize, String nationality) {
            super(name, email, nationality);
            this.maxSize = maxSize;
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
