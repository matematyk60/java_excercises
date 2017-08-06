package dgodek.company.report;

import dgodek.company.employee.Employee;
import dgodek.company.employee.Role;

public class ManagerReport implements Report {
    private int amountOfWork;

    private Role role;

    private String nameOfWorker;

    private Report[] reports;

    public ManagerReport(Employee employee, Report[] reports) {
        nameOfWorker = employee.getName();
        amountOfWork = employee.getAmountOfWork();
        role = employee.getRole();
        this.reports = reports;
    }

    @Override
    public String toString() {
        return "Report{" +
                "nameOfWorker='" + nameOfWorker  +
                "', role='" + role +
                "', amountOfWork='" + amountOfWork + '\'' +
                '}';
    }

    public void print(){
        if(reports != null) {
            for(Report r : reports) {
                r.print();
            }
        }
        System.out.println(this.toString());
    }
}
