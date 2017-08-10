package dgodek.company;

import dgodek.company.employee.Developer;
import dgodek.company.employee.TeamManager;
import dgodek.company.report.ManagerReport;
import dgodek.company.report.Report;

import java.util.List;

public class ReportService {
    public void printReport(Report report) {
        List<Report> reports = report.getReports();
        if (reports != null) {
            for (Report r : reports) {
                this.printReport(r);
            }
        }
        System.out.println(report.toString());
    }
}
