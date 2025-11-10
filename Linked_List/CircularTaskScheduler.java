import java.util.Scanner;

class Task {
    int id;
    String name;
    String priority;
    String dueDate;
    Task next;

    Task(int id, String name, String priority, String dueDate) {
        this.id = id;
        this.name = name;
        this.priority = priority;
        this.dueDate = dueDate;
        this.next = null;
    }
}

class TaskScheduler {
    Task head = null;
    Task tail = null;
    Task current = null; 

    
    void addAtBeginning(int id, String name, String priority, String dueDate) {
        Task newTask = new Task(id, name, priority, dueDate);
        if (head == null) {
            head = tail = newTask;
            tail.next = head; 
        } else {
            newTask.next = head;
            head = newTask;
            tail.next = head; 
        }
        System.out.println("Task added at beginning!");
    }

 
    void addAtEnd(int id, String name, String priority, String dueDate) {
        Task newTask = new Task(id, name, priority, dueDate);
        if (head == null) {
            head = tail = newTask;
            tail.next = head;
        } else {
            tail.next = newTask;
            tail = newTask;
            tail.next = head; 
        }
        System.out.println("Task added at end!");
    }

    
    void addAtPosition(int pos, int id, String name, String priority, String dueDate) {
        Task newTask = new Task(id, name, priority, dueDate);

        if (head == null || pos == 1) {
            addAtBeginning(id, name, priority, dueDate);
            return;
        }

        Task temp = head;
        int count = 1;

        while (count < pos - 1 && temp.next != head) {
            temp = temp.next;
            count++;
        }

        newTask.next = temp.next;
        temp.next = newTask;

        if (temp == tail)
            tail = newTask;

        tail.next = head;
        System.out.println("Task added at position " + pos + "!");
    }

    
    void removeById(int id) {
        if (head == null) {
            System.out.println("No tasks to remove!");
            return;
        }

        Task temp = head;
        Task prev = tail;

        do {
            if (temp.id == id) {
            if (temp == head && temp == tail) {
                    head = tail = null;
                } else if (temp == head) {
                    head = head.next;
                    tail.next = head;
                } else if (temp == tail) {
                    tail = prev;
                    tail.next = head;
                } else {
                    prev.next = temp.next;
                }
                System.out.println("Task with ID " + id + " removed!");
                return;
            }
            prev = temp;
            temp = temp.next;
        } while (temp != head);

        System.out.println("Task with ID " + id + " not found!");
    }

    
    void viewAndMoveNext() {
        if (head == null) {
            System.out.println("No tasks to view!");
            return;
        }

        if (current == null)
            current = head;

        System.out.println("\nCurrent Task:");
        System.out.println("ID: " + current.id + ", Name: " + current.name +
                           ", Priority: " + current.priority + ", Due: " + current.dueDate);

        current = current.next; 
    }

    
    void displayAll() {
        if (head == null) {
            System.out.println("No tasks in the list!");
            return;
        }

        System.out.println("\n--- Task List ---");
        Task temp = head;
        do {
            System.out.println("ID: " + temp.id + ", Name: " + temp.name +
                               ", Priority: " + temp.priority + ", Due: " + temp.dueDate);
            temp = temp.next;
        } while (temp != head);
    }

    
    void searchByPriority(String priority) {
        if (head == null) {
            System.out.println("No tasks available!");
            return;
        }

        boolean found = false;
        Task temp = head;
        do {
            if (temp.priority.equalsIgnoreCase(priority)) {
                System.out.println("Task Found → ID: " + temp.id + ", Name: " + temp.name +
                                   ", Due: " + temp.dueDate);
                found = true;
            }
            temp = temp.next;
        } while (temp != head);

        if (!found)
            System.out.println("No tasks found with priority: " + priority);
    }
}

public class CircularTaskScheduler {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        TaskScheduler scheduler = new TaskScheduler();
        int choice;

        do {
            System.out.println("\n--- Task Scheduler ---");
            System.out.println("1. Add Task at Beginning");
            System.out.println("2. Add Task at End");
            System.out.println("3. Add Task at Specific Position");
            System.out.println("4. Remove Task by ID");
            System.out.println("5. View Current Task & Move to Next");
            System.out.println("6. Display All Tasks");
            System.out.println("7. Search Task by Priority");
            System.out.println("8. Exit");
            System.out.print("Enter your choice: ");
            choice = sc.nextInt();
            sc.nextLine(); // consume newline

            switch (choice) {
                case 1:
                    System.out.print("Enter ID, Name, Priority, Due Date: ");
                    scheduler.addAtBeginning(sc.nextInt(), sc.next(), sc.next(), sc.next());
                    break;
                case 2:
                    System.out.print("Enter ID, Name, Priority, Due Date: ");
                    scheduler.addAtEnd(sc.nextInt(), sc.next(), sc.next(), sc.next());
                    break;
                case 3:
                    System.out.print("Enter Position: ");
                    int pos = sc.nextInt();
                    System.out.print("Enter ID, Name, Priority, Due Date: ");
                    scheduler.addAtPosition(pos, sc.nextInt(), sc.next(), sc.next(), sc.next());
                    break;
                case 4:
                    System.out.print("Enter Task ID to Remove: ");
                    scheduler.removeById(sc.nextInt());
                    break;
                case 5:
                    scheduler.viewAndMoveNext();
                    break;
                case 6:
                    scheduler.displayAll();
                    break;
                case 7:
                    System.out.print("Enter Priority to Search: ");
                    scheduler.searchByPriority(sc.next());
                    break;
                case 8:
                    System.out.println("Exiting...");
                    break;
                default:
                    System.out.println("Invalid choice! Try again.");
            }
        } while (choice != 8);

        sc.close();
    }
}
