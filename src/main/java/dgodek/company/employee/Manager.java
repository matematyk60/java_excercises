package dgodek.company.employee;

/**
 * Created by matematyk60 on 23.07.17.
 */
public interface Manager extends Employee {
    void hire(Employee employee);

    void fire(Employee employee);

    boolean canHire(Employee employee);

    boolean isNotFull();

    Employee getWorkerWithLowestAmountOfWork();

}
