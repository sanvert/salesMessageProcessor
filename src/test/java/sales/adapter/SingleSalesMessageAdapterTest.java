package sales.adapter;

import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;
import sales.adapter.MessageAdapter;
import sales.adapter.SingleSalesMessageAdapter;
import sales.model.SalesMessage;
import sales.model.SalesUpdateMessage;

import static org.junit.Assert.assertEquals;

/**
 * Created by sanver.
 */
@RunWith(JUnit4.class)
public class SingleSalesMessageAdapterTest {
    private static MessageAdapter<SalesMessage> adapter;

    @BeforeClass
    public static void setUp() {
        adapter = new SingleSalesMessageAdapter();
    }

    @Test
    public void shouldAdaptRawContentIntoValidMessage() {
        String messageContent = "melon 18";
        SalesMessage message = adapter.adapt(messageContent);
        assertEquals(message.isValid(), true);
        assertEquals(message.getAmount(), 1);
        assertEquals(message.getItemType(), "melon");
        assertEquals(message.getPrice(), Double.valueOf(18));
    }

    @Test
    public void shouldAdaptInvalidRawContentIntoInvalidMessage() {
        String messageContent = " melon 18.1 a";
        SalesMessage message = adapter.adapt(messageContent);
        assertEquals(message.isValid(), false);
    }
}
