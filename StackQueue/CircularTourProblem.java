
public class CircularTourProblem{
    public static int findStartingPoint(int[] petrol, int[] distance){
        int n = petrol.length;
        int start = 0;
        int current = 0;
        int total = 0;

        for(int i=0;i<n;i++){
            int gain = petrol[i] - distance[i];

            current +=gain;
            total += gain;
            if(current < 0){
                start = i+1;
                current = 0;
            }
        }
        return (total >= 0) ? start % n : -1;
    }

    public static void main(String[] args){
        int[] petrol = {4,6,7,4};
        int[] distance = {6,5,3,5};

        int start = findStartingPoint(petrol, distance);
        
        if(start != -1){
            System.out.println("Truck can start at petrol pump index: " + start);
        }else{
            System.out.println("No possible circular tour.");
        }
    }
}