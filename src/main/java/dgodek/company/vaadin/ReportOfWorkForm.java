package dgodek.company.vaadin;

import com.vaadin.ui.*;
import dgodek.company.Task;
import dgodek.company.report.ManagerReport;
import dgodek.company.report.Report;
import dgodek.company.report.WorkerReport;

public class ReportOfWorkForm extends FormLayout {
    private final Label nameLabel = new Label();
    private final Label surnameLabel = new Label();
    private final Label unitsLabel = new Label();

    private final Grid<Report> reportGrid = new Grid<>(Report.class);

    private final Grid<Task> taskGrid = new Grid<>(Task.class);

    public ReportOfWorkForm(ManagerReport report) {
        initView();
        initFields(report);
        reportGrid.setItems(report.getReports());
        taskGrid.setVisible(false);
    }

    public ReportOfWorkForm(WorkerReport report) {
        initView();
        initFields(report);
        taskGrid.setItems(report.getTasks());
        reportGrid.setVisible(false);
    }

    private void initFields(Report report) {
        nameLabel.setValue(report.getNameOfWorker());
        surnameLabel.setValue(report.getSurnameOfWorker());
        unitsLabel.setValue(report.getAmountOfWork().toString());
    }

    private  void initView() {
        VerticalLayout mainLayout = new VerticalLayout();
        HorizontalLayout detailsLayout = new HorizontalLayout();
        detailsLayout.addComponents(new Label("Name"),nameLabel,new Label("Surname"),
                surnameLabel,new Label("Units of work:"), unitsLabel);
        reportGrid.setCaption("Reports");
        taskGrid.setCaption("Tasks");
        nameLabel.setValue("Chuj");
        HorizontalLayout gridsLayout = new HorizontalLayout();
        taskGrid.setWidth("300px");
        gridsLayout.addComponents(reportGrid,taskGrid);
        mainLayout.addComponents(detailsLayout, gridsLayout);
        addComponent(mainLayout);
    }
}

