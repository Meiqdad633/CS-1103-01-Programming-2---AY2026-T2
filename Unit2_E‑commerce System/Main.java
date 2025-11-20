import com.ecommerce.*;
import com.ecommerce.orders.Order;

import java.util.*;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        Product p1 = new Product("P01","Java Book", 12.50, 10);
        Product p2 = new Product("P02","Coffee Mug", 6.00, 2);
        Product p3 = new Product("P03","Notebook", 3.00, 5);
        List<Product> catalog = Arrays.asList(p1,p2,p3);

        Customer customer = new Customer("C100","Alex");

        System.out.println("=== Welcome to the Mini Shop ===");

        mainLoop:
        while (true) {
            System.out.println("\nMenu:");
            System.out.println("1) Show products");
            System.out.println("2) Add to cart");
            System.out.println("3) View cart");
            System.out.println("4) Remove from cart");
            System.out.println("5) Checkout (place order)");
            System.out.println("6) Exit");
            System.out.print("Choose an option: ");
            String choice = sc.nextLine().trim();
            try {
                switch (choice) {
                    case "1":
                        System.out.println("\nProduct catalog:");
                        catalog.forEach(System.out::println);
                        break;
                    case "2":
                        System.out.print("Enter product ID (e.g. P01): ");
                        String id = sc.nextLine().trim();
                        Product sel = findProductById(catalog, id);
                        if (sel == null) { System.out.println("Product not found."); break; }
                        System.out.print("Enter quantity: ");
                        int q = Integer.parseInt(sc.nextLine().trim());
                        customer.addToCart(sel, q);
                        System.out.println("Added to cart.");
                        break;
                    case "3":
                        System.out.println("\nCart contents:");
                        customer.getCartItems().forEach((pr,qty) ->
                                System.out.println(pr.getProductID() + " - " + pr.getName() + " x" + qty));
                        System.out.println("Total: $" + String.format("%.2f", customer.cartTotal()));
                        System.out.println("Unique items: " + customer.uniqueItemCount());
                        break;
                    case "4":
                        System.out.print("Enter product ID to remove: ");
                        String rid = sc.nextLine().trim();
                        Product rem = findProductById(catalog, rid);
                        if (rem == null) { System.out.println("Product not found."); break; }
                        customer.removeFromCart(rem);
                        System.out.println("Removed (if present).");
                        break;
                    case "5":
                        if (customer.getCartItems().isEmpty()) { System.out.println("Cart is empty."); break; }
                        Order order = new Order(customer, customer.getCartItems());
                        try {
                            order.placeOrder();
                            System.out.println("Order placed successfully:");
                            System.out.println(order.summary());
                            customer.clearCart();
                        } catch (IllegalStateException ex) {
                            System.out.println("Error placing order: " + ex.getMessage());
                        }
                        break;
                    case "6":
                        System.out.println("Goodbye!");
                        break mainLoop;
                    default:
                        System.out.println("Invalid option. Try again.");
                }
            } catch (NumberFormatException ex) {
                System.out.println("Invalid numeric input.");
            } catch (IllegalArgumentException ex) {
                System.out.println("Error: " + ex.getMessage());
            }
        }

        System.out.println("\nFinal product stocks:");
        catalog.forEach(System.out::println);
        sc.close();
    }

    private static Product findProductById(List<Product> catalog, String id) {
        return catalog.stream().filter(p -> p.getProductID().equalsIgnoreCase(id)).findFirst().orElse(null);
    }
}
