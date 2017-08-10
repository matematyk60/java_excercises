package dgodek.company.employee;

import dgodek.company.exceptions.NoSuchHiredEmployeeException;
import dgodek.company.Task;
import dgodek.company.exceptions.TooManyEmployeesException;
import dgodek.company.report.ManagerReport;
import dgodek.company.report.Report;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by matematyk60 on 23.07.17.
 */
public class TeamManager extends AbstactEmployee implements Manager {
    private List<Employee> employees;

    private int numberOfEmployees;

    private final int maxSize;

    private int nextWorkerIdx;


    public TeamManager(String name, Role role, int maxSize) {
        super(name, role);
        this.maxSize = maxSize;
        this.nextWorkerIdx = 0;
        this.numberOfEmployees = 0;
        this.employees = new ArrayList<>();
    }

    @Override
    public boolean canHire() {
        return (maxSize > employees.size());
    }

    @Override
    public void hire(Employee employee) {
        if (canHire()) {
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
    public void assign(Task task) {
        if (nextWorkerIdx == employees.size()) {
            nextWorkerIdx = 0;
        }
        System.out.println(this.toString() + " | Assigning " + task.toString() + "to employee " + employees.get(nextWorkerIdx).toString());
        employees.get(nextWorkerIdx).assign(task);
        nextWorkerIdx++;
        setAmountOfWork(getAmountOfWork() + task.getunitsOfWork());
    }


    @Override
    public Report reportWork() {
        List<Report> subWorkersReports = new ArrayList<>();
        for (Employee employee : employees) {
            subWorkersReports.add(employee.reportWork());
        }
        return new ManagerReport(this, subWorkersReports);
    }


    @Override
    public String toString() {
        return super.toString();
    }

}
