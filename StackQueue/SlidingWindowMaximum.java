import java.util.Deque;
import java.util.LinkedList;
public class SlidingWindowMaximum{
    public static int[] maxSlidingWindow(int[] arr,int k){
        int n = arr.length;
        if(n==0 || k==0) return new int[0];
        int[] result = new int[n-k+1];
        Deque<Integer> deque = new LinkedList<>();
        for(int i=0; i<n; i++){
            while(!deque.isEmpty() && deque.peekFirst() <= i-k){
                deque.pollFirst();
            }
            while(!deque.isEmpty() && arr[deque.peekLast()] <= arr[i]){
                deque.pollLast();
            }
            deque.offerLast(i);
            if(i>=k-1){
                result[i-k+1] = arr[deque.peekFirst()];
            }
        }
        return result;
    }

    public static void printArray(int[] arr){
        for(int num:arr){
            System.out.print(num + " ");
        }
        System.out.println();
    }
    public static void main(String[] args){
        int[] arr = {1,3,-1,-3,5};
        int k = 3;

        System.out.println("Array: ");
        printArray(arr);
        System.out.println("Window size: "+k);
        int[] result = maxSlidingWindow(arr, k);
        System.out.println("\n Maximums of each sliding window: ");
        printArray(result);
    }

}