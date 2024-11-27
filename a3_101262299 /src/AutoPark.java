import java.io.*;
import java.util.*;
import java.util.stream.Collectors;

public class AutoPark implements Serializable {
    private static final long serialVersionUID = 1L; // For serialization

    private String name;
    private double revenue;
    private List<Item> items;
    private List<Customer> customers;

    public AutoPark(String name) {
        this.name = name;
        this.revenue = 0;
        this.items = new ArrayList<>();
        this.customers = new ArrayList<>();
    }

    // Register a customer
    public boolean registerCustomer(Customer customer) {
        if (customer == null || customers.stream().anyMatch(c -> c.getName().equalsIgnoreCase(customer.getName()))) {
            return false; // Null check or duplicate customer
        }
        customers.add(customer);
        return true;
    }

    // Add an item
    public boolean addItem(Item item) {
        if (item == null || items.stream().anyMatch(i -> i.toString().equalsIgnoreCase(item.toString()))) {
            return false; // Null check or duplicate item
        }
        items.add(item);
        return true;
    }

    // Get list of customers
    public List<Customer> getCustomers() {
        return new ArrayList<>(customers);
    }

    // Get list of items
    public List<Item> getItems() {
        return new ArrayList<>(items);
    }

    // Search items by name
    public List<Item> searchItems(String x) {
        if (x == null || x.isEmpty()) {
            return Collections.emptyList();
        }
        return items.stream()
                .filter(item -> item.toString().toLowerCase().contains(x.toLowerCase()))
                .collect(Collectors.toList());
    }

    // Search items by name and price range
    public List<Item> searchItems(String x, double minPrice, double maxPrice) {
        if (x == null || x.isEmpty()) {
            return searchItems(x);
        }
        double validMinPrice = minPrice >= 0 ? minPrice : 0;
        double validMaxPrice = maxPrice >= 0 ? maxPrice : Double.MAX_VALUE;

        return items.stream()
                .filter(item -> item.toString().toLowerCase().contains(x.toLowerCase()))
                .filter(item -> item.getPrice() >= validMinPrice && item.getPrice() <= validMaxPrice)
                .collect(Collectors.toList());
    }

    // Add stock to an item
    public boolean addStock(Item anItem, int amount) {
        if (anItem == null || amount <= 0) {
            return false;
        }
        for (Item item : items) {
            if (item.toString().equalsIgnoreCase(anItem.toString())) {
                item.setInvQuantity(item.getInvQuantity() + amount);
                return true;
            }
        }
        return false;
    }

    // Sell an item to a customer
    public boolean sellItem(Item anItem, Customer c, int amount) {
        if (anItem == null || c == null || amount <= 0) {
            return false;
        }
        if (!customers.contains(c)) {
            return false; // Customer not registered
        }
        for (Item item : items) {
            if (item.toString().equalsIgnoreCase(anItem.toString())) {
                if (item.getInvQuantity() < amount) {
                    return false; // Not enough stock
                }
                item.setInvQuantity(item.getInvQuantity() - amount);
                item.setSoldQuantity(item.getSoldQuantity() + amount);
                c.purchaseItem(item, amount);
                revenue += item.getPrice() * amount;
                return true;
            }
        }
        return false; // Item not found
    }

    // Get top x popular items
    public List<Item> getXPopularItems(int x) {
        if (x <= 0) {
            return Collections.emptyList();
        }
        return items.stream()
                .sorted((i1, i2) -> Integer.compare(i2.getSoldQuantity(), i1.getSoldQuantity()))
                .limit(x)
                .collect(Collectors.toList());
    }

    // Save the AutoPark state to a file
    public boolean saveToFile(String filename) {
        try (ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(filename))) {
            out.writeObject(this);
            return true;
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }

    // Load the AutoPark state from a file
    public static AutoPark loadFromFile(String filename) {
        try (ObjectInputStream in = new ObjectInputStream(new FileInputStream(filename))) {
            return (AutoPark) in.readObject();
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
            return null;
        }
    }


    public String toString() {
        return String.format("AutoPark[name=%s, revenue=%.2f, items=%d, customers=%d]",
                name, revenue, items.size(), customers.size());
    }
}
