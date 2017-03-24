package sales;

import sales.type.NotificationType;
import sales.type.UpdateOperationType;

/**
 * Created by sanver.
 */
public class MessageUtils {
    public static final String INVALID_MSG = "INVALID MESSAGE";
    public static final String PROCESSED = "PROCESSED SUCCESSFULLY";

    public static NotificationType getSalesMessageNotificationType(String message) {
        int index = Integer.valueOf(message.substring(0, 1));
        if(index>NotificationType.values().length-1)
            return NotificationType.INVALID_MSG;
        return NotificationType.values()[index-1];
    }

    public static UpdateOperationType getUpdateOperationType(String operation) {
        UpdateOperationType operationType;
        try {
            operationType = UpdateOperationType.valueOf(operation.toUpperCase());
        } catch (Exception e) {
            operationType = UpdateOperationType.INVALID;
        }
        return  operationType;
    }
}
