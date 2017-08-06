package dgodek.company.employee;

import dgodek.company.Task;
import dgodek.company.report.DeveloperReport;
import dgodek.company.report.Report;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by matematyk60 on 23.07.17.
 */
public class Developer extends AbstactEmployee implements Employee{
    private List<Task> tasks;

    public Developer(String name, Role role) {
        super(name, role);
        tasks = new ArrayList<>();
    }

    @Override
    public void assign(Task task) {
        System.out.println(this.toString() + " | Adding task " + task.toString() + " to my tasklist");
        tasks.add(task);
        setAmountOfWork(getAmountOfWork()+task.getunitsOfWork());
    }

    @Override
    public Report reportWork() {
        return new DeveloperReport(this);
    }

}
