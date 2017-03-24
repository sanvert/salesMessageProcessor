package sales;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;
import sales.type.NotificationType;
import sales.type.UpdateOperationType;

import static org.junit.Assert.assertEquals;

/**
 * Created by sanver.
 */
@RunWith(JUnit4.class)
public class MessageUtilsTest {

    @Test
    public void shouldGetInvalidNotificationType() {
        NotificationType type = MessageUtils.getSalesMessageNotificationType("5 msg");
        assertEquals(type, NotificationType.INVALID_MSG);
    }

    @Test
    public void shouldGetValidNotificationType() {
        NotificationType type = MessageUtils.getSalesMessageNotificationType("1 msg");
        assertEquals(type, NotificationType.SINGLE_SALES);
    }

    @Test
    public void shouldGetInvalidUpdateOperation() {
        UpdateOperationType type = MessageUtils.getUpdateOperationType("DUMMY");
        assertEquals(type, UpdateOperationType.INVALID);
    }

    @Test
    public void shouldGetValidUpdateOperation() {
        UpdateOperationType type = MessageUtils.getUpdateOperationType("ADD");
        assertEquals(type, UpdateOperationType.ADD);
    }
}
