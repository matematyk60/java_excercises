package dgodek.company.report;

import com.google.common.collect.ComparisonChain;
import dgodek.company.employee.Developer;
import dgodek.company.employee.Employee;
import dgodek.company.employee.Role;

import java.util.List;
import java.util.Optional;

public class ManagerReport extends AbstractReport {
    private List<Report> reports;

    public ManagerReport(Employee employee, List<Report> reports) {
        super(employee);
        this.reports = reports;
        sortReports();
    }

    public void sortReports() {
        reports.sort((o1, o2) -> ComparisonChain.start()
                .compare(o1.getNameOfWorker(), o2.getNameOfWorker())
                .compare(o1.getRole(), o2.getRole())
                .compare(o1.getAmountOfWork(), o2.getAmountOfWork())
                .result());
    }

    @Override
    public String toString() {
        return "ManagerReport{" +
                "name='" + name + '\'' +
                ", surname='" + surname + '\'' +
                ", email='" + email + '\'' +
                ", nationality='" + nationality + '\'' +
                ", sex=" + sex +
                ", academy='" + academy + '\'' +
                ", role=" + role +
                ", amountOfWork=" + amountOfWork +
                ", reports=" + reports +
                "} ";
    }
}
