package dgodek.company.report;

import dgodek.company.employee.Employee;
import dgodek.company.employee.Role;

import java.util.List;

public class ManagerReport implements Report {
    private int amountOfWork;

    private Role role;

    private String nameOfWorker;

    private List<Report> reports;

    public ManagerReport(Employee employee, List<Report> reports) {
        nameOfWorker = employee.getName();
        amountOfWork = employee.getAmountOfWork();
        role = employee.getRole();
        this.reports = reports;
    }

    @Override
    public String toString() {
        return "Report{" +
                "nameOfWorker='" + nameOfWorker +
                "', role='" + role +
                "', amountOfWork='" + amountOfWork + '\'' +
                '}';
    }

    public List<Report> getReports() {
        return reports;
    }
}
