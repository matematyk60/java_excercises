package dgodek.company.GUI;

import dgodek.company.Task;
import dgodek.company.report.ManagerReport;
import dgodek.company.report.Report;
import dgodek.company.report.WorkerReport;
import javafx.beans.property.ReadOnlyStringWrapper;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;

public class ReportWorkController extends AbstractEmployeeWindow {

    @FXML
    private TableColumn<Task, String> taskNameColumn;

    @FXML
    private TableColumn<Task, String> taskUnitsColumn;

    @FXML
    private TableView<Task> tasksTableView;

    @FXML
    private Label nameLabel;

    @FXML
    private Label surnameLabel;

    @FXML
    private Label unitsOfWorkLabel;

    @FXML
    private TableView<WorkerReport> reportsTableView;

    @FXML
    private TableColumn<WorkerReport, String> nameColumn;

    @FXML
    private TableColumn<WorkerReport, String> surnameColumn;

    @FXML
    private TableColumn<WorkerReport, String> roleColumn;

    @FXML
    private TableColumn<WorkerReport, String> unitsColumn;

    private Boolean initializedCorrectly = false;

    private Report report;

    private WorkerReport actualReport;

    @FXML
    void initialize() {
        initColumns();
        initListeners();
    }

    private void initColumns() {
        nameColumn.setCellValueFactory(param ->
                new ReadOnlyStringWrapper(param.getValue().getNameOfWorker()));
        surnameColumn.setCellValueFactory(param ->
                new ReadOnlyStringWrapper(param.getValue().getSurnameOfWorker()));
        roleColumn.setCellValueFactory(param ->
                new ReadOnlyStringWrapper(param.getValue().getRole().toString()));
        unitsColumn.setCellValueFactory(param ->
                new ReadOnlyStringWrapper(param.getValue().getAmountOfWork().toString()));

        taskNameColumn.setCellValueFactory(param ->
                new ReadOnlyStringWrapper(param.getValue().getName()));
        taskUnitsColumn.setCellValueFactory(param ->
                new ReadOnlyStringWrapper(Integer.valueOf(param.getValue().getUnitsOfWork()).toString()));
    }

    public void setReport(Report report) {
        this.report = report;
        this.nameLabel.setText(report.getNameOfWorker());
        this.surnameLabel.setText(report.getSurnameOfWorker());
        this.unitsOfWorkLabel.setText(report.getAmountOfWork().toString());
        if(isManager(report.getRole())) {
            ((ManagerReport)report).getReports().forEach(r -> reportsTableView.getItems().add((WorkerReport)r));
        } else {
            ((WorkerReport)report).getTasks().forEach(t -> tasksTableView.getItems().add(t));
        }
    }



    private void initListeners() {
        reportsTableView.getSelectionModel().selectedItemProperty().addListener(((observable, oldValue, newValue) -> {
            if (newValue == null) {
                return;
            }
            tasksTableView.getItems().clear();
            actualReport = reportsTableView.getSelectionModel()
                    .getSelectedItem();
            actualReport.getTasks()
                    .forEach(t -> tasksTableView.getItems().add(t));
        }));

    }

    public Boolean isInitializedCorrectly() {
        return initializedCorrectly;
    }
}
