package dgodek.company.report;

import dgodek.company.employee.Role;

import java.util.List;
import java.util.Optional;

public interface Report {
    int getAmountOfWork();

    Role getRole();

    String getNameOfWorker();
}
