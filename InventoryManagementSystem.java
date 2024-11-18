import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

class Product {
    private String name;
    private int quantity;
    private double price;

    public Product(String name, int quantity, double price) {
        this.name = name;
        this.quantity = quantity;
        this.price = price;
    }

    public void updateQuantity(int amount) {
        this.quantity += amount;
    }

    public int getQuantity() {
        return quantity;
    }

    public double getValue() {
        return quantity * price;
    }

    @Override
    public String toString() {
        return name + " - Quantity: " + quantity + ", Price: $" + price;
    }
}

class Inventory {
    private Map<String, Product> products;

    public Inventory() {
        products = new HashMap<>();
    }

    public void addProduct(String name, int quantity, double price) {
        if (products.containsKey(name)) {
            products.get(name).updateQuantity(quantity);
        } else {
            products.put(name, new Product(name, quantity, price));
        }
    }

    public void removeProduct(String name, int quantity) {
        if (products.containsKey(name)) {
            Product product = products.get(name);
            if (product.getQuantity() >= quantity) {
                product.updateQuantity(-quantity);
            } else {
                System.out.println("Not enough stock to remove.");
            }
        } else {
            System.out.println("Product not found.");
        }
    }

    public Map<String, Integer> getStock() {
        Map<String, Integer> stock = new HashMap<>();
        for (Map.Entry<String, Product> entry : products.entrySet()) {
            stock.put(entry.getKey(), entry.getValue().getQuantity());
        }
        return stock;
    }

    public double totalInventoryValue() {
        double total = 0;
        for (Product product : products.values()) {
            total += product.getValue();
        }
        return total;
    }

    public void displayProducts() {
        for (Product product : products.values()) {
            System.out.println(product);
        }
    }
}

// Main Class with User Interaction
public class InventoryManagementSystem {
    public static void main(String[] args) {
        Inventory inventory = new Inventory();
        Scanner scanner = new Scanner(System.in);
        String command;

        while (true) {
            System.out.println("\nOptions: add, remove, view, total, exit");
            System.out.print("Enter a command: ");
            command = scanner.nextLine().toLowerCase();

            switch (command) {
                case "add":
                    System.out.print("Enter product name: ");
                    String addName = scanner.nextLine();
                    System.out.print("Enter quantity: ");
                    int addQuantity = Integer.parseInt(scanner.nextLine());
                    System.out.print("Enter price: ");
                    double addPrice = Double.parseDouble(scanner.nextLine());
                    inventory.addProduct(addName, addQuantity, addPrice);
                    break;

                case "remove":
                    System.out.print("Enter product name: ");
                    String removeName = scanner.nextLine();
                    System.out.print("Enter quantity to remove: ");
                    int removeQuantity = Integer.parseInt(scanner.nextLine());
                    inventory.removeProduct(removeName, removeQuantity);
                    break;

                case "view":
                    System.out.println("Current Stock:");
                    inventory.displayProducts();
                    break;

                case "total":
                    System.out.println("Total Inventory Value: $" + inventory.totalInventoryValue());
                    break;

                case "exit":
                    System.out.println("Exiting the system.");
                    scanner.close();
                    return;

                default:
                    System.out.println("Invalid command. Please try again.");
            }
        }
    }
}
