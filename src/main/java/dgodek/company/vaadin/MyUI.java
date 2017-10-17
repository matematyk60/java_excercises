package dgodek.company.vaadin;

import com.vaadin.server.VaadinRequest;
import com.vaadin.ui.*;
import dgodek.company.EmployeeFactory;
import dgodek.company.employee.Employee;

import java.util.ArrayList;
import java.util.List;

public class MyUI extends UI {

    private Button newEmployeeButton = new Button("New employee!");
    private Grid<Employee> employeeGrid = new Grid<>(Employee.class);
    private EmployeeFactory employeeFactory = new EmployeeFactory();

    private List<Employee> employeeList = new ArrayList<>();


    @Override
    protected void init(VaadinRequest request){
        employeeGrid.setColumns("name","surname","sex","email","nationality","academy","role");
        employeeGrid.setSizeFull();
        setDefaultContent();
        newEmployeeButton.addClickListener(event -> popEmployeeWindow());
        employeeList.add(employeeFactory.getDeveloper());
        refresh();
    }

    private void refresh() {
        employeeGrid.setItems(employeeList);
    }

    void addEmployee(Employee employee) {
        employeeList.add(employee);
        refresh();
    }

    private void popEmployeeWindow() {
        Window window = new Window("New employee", new NewEmployeeForm(this));
        window.setWidth("500px");
        window.setPositionX(100);
        window.setPositionY(100);
        addWindow(window);
    }

    void setDefaultContent() {
        VerticalLayout layout = new VerticalLayout();
        layout.addComponentsAndExpand(new CssLayout(newEmployeeButton),employeeGrid);
        setContent(layout);
        getWindows().forEach(Window::close);
    }
}