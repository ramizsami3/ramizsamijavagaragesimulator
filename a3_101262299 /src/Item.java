import java.io.Serializable;

public abstract class Item implements Serializable {
    private static final long serialVersionUID = 1L; // Unique ID for serialization

    private double price;
    private int invQuantity;
    private int soldQuantity;

    public Item(double price, int invQuantity) {
        this.price = price;
        this.invQuantity = invQuantity;
        this.soldQuantity = 0;
    }

    public double getPrice() {
        return price;
    }

    public int getInvQuantity() {
        return invQuantity;
    }

    public int getSoldQuantity() {
        return soldQuantity;
    }

    // Setters (if needed for updating during deserialization)
    public void setInvQuantity(int invQuantity) {
        this.invQuantity = invQuantity;
    }

    public void setSoldQuantity(int soldQuantity) {
        this.soldQuantity = soldQuantity;
    }


}
