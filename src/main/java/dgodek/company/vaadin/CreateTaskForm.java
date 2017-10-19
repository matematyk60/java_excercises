package dgodek.company.vaadin;

import com.vaadin.data.Binder;
import com.vaadin.data.converter.StringToIntegerConverter;
import com.vaadin.ui.*;
import dgodek.company.Task;
import dgodek.company.TaskFactory;
import dgodek.company.employee.Employee;

import java.util.stream.IntStream;

public class CreateTaskForm extends FormLayout {

    private final MainUI ui;

    private final Button randomButton = new Button("Get random!");
    private final TextField nameField = new TextField("Name of task");
    private final TextField unitsField = new TextField("Units of Work");
    private final Button submitButton= new Button("Submit");

    private final TaskFactory taskFactory = new TaskFactory();

    private final Binder<TaskBuilder> binder = new Binder<>();

    private final Employee employee;

    CreateTaskForm(MainUI ui, Employee employee) {
        this.ui = ui;
        this.employee = employee;
        initListeners();
        initBinder();
        VerticalLayout layout = new VerticalLayout();
        layout.addComponents(randomButton,nameField,unitsField,submitButton);
        addComponents(layout);
        setSpacing(true);
        setSizeUndefined();
    }

    private void initBinder() {
        binder.forField(unitsField)
                .withConverter(new StringToIntegerConverter("Must enter the number"))
                .bind(TaskBuilder::getUnitsOfWork,TaskBuilder::setUnitsOfWork);
        binder.forField(nameField)
                .bind(TaskBuilder::getName,TaskBuilder::setName);
    }

    private void initListeners() {
        randomButton.addClickListener(event -> {
            Task newTask = taskFactory.getTask();
            nameField.setValue(newTask.getName());
            unitsField.setValue(Integer.valueOf(newTask.getUnitsOfWork()).toString());
        });
        submitButton.addClickListener(event -> {
            if(binder.isValid()) {
                exportTaskAndClose();
            } else {
                Notification.show("Provide correct task values");
            }
        });
    }

    private void exportTaskAndClose() {
        TaskBuilder builder = new TaskBuilder();
        binder.writeBeanIfValid(builder);
        employee.assign(builder.build());
        ui.refreshTasks();
        ui.setDefaultContent();
    }


    private static class TaskBuilder {
        private String name;
        private int unitsOfWork;

        public String getName() {
            return name;
        }

        public int getUnitsOfWork() {
            return unitsOfWork;
        }

        public void setName(String name) {
            this.name = name;
        }

        public void setUnitsOfWork(int unitsOfWork) {
            this.unitsOfWork = unitsOfWork;
        }

        public Task build() {
            return new Task(name,unitsOfWork);
        }
    }
}
