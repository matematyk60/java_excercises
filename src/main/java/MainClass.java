/**
 * Created by matematyk60 on 23.07.17.
 */

import dgodek.company.*;
import dgodek.company.employee.*;
import dgodek.company.report.Report;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.stream.IntStream;


public class MainClass {
    private static void assignEmployeesToManagers(List<TeamManager> managers, List<Employee> employees, int size) {
        int actualEmployee = 0;

        for (TeamManager manager : managers) {
            for (int i = 0; i < size; i++) {
                manager.hire(employees.get(actualEmployee));
                actualEmployee++;
            }
        }
    }

    public static void main(String[] args) {
        if (args.length == 0) {
            throw (new RuntimeException("Size argument not given"));
        }

        Random generator = new Random();
        int depth = generator.nextInt(5) + 1;
        int size = Integer.parseInt(args[0]);
        ReportService reportService = new ReportService();
        EmployeeFactory employeeFactory = new EmployeeFactory();

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

            List<TeamManager> managers;

            managers = employeeFactory.getManagers((int) Math.pow(size, depth - 1), size);

            assignEmployeesToManagers(managers, employees, size);

            for (int i = depth - 1; i > 1; i--) {
                employees.clear();
                employees.addAll(managers);
                managers = employeeFactory.getManagers((int) Math.pow(size, i - 1), size);
                assignEmployeesToManagers(managers, employees, size);
            }

            for (Employee e : managers) {
                ceo.hire(e);
            }
        }

        int tasks = generator.nextInt(100) + 1;

        for (int i = 0; i < tasks; i++) {
            ceo.assign(employeeFactory.getTask());
        }

        Report report = ceo.reportWork();

        reportService.printReport(report);

        System.out.println("Just created Company of depth " + depth + " and size " + size + ", recieved " + tasks + " tasks");
    }
}
