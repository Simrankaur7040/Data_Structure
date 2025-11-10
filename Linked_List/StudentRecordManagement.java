import java.util.Scanner;

class Student {
    int rollNo;
    String name;
    int age;
    char grade;
    Student next;

    Student(int rollNo, String name, int age, char grade) {
        this.rollNo = rollNo;
        this.name = name;
        this.age = age;
        this.grade = grade;
        this.next = null;
    }
}

class StudentList {
    Student head = null;
    Student tail = null;

    void addAtBeginning(int rollNo, String name, int age, char grade) {
        Student newStudent = new Student(rollNo, name, age, grade);

        if (head == null) {
            head = newStudent;
            tail = newStudent;
        } else {
            newStudent.next = head;
            head = newStudent;
        }
    }

    void addAtEnd(int rollNo, String name, int age, char grade) {
        Student newStudent = new Student(rollNo, name, age, grade);

        if (head == null) {
            head = newStudent;
            tail = newStudent;
        } else {
            tail.next = newStudent;
            tail = newStudent;
        }
    }

    void addAtPosition(int pos, int rollNo, String name, int age, char grade) {
        Student newStudent = new Student(rollNo, name, age, grade);
        if (pos == 1) {
            newStudent.next = head;
            head = newStudent;
            if (tail == null) tail = newStudent;
            return;
        }

        Student temp = head;
        for (int i = 1; i < pos - 1 && temp != null; i++) {
            temp = temp.next;
        }

        newStudent.next = temp.next;
        temp.next = newStudent;

        if (newStudent.next == null)
            tail = newStudent;
    }

    void deleteByRoll(int rollNo) {
        if (head == null) {
            System.out.println("List is empty!");
            return;
        }

        if (head.rollNo == rollNo) {
            head = head.next;
            if (head == null) tail = null;
            System.out.println("Student deleted successfully!");
            return;
        }

        Student temp = head;
        while (temp.next != null && temp.next.rollNo != rollNo) {
            temp = temp.next;
        }

        if (temp.next == null) {
            System.out.println("Student with Roll No " + rollNo + " not found!");
        } else {
            temp.next = temp.next.next;
            if (temp.next == null) tail = temp;
            System.out.println("Student deleted successfully!");
        }
    }

    void searchByRoll(int rollNo) {
        Student temp = head;
        while (temp != null) {
            if (temp.rollNo == rollNo) {
                System.out.println("Student Found:");
                System.out.println("Roll No: " + temp.rollNo + ", Name: " + temp.name + ", Age: " + temp.age + ", Grade: " + temp.grade);
                return;
            }
            temp = temp.next;
        }
        System.out.println("Student with Roll No " + rollNo + " not found!");
    }

    void updateGrade(int rollNo, char newGrade) {
        Student temp = head;
        while (temp != null) {
            if (temp.rollNo == rollNo) {
                temp.grade = newGrade;
                System.out.println("Grade updated successfully!");
                return;
            }
            temp = temp.next;
        }
        System.out.println("Student with Roll No " + rollNo + " not found!");
    }

    void displayAll() {
        if (head == null) {
            System.out.println("No records to display!");
            return;
        }

        System.out.println("\nStudent Records:");
        Student temp = head;
        while (temp != null) {
            System.out.println("Roll No: " + temp.rollNo + ", Name: " + temp.name + ", Age: " + temp.age + ", Grade: " + temp.grade);
            temp = temp.next;
        }
    }
}

public class StudentRecordManagement {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        StudentList list = new StudentList();
        int choice;

        do {
            System.out.println("\n--- Student Record Management ---");
            System.out.println("1. Add Student at Beginning");
            System.out.println("2. Add Student at End");
            System.out.println("3. Add Student at Specific Position");
            System.out.println("4. Delete Student by Roll No");
            System.out.println("5. Search Student by Roll No");
            System.out.println("6. Update Grade");
            System.out.println("7. Display All Students");
            System.out.println("8. Exit");
            System.out.print("Enter your choice: ");
            choice = sc.nextInt();

            switch (choice) {
                case 1:
                    System.out.print("Enter Roll No, Name, Age, Grade: ");
                    list.addAtBeginning(sc.nextInt(), sc.next(), sc.nextInt(), sc.next().charAt(0));
                    break;
                case 2:
                    System.out.print("Enter Roll No, Name, Age, Grade: ");
                    list.addAtEnd(sc.nextInt(), sc.next(), sc.nextInt(), sc.next().charAt(0));
                    break;
                case 3:
                    System.out.print("Enter Position: ");
                    int pos = sc.nextInt();
                    System.out.print("Enter Roll No, Name, Age, Grade: ");
                    list.addAtPosition(pos, sc.nextInt(), sc.next(), sc.nextInt(), sc.next().charAt(0));
                    break;
                case 4:
                    System.out.print("Enter Roll No to Delete: ");
                    list.deleteByRoll(sc.nextInt());
                    break;
                case 5:
                    System.out.print("Enter Roll No to Search: ");
                    list.searchByRoll(sc.nextInt());
                    break;
                case 6:
                    System.out.print("Enter Roll No and New Grade: ");
                    list.updateGrade(sc.nextInt(), sc.next().charAt(0));
                    break;
                case 7:
                    list.displayAll();
                    break;
                case 8:
                    System.out.println("Exiting...");
                    break;
                default:
                    System.out.println("Invalid choice!");
            }
        } while (choice != 8);

        sc.close();
    }
}
