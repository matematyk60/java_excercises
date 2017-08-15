package dgodek.company.employee;

/**
 * Created by matematyk60 on 23.07.17.
 */
public interface Manager extends Employee {
    public void hire(Employee employee);

    public void fire(Employee employee);

    public boolean canHire(Employee employee);

    public Employee getWorkerWithLowestAmountOfWork();

}
