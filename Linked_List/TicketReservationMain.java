import java.util.Scanner;

class Ticket {
    int ticketID;
    String customerName;
    String movieName;
    String seatNumber;
    String bookingTime;
    Ticket next;

    Ticket(int ticketID, String customerName, String movieName, String seatNumber, String bookingTime) {
        this.ticketID = ticketID;
        this.customerName = customerName;
        this.movieName = movieName;
        this.seatNumber = seatNumber;
        this.bookingTime = bookingTime;
        this.next = null;
    }
}

class TicketReservationSystem {
    Ticket head = null;
    Ticket tail = null;

    void addTicket(int id, String name, String movie, String seat, String time) {
        Ticket newTicket = new Ticket(id, name, movie, seat, time);

        if (head == null) {
            head = tail = newTicket;
            newTicket.next = head;  
        } else {
            tail.next = newTicket;
            newTicket.next = head;
            tail = newTicket;
        }
        System.out.println("Ticket booked successfully!");
    }

    
    void removeTicket(int id) {
        if (head == null) {
            System.out.println("No tickets booked yet!");
            return;
        }

        Ticket current = head;
        Ticket prev = tail;
        boolean found = false;

        do {
            if (current.ticketID == id) {
                found = true;
                if (current == head) {  
                    if (head == tail) {
                        head = tail = null;
                    } else {
                        head = head.next;
                        tail.next = head;
                    }
                } else if (current == tail) {  
                    tail = prev;
                    tail.next = head;
                } else {  
                    prev.next = current.next;
                }
                System.out.println("Ticket with ID " + id + " removed successfully!");
                return;
            }
            prev = current;
            current = current.next;
        } while (current != head);

        if (!found) {
            System.out.println("Ticket ID " + id + " not found!");
        }
    }

    void displayTickets() {
        if (head == null) {
            System.out.println("No tickets booked yet!");
            return;
        }

        Ticket temp = head;
        System.out.println("\n--- Current Booked Tickets ---");
        do {
            System.out.println("Ticket ID: " + temp.ticketID + 
                               ", Customer: " + temp.customerName +
                               ", Movie: " + temp.movieName +
                               ", Seat: " + temp.seatNumber +
                               ", Time: " + temp.bookingTime);
            temp = temp.next;
        } while (temp != head);
    }

    void searchTicket(String keyword) {
        if (head == null) {
            System.out.println("No tickets booked yet!");
            return;
        }

        boolean found = false;
        Ticket temp = head;

        System.out.println("\nSearch results for: " + keyword);
        do {
            if (temp.customerName.equalsIgnoreCase(keyword) || temp.movieName.equalsIgnoreCase(keyword)) {
                System.out.println("Ticket ID: " + temp.ticketID + 
                                   ", Customer: " + temp.customerName +
                                   ", Movie: " + temp.movieName +
                                   ", Seat: " + temp.seatNumber +
                                   ", Time: " + temp.bookingTime);
                found = true;
            }
            temp = temp.next;
        } while (temp != head);

        if (!found) {
            System.out.println("No matching tickets found!");
        }
    }

    
    void totalTickets() {
        if (head == null) {
            System.out.println("No tickets booked yet!");
            return;
        }

        int count = 0;
        Ticket temp = head;
        do {
            count++;
            temp = temp.next;
        } while (temp != head);

        System.out.println("Total number of booked tickets: " + count);
    }
}


public class TicketReservationMain {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        TicketReservationSystem system = new TicketReservationSystem();
        int choice;

        do {
            System.out.println("\n--- Online Ticket Reservation System (Circular Linked List) ---");
            System.out.println("1. Add Ticket Reservation");
            System.out.println("2. Remove Ticket by ID");
            System.out.println("3. Display All Tickets");
            System.out.println("4. Search Ticket by Customer or Movie");
            System.out.println("5. Show Total Booked Tickets");
            System.out.println("6. Exit");
            System.out.print("Enter your choice: ");
            choice = sc.nextInt();
            sc.nextLine(); 

            switch (choice) {
                case 1:
                    System.out.print("Enter Ticket ID: ");
                    int id = sc.nextInt();
                    sc.nextLine();
                    System.out.print("Enter Customer Name: ");
                    String name = sc.nextLine();
                    System.out.print("Enter Movie Name: ");
                    String movie = sc.nextLine();
                    System.out.print("Enter Seat Number: ");
                    String seat = sc.nextLine();
                    System.out.print("Enter Booking Time: ");
                    String time = sc.nextLine();
                    system.addTicket(id, name, movie, seat, time);
                    break;

                case 2:
                    System.out.print("Enter Ticket ID to remove: ");
                    int rid = sc.nextInt();
                    system.removeTicket(rid);
                    break;

                case 3:
                    system.displayTickets();
                    break;

                case 4:
                    System.out.print("Enter Customer Name or Movie Name to search: ");
                    String key = sc.nextLine();
                    system.searchTicket(key);
                    break;

                case 5:
                    system.totalTickets();
                    break;

                case 6:
                    System.out.println("Exiting system...");
                    break;

                default:
                    System.out.println("Invalid choice!");
            }
        } while (choice != 6);

        sc.close();
    }
}
