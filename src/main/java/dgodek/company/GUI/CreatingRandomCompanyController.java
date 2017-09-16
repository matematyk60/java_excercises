package dgodek.company.GUI;

import dgodek.company.TaskFactory;
import dgodek.company.employee.*;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.control.TreeItem;
import javafx.stage.Stage;

import java.util.List;
import java.util.Random;
import java.util.regex.Pattern;

public class CreatingRandomCompanyController extends WindowThrowingWarnings {

    @FXML
    private TextField sizeField;

    @FXML
    private TextField depthField;

    @FXML
    private Button submitButton;

    private Integer size;

    private Integer depth;

    private TreeItem<Employee> root;

    private GUIService GUIService;

    private TaskFactory taskFactory;

    private boolean companyCreatedSuccessfully = false;

    private Random generator;

    @FXML
    void initialize() {
        taskFactory = new TaskFactory();
        GUIService = new GUIService();
        generator = new Random();
    }

    @FXML
    void submitButtonClicked(ActionEvent event) {
        if(isValid()) {
            size = Integer.parseInt(sizeField.getText());
            depth = Integer.parseInt(depthField.getText());
        } else {
            getWarningBox("Canonot create company", "You must type number values");
            return;
        }

        createCompany();
        ((Stage)submitButton.getScene().getWindow()).close();
    }

    private void createCompany() {
        List<TreeItem<Employee>> employees = GUIService.getWrappedDevs((int) Math.pow(size, depth));

        TreeItem<Employee> ceoItem = GUIService.wrapUpEmployee(new TeamManager.Builder("Łukasz","Łosoś",
                "pstrongu@hotmail.com",size,"Poland")
                .role(Role.CEO).academy("AGH").sex(Sex.MALE).build());

        if(depth == 1) {
            employees.forEach((i) -> {
                        ((Manager) ceoItem.getValue()).hire(i.getValue());
                        ceoItem.getChildren().add(i);
                    }
            );
        } else {
            List<TreeItem<Employee>> managers = GUIService.getWrappedManagers((int) Math.pow(size, depth - 1), size);

            GUIService.assignEmployeesToManagers(employees,managers);

            for (int i = depth - 1; i > 1; i--) {
                employees.clear();
                employees.addAll(managers);
                managers = GUIService.getWrappedManagers((int) Math.pow(size, i - 1), size);
                GUIService.assignEmployeesToManagers(employees, managers);
            }

            managers.forEach((i) -> GUIService.assignEmployeeToManager(i,ceoItem));
        }

        int amountOfTasks = generator.nextInt(80)+1;

        for (int i = 0; i < amountOfTasks; i++) {
            ceoItem.getValue().assign(taskFactory.getTask());
        }

        root = ceoItem;
        companyCreatedSuccessfully = true;
    }

    private boolean isValid() {
        Pattern pattern = Pattern.compile("[0-9]+");
        return pattern.matcher(sizeField.getText()).matches()
                && pattern.matcher(depthField.getText()).matches();
    }

    TreeItem<Employee> getRoot() {
        return root;
    }

    boolean isCompanyCreatedSuccessfully() {
        return companyCreatedSuccessfully;
    }
}
