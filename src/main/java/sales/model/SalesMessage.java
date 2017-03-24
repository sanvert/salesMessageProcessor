package sales.model;

/**
 * Created by sanver.
 */
public class SalesMessage {
    private boolean valid;
    private int amount;
    private String itemType;
    private Double price;

    public SalesMessage(int amount, String itemType, Double price) {
        this.valid = true;
        this.amount = amount;
        this.itemType = itemType;
        this.price = price;
    }

    public SalesMessage() {
        this.valid = false;
    }

    public int getAmount() {
        return amount;
    }

    public String getItemType() {
        return itemType;
    }

    public Double getPrice() {
        return price;
    }

    public boolean isValid() {
        return valid;
    }
}
