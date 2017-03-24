package sales.adapter;

import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;
import sales.adapter.MessageAdapter;
import sales.adapter.SalesUpdateMessageAdapter;
import sales.model.SalesUpdateMessage;
import sales.type.UpdateOperationType;

import static org.junit.Assert.assertEquals;

/**
 * Created by sanver.
 */
@RunWith(JUnit4.class)
public class SalesUpdateMessageAdapterTest {
    private static MessageAdapter<SalesUpdateMessage> adapter;

    @BeforeClass
    public static void setUp() {
        adapter = new SalesUpdateMessageAdapter();
    }

    @Test
    public void shouldAdaptRawContentIntoValidAddMessage() {
        String messageContent = "apple 10.1 add";
        SalesUpdateMessage message = adapter.adapt(messageContent);
        assertEquals(message.getOperationType(), UpdateOperationType.ADD);
        assertEquals(message.isValid(), true);
        assertEquals(message.getAmount(), 1);
        assertEquals(message.getItemType(), "apple");
        assertEquals(message.getPrice(), Double.valueOf(10.1));
    }

    @Test
    public void shouldAdaptRawContentIntoValidSubtractMessage() {
        String messageContent = "melon 11 subtract";
        SalesUpdateMessage message = adapter.adapt(messageContent);
        assertEquals(message.getOperationType(), UpdateOperationType.SUBTRACT);
        assertEquals(message.isValid(), true);
        assertEquals(message.getAmount(), 1);
        assertEquals(message.getItemType(), "melon");
        assertEquals(message.getPrice(), Double.valueOf(11));
    }

    @Test
    public void shouldAdaptRawContentIntoValidMultiplyMessage() {
        String messageContent = "carrot 2 multiply";
        SalesUpdateMessage message = adapter.adapt(messageContent);
        assertEquals(message.getOperationType(), UpdateOperationType.MULTIPLY);
        assertEquals(message.isValid(), true);
        assertEquals(message.getAmount(), 1);
        assertEquals(message.getItemType(), "carrot");
        assertEquals(message.getPrice(), Double.valueOf(2));
    }

    @Test
    public void shouldAdaptInvalidRawContentIntoInvalidMessage() {
        String messageContent = "apple 10.1 remove";
        SalesUpdateMessage message = adapter.adapt(messageContent);
        assertEquals(message.isValid(), false);
    }
}
