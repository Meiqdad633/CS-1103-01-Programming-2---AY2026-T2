// Generic Library Catalog Assignment (Funny Edition)
// Author: Student
// Date: December 2025
// Warning: May contain bad librarian jokes.

import java.util.*;

// Generic LibraryItem class
class LibraryItem<T> {
    private String title;
    private String author;
    private String itemID;
    private T type; // e.g., Book, DVD, Magazine

    public LibraryItem(String title, String author, String itemID, T type) {
        this.title = title;
        this.author = author;
        this.itemID = itemID;
        this.type = type;
    }

    public String getTitle() { return title; }
    public String getAuthor() { return author; }
    public String getItemID() { return itemID; }
    public T getType() { return type; }

    @Override
    public String toString() {
        return "📖 ItemID: " + itemID + " | Title: " + title + " | Author: " + author + " | Type: " + type;
    }
}

// Generic Catalog class
class Catalog<T> {
    private Map<String, LibraryItem<T>> items;

    public Catalog() {
        items = new HashMap<>();
    }

    // Add item
    public void addItem(LibraryItem<T> item) {
        items.put(item.getItemID(), item);
        System.out.println("✅ Added: " + item.getTitle() + " (Don’t dog-ear the pages!)");
    }

    // Remove item
    public void removeItem(String itemID) {
        if (items.containsKey(itemID)) {
            LibraryItem<T> removed = items.remove(itemID);
            System.out.println("❌ Removed: " + removed.getTitle() + " (Hope you returned it on time!)");
        } else {
            System.out.println("⚠️ Error: Item with ID " + itemID + " does not exist. Maybe it’s hiding under your bed?");
        }
    }

    // Retrieve item
    public void getItem(String itemID) {
        if (items.containsKey(itemID)) {
            System.out.println("🔎 Found it! " + items.get(itemID));
        } else {
            System.out.println("🤷 Sorry, item not found. Did you check the lost-and-found box?");
        }
    }

    // Display catalog
    public void displayCatalog() {
        if (items.isEmpty()) {
            System.out.println("📂 Catalog is empty. The librarian is crying softly in the corner.");
        } else {
            System.out.println("📚 Current Catalog (Shhh… quiet please!):");
            for (LibraryItem<T> item : items.values()) {
                System.out.println(item);
            }
        }
    }
}

// Main class with simple command-line interface
public class LibraryCatalogApp {
    public static void main(String[] args) {
        Catalog<String> catalog = new Catalog<>();
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("\n--- Library Catalog Menu ---");
            System.out.println("1. Add Item 📥");
            System.out.println("2. Remove Item 🗑️");
            System.out.println("3. View Item 🔎");
            System.out.println("4. Display Catalog 📚");
            System.out.println("5. Exit 🚪");
            System.out.print("Choose an option: ");

            int choice = scanner.nextInt();
            scanner.nextLine(); // consume newline

            switch (choice) {
                case 1:
                    System.out.print("Enter Item ID: ");
                    String id = scanner.nextLine();
                    System.out.print("Enter Title: ");
                    String title = scanner.nextLine();
                    System.out.print("Enter Author: ");
                    String author = scanner.nextLine();
                    System.out.print("Enter Type (Book/DVD/Magazine): ");
                    String type = scanner.nextLine();

                    LibraryItem<String> item = new LibraryItem<>(title, author, id, type);
                    catalog.addItem(item);
                    break;

                case 2:
                    System.out.print("Enter Item ID to remove: ");
                    String removeID = scanner.nextLine();
                    catalog.removeItem(removeID);
                    break;

                case 3:
                    System.out.print("Enter Item ID to view: ");
                    String viewID = scanner.nextLine();
                    catalog.getItem(viewID);
                    break;

                case 4:
                    catalog.displayCatalog();
                    break;

                case 5:
                    System.out.println("👋 Exiting Library Catalog. Don’t forget to return your books!");
                    scanner.close();
                    return;

                default:
                    System.out.println("⚠️ Invalid choice. The librarian gives you a stern look.");
            }
        }
    }
}
