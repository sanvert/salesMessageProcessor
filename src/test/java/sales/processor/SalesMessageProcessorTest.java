package sales.processor;

import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;
import sales.MessageUtils;
import sales.analytics.MessageAnalytics;
import sales.model.SalesMessage;
import sales.repository.SalesRepository;

import static org.junit.Assert.assertEquals;

/**
 * Created by sanver.
 */
@RunWith(JUnit4.class)
public class SalesMessageProcessorTest {
    private static SalesMessageProcessor processor;
    private static SalesRepository salesRepository;
    private static MessageAnalytics messageAnalytics;

    @BeforeClass
    public static void setUp() {
        salesRepository = SalesRepository.getInstance();
        messageAnalytics = MessageAnalytics.getInstance();
        processor = new SalesMessageProcessor(salesRepository, messageAnalytics);
    }

    @Test
    public void shouldReturnInvalidForInvalidMessage() {
        SalesMessage message = new SalesMessage();
        String result = processor.process(message);
        assertEquals(result, MessageUtils.INVALID_MSG);
    }

    @Test
    public void shouldReturnProcessedForValidMessage() {
        SalesMessage message = new SalesMessage(1, "apple", Double.valueOf(1.1));
        String result = processor.process(message);
        assertEquals(result, MessageUtils.PROCESSED);
    }
}
