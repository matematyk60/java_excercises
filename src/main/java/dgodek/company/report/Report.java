package dgodek.company.report;

import java.util.List;
import java.util.Optional;

public interface Report {
    Optional<List<Report>> getReports();
}
