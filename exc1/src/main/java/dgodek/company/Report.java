package dgodek.company;

/**
 * Created by matematyk60 on 23.07.17.
 */
public class Report {
    private int amountOfWork;

    private Role role;

    private String nameOfWorker;

    public Report(Employee employee) {
        nameOfWorker = employee.getName();
        amountOfWork = employee.getAmountOfWork();
        role = employee.getRole();
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
