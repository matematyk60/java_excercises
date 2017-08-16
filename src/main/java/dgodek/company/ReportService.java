package dgodek.company;

import dgodek.company.report.Report;

public class ReportService {
    public void printReport(Report report) {
        System.out.println(report.toString());
    }
}
