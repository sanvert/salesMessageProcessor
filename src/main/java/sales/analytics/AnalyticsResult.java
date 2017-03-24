package sales.analytics;

import sales.type.AnalyticsType;

/**
 * Created by sanver.
 */
public class AnalyticsResult {
    private String report;
    private AnalyticsType type;

    public AnalyticsResult(String report, AnalyticsType type) {
        this.report = report;
        this.type = type;
    }

    public String getReport() {
        return report;
    }

    public AnalyticsType getType() {
        return type;
    }
}
