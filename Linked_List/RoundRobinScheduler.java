import java.util.Scanner;

class Process {
    int pid;
    int burstTime;
    int remainingTime;
    int priority;
    Process next;

    Process(int pid, int burstTime, int priority) {
        this.pid = pid;
        this.burstTime = burstTime;
        this.remainingTime = burstTime;
        this.priority = priority;
        this.next = null;
    }
}

class RoundRobin {
    Process head = null;
    Process tail = null;

    
    void addProcess(int pid, int burstTime, int priority) {
        Process newProcess = new Process(pid, burstTime, priority);

        if (head == null) {
            head = tail = newProcess;
            tail.next = head; 
        } else {
            tail.next = newProcess;
            newProcess.next = head;
            tail = newProcess;
        }
        System.out.println("Process " + pid + " added.");
    }

    void removeProcess(int pid) {
        if (head == null) {
            System.out.println("No processes to remove!");
            return;
        }

        Process temp = head;
        Process prev = tail;

        do {
            if (temp.pid == pid) {
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
                System.out.println("Process " + pid + " completed and removed.");
                return;
            }
            prev = temp;
            temp = temp.next;
        } while (temp != head);

        System.out.println("Process with ID " + pid + " not found!");
    }

    
    void displayProcesses() {
        if (head == null) {
            System.out.println("No processes in the queue.");
            return;
        }
        System.out.println("\nCurrent Process Queue:");
        Process temp = head;
        do {
            System.out.println("PID: " + temp.pid + ", Burst Time: " + temp.remainingTime + ", Priority: " + temp.priority);
            temp = temp.next;
        } while (temp != head);
    }

    
    void simulate(int quantum) {
        if (head == null) {
            System.out.println("No processes to schedule!");
            return;
        }

        int totalWaitingTime = 0;
        int totalTurnAroundTime = 0;
        int currentTime = 0;
        int processCount = 0;

        
        Process temp = head;
        do {
            processCount++;
            temp = temp.next;
        } while (temp != head);

        System.out.println("\n--- Starting Round Robin Scheduling ---");
        Process current = head;

        while (head != null) {
            if (current.remainingTime > 0) {
                int executeTime = Math.min(quantum, current.remainingTime);
                current.remainingTime -= executeTime;
                currentTime += executeTime;

                System.out.println("\nExecuting Process " + current.pid + " for " + executeTime + " units (Remaining: " + current.remainingTime + ")");
                displayProcesses();

                if (current.remainingTime == 0) {
                    totalTurnAroundTime += currentTime;
                    totalWaitingTime += (currentTime - current.burstTime);
                    int pidToRemove = current.pid;
                    current = current.next;
                    removeProcess(pidToRemove);

                    if (head == null) break;
                } else {
                    current = current.next;
                }
            } else {
                current = current.next;
            }
        }

        // Calculate and display averages
        double avgWaiting = (double) totalWaitingTime / processCount;
        double avgTurnAround = (double) totalTurnAroundTime / processCount;

        System.out.println("\nAll processes completed!");
        System.out.println("Average Waiting Time: " + avgWaiting);
        System.out.println("Average Turnaround Time: " + avgTurnAround);
    }
}

public class RoundRobinScheduler {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        RoundRobin rr = new RoundRobin();
        int choice;

        do {
            System.out.println("\n--- Round Robin Scheduling ---");
            System.out.println("1. Add Process");
            System.out.println("2. Remove Process");
            System.out.println("3. Display Processes");
            System.out.println("4. Simulate Scheduling");
            System.out.println("5. Exit");
            System.out.print("Enter choice: ");
            choice = sc.nextInt();

            switch (choice) {
                case 1:
                    System.out.print("Enter Process ID, Burst Time, and Priority: ");
                    rr.addProcess(sc.nextInt(), sc.nextInt(), sc.nextInt());
                    break;
                case 2:
                    System.out.print("Enter Process ID to Remove: ");
                    rr.removeProcess(sc.nextInt());
                    break;
                case 3:
                    rr.displayProcesses();
                    break;
                case 4:
                    System.out.print("Enter Time Quantum: ");
                    rr.simulate(sc.nextInt());
                    break;
                case 5:
                    System.out.println("Exiting...");
                    break;
                default:
                    System.out.println("Invalid choice!");
            }
        } while (choice != 5);

        sc.close();
    }
}
