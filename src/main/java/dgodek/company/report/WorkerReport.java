package dgodek.company.report;

import dgodek.company.Task;
import dgodek.company.employee.Developer;
import dgodek.company.employee.Employee;

import java.util.List;

public class WorkerReport extends AbstractReport {
    private List<Task> tasks;

    public WorkerReport(Employee employee) {
        super(employee);
        this.tasks = employee.getTasks();
    }

    @Override
    public String toString() {
        return "WorkerReport{" +
                "name='" + name + '\'' +
                ", surname='" + surname + '\'' +
                ", email='" + email + '\'' +
                ", role=" + role +
                ", amountOfWork=" + amountOfWork +
                ", tasks=" + tasks +
                "} \n";
    }
}
