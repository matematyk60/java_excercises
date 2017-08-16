package dgodek.company.employee;

import dgodek.company.Task;
import dgodek.company.report.DeveloperReport;
import dgodek.company.report.Report;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by matematyk60 on 23.07.17.
 */
public class Developer extends AbstractEmployee {
    private final List<Task> tasks;

    public Developer(String name, String email, Sex sex, String academy, String nationality, Role role) {
        super(name, email, sex, academy, nationality, role);
        this.tasks = new ArrayList<>();
    }

    public Developer(String name, Role role) {
        Employee employee = new AbstractEmployee
                .Builder(name, "dawid.gdk@gmail.com", "polska")
                .academy("AGH")
                .role(Role.DEVELOPER)
                .sex(Sex.MALE)
                .developerBuild();

        tasks = new ArrayList<>();
    }

    public Developer(AbstractEmployee.Builder builder) {
        super(builder);
        tasks = new ArrayList<>();
    }

    public List<Task> getTasks() {
        return tasks;
    }

    @Override
    public void assign(Task task) {
        System.out.println(this.toString() + " | Adding task " + task.toString() + " to my tasklist");
        tasks.add(task);
        setAmountOfWork(this.getAmountOfWork() + task.getunitsOfWork());
    }

    @Override
    public Report reportWork() {
        return new DeveloperReport(this);
    }

}
