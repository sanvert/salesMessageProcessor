package sales.processor;

import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;
import sales.MessageUtils;
import sales.analytics.MessageAnalytics;
import sales.entity.PriceAmountPairs;
import sales.model.SalesMessage;
import sales.model.SalesUpdateMessage;
import sales.repository.SalesRepository;
import sales.repository.UpdateOperationRepository;
import sales.type.UpdateOperationType;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

/**
 * Created by sanver.
 */
@RunWith(JUnit4.class)
public class UpdateMessageProcessorTest {
    private static UpdateMessageProcessor processor;
    private static SalesMessageProcessor salesProcessor;
    private static SalesRepository salesRepository;
    private static UpdateOperationRepository updateOperationRepository;
    private static MessageAnalytics messageAnalytics;

    @BeforeClass
    public static void setUp() {
        salesRepository = SalesRepository.getInstance();
        updateOperationRepository = UpdateOperationRepository.getInstance();
        messageAnalytics = MessageAnalytics.getInstance();
        salesProcessor = new SalesMessageProcessor(salesRepository, messageAnalytics);
        processor = new UpdateMessageProcessor(salesRepository, updateOperationRepository, messageAnalytics);
    }

    @Test
    public void shouldReturnInvalidForInvalidMessage() {
        SalesUpdateMessage message = new SalesUpdateMessage();
        String result = processor.process(message);
        assertEquals(result, MessageUtils.INVALID_MSG);
    }

    @Test
    public void shouldReturnProcessedForValidMessage() {
        SalesUpdateMessage message = new SalesUpdateMessage("apple", Double.valueOf(1.1), UpdateOperationType.ADD);
        String result = processor.process(message);
        assertEquals(result, MessageUtils.PROCESSED);
    }

    @Test
    public void shouldApplyAddOperation() {
        //Test sales message is added to check add kind of update operation.
        SalesMessage saleMessage = new SalesMessage(1, "apple", Double.valueOf(10));
        salesProcessor.process(saleMessage);

        SalesUpdateMessage updateMessage = new SalesUpdateMessage("apple", Double.valueOf(2), UpdateOperationType.ADD);
        String result = processor.process(updateMessage);
        PriceAmountPairs priceAmountPairs = salesRepository.getItemCollection().get(updateMessage.getItemType());
        assertNotNull(priceAmountPairs.getMap().get(Double.valueOf(12)));
    }

    @Test
    public void shouldApplySubtractOperation() {
        //Test sales message is added to check subtract kind of update operation.
        SalesMessage saleMessage = new SalesMessage(1, "melon", Double.valueOf(10));
        salesProcessor.process(saleMessage);

        SalesUpdateMessage updateMessage = new SalesUpdateMessage("melon", Double.valueOf(2), UpdateOperationType.SUBTRACT);
        String result = processor.process(updateMessage);
        PriceAmountPairs priceAmountPairs = salesRepository.getItemCollection().get(updateMessage.getItemType());
        assertNotNull(priceAmountPairs.getMap().get(Double.valueOf(8)));
    }

    @Test
    public void shouldApplyMultiplyOperation() {
        //Test sales message is added to check subtract kind of update operation.
        SalesMessage saleMessage = new SalesMessage(1, "carrot", Double.valueOf(10));
        salesProcessor.process(saleMessage);

        SalesUpdateMessage updateMessage = new SalesUpdateMessage("carrot", Double.valueOf(2), UpdateOperationType.MULTIPLY);
        String result = processor.process(updateMessage);
        PriceAmountPairs priceAmountPairs = salesRepository.getItemCollection().get(updateMessage.getItemType());
        assertNotNull(priceAmountPairs.getMap().get(Double.valueOf(20)));
    }
}
