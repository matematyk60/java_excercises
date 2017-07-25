package dgodek.company;

import java.util.List;

/**
 * Created by matematyk60 on 23.07.17.
 */
public class Report {
    private int amountOfWork;

    private Role role;

    private String nameOfWorker;

    private Report[] reports;

    public Report(Employee employee) {
        nameOfWorker = employee.getName();
        amountOfWork = employee.getAmountOfWork();
        role = employee.getRole();
    }

    public Report(Employee employee, Report[] reports) {
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
