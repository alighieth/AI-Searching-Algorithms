package code;
public class RequestDelivery {
    
    public enum deliveryType {
        FOOD, ENERGY, MATERIALS
    }
    public deliveryType type;
    public int delay;
    public int amount;

    public RequestDelivery(int delay, int amount, deliveryType type) {
        this.delay = delay;
        this.amount = amount;
        this.type = type;
    }
}
