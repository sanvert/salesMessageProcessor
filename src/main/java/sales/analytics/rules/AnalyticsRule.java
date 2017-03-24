package sales.analytics.rules;

import sales.analytics.MessageAnalytics;

/**
 * Created by sanver.
 */
public interface AnalyticsRule {
    public boolean check(int messageCount);
}
