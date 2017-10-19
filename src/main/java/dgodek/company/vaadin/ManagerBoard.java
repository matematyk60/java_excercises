package dgodek.company.vaadin;

import com.vaadin.ui.GridLayout;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Label;
import com.vaadin.ui.VerticalLayout;
import dgodek.company.employee.TeamManager;

public class ManagerBoard extends GridLayout {
    private final Label hiringLabel = new Label();
    private final Label strategyLabel = new Label();

    public ManagerBoard() {
        initView();
    }

    public void setValues(TeamManager manager) {
        String hiring;
        if(manager.isNotFull()) {
            hiring = "YES";
        } else {
            hiring = "NO";
        }
        hiringLabel.setValue(hiring);
        strategyLabel.setValue(manager.getHiringStrategy().toString());
    }

    private void initView() {
        VerticalLayout mainLayout = new VerticalLayout();

        HorizontalLayout hiringLayout = new HorizontalLayout();
        hiringLayout.addComponents(new Label("Hiring:"), hiringLabel);

        HorizontalLayout strategyLayout = new HorizontalLayout();
        strategyLayout.addComponents(new Label("Strategy: "), strategyLabel);

        mainLayout.addComponents(hiringLayout,strategyLayout);

        addComponent(mainLayout);
    }
}
