package dgodek.company.GUI;

import dgodek.company.Task;
import dgodek.company.employee.Employee;
import dgodek.company.employee.Manager;
import dgodek.company.employee.TeamManager;
import dgodek.company.exceptions.NoHiredEmployeesException;
import javafx.beans.property.ReadOnlyStringWrapper;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.IOException;

public class MainViewController extends AbstractEmployeeWindow{

    @FXML
    private Button createCEO;

    @FXML
    private TitledPane roleTitledPane;

    @FXML
    private Label nameLabel;

    @FXML
    private Label surnameLabel;

    @FXML
    private Label academyLabel;

    @FXML
    private Label nationalityLabel;

    @FXML
    private Label sexLabel;

    @FXML
    private Label emailLabel;

    @FXML
    private Circle hiringCircle;

    @FXML
    private Label hiringStrategyLabel;

    @FXML
    private Button reportWorkButton;

    @FXML
    private Button assignTaskButton;


    @FXML
    private Button hireButton;

    @FXML
    private TreeTableView<Employee> employeeTreeTableView;

    @FXML
    private TreeTableColumn<Employee, String> surnameColumn;

    @FXML
    private TreeTableColumn<Employee, String> nameColumn;

    @FXML
    private TreeTableColumn<Employee, String> roleColumn;

    @FXML
    private TableView<Task> taskTableView;

    @FXML
    private TableColumn<Task, String> taskNameTableColumn;

    @FXML
    private TableColumn<Task, String> taskValTableColumn;

    private Employee actualEmployee;

    private boolean actualEmployeeIsManager;

    private TreeItem<Employee> actualTreeItem;

    private boolean CEOCreated = false;


    @FXML
    void initialize() {
        initColumns();
        initListeners();
        setDefaultView();
    }

    @FXML
    void assignTaskButtonClicked(ActionEvent event) throws IOException{
        FXMLLoader loader = new FXMLLoader(getClass().getClassLoader().getResource("CreateTaskBox.fxml"));
        Parent loaded = loader.load();
        CreateTaskController controller = loader.getController();

        Stage stage = new Stage();
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.setTitle("Creating task");
        stage.setScene(new Scene(loaded));
        stage.showAndWait();


        if(controller.isTaskCreatedCorrectly()){
            Task task = controller.getTask();
            try {
                actualEmployee.assign(task);
            } catch( NoHiredEmployeesException e) {
                getWarningBox("No hired workers", "You have to assign workers to manager");

                return;
            }

            taskTableView.getItems().add(task);
        }
    }

    @FXML
    void createCEOClicked(ActionEvent event) throws IOException {
        Employee newEmployee = getNewEmployee();
        if(newEmployee == null) {
            if(actualEmployee == null){
                setDefaultView();
            }
            return;
        }
        employeeTreeTableView.setRoot(new TreeItem<>(newEmployee));
        employeeTreeTableView.getRoot().setExpanded(true);
        CEOCreated = true;
    }

    @FXML
    void hireButtonClicked(ActionEvent event) throws IOException {
        Employee newEmployee = getNewEmployee();
        if(newEmployee == null) { return; }
        Manager manager = (Manager)actualEmployee;
        if(manager.canHire(newEmployee)) {
            manager.hire(newEmployee);
            TreeItem<Employee> treeItem = new TreeItem<>(newEmployee);
            actualTreeItem.getChildren().add(treeItem);
            treeItem.setExpanded(true);
            employeeTreeTableView.getSelectionModel().select(treeItem);
        } else {
            getWarningBox("Can't hire","Cannot hire this employee by this manager");
        }

    }

    @FXML
    void reportWorkButtonClicked(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getClassLoader().getResource("ReportWorkView.fxml"));
        Parent loaded = loader.load();
        ReportWorkController controller = loader.getController();
        controller.setReport(actualEmployee.reportWork());


        Stage stage = new Stage();
        stage.initModality(Modality.NONE);
        stage.setTitle("Report");
        stage.setScene(new Scene(loaded));
        stage.showAndWait();


    }

    @FXML
    void randomCompanyClicked(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getClassLoader().getResource("CreatingRandomCompany.fxml"));
        Parent loaded = loader.load();
        CreatingRandomCompanyController controller = loader.getController();

        Stage stage = new Stage();
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.setTitle("Creating company");
        stage.setScene(new Scene(loaded));
        stage.showAndWait();


        if(controller.isCompanyCreatedSuccessfully()){
            employeeTreeTableView.setRoot(controller.getRoot());
        }
    }

    private void setDeveloperView() {
        hiringCircle.setFill(Color.GRAY);
        hiringStrategyLabel.setText("--");
        hireButton.setDisable(true);
        setEmployeeFields();
    }


    private void setManagerView(TeamManager teamManager) {
        setEmployeeFields();
        boolean canHire = teamManager.isNotFull();
        hiringCircle.setFill(canHire ? Color.LIGHTGREEN : Color.ORANGERED);
        hireButton.setDisable(!canHire);
        hiringStrategyLabel.setText(teamManager.getHiringStrategy().getStringRepresentation());
    }

    private void setDefaultView() {
        roleTitledPane.setText("--");
        surnameLabel.setText("--");
        nameLabel.setText("--");
        academyLabel.setText("--");
        nationalityLabel.setText("--");
        sexLabel.setText("--");
        emailLabel.setText("--");
        hiringCircle.setFill(Color.GRAY);
        hiringStrategyLabel.setText("--");
        hireButton.setDisable(true);
        assignTaskButton.setDisable(true);
        reportWorkButton.setDisable(true);

    }

    private Employee getNewEmployee() throws IOException{
        FXMLLoader loader = new FXMLLoader(getClass().getClassLoader().getResource("CreateEmployeeBox.fxml"));
        Parent loaded = loader.load();
        CreateEmployeeController controller = loader.getController();

        if(!CEOCreated) {
            controller.initCreatingCEO();
        }

        Stage stage = new Stage();
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.setTitle("Creating employee");
        stage.setScene(new Scene(loaded));
        stage.showAndWait();


        if(controller.employeeCreatedCorrectly()) {
            return (controller.getEmployee());
        }
        return null;
    }

    private void setEmployeeFields() {
        roleTitledPane.setText(actualEmployee.getRole().toString().toUpperCase());
        surnameLabel.setText(actualEmployee.getSurname());
        nameLabel.setText(actualEmployee.getName());
        academyLabel.setText(actualEmployee.getAcademy());
        nationalityLabel.setText(actualEmployee.getNationality());
        sexLabel.setText(actualEmployee.getSex().toString());
        emailLabel.setText(actualEmployee.getEmail());
        taskTableView.getItems().clear();
        actualEmployee.getTasks().forEach((e) -> taskTableView.getItems().add(e));
        assignTaskButton.setDisable(false);
        reportWorkButton.setDisable(false);
    }

    private void initColumns() {
        surnameColumn.setCellValueFactory((param ->
                new ReadOnlyStringWrapper(param.getValue().getValue().getSurname())));
        nameColumn.setCellValueFactory((param ->
                new ReadOnlyStringWrapper(param.getValue().getValue().getName())));
        roleColumn.setCellValueFactory((param ->
                new ReadOnlyStringWrapper(param.getValue().getValue().getRole().toString())));
        taskNameTableColumn.setCellValueFactory(param -> new ReadOnlyStringWrapper(param.getValue().getName()));
        taskValTableColumn.setCellValueFactory(param ->  new ReadOnlyStringWrapper(Integer.valueOf(param.getValue().getUnitsOfWork()).toString()));
    }

    private void initListeners() {
        employeeTreeTableView.getSelectionModel().selectedItemProperty().addListener(((observable, oldValue, newValue) -> {
            if(newValue == null) {
                return;
            }
            actualTreeItem = employeeTreeTableView.getSelectionModel().getSelectedItem();
            actualEmployee = actualTreeItem.getValue();
            actualEmployeeIsManager = isManager(actualEmployee.getRole());
            if(actualEmployeeIsManager) {
                setManagerView((TeamManager)actualEmployee);
            } else {
                setDeveloperView();
            }
        }));
    }

}
