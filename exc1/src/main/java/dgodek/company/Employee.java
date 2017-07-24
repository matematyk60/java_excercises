package dgodek.company;

/**
 * Created by matematyk60 on 23.07.17.
 */

public interface Employee {
    public String getName();

    public Role getRole();

    public void assign(Task task);

    public Report reportWork();

    public int getAmountOfWork();






}
