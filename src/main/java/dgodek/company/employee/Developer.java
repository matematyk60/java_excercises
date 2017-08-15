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

    public Developer(Developer.Builder builder) {
        super(builder);
        tasks = new ArrayList<>();
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

    public List<Task> getTasks() {
        return tasks;
    }

    public static class Builder extends AbstractEmployee.Builder {
        public Builder(String name, String email, String nationality) {
            super(name, email, nationality);
        }

        @Override
        public Developer build() {
            return new Developer(this);
        }
    }
}
