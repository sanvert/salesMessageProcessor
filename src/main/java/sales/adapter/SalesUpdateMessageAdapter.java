package sales.adapter;

import sales.MessageUtils;
import sales.model.SalesUpdateMessage;
import sales.type.UpdateOperationType;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by sanver.
 */
public class SalesUpdateMessageAdapter implements MessageAdapter {
    private Pattern messageType3Pattern = Pattern.compile("^\\s*([A-z]+)\\s*(\\d+(\\.\\d+)?)\\s*([A-z]+)\\s*$");

    public SalesUpdateMessage adapt(String content) {
        Matcher m = messageType3Pattern.matcher(content);
        UpdateOperationType operationType;
        SalesUpdateMessage message = m.find()
                && (operationType = MessageUtils.getUpdateOperationType(m.group(4))) != UpdateOperationType.INVALID
                ? new SalesUpdateMessage(m.group(1),
                                            Double.valueOf(m.group(2)),
                                            operationType)
                : new SalesUpdateMessage();

        return message;
    }
}