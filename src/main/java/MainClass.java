/**
 * Created by matematyk60 on 23.07.17.
 */

import dgodek.company.*;
import dgodek.company.employee.*;
import dgodek.company.report.Report;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.StackPane;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class MainClass extends Application {

    public static void maidn(String[] args) {
        if (args.length == 0) {
            throw (new RuntimeException("Size argument not given"));
        }

        Random generator = new Random();
        int depth = generator.nextInt(5) + 1;
        int size = Integer.parseInt(args[0]);
        ReportService reportService = new ReportService();
        EmployeeFactory employeeFactory = new EmployeeFactory();
        TaskFactory taskFactory = new TaskFactory();

        List<Employee> employees = new ArrayList<>();

        employees.addAll(
                employeeFactory.getDevs((int) Math.pow(size, depth)
                ));

        TeamManager ceo = new TeamManager.Builder("Łukasz","Łosoś",
                "pstrongu@hotmail.com",size,"Poland")
                .role(Role.CEO).academy("AGH").sex(Sex.MALE).build();

        if (depth == 1) {
            for (Employee dev : employees) {
                ceo.hire(dev);
            }
        } else {

            List<Manager> managers = employeeFactory.getManagers((int) Math.pow(size, depth - 1), size);

            EmployeeFactory.assignEmployeesToManagers(managers, employees, size);

            for (int i = depth - 1; i > 1; i--) {
                employees.clear();
                employees.addAll(managers);
                managers = employeeFactory.getManagers((int) Math.pow(size, i - 1), size);
                EmployeeFactory.assignEmployeesToManagers(managers, employees, size);
            }

            for (Employee e : managers) {
                ceo.hire(e);
            }
        }

        int tasks = generator.nextInt(80) + 1;

        for (int i = 0; i < tasks; i++) {
            ceo.assign(taskFactory.getTask());
        }

        Report report = ceo.reportWork();

        reportService.printReport(report);

        System.out.println("Just created Company of depth " + depth + " and size " + size + ", recieved " + tasks + " tasks");
    }

    @Override
    public void start(Stage primaryStage) throws Exception{
        Parent root = FXMLLoader.load(getClass().getResource("MainView.fxml"));
        Stage stage = new Stage();
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.setTitle("Company");
        stage.setScene(new Scene(root));
        stage.show();
    }


    public static void main(String[] args) {
        launch(args);
    }
}
