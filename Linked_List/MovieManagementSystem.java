import java.util.Scanner;

class Movie {
    String title;
    String director;
    int year;
    double rating;
    Movie next;
    Movie prev;

    Movie(String title, String director, int year, double rating) {
        this.title = title;
        this.director = director;
        this.year = year;
        this.rating = rating;
        this.next = null;
        this.prev = null;
    }
}

class MovieList {
    Movie head = null;
    Movie tail = null;

    
    void addAtBeginning(String title, String director, int year, double rating) {
        Movie newMovie = new Movie(title, director, year, rating);
        if (head == null) {
            head = tail = newMovie;
        } else {
            newMovie.next = head;
            head.prev = newMovie;
            head = newMovie;
        }
        System.out.println("Movie added at beginning successfully!");
    }

    
    void addAtEnd(String title, String director, int year, double rating) {
        Movie newMovie = new Movie(title, director, year, rating);
        if (head == null) {
            head = tail = newMovie;
        } else {
            tail.next = newMovie;
            newMovie.prev = tail;
            tail = newMovie;
        }
        System.out.println("Movie added at end successfully!");
    }

    
    void addAtPosition(int pos, String title, String director, int year, double rating) {
        Movie newMovie = new Movie(title, director, year, rating);
        if (pos == 1) {
            addAtBeginning(title, director, year, rating);
            return;
        }

        Movie temp = head;
        for (int i = 1; i < pos - 1 && temp != null; i++) {
            temp = temp.next;
        }

        if (temp == null) {
            System.out.println("Position out of range!");
            return;
        }

        newMovie.next = temp.next;
        newMovie.prev = temp;

        if (temp.next != null)
            temp.next.prev = newMovie;
        else
            tail = newMovie;

        temp.next = newMovie;

        System.out.println("Movie added at position " + pos + " successfully!");
    }

    
    void removeByTitle(String title) {
        if (head == null) {
            System.out.println("List is empty!");
            return;
        }

        Movie temp = head;

        while (temp != null && !temp.title.equalsIgnoreCase(title)) {
            temp = temp.next;
        }

        if (temp == null) {
            System.out.println("Movie with title \"" + title + "\" not found!");
            return;
        }

        if (temp == head && temp == tail) {
            head = tail = null;
        } else if (temp == head) {
            head = head.next;
            head.prev = null;
        } else if (temp == tail) {
            tail = tail.prev;
            tail.next = null;
        } else {
            temp.prev.next = temp.next;
            temp.next.prev = temp.prev;
        }

        System.out.println("Movie \"" + title + "\" removed successfully!");
    }

    
    void searchByDirector(String director) {
        Movie temp = head;
        boolean found = false;
        while (temp != null) {
            if (temp.director.equalsIgnoreCase(director)) {
                System.out.println("Title: " + temp.title + ", Year: " + temp.year + ", Rating: " + temp.rating);
                found = true;
            }
            temp = temp.next;
        }
        if (!found) System.out.println("No movie found for Director: " + director);
    }

    
    void searchByRating(double rating) {
        Movie temp = head;
        boolean found = false;
        while (temp != null) {
            if (temp.rating == rating) {
                System.out.println("Title: " + temp.title + ", Director: " + temp.director + ", Year: " + temp.year);
                found = true;
            }
            temp = temp.next;
        }
        if (!found) System.out.println("No movie found with Rating: " + rating);
    }

    
    void updateRating(String title, double newRating) {
        Movie temp = head;
        while (temp != null) {
            if (temp.title.equalsIgnoreCase(title)) {
                temp.rating = newRating;
                System.out.println("Rating for \"" + title + "\" updated successfully!");
                return;
            }
            temp = temp.next;
        }
        System.out.println("Movie \"" + title + "\" not found!");
    }

    
    void displayForward() {
        if (head == null) {
            System.out.println("No movie records to display!");
            return;
        }
        System.out.println("\nMovies (Forward):");
        Movie temp = head;
        while (temp != null) {
            System.out.println("Title: " + temp.title + ", Director: " + temp.director + ", Year: " + temp.year + ", Rating: " + temp.rating);
            temp = temp.next;
        }
    }


    void displayReverse() {
        if (tail == null) {
            System.out.println("No movie records to display!");
            return;
        }
        System.out.println("\nMovies (Reverse):");
        Movie temp = tail;
        while (temp != null) {
            System.out.println("Title: " + temp.title + ", Director: " + temp.director + ", Year: " + temp.year + ", Rating: " + temp.rating);
            temp = temp.prev;
        }
    }
}

public class MovieManagementSystem {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        MovieList list = new MovieList();
        int choice;

        do {
            System.out.println("\n--- Movie Management System ---");
            System.out.println("1. Add Movie at Beginning");
            System.out.println("2. Add Movie at End");
            System.out.println("3. Add Movie at Specific Position");
            System.out.println("4. Remove Movie by Title");
            System.out.println("5. Search Movie by Director");
            System.out.println("6. Search Movie by Rating");
            System.out.println("7. Update Movie Rating");
            System.out.println("8. Display Movies (Forward)");
            System.out.println("9. Display Movies (Reverse)");
            System.out.println("10. Exit");
            System.out.print("Enter your choice: ");
            choice = sc.nextInt();

            sc.nextLine(); 

            switch (choice) {
                case 1:
                    System.out.print("Enter Title, Director, Year, Rating: ");
                    list.addAtBeginning(sc.nextLine(), sc.nextLine(), sc.nextInt(), sc.nextDouble());
                    break;
                case 2:
                    System.out.print("Enter Title, Director, Year, Rating: ");
                    list.addAtEnd(sc.nextLine(), sc.nextLine(), sc.nextInt(), sc.nextDouble());
                    break;
                case 3:
                    System.out.print("Enter Position: ");
                    int pos = sc.nextInt();
                    sc.nextLine();
                    System.out.print("Enter Title, Director, Year, Rating: ");
                    list.addAtPosition(pos, sc.nextLine(), sc.nextLine(), sc.nextInt(), sc.nextDouble());
                    break;
                case 4:
                    System.out.print("Enter Movie Title to Remove: ");
                    list.removeByTitle(sc.nextLine());
                    break;
                case 5:
                    System.out.print("Enter Director to Search: ");
                    list.searchByDirector(sc.nextLine());
                    break;
                case 6:
                    System.out.print("Enter Rating to Search: ");
                    list.searchByRating(sc.nextDouble());
                    break;
                case 7:
                    System.out.print("Enter Movie Title and New Rating: ");
                    list.updateRating(sc.next(), sc.nextDouble());
                    break;
                case 8:
                    list.displayForward();
                    break;
                case 9:
                    list.displayReverse();
                    break;
                case 10:
                    System.out.println("Exiting...");
                    break;
                default:
                    System.out.println("Invalid choice!");
            }
        } while (choice != 10);

        sc.close();
    }
}
