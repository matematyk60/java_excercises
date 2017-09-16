package dgodek.company.employee;

import dgodek.company.Task;
import dgodek.company.report.Report;

import java.util.List;

/**
 * Created by matematyk60 on 23.07.17.
 */

public interface Employee {
    String getName();

    String getSurname();

    Role getRole();

    String getAcademy();

    String getNationality();

    Sex getSex();

    String getEmail();

    void assign(Task task);

    Report reportWork();

    List<Task> getTasks();

    List<Report> reportWork(List<Report> reports);

    Boolean isAssignable();

    Integer getAmountOfWork();
}
