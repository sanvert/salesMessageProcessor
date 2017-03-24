package sales.adapter;

import sales.type.NotificationType;

import java.util.EnumMap;
import java.util.Map;

/**
 * Created by sanver.
 */
public class AdapterMappings {
    private static final Map<NotificationType, MessageAdapter> adapterMap = new EnumMap<NotificationType, MessageAdapter>(NotificationType.class)
    {{
        MessageAdapter salesMessageAdapter = new SalesMessageAdapter();
        MessageAdapter salesUpdateMessageAdapter = new SalesUpdateMessageAdapter();
        MessageAdapter singleSaMessageAdapter = new SingleSalesMessageAdapter();

        put(NotificationType.SINGLE_SALES, singleSaMessageAdapter);
        put(NotificationType.SALES, salesMessageAdapter);
        put(NotificationType.UPDATE, salesUpdateMessageAdapter);
    }};

    public static MessageAdapter get(NotificationType type) {
        return adapterMap.get(type);
    }
}
