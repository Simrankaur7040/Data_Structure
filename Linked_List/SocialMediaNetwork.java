import java.util.Scanner;

class FriendNode {
    int friendId;
    FriendNode next;

    FriendNode(int friendId) {
        this.friendId = friendId;
        this.next = null;
    }
}

class UserNode {
    int userId;
    String name;
    int age;
    FriendNode friends; 
    UserNode next;

    UserNode(int userId, String name, int age) {
        this.userId = userId;
        this.name = name;
        this.age = age;
        this.friends = null;
        this.next = null;
    }
}

class SocialNetwork {
    UserNode head = null;

    
    void addUser(int id, String name, int age) {
        UserNode newUser = new UserNode(id, name, age);
        if (head == null) {
            head = newUser;
        } else {
            UserNode temp = head;
            while (temp.next != null)
                temp = temp.next;
            temp.next = newUser;
        }
        System.out.println("User added successfully!");
    }

    UserNode findUser(int id) {
        UserNode temp = head;
        while (temp != null) {
            if (temp.userId == id)
                return temp;
            temp = temp.next;
        }
        return null;
    }

    void addFriend(int user1, int user2) {
        UserNode u1 = findUser(user1);
        UserNode u2 = findUser(user2);

        if (u1 == null || u2 == null) {
            System.out.println("One or both users not found!");
            return;
        }

        if (isFriend(u1, user2)) {
            System.out.println("Already friends!");
            return;
        }

        addFriendToList(u1, user2);
        addFriendToList(u2, user1);
        System.out.println("Friend connection added between " + u1.name + " and " + u2.name);
    }

    void addFriendToList(UserNode user, int friendId) {
        FriendNode newFriend = new FriendNode(friendId);
        if (user.friends == null)
            user.friends = newFriend;
        else {
            FriendNode temp = user.friends;
            while (temp.next != null)
                temp = temp.next;
            temp.next = newFriend;
        }
    }

    boolean isFriend(UserNode user, int friendId) {
        FriendNode temp = user.friends;
        while (temp != null) {
            if (temp.friendId == friendId)
                return true;
            temp = temp.next;
        }
        return false;
    }

    
    void removeFriend(int user1, int user2) {
        UserNode u1 = findUser(user1);
        UserNode u2 = findUser(user2);

        if (u1 == null || u2 == null) {
            System.out.println("User not found!");
            return;
        }

        u1.friends = removeFriendFromList(u1.friends, user2);
        u2.friends = removeFriendFromList(u2.friends, user1);

        System.out.println("Friend connection removed between " + u1.name + " and " + u2.name);
    }

    FriendNode removeFriendFromList(FriendNode head, int friendId) {
        if (head == null) return null;

        if (head.friendId == friendId)
            return head.next;

        FriendNode temp = head;
        while (temp.next != null) {
            if (temp.next.friendId == friendId) {
                temp.next = temp.next.next;
                break;
            }
            temp = temp.next;
        }
        return head;
    }

    
    void displayFriends(int userId) {
        UserNode user = findUser(userId);
        if (user == null) {
            System.out.println("User not found!");
            return;
        }

        if (user.friends == null) {
            System.out.println(user.name + " has no friends.");
            return;
        }

        System.out.println(user.name + "'s friends:");
        FriendNode temp = user.friends;
        while (temp != null) {
            UserNode friend = findUser(temp.friendId);
            System.out.println("Friend ID: " + friend.userId + ", Name: " + friend.name);
            temp = temp.next;
        }
    }


    void findMutualFriends(int user1, int user2) {
        UserNode u1 = findUser(user1);
        UserNode u2 = findUser(user2);

        if (u1 == null || u2 == null) {
            System.out.println("User not found!");
            return;
        }

        System.out.println("Mutual friends between " + u1.name + " and " + u2.name + ":");
        FriendNode f1 = u1.friends;
        boolean found = false;

        while (f1 != null) {
            FriendNode f2 = u2.friends;
            while (f2 != null) {
                if (f1.friendId == f2.friendId) {
                    UserNode common = findUser(f1.friendId);
                    System.out.println("Friend ID: " + common.userId + ", Name: " + common.name);
                    found = true;
                }
                f2 = f2.next;
            }
            f1 = f1.next;
        }

        if (!found)
            System.out.println("No mutual friends found.");
    }


    void searchUser(String key) {
        UserNode temp = head;
        boolean found = false;

        while (temp != null) {
            if (String.valueOf(temp.userId).equals(key) || temp.name.equalsIgnoreCase(key)) {
                System.out.println("User Found → ID: " + temp.userId + ", Name: " + temp.name + ", Age: " + temp.age);
                found = true;
            }
            temp = temp.next;
        }

        if (!found)
            System.out.println("No user found with key: " + key);
    }

    void countFriends() {
        if (head == null) {
            System.out.println("No users in the network.");
            return;
        }

        UserNode temp = head;
        while (temp != null) {
            int count = 0;
            FriendNode f = temp.friends;
            while (f != null) {
                count++;
                f = f.next;
            }
            System.out.println("User: " + temp.name + " → Total Friends: " + count);
            temp = temp.next;
        }
    }

    void displayAllUsers() {
        if (head == null) {
            System.out.println("No users available.");
            return;
        }

        System.out.println("\nAll Users:");
        UserNode temp = head;
        while (temp != null) {
            System.out.println("ID: " + temp.userId + ", Name: " + temp.name + ", Age: " + temp.age);
            temp = temp.next;
        }
    }
}

public class SocialMediaNetwork {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        SocialNetwork sn = new SocialNetwork();
        int choice;

        do {
            System.out.println("\n--- Social Media Friend Connection System ---");
            System.out.println("1. Add User");
            System.out.println("2. Add Friend Connection");
            System.out.println("3. Remove Friend Connection");
            System.out.println("4. Display Friends of a User");
            System.out.println("5. Find Mutual Friends");
            System.out.println("6. Search User by ID/Name");
            System.out.println("7. Count Friends");
            System.out.println("8. Display All Users");
            System.out.println("9. Exit");
            System.out.print("Enter your choice: ");
            choice = sc.nextInt();

            switch (choice) {
                case 1:
                    System.out.print("Enter User ID, Name, Age: ");
                    sn.addUser(sc.nextInt(), sc.next(), sc.nextInt());
                    break;
                case 2:
                    System.out.print("Enter User1 ID and User2 ID: ");
                    sn.addFriend(sc.nextInt(), sc.nextInt());
                    break;
                case 3:
                    System.out.print("Enter User1 ID and User2 ID to remove: ");
                    sn.removeFriend(sc.nextInt(), sc.nextInt());
                    break;
                case 4:
                    System.out.print("Enter User ID: ");
                    sn.displayFriends(sc.nextInt());
                    break;
                case 5:
                    System.out.print("Enter two User IDs: ");
                    sn.findMutualFriends(sc.nextInt(), sc.nextInt());
                    break;
                case 6:
                    System.out.print("Enter Name or User ID: ");
                    sn.searchUser(sc.next());
                    break;
                case 7:
                    sn.countFriends();
                    break;
                case 8:
                    sn.displayAllUsers();
                    break;
                case 9:
                    System.out.println("Exiting...");
                    break;
                default:
                    System.out.println("Invalid choice!");
            }
        } while (choice != 9);

        sc.close();
    }
}
