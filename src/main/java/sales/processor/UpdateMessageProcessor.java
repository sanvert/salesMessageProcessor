package sales.processor;

import sales.MessageUtils;
import sales.analytics.MessageAnalytics;
import sales.entity.ItemUpdateOperations;
import sales.entity.PriceAmountPairs;
import sales.entity.UpdateOperation;
import sales.model.SalesUpdateMessage;
import sales.repository.SalesRepository;
import sales.repository.UpdateOperationRepository;

/**
 * Created by sanver.
 */
public class UpdateMessageProcessor implements MessageProcessor<SalesUpdateMessage> {
    private final SalesRepository salesRepository;
    private final UpdateOperationRepository updateOperationRepository;
    private final MessageAnalytics messageAnalytics;

    public UpdateMessageProcessor(SalesRepository salesRepository,
                                  UpdateOperationRepository updateOperationRepository,
                                  MessageAnalytics messageAnalytics) {
        this.salesRepository = salesRepository;
        this.updateOperationRepository = updateOperationRepository;
        this.messageAnalytics = messageAnalytics;
    }

    @Override
    public String process(SalesUpdateMessage message) {
        if(!message.isValid())
            return MessageUtils.INVALID_MSG;

        salesRepository.getItemCollection().computeIfPresent(message.getItemType(), (key, value) -> {
            PriceAmountPairs newPairs = new PriceAmountPairs();
            value.getMap().forEach((keyOld, valueOld) -> {
                Double newKey = (Double) UpdateOperationMappings.get(message.getOperationType())
                                                                    .apply(keyOld, message.getPrice());
                newPairs.addPriceAmountPair(newKey, valueOld);
            });
            recordOperation(message);
            messageAnalytics.increaseAndGetCount();
            return newPairs;
        });

        return MessageUtils.PROCESSED;
    }

    private void recordOperation(SalesUpdateMessage message) {
        updateOperationRepository.getItemCollection().computeIfPresent(message.getItemType(),
                (key, value) -> {
                    value.getList().add(new UpdateOperation(message.getPrice(), message.getOperationType()));
                    return value;
                });
        updateOperationRepository.getItemCollection().computeIfAbsent(message.getItemType(),
                key -> {
                    ItemUpdateOperations operations = new ItemUpdateOperations();
                    operations.getList().add(new UpdateOperation(message.getPrice(), message.getOperationType()));
                    return operations;
                });
    }
}
