import java.util.Scanner;

class Item {
    String name;
    int id;
    int quantity;
    double price;
    Item next;

    Item(String name, int id, int quantity, double price) {
        this.name = name;
        this.id = id;
        this.quantity = quantity;
        this.price = price;
        this.next = null;
    }
}

class Inventory {
    Item head = null;


    void addAtBeginning(String name, int id, int quantity, double price) {
        Item newItem = new Item(name, id, quantity, price);
        newItem.next = head;
        head = newItem;
        System.out.println("Item added at the beginning!");
    }

    
    void addAtEnd(String name, int id, int quantity, double price) {
        Item newItem = new Item(name, id, quantity, price);
        if (head == null) {
            head = newItem;
        } else {
            Item temp = head;
            while (temp.next != null)
                temp = temp.next;
            temp.next = newItem;
        }
        System.out.println("Item added at the end!");
    }

    
    void addAtPosition(int pos, String name, int id, int quantity, double price) {
        Item newItem = new Item(name, id, quantity, price);
        if (pos == 1 || head == null) {
            newItem.next = head;
            head = newItem;
            System.out.println("Item added at position " + pos);
            return;
        }
        Item temp = head;
        for (int i = 1; i < pos - 1 && temp.next != null; i++)
            temp = temp.next;

        newItem.next = temp.next;
        temp.next = newItem;
        System.out.println("Item added at position " + pos);
    }

    
    void removeById(int id) {
        if (head == null) {
            System.out.println("Inventory is empty!");
            return;
        }
        if (head.id == id) {
            head = head.next;
            System.out.println("Item removed successfully!");
            return;
        }
        Item temp = head;
        while (temp.next != null && temp.next.id != id)
            temp = temp.next;

        if (temp.next == null)
            System.out.println("Item ID not found!");
        else {
            temp.next = temp.next.next;
            System.out.println("Item removed successfully!");
        }
    }

    
    void updateQuantity(int id, int newQty) {
        Item temp = head;
        while (temp != null) {
            if (temp.id == id) {
                temp.quantity = newQty;
                System.out.println("Quantity updated successfully!");
                return;
            }
            temp = temp.next;
        }
        System.out.println("Item ID not found!");
    }

    
    void searchById(int id) {
        Item temp = head;
        while (temp != null) {
            if (temp.id == id) {
                System.out.println("Item Found → " + temp.name + " | Qty: " + temp.quantity + " | Price: " + temp.price);
                return;
            }
            temp = temp.next;
        }
        System.out.println("Item with ID " + id + " not found!");
    }

    
    void searchByName(String name) {
        Item temp = head;
        while (temp != null) {
            if (temp.name.equalsIgnoreCase(name)) {
                System.out.println("Item Found → ID: " + temp.id + " | Qty: " + temp.quantity + " | Price: " + temp.price);
                return;
            }
            temp = temp.next;
        }
        System.out.println("Item with name '" + name + "' not found!");
    }

    
    void displayAll() {
        if (head == null) {
            System.out.println("Inventory is empty!");
            return;
        }
        System.out.println("\n--- Inventory List ---");
        Item temp = head;
        while (temp != null) {
            System.out.println("ID: " + temp.id + ", Name: " + temp.name + ", Qty: " + temp.quantity + ", Price: " + temp.price);
            temp = temp.next;
        }
    }

    
    void calculateTotalValue() {
        double total = 0;
        Item temp = head;
        while (temp != null) {
            total += temp.quantity * temp.price;
            temp = temp.next;
        }
        System.out.println("💰 Total Inventory Value: " + total);
    }


    void sortByName(boolean ascending) {
        if (head == null) return;
        for (Item i = head; i != null; i = i.next) {
            for (Item j = i.next; j != null; j = j.next) {
                if ((ascending && i.name.compareToIgnoreCase(j.name) > 0) ||
                    (!ascending && i.name.compareToIgnoreCase(j.name) < 0)) {
                    swap(i, j);
                }
            }
        }
        System.out.println("Inventory sorted by name (" + (ascending ? "ASC" : "DESC") + ")");
    }

    
    void sortByPrice(boolean ascending) {
        if (head == null) return;
        for (Item i = head; i != null; i = i.next) {
            for (Item j = i.next; j != null; j = j.next) {
                if ((ascending && i.price > j.price) || (!ascending && i.price < j.price)) {
                    swap(i, j);
                }
            }
        }
        System.out.println("Inventory sorted by price (" + (ascending ? "ASC" : "DESC") + ")");
    }

    
    void swap(Item a, Item b) {
        String tempName = a.name;
        int tempId = a.id;
        int tempQty = a.quantity;
        double tempPrice = a.price;

        a.name = b.name;
        a.id = b.id;
        a.quantity = b.quantity;
        a.price = b.price;

        b.name = tempName;
        b.id = tempId;
        b.quantity = tempQty;
        b.price = tempPrice;
    }
}

public class InventoryManagement {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        Inventory inv = new Inventory();
        int choice;

        do {
            System.out.println("\n--- Inventory Management ---");
            System.out.println("1. Add Item at Beginning");
            System.out.println("2. Add Item at End");
            System.out.println("3. Add Item at Specific Position");
            System.out.println("4. Remove Item by ID");
            System.out.println("5. Update Quantity");
            System.out.println("6. Search by ID");
            System.out.println("7. Search by Name");
            System.out.println("8. Display All Items");
            System.out.println("9. Calculate Total Inventory Value");
            System.out.println("10. Sort by Name");
            System.out.println("11. Sort by Price");
            System.out.println("12. Exit");
            System.out.print("Enter choice: ");
            choice = sc.nextInt();
            sc.nextLine(); 

            switch (choice) {
                case 1:
                    System.out.print("Enter Name, ID, Quantity, Price: ");
                    inv.addAtBeginning(sc.next(), sc.nextInt(), sc.nextInt(), sc.nextDouble());
                    break;
                case 2:
                    System.out.print("Enter Name, ID, Quantity, Price: ");
                    inv.addAtEnd(sc.next(), sc.nextInt(), sc.nextInt(), sc.nextDouble());
                    break;
                case 3:
                    System.out.print("Enter Position: ");
                    int pos = sc.nextInt();
                    System.out.print("Enter Name, ID, Quantity, Price: ");
                    inv.addAtPosition(pos, sc.next(), sc.nextInt(), sc.nextInt(), sc.nextDouble());
                    break;
                case 4:
                    System.out.print("Enter Item ID to Remove: ");
                    inv.removeById(sc.nextInt());
                    break;
                case 5:
                    System.out.print("Enter Item ID and New Quantity: ");
                    inv.updateQuantity(sc.nextInt(), sc.nextInt());
                    break;
                case 6:
                    System.out.print("Enter Item ID: ");
                    inv.searchById(sc.nextInt());
                    break;
                case 7:
                    System.out.print("Enter Item Name: ");
                    inv.searchByName(sc.next());
                    break;
                case 8:
                    inv.displayAll();
                    break;
                case 9:
                    inv.calculateTotalValue();
                    break;
                case 10:
                    System.out.print("Sort Ascending (true/false): ");
                    inv.sortByName(sc.nextBoolean());
                    break;
                case 11:
                    System.out.print("Sort Ascending (true/false): ");
                    inv.sortByPrice(sc.nextBoolean());
                    break;
                case 12:
                    System.out.println("Exiting...");
                    break;
                default:
                    System.out.println("Invalid choice!");
            }
        } while (choice != 12);

        sc.close();
    }
}
