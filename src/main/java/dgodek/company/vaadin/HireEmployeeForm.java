package dgodek.company.vaadin;

import com.vaadin.ui.Notification;
import dgodek.company.employee.Employee;
import dgodek.company.employee.TeamManager;

public class HireEmployeeForm extends NewEmployeeForm {
    private final TeamManager teamManager;

    public HireEmployeeForm(MainUI ui, TeamManager teamManager) {
        super(ui);
        this.teamManager = teamManager;
    }

    @Override
    protected boolean isValid(Employee employee) {
        return super.isValid(employee) && teamManager.canHire(employee);
    }

    @Override
    protected void validateAndExport(Employee employee) {
        if(isValid(employee)) {
            exportEmployeeAndClose(employee);
        } else {
            Notification.show("Cannot hire this employee by this manager");
        }
    }

    @Override
    protected void exportEmployeeAndClose(Employee employee) {
        teamManager.hire(employee);
        super.exportEmployeeAndClose(employee);
    }
}
