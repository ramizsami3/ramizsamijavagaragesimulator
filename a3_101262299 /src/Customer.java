import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

public class Customer implements Serializable {
    private static final long serialVersionUID = 1L; // Unique ID for serialization

    private String name;
    private double totalSpent;
    private Map<Item, Integer> purchaseHistory;

    public Customer(String name) {
        this.name = name;
        this.totalSpent = 0;
        this.purchaseHistory = new HashMap<>();
    }

    public void purchaseItem(Item item, int quantity) {
        if (item == null || quantity <= 0) {
            throw new IllegalArgumentException("Invalid item or quantity.");
        }
        double cost = item.getPrice() * quantity;
        totalSpent += cost;

        purchaseHistory.put(item, purchaseHistory.getOrDefault(item, 0) + quantity);
    }


    public String toString() {
        return String.format("%s has spent $%.2f", name, totalSpent);
    }

    public void printPurchaseHistory() {
        if (purchaseHistory.isEmpty()) {
            System.out.println("Customer didn't purchase anything");
            return;
        }

        for (Map.Entry<Item, Integer> entry : purchaseHistory.entrySet()) {
            Item item = entry.getKey();
            int quantity = entry.getValue();
            System.out.println(quantity + " x " + item.toString());
        }
    }

    public String getName() {
        return name;
    }
}
