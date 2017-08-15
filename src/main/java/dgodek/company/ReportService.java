package dgodek.company;

import dgodek.company.employee.Developer;
import dgodek.company.employee.TeamManager;
import dgodek.company.report.ManagerReport;
import dgodek.company.report.Report;

import java.util.List;
import java.util.Optional;

public class ReportService {
    public void printReport(Report report) {
        Optional<List<Report>> reports = report.getReports();

        reports.ifPresent(r -> r
                .forEach(rp -> System.out.println(rp.toString())));

        System.out.println(report.toString());
    }
}
