package sales.analytics;

import sales.analytics.rules.AnalyticsRule;
import sales.analytics.rules.GeneralReportRule;
import sales.analytics.rules.InterimReportRule;
import sales.repository.SalesRepository;
import sales.repository.UpdateOperationRepository;
import sales.type.AnalyticsType;

/**
 * Created by sanver.
 */
public class MessageAnalytics {
    private static MessageAnalytics messageAnalytics;
    private static int currentMessageCount;
    private static SalesRepository salesRepository;
    private static UpdateOperationRepository updateOperationRepository;
    private static AnalyticsRule interimRule;
    private static AnalyticsRule generalRule;

    private MessageAnalytics() {
        //Private constructor to make a singleton object.
    }

    public static MessageAnalytics getInstance() {
        if(messageAnalytics == null) {
            salesRepository = SalesRepository.getInstance();
            updateOperationRepository = UpdateOperationRepository.getInstance();
            interimRule = new InterimReportRule();
            generalRule = new GeneralReportRule();
            messageAnalytics = new MessageAnalytics();
        }
        return messageAnalytics;
    }

    public int increaseAndGetCount() {
        return ++currentMessageCount;
    }

    public int getCurrentMessageCount() {
        return currentMessageCount;
    }

    public AnalyticsResult generate() {
        final StringBuilder sb = new StringBuilder();
        AnalyticsType type = AnalyticsType.EMPTY_REPORT;

        if(interimRule.check(currentMessageCount)){
            salesRepository.getItemCollection().forEach((key, value) -> {
                Double total = value.getMap().entrySet().stream()
                        .mapToDouble(e -> e.getKey() * e.getValue())
                        .reduce(0.0, (v1, v2) -> v1 + v2);

                sb.append(key).append(" ").append(total).append("\n");
            });
            sb.append("\n");
            type = AnalyticsType.INTERIM_REPORT;
        }

        if(generalRule.check(currentMessageCount)) {
            updateOperationRepository.getItemCollection().forEach((key, value) -> {
                sb.append(key);
                value.getList().stream().forEach(e -> {
                    sb.append("\n\t").append(e.getType().name()).append(" ").append(e.getPrice());
                });
            });
            sb.append("\n");
            sb.insert(0, "Message Processor paused.\n");
            type = AnalyticsType.GENERAL_REPORT;
        }

        return new AnalyticsResult(sb.toString(), type);
    }
}
