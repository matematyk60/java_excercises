package dgodek.company.GUI;

import dgodek.company.Task;
import dgodek.company.TaskFactory;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import jdk.nashorn.internal.runtime.NumberToString;

public class CreateTaskController {

    @FXML
    private TextField nameField;

    @FXML
    private Button randomButton;

    @FXML
    private Button submitButton;

    @FXML
    private TextField unitsField;

    private TaskFactory taskFactory;

    private Task task;

    private boolean taskCreatedCorrectly = false;

    @FXML
    void randomButtonClicked(ActionEvent event) {
        Task randomTask = taskFactory.getTask();
        this.nameField.setText(randomTask.getName());
        this.unitsField.setText(NumberToString.stringFor(randomTask.getUnitsOfWork()));
    }

    @FXML
    void submitButtonClicked(ActionEvent event) {
        this.task = new Task(nameField.getText(), Integer.parseInt(unitsField.getText()));
        taskCreatedCorrectly = true;
        ((Stage)submitButton.getScene().getWindow()).close();
    }

    @FXML
    void initialize() {
        this.taskFactory = new TaskFactory();
    }

    boolean isTaskCreatedCorrectly() {
        return taskCreatedCorrectly;
    }

    public Task getTask() {
        return task;
    }
}
