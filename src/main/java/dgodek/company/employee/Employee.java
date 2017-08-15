package dgodek.company.employee;

import dgodek.company.Task;
import dgodek.company.report.Report;

/**
 * Created by matematyk60 on 23.07.17.
 */

public interface Employee {
    String getName();

    Role getRole();

    String getAcademy();

    String getNationality();

    Sex getSex();

    String getEmail();

    void assign(Task task);

    Report reportWork();

    int getAmountOfWork();
}
