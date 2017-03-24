package sales.entity;

import sales.type.UpdateOperationType;

/**
 * Created by sanver.
 *
 **/

public class UpdateOperation {
    private Double price;
    private UpdateOperationType type;

    public UpdateOperation(Double price, UpdateOperationType type) {
        this.price = price;
        this.type = type;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public UpdateOperationType getType() {
        return type;
    }

    public void setType(UpdateOperationType type) {
        this.type = type;
    }
}
