
class Node {
    int data;
    Node next;
    Node(int data) {
        this.data = data;
        this.next = null;
    }
}


class MyStack {
    Node top;

    
    void push(int data) {
        Node newNode = new Node(data);
        newNode.next = top;
        top = newNode;
    }

    int pop() {
        if (isEmpty()) {
            System.out.println("Stack is empty!");
            return -1;
        }
        int value = top.data;
        top = top.next;
        return value;
    }

    
    int peek() {
        if (isEmpty()) {
            System.out.println("Stack is empty!");
            return -1;
        }
        return top.data;
    }

    
    boolean isEmpty() {
        return top == null;
    }

    void display() {
        Node temp = top;
        while (temp != null) {
            System.out.print(temp.data + " ");
            temp = temp.next;
        }
    }
}

class QueueUsingTwoStacks {
    MyStack stack1 = new MyStack(); 
    MyStack stack2 = new MyStack(); 

    
    void enqueue(int value) {
        stack1.push(value);
        System.out.println("Enqueued: " + value);
    }


    int dequeue() {
        if (stack1.isEmpty() && stack2.isEmpty()) {
            System.out.println("Queue is empty!");
            return -1;
        }

        
        if (stack2.isEmpty()) {
            while (!stack1.isEmpty()) {
                stack2.push(stack1.pop());
            }
        }

        int removed = stack2.pop();
        System.out.println("Dequeued: " + removed);
        return removed;
    }

    
    int peek() {
        if (stack1.isEmpty() && stack2.isEmpty()) {
            System.out.println("Queue is empty!");
            return -1;
        }

        if (stack2.isEmpty()) {
            while (!stack1.isEmpty()) {
                stack2.push(stack1.pop());
            }
        }

        return stack2.peek();
    }

    
    void display() {
        if (stack1.isEmpty() && stack2.isEmpty()) {
            System.out.println("Queue is empty!");
            return;
        }

        System.out.print("Queue (front → rear): ");

        
        MyStack temp = new MyStack();
        Node t = stack2.top;
        while (t != null) {
            temp.push(t.data);
            t = t.next;
        }

        
        Node tt = temp.top;
        while (tt != null) {
            System.out.print(tt.data + " ");
            tt = tt.next;
        }

    
        printBottomToTop(stack1.top);

        System.out.println();
    }

    
    void printBottomToTop(Node node) {
        if (node == null) return;
        printBottomToTop(node.next);
        System.out.print(node.data + " ");
    }
}

public class QueueUsingStacksNoImport {
    public static void main(String[] args) {
        QueueUsingTwoStacks q = new QueueUsingTwoStacks();

        q.enqueue(10);
        q.enqueue(20);
        q.enqueue(30);

        q.display();

        q.dequeue();

        q.display();

        System.out.println("Front element: " + q.peek());
    }
}
