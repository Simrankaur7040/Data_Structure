import java.util.Scanner;

class Book {
    int bookId;
    String title;
    String author;
    String genre;
    boolean available;
    Book next;
    Book prev;

    Book(int bookId, String title, String author, String genre, boolean available) {
        this.bookId = bookId;
        this.title = title;
        this.author = author;
        this.genre = genre;
        this.available = available;
        this.next = null;
        this.prev = null;
    }
}

class Library {
    Book head = null;
    Book tail = null;

    // 1️⃣ Add book at beginning
    void addAtBeginning(int id, String title, String author, String genre, boolean available) {
        Book newBook = new Book(id, title, author, genre, available);
        if (head == null) {
            head = tail = newBook;
        } else {
            newBook.next = head;
            head.prev = newBook;
            head = newBook;
        }
        System.out.println("Book added at beginning!");
    }

    // 2️⃣ Add book at end
    void addAtEnd(int id, String title, String author, String genre, boolean available) {
        Book newBook = new Book(id, title, author, genre, available);
        if (head == null) {
            head = tail = newBook;
        } else {
            tail.next = newBook;
            newBook.prev = tail;
            tail = newBook;
        }
        System.out.println("Book added at end!");
    }

    // 3️⃣ Add book at a specific position
    void addAtPosition(int pos, int id, String title, String author, String genre, boolean available) {
        Book newBook = new Book(id, title, author, genre, available);

        if (head == null || pos <= 1) {
            addAtBeginning(id, title, author, genre, available);
            return;
        }

        Book temp = head;
        int count = 1;
        while (count < pos - 1 && temp.next != null) {
            temp = temp.next;
            count++;
        }

        if (temp.next == null) {
            addAtEnd(id, title, author, genre, available);
            return;
        }

        newBook.next = temp.next;
        newBook.prev = temp;
        temp.next.prev = newBook;
        temp.next = newBook;

        System.out.println("Book added at position " + pos + "!");
    }

    // 4️⃣ Remove a book by Book ID
    void removeById(int id) {
        if (head == null) {
            System.out.println("Library is empty!");
            return;
        }

        Book temp = head;
        while (temp != null && temp.bookId != id) {
            temp = temp.next;
        }

        if (temp == null) {
            System.out.println("Book with ID " + id + " not found!");
            return;
        }

        if (temp == head) {
            head = head.next;
            if (head != null) head.prev = null;
        } else if (temp == tail) {
            tail = tail.prev;
            tail.next = null;
        } else {
            temp.prev.next = temp.next;
            temp.next.prev = temp.prev;
        }

        System.out.println("Book with ID " + id + " removed!");
    }

    // 5️⃣ Search for a book by Title or Author
    void searchBook(String key) {
        if (head == null) {
            System.out.println("Library is empty!");
            return;
        }

        boolean found = false;
        Book temp = head;
        while (temp != null) {
            if (temp.title.equalsIgnoreCase(key) || temp.author.equalsIgnoreCase(key)) {
                System.out.println("\nBook Found:");
                System.out.println("ID: " + temp.bookId + ", Title: " + temp.title +
                                   ", Author: " + temp.author + ", Genre: " + temp.genre +
                                   ", Available: " + (temp.available ? "Yes" : "No"));
                found = true;
            }
            temp = temp.next;
        }

        if (!found)
            System.out.println("No book found with Title/Author: " + key);
    }

    // 6️⃣ Update a book's availability status
    void updateAvailability(int id, boolean status) {
        Book temp = head;
        while (temp != null) {
            if (temp.bookId == id) {
                temp.available = status;
                System.out.println("Availability updated for Book ID " + id);
                return;
            }
            temp = temp.next;
        }
        System.out.println("Book ID not found!");
    }

    // 7️⃣ Display all books (forward)
    void displayForward() {
        if (head == null) {
            System.out.println("Library is empty!");
            return;
        }

        System.out.println("\n--- Library Books (Forward) ---");
        Book temp = head;
        while (temp != null) {
            System.out.println("ID: " + temp.bookId + ", Title: " + temp.title +
                               ", Author: " + temp.author + ", Genre: " + temp.genre +
                               ", Available: " + (temp.available ? "Yes" : "No"));
            temp = temp.next;
        }
    }

    // 8️⃣ Display all books (reverse)
    void displayReverse() {
        if (tail == null) {
            System.out.println("Library is empty!");
            return;
        }

        System.out.println("\n--- Library Books (Reverse) ---");
        Book temp = tail;
        while (temp != null) {
            System.out.println("ID: " + temp.bookId + ", Title: " + temp.title +
                               ", Author: " + temp.author + ", Genre: " + temp.genre +
                               ", Available: " + (temp.available ? "Yes" : "No"));
            temp = temp.prev;
        }
    }

    // 9️⃣ Count total books
    void countBooks() {
        int count = 0;
        Book temp = head;
        while (temp != null) {
            count++;
            temp = temp.next;
        }
        System.out.println("Total number of books in the library: " + count);
    }
}

public class LibraryManagementSystem {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        Library lib = new Library();
        int choice;

        do {
            System.out.println("\n--- Library Management System ---");
            System.out.println("1. Add Book at Beginning");
            System.out.println("2. Add Book at End");
            System.out.println("3. Add Book at Position");
            System.out.println("4. Remove Book by ID");
            System.out.println("5. Search Book by Title/Author");
            System.out.println("6. Update Book Availability");
            System.out.println("7. Display Books (Forward)");
            System.out.println("8. Display Books (Reverse)");
            System.out.println("9. Count Total Books");
            System.out.println("10. Exit");
            System.out.print("Enter your choice: ");
            choice = sc.nextInt();
            sc.nextLine(); // consume newline

            switch (choice) {
                case 1:
                    System.out.print("Enter ID, Title, Author, Genre, Available(true/false): ");
                    lib.addAtBeginning(sc.nextInt(), sc.next(), sc.next(), sc.next(), sc.nextBoolean());
                    break;
                case 2:
                    System.out.print("Enter ID, Title, Author, Genre, Available(true/false): ");
                    lib.addAtEnd(sc.nextInt(), sc.next(), sc.next(), sc.next(), sc.nextBoolean());
                    break;
                case 3:
                    System.out.print("Enter Position: ");
                    int pos = sc.nextInt();
                    System.out.print("Enter ID, Title, Author, Genre, Available(true/false): ");
                    lib.addAtPosition(pos, sc.nextInt(), sc.next(), sc.next(), sc.next(), sc.nextBoolean());
                    break;
                case 4:
                    System.out.print("Enter Book ID to Remove: ");
                    lib.removeById(sc.nextInt());
                    break;
                case 5:
                    System.out.print("Enter Book Title or Author to Search: ");
                    lib.searchBook(sc.next());
                    break;
                case 6:
                    System.out.print("Enter Book ID and Availability (true/false): ");
                    lib.updateAvailability(sc.nextInt(), sc.nextBoolean());
                    break;
                case 7:
                    lib.displayForward();
                    break;
                case 8:
                    lib.displayReverse();
                    break;
                case 9:
                    lib.countBooks();
                    break;
                case 10:
                    System.out.println("Exiting...");
                    break;
                default:
                    System.out.println("Invalid choice! Try again.");
            }
        } while (choice != 10);

        sc.close();
    }
}
