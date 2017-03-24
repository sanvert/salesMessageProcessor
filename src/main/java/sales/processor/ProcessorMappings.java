package sales.processor;

import sales.analytics.MessageAnalytics;
import sales.repository.SalesRepository;
import sales.repository.UpdateOperationRepository;
import sales.type.NotificationType;

import java.util.EnumMap;
import java.util.Map;

/**
 * Created by sanver.
 */
public class ProcessorMappings {
    private static final Map<NotificationType, MessageProcessor> processorMap = new EnumMap<NotificationType, MessageProcessor>(NotificationType.class)
    {{
        MessageProcessor salesMessageProcessor = new SalesMessageProcessor(SalesRepository.getInstance(),
                MessageAnalytics.getInstance());
        MessageProcessor updateMessagesProcessor = new UpdateMessageProcessor(SalesRepository.getInstance(),
                UpdateOperationRepository.getInstance(),
                MessageAnalytics.getInstance());

        put(NotificationType.SINGLE_SALES, salesMessageProcessor);
        put(NotificationType.SALES, salesMessageProcessor);
        put(NotificationType.UPDATE, updateMessagesProcessor);
    }};

    public static MessageProcessor get(NotificationType type) {
        return processorMap.get(type);
    }
}
