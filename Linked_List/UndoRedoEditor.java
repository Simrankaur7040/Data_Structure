import java.util.Scanner;
class TextState {
    String content;
    TextState prev, next;

    TextState(String content) {
        this.content = content;
        this.prev = null;
        this.next = null;
    }
}

class TextEditor {
    TextState head, tail, current;
    int size = 0;
    final int MAX_HISTORY = 10;  

    void addState(String text) {
        TextState newState = new TextState(text);

        if (current != null && current.next != null) {
            current.next = null;
            tail = current;
        }

        if (head == null) {
            head = tail = current = newState;
        } else {
            tail.next = newState;
            newState.prev = tail;
            tail = newState;
            current = newState;
        }

        size++;

        
        if (size > MAX_HISTORY) {
            head = head.next;
            head.prev = null;
            size--;
        }

        System.out.println("State added successfully.");
    }

    void undo() {
        if (current != null && current.prev != null) {
            current = current.prev;
            System.out.println("Undo successful.");
        } else {
            System.out.println("No more undo available!");
        }
    }

    void redo() {
        if (current != null && current.next != null) {
            current = current.next;
            System.out.println("Redo successful.");
        } else {
            System.out.println("No more redo available!");
        }
    }

    void displayCurrent() {
        if (current == null)
            System.out.println("No text state available.");
        else
            System.out.println("Current Text: \"" + current.content + "\"");
    }


    void displayAllStates() {
        if (head == null) {
            System.out.println("No history available.");
            return;
        }

        System.out.println("\nAll States in History:");
        TextState temp = head;
        int i = 1;
        while (temp != null) {
            if (temp == current)
                System.out.println(i + ". [CURRENT] " + temp.content);
            else
                System.out.println(i + ". " + temp.content);
            temp = temp.next;
            i++;
        }
    }
}

public class UndoRedoEditor {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        TextEditor editor = new TextEditor();
        int choice;
        String text;

        do {
            System.out.println("\n--- Text Editor (Undo/Redo using Doubly Linked List) ---");
            System.out.println("1. Type or Add New Text");
            System.out.println("2. Undo");
            System.out.println("3. Redo");
            System.out.println("4. Display Current Text");
            System.out.println("5. Show All States");
            System.out.println("6. Exit");
            System.out.print("Enter your choice: ");
            choice = sc.nextInt();
            sc.nextLine();

            switch (choice) {
                case 1:
                    System.out.print("Enter new text: ");
                    text = sc.nextLine();
                    editor.addState(text);
                    break;
                case 2:
                    editor.undo();
                    break;
                case 3:
                    editor.redo();
                    break;
                case 4:
                    editor.displayCurrent();
                    break;
                case 5:
                    editor.displayAllStates();
                    break;
                case 6:
                    System.out.println("Exiting...");
                    break;
                default:
                    System.out.println("Invalid choice!");
            }
        } while (choice != 6);

        sc.close();
    }
}
