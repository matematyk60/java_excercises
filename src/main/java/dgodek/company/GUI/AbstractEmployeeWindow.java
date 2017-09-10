package dgodek.company.GUI;

import dgodek.company.employee.Role;
import javafx.scene.control.Alert;

abstract class AbstractEmployeeWindow extends WindowThrowingWarnings {
    boolean isManager(Role role) {
        return (role.equals(Role.CEO)
                || role.equals(Role.DEVELOPMENT_MANAGER)
                || role.equals(Role.MANAGER));
    }
}
