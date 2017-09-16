package dgodek.company.GUI;

import dgodek.company.EmployeeFactory;
import dgodek.company.employee.*;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.stage.Stage;

import java.util.Arrays;

public class CreateEmployeeController extends AbstractEmployeeWindow{

    private EmployeeFactory employeeFactory;

    private Employee employee;

    @FXML
    private TextField nameField;

    @FXML
    private TextField surnameField;

    @FXML
    private TextField emailField;

    @FXML
    private TextField nationalityField;

    @FXML
    private TextField academyField;

    @FXML
    private ComboBox<Role> roleBox;

    @FXML
    private Label strategyLabel;

    @FXML
    private ComboBox<TeamManager.HiringStrategy> strategyBox;

    @FXML
    private RadioButton maleRadioButton;

    @FXML
    private ToggleGroup sexToggleGroup;

    @FXML
    private RadioButton femaleRadioButton;

    private boolean employeeCreatedCorrectly = false;



    @FXML
    void initialize() {
        employeeFactory = new EmployeeFactory();
        initComboBoxes();
        strategyLabel.setVisible(false);
        strategyBox.setVisible(false);
    }

    private void initComboBoxes() {
        roleBox.getItems().addAll(Role.values());
        roleBox.getSelectionModel().select(Role.TESTER);
        roleBox.setOnAction((e) -> {
            if(isManager(roleBox.getValue())) {
                strategyLabel.setVisible(true);
                strategyBox.setVisible(true);
            } else {
                strategyLabel.setVisible(false);
                strategyBox.setVisible(false);
            }
        });
        strategyBox.getItems().addAll(Arrays.asList(TeamManager.HiringStrategy.NONE,
                TeamManager.HiringStrategy.HIRING_ONLY_FROM_AGH,
                TeamManager.HiringStrategy.HIRING_ONLY_FROM_POLAND,
                TeamManager.HiringStrategy.HIRING_ONLY_MAN,
                TeamManager.HiringStrategy.HIRING_ONLY_WITH_GMAIL_MAIL));
        strategyBox.getSelectionModel().select(TeamManager.HiringStrategy.NONE);
    }

    @FXML
    void allRandomButtonClicked(ActionEvent event) {
        nameField.setText(employeeFactory.getName());

        surnameField.setText(employeeFactory.getSurname());

        emailField.setText(nameField.getText()+surnameField.getText()+"@"
                +employeeFactory.getDomain());

        maleRadioButton.setSelected(true);

        nationalityField.setText(employeeFactory.getNationality());

        academyField.setText(employeeFactory.getAcademy());
    }

    @FXML
    void randomAcademyButtonClicked(ActionEvent event) {
        academyField.setText(employeeFactory.getAcademy());
    }

    @FXML
    void randomEmailButtonClicked(ActionEvent event) {
        emailField.setText(nameField.getText()+surnameField.getText()+"@"
                +employeeFactory.getDomain());
    }

    @FXML
    void randomNameButtonClicked(ActionEvent event) {
        nameField.setText(employeeFactory.getName());
    }

    @FXML
    void randomNationalityButtonClicked(ActionEvent event) {
        nationalityField.setText(employeeFactory.getNationality());
    }

    @FXML
    void randomSurnameButtonClicked(ActionEvent event) {
        surnameField.setText(employeeFactory.getSurname());
    }

    @FXML
    void submitButtonClicked(ActionEvent event) {
        if(!isValid()) {
            return;
        }

        Sex sex = ((RadioButton)sexToggleGroup.getSelectedToggle()).getText().equals("Male")
                ? Sex.MALE
                : Sex.FEMALE;

        if(isManager(roleBox.getValue())){
            employee = new TeamManager.Builder(nameField.getText(),
                    surnameField.getText(), emailField.getText(),
                    4, nationalityField.getText())
                    .academy(academyField.getText())
                    .role(roleBox.getValue())
                    .hiringStategy(strategyBox.getValue())
                    .sex(sex)
                    .build();
        } else {
            employee = new Developer.Builder(nameField.getText(),
                    surnameField.getText(), emailField.getText(),
                    nationalityField.getText())
                    .academy(academyField.getText())
                    .role(roleBox.getValue())
                    .sex(sex)
                    .build();
        }

        employeeCreatedCorrectly = true;

        ((Stage)nameField.getScene().getWindow()).close();
    }

    private boolean isValid() {
        String title = "Can't create employee";
        if (nameField.getText() == null || nameField.getText().trim().isEmpty()) {
            getWarningBox(title, "You must provide a name");
            return false;
        } else if (surnameField.getText() == null || surnameField.getText().trim().isEmpty()){
            getWarningBox(title, "You must provide a surname");
            return false;
        } else if (emailField.getText() == null || emailField.getText().trim().isEmpty()){
            getWarningBox(title, "You must provide an email");
            return false;
        } else if (academyField.getText() == null || academyField.getText().trim().isEmpty()){
            getWarningBox(title, "You must provide an academy ");
            return false;
        } else if (nationalityField.getText() == null || nationalityField.getText().trim().isEmpty()){
            getWarningBox(title, "You must provide a nationality");
            return false;
        } else if (sexToggleGroup.getSelectedToggle() == null) {
            getWarningBox(title, "You mast provide gender");
            return false;
        } else {
            return true;
        }
    }

    public Employee getEmployee() {
        return employee;
    }

    boolean employeeCreatedCorrectly() {
        return employeeCreatedCorrectly;
    }

    void initCreatingCEO() {
        roleBox.getSelectionModel().select(Role.CEO);
        roleBox.setDisable(true);
        strategyLabel.setVisible(true);
        strategyBox.setVisible(true);
    }
}
