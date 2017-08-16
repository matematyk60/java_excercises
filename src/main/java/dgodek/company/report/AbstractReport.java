package dgodek.company.report;

import dgodek.company.employee.Employee;
import dgodek.company.employee.Role;
import dgodek.company.employee.Sex;

import java.util.List;

public abstract class AbstractReport implements Report {
    protected final String name;
    protected final String surname;
    protected final String email;
    protected final String nationality;
    protected final Sex sex;
    protected final String academy;
    protected final Role role;
    protected final int amountOfWork;

    public AbstractReport(Employee employee) {
        this.name = employee.getName();
        this.surname = employee.getSurname();
        this.email = employee.getEmail();
        this.nationality = employee.getNationality();
        this.sex = employee.getSex();
        this.academy = employee.getAcademy();
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
