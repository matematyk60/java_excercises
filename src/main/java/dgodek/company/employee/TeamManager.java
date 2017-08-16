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


    public TeamManager(String name, String email, Sex sex, String academy, String nationality, Role role, int maxSize) {
        super(name, email, sex, academy, nationality, role);
        this.employees = new ArrayList<>();
        this.maxSize = maxSize;
        this.predicate = (e) -> true;
    }

    public TeamManager(Builder builder, int maxSize) {
        super(builder);
        this.employees = new ArrayList<>();
    }

    @Override
    public boolean canHire(Employee employee) {
        if(maxSize <= employees.size()) {
            return false;
        }
        return predicate.test(employee);
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
        Optional<Employee> employeeWithLowerstAmountOfWork = employees
                .stream()
                .min(Comparator.comparing(Employee::getAmountOfWork));

        return employeeWithLowerstAmountOfWork
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


    @Override
    public String toString() {
        return super.toString();
    }

}
