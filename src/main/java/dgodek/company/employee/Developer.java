package dgodek.company.employee;

import dgodek.company.Task;
import dgodek.company.report.WorkerReport;
import dgodek.company.report.Report;

import java.util.List;

/**
 * Created by matematyk60 on 23.07.17.
 */
public class Developer extends AbstractEmployee {

    public Developer(Developer.Builder builder) {
        super(builder);
    }

    @Override
    public void assign(Task task) {
        System.out.println(this.toString() + " | Adding task " + task.toString() + " to my tasklist");
        tasks.add(task);
        amountOfWork += task.getunitsOfWork();
    }

    @Override
    public Report reportWork() {
        return new WorkerReport(this);
    }

    @Override
    public List<Report> reportWork(List<Report> reports) {
        reports.add(new WorkerReport(this));
        return reports;
    }

    public static class Builder extends AbstractEmployee.Builder {
        public Builder(String name, String surname, String email, String nationality) {
            super(name, surname, email, nationality);
            role(Role.DEVELOPER);
        }

        @Override
        public Developer build() {
            return new Developer(this);
        }
    }
}
