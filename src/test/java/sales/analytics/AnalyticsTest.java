package sales.analytics;

import org.junit.BeforeClass;
import org.junit.Test;
import sales.model.SalesMessage;
import sales.model.SalesUpdateMessage;
import sales.processor.SalesMessageProcessor;
import sales.processor.UpdateMessageProcessor;
import sales.repository.SalesRepository;
import sales.repository.UpdateOperationRepository;
import sales.type.AnalyticsType;
import sales.type.UpdateOperationType;

import java.util.stream.IntStream;

import static org.junit.Assert.assertEquals;

/**
 * Created by sanver.
 */
public class AnalyticsTest {

    private static MessageAnalytics analyticsGenerator;
    private static SalesMessageProcessor salesMessageProcessor;
    private static UpdateMessageProcessor updateMessageProcessor;

    @BeforeClass
    public static void setUp() {
        analyticsGenerator = MessageAnalytics.getInstance();
        salesMessageProcessor = new SalesMessageProcessor(SalesRepository.getInstance(), analyticsGenerator);
        updateMessageProcessor = new UpdateMessageProcessor(SalesRepository.getInstance(),
                UpdateOperationRepository.getInstance(),
                analyticsGenerator);
    }

    @Test
    public void testInterimReportGeneration() {
        IntStream.range(0, 10).forEach(i -> {
            SalesMessage msg = new SalesMessage(1, "apple", 10.0);
            salesMessageProcessor.process(msg);
        });
        AnalyticsResult result = analyticsGenerator.generate();
        assertEquals(result.getType(), AnalyticsType.INTERIM_REPORT);
    }

    @Test
    public void testGeneralReportGeneration() {
        IntStream.range(0, 10).forEach(i -> {
            SalesMessage msg = new SalesMessage(1, "apricot", 10.0);
            salesMessageProcessor.process(msg);
        });
        IntStream.range(0, 20).forEach(i -> {
            SalesUpdateMessage updateMessage = new SalesUpdateMessage("apricot", 2.0, UpdateOperationType.ADD);
            updateMessageProcessor.process(updateMessage);
        });
        IntStream.range(0, 20).forEach(i -> {
            SalesUpdateMessage updateMessage = new SalesUpdateMessage("apricot", 1.0, UpdateOperationType.SUBTRACT);
            updateMessageProcessor.process(updateMessage);
        });
        AnalyticsResult result = analyticsGenerator.generate();
        assertEquals(result.getType(), AnalyticsType.GENERAL_REPORT);
    }
}
