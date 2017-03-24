package sales.adapter;

import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;
import sales.adapter.MessageAdapter;
import sales.adapter.SalesMessageAdapter;
import sales.model.SalesMessage;

import static org.junit.Assert.assertEquals;

/**
 * Created by sanver.
 */
@RunWith(JUnit4.class)
public class SalesMessageAdapterTest {
    private static MessageAdapter<SalesMessage> adapter;

    @BeforeClass
    public static void setUp() {
        adapter = new SalesMessageAdapter();
    }

    @Test
    public void shouldAdaptRawContentIntoValidMessage() {
        String messageContent = "10 apple 15.1";
        SalesMessage message = adapter.adapt(messageContent);
        assertEquals(message.isValid(), true);
        assertEquals(message.getAmount(), 10);
        assertEquals(message.getItemType(), "apple");
        assertEquals(message.getPrice(), Double.valueOf(15.1));
    }

    @Test
    public void shouldAdaptInvalidRawContentIntoInvalidMessage() {
        String messageContent = "a 10 apple 15.1";
        SalesMessage message = adapter.adapt(messageContent);
        assertEquals(message.isValid(), false);
    }
}
