package dgodek.company.vaadin;

import com.vaadin.data.provider.TreeDataProvider;
import com.vaadin.server.VaadinRequest;
import com.vaadin.ui.*;
import dgodek.company.EmployeeFactory;
import dgodek.company.Task;
import dgodek.company.employee.Employee;
import dgodek.company.employee.Role;
import dgodek.company.employee.TeamManager;
import dgodek.company.report.*;

import java.util.List;
import java.util.Optional;

public class MainUI extends UI {

    private final Button newEmployeeButton = new Button("Start company!");
    private Button assignTaskButton = new Button("Assign task");
    private final Button hireButton = new Button("Hire");
    private final TreeGrid<Employee> employeeTreeGrid = new TreeGrid<>(Employee.class);
    private final TreeDataProvider<Employee> treeDataProvider = (TreeDataProvider<Employee>) employeeTreeGrid.getDataProvider();
    private final EmployeeFactory employeeFactory = new EmployeeFactory();

    private Employee actualEmployee;

    private final UserBoard userBoard = new UserBoard();
    private final ManagerBoard managerBoard = new ManagerBoard();
    private final Grid<Task> taskGrid = new Grid<>(Task.class);
    private final Button reportWorkButton = new Button("Report work!");

    @Override
    protected void init(VaadinRequest request){
        employeeTreeGrid.setColumns("name","surname","role");
        employeeButtonsVisible(false);
        initSizes();
        initView();
        initListeners();
    }

    private void employeeButtonsVisible(boolean b) {
        assignTaskButton.setVisible(b);
        reportWorkButton.setVisible(b);
    }

    private void initListeners() {
        newEmployeeButton.addClickListener(event -> popEmployeeWindow());
        assignTaskButton.addClickListener(event -> popTaskWindow(actualEmployee));
        employeeTreeGrid.addSelectionListener(event -> {
                    Optional<Employee> employee = event.getFirstSelectedItem();
                    if(employee.isPresent()) {
                        newEmployeeButton.setVisible(false);
                        actualEmployee = employee.get();
                        userBoard.setValues(actualEmployee);
                        userBoard.setVisible(true);
                        employeeButtonsVisible(true);
                        if(isManager(actualEmployee.getRole())) {
                            hireButton.setVisible(true);
                            managerBoard.setValues((TeamManager)actualEmployee);
                            managerBoard.setVisible(true);
                        } else {
                            hireButton.setVisible(false);
                            managerBoard.setVisible(false);
                        }
                    } else {
                        employeeButtonsVisible(false);
                    }

                });
        hireButton.addClickListener(event -> popEmployeeWindowHiring((TeamManager)actualEmployee));
        reportWorkButton.addClickListener(event -> popReportWindow(actualEmployee));
    }

    private void popReportWindow(Employee actualEmployee) {
        Report report = actualEmployee.reportWork();
        Window window;
        if(isManager(actualEmployee.getRole())) {
            window = new Window("Report work", new ReportOfWorkForm((ManagerReport)report));
        } else {
            window = new Window("Report work", new ReportOfWorkForm((WorkerReport)report));
        }
        window.setWidth("300px");
        window.setPositionX(150);
        window.setPositionY(150);
        addWindow(window);
    }

    private void initView() {
        HorizontalLayout buttonMenu = new HorizontalLayout();
        hireButton.setVisible(false);
        buttonMenu.addComponents(newEmployeeButton, assignTaskButton, reportWorkButton, hireButton);
        HorizontalLayout bottomLayout = new HorizontalLayout();
        managerBoard.setVisible(false);
        HorizontalLayout boardsLayout = new HorizontalLayout();
        boardsLayout.addComponents(userBoard,managerBoard);
        GridLayout rightLayout = new GridLayout();
        rightLayout.addComponents(boardsLayout,taskGrid);
        bottomLayout.addComponents(employeeTreeGrid, rightLayout);
        VerticalLayout verticalLayout = new VerticalLayout();
        verticalLayout.addComponents(buttonMenu, bottomLayout);
        setContent(verticalLayout);
    }

    private void initSizes() {
        userBoard.setWidth("300px");
        taskGrid.setCaption("Tasks");
        taskGrid.setHeight("190px");
        employeeTreeGrid.setHeight("500px");
    }

    private void popTaskWindow(Employee employee) {
        Window window = new Window("New task", new CreateTaskForm(this, employee));
        window.setWidth("300px");
        window.setPositionX(150);
        window.setPositionY(150);
        addWindow(window);
    }

    void addEmployee(Employee employee) {
        treeDataProvider.getTreeData().addItem(actualEmployee, employee);
        treeDataProvider.refreshAll();
    }

    private void popEmployeeWindow() {
        Window window = new Window("New employee", new NewEmployeeForm(this));
        window.setWidth("500px");
        window.setPositionX(100);
        window.setPositionY(100);
        addWindow(window);
    }

    private void popEmployeeWindowHiring(TeamManager manager) {
        Window window = new Window("New emplyoee", new HireEmployeeForm(this, manager));
        window.setWidth("500px");
        window.setPositionX(100);
        window.setPositionY(100);
        addWindow(window);
    }

    public void refreshTasks() {
        List<Task> tasks = actualEmployee.getTasks();
        taskGrid.setItems(tasks);
    }

    void setDefaultContent() {
        getWindows().forEach(Window::close);
    }

    private static boolean isManager(Role role) {
        return role == Role.CEO ||
                role == Role.MANAGER ||
                role == Role.DEVELOPMENT_MANAGER;
    }
}