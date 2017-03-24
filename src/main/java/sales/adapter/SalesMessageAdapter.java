package sales.adapter;

import sales.model.SalesMessage;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by sanver.
 */
public class SalesMessageAdapter implements MessageAdapter {
    private Pattern messageType2Pattern = Pattern.compile("^\\s*(\\d+)\\s*([A-z]+)\\s*(\\d+(\\.\\d+)?)\\s*$");

    public SalesMessage adapt(String content) {
        Matcher m = messageType2Pattern.matcher(content);
        SalesMessage message = m.find() ? new SalesMessage(Integer.valueOf(m.group(1)),
                                                        m.group(2),
                                                        Double.valueOf(m.group(3))) : new SalesMessage();

        return message;
    }
}
