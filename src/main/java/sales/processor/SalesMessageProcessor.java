package sales.processor;

import sales.MessageUtils;
import sales.analytics.MessageAnalytics;
import sales.entity.PriceAmountPairs;
import sales.model.SalesMessage;
import sales.repository.SalesRepository;

/**
 * Created by sanver.
 */
public class SalesMessageProcessor implements MessageProcessor<SalesMessage> {

    private final SalesRepository salesRepository;
    private final MessageAnalytics messageAnalytics;

    public SalesMessageProcessor(SalesRepository salesRepository, MessageAnalytics messageAnalytics) {
        this.salesRepository = salesRepository;
        this.messageAnalytics = messageAnalytics;
    }

    @Override
    public String process(final SalesMessage message) {
        if(!message.isValid())
            return MessageUtils.INVALID_MSG;

        salesRepository.getItemCollection()
                .computeIfPresent(message.getItemType(), (key, value) -> {value.addPriceAmountPair(message.getPrice(), message.getAmount());
                                                                                messageAnalytics.increaseAndGetCount();
                                                                                return value;
                                                                            });
        salesRepository.getItemCollection().computeIfAbsent(message.getItemType(), key -> {PriceAmountPairs pMap = new PriceAmountPairs();
                                                                                                pMap.addPriceAmountPair(message.getPrice(), message.getAmount());
                                                                                                messageAnalytics.increaseAndGetCount();
                                                                                                return pMap;
                                                                                            });

        return MessageUtils.PROCESSED;
    }
}
