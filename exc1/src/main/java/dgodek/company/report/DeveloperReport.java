package dgodek.company.report;

import dgodek.company.employee.Employee;
import dgodek.company.employee.Role;

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
    public void print() {
        System.out.println(this.toString());

    }

    @Override
    public String toString() {
        return "Report{" +
                "nameOfWorker='" + nameOfWorker  +
                "', role='" + role +
                "', amountOfWork='" + amountOfWork + '\'' +
                '}';
    }
}
