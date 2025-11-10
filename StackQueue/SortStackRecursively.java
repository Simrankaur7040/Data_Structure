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
            return -1;
        }
        return top.data;
    }

    boolean isEmpty() {
        return top == null;
    }

    
    void display() {
        System.out.print("Stack (top → bottom): ");
        Node temp = top;
        while (temp != null) {
            System.out.print(temp.data + " ");
            temp = temp.next;
        }
        System.out.println();
    }
}


public class SortStackRecursively {
    
   
    static void sortStack(MyStack stack) {
        if (!stack.isEmpty()) {
          
            int top = stack.pop();

          
            sortStack(stack);

          
            insertInSortedOrder(stack, top);
        }
    }

   
    static void insertInSortedOrder(MyStack stack, int element) {
       
        if (stack.isEmpty() || element > stack.peek()) {
            stack.push(element);
            return;
        }

        int top = stack.pop();
        insertInSortedOrder(stack, element);

        
        stack.push(top);
    }

    public static void main(String[] args) {
        MyStack stack = new MyStack();

        stack.push(30);
        stack.push(-5);
        stack.push(18);
        stack.push(14);
        stack.push(-3);

        System.out.println("Original stack:");
        stack.display();

     
        sortStack(stack);

        System.out.println("Sorted stack (ascending):");
        stack.display();
    }
}
