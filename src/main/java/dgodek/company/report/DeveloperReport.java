package dgodek.company.report;

import dgodek.company.employee.Employee;
import dgodek.company.employee.Role;

import java.util.List;
import java.util.Optional;

public class DeveloperReport implements Report {
    private int amountOfWork;

    private Role role;

    private String nameOfWorker;

    public DeveloperReport(Employee employee) {
        nameOfWorker = employee.getName();
        amountOfWork = employee.getAmountOfWork();
        role = employee.getRole();
    }

    @Override
    public Optional<List<Report>> getReports() {
        return Optional.empty();
    }

    @Override
    public String toString() {
        return "Report{" +
                "nameOfWorker='" + nameOfWorker +
                "', role='" + role +
                "', amountOfWork='" + amountOfWork + '\'' +
                '}';
    }
}
