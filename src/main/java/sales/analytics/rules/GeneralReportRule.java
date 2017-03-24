package sales.analytics.rules;

import sales.type.AnalyticsType;

/**
 * Created by sanver.
 */
public class GeneralReportRule implements AnalyticsRule {
    @Override
    public boolean check(int messageCount) {
        return messageCount > 0 && messageCount % AnalyticsType.GENERAL_REPORT.getMessageFrequency() == 0;
    }
}
