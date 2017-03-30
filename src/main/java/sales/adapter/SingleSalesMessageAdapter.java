package sales.adapter;

import sales.model.SalesMessage;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by sanver.
 */
public class SingleSalesMessageAdapter implements MessageAdapter {
    private Pattern messageType1Pattern = Pattern.compile("^\\s*([A-z]+)\\s*(\\d+(\\.\\d+)?)\\s*$");

    public SalesMessage adapt(String content) {
        Matcher m = messageType1Pattern.matcher(content);
        SalesMessage message = m.find() ? new SalesMessage(1, m.group(1),
                                                            Double.valueOf(m.group(2)))
                                        : new SalesMessage(false);

        return message;
    }
}
