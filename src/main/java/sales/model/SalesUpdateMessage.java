package sales.model;

import sales.type.UpdateOperationType;

/**
 * Created by sanver.
 */
public class SalesUpdateMessage extends SalesMessage {
    private UpdateOperationType operationType;

    public SalesUpdateMessage(String itemType, Double price, UpdateOperationType operationType) {
        super(1, itemType, price);
        this.operationType = operationType;
    }

    public SalesUpdateMessage() {
        super();
    }

    public UpdateOperationType getOperationType() {
        return operationType;
    }
}
