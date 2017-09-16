package dgodek.company.GUI;

import dgodek.company.EmployeeFactory;
import dgodek.company.employee.Developer;
import dgodek.company.employee.Employee;
import dgodek.company.employee.Manager;
import dgodek.company.employee.TeamManager;
import javafx.scene.control.TreeItem;

import java.util.ArrayList;
import java.util.List;

public class CompanyService {
    private EmployeeFactory employeeFactory;

    CompanyService() {
        this.employeeFactory = new EmployeeFactory();
    }

    List<TreeItem<Employee>> wrapUpDevs(List<Developer> employees) {
        List<TreeItem<Employee>> itemsList = new ArrayList<>();
        employees.forEach((e) -> itemsList.add(new TreeItem<>(e)));

        return itemsList;
    }

    List<TreeItem<Employee>> wrapUpManagers(List<Manager> managers) {
        List<TreeItem<Employee>> itemsList = new ArrayList<>();
        managers.forEach((e) -> itemsList.add(new TreeItem<>(e)));

        return itemsList;
    }


    List<TreeItem<Employee>> getWrappedDevs(int amount) {
        return wrapUpDevs(employeeFactory.getDevs(amount));
    }

    List<TreeItem<Employee>> getWrappedManagers(int amount, int size) {
        return wrapUpManagers(employeeFactory.getManagers(amount,size));
    }

    TreeItem<Employee> wrapUpEmployee(Employee employee) {
        return new TreeItem<>(employee);
    }

    public Employee unwrapEmployee(TreeItem<Employee> employeeTreeItem) {
        return employeeTreeItem.getValue();
    }

    public Manager unwrapManager(TreeItem<Employee> managerTreeItem) {
        return (Manager)managerTreeItem.getValue();
    }

    void assignEmployeesToManagers(List<TreeItem<Employee>> employees, List<TreeItem<Employee>> managers) {
        int actualEmployee = 0;

        for (TreeItem<Employee> manager : managers) {
            while (((Manager) manager.getValue()).isNotFull()) {
                ((Manager) manager.getValue()).hire(employees.get(actualEmployee).getValue());
                manager.getChildren().add(employees.get(actualEmployee));
                actualEmployee++;
            }
        }
    }

    void assignEmployeeToManager(TreeItem<Employee> employee, TreeItem<Employee> manager) {
        ((Manager) manager.getValue()).hire(employee.getValue());
        manager.getChildren().add(employee);
    }
}
