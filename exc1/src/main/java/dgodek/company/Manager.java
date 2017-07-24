package dgodek.company;

/**
 * Created by matematyk60 on 23.07.17.
 */
public interface Manager extends Employee{
    public void Hire(Employee employee);

    public void Fire(Employee employee);

    public boolean canHire();

}
