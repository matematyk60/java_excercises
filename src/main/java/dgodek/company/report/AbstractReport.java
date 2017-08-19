package dgodek.company.report;

import dgodek.company.employee.Employee;
import dgodek.company.employee.Role;
import dgodek.company.employee.Sex;

import java.util.List;

public abstract class AbstractReport implements Report {
    final String name;
    final String surname;
    final String email;
    final Role role;
    final int amountOfWork;

    AbstractReport(Employee employee) {
        this.name = employee.getName();
        this.surname = employee.getSurname();
        this.email = employee.getEmail();
        this.role = employee.getRole();
        this.amountOfWork = employee.getAmountOfWork();
    }

    @Override
    public int getAmountOfWork() {
        return amountOfWork;
    }

    @Override
    public Role getRole() {
        return role;
    }

    @Override
    public String getNameOfWorker() {
        return name;
    }
}
