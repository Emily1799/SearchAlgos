
import java.util.Random;
import java.util.ArrayList;
import java.lang.System.*;

/**
 * Write a description of class project here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
public class project{

    public static ArrayList<Integer> Quicksort(ArrayList<Integer> list, int pivot) {
        ArrayList<Integer> greater_or_equal = new ArrayList<>();
        ArrayList<Integer> less = new ArrayList<>();
        ArrayList<Integer> sorted = new ArrayList<>();
        //base case -- we've recursed to the bottom
        if(list.size() == 1) {
            return list;
        }
        //divide
        else { //sort into two lists -- one greater than, one less than the pivot
            list.remove(0);
            for(Integer i : list) {
                if(i >= pivot) {
                    greater_or_equal.add(i);
                }
                else {
                    less.add(i);
                }
            }
            //Conquer -- recursively sort smaller lists
            //pivot off first element -- naive quicksort
            if(greater_or_equal.size() >= 1) {
                greater_or_equal = Quicksort(greater_or_equal, greater_or_equal.get(0));
                
            }
            if(less.size() >= 1) {
                less = Quicksort(less,less.get(0));
            }
        }
        
        //combine
        sorted.addAll(less);
        sorted.add(pivot);
        sorted.addAll(greater_or_equal);
        return sorted;
    }

    public static void main(String[] args) {
     
        int list_size = 100;
        int max = list_size*10;
        Random r = new Random();
        ArrayList<Integer> list = new ArrayList<>();
        for(int i = 0; i < list_size; i++) {
            list.add(r.nextInt(max));
        }

        System.out.println(list);
        
        //get start time
        long startTime = System.nanoTime();
        list = Quicksort(list, list.get(0));
        long endTime   = System.nanoTime();
        
        System.out.println(list);
        long totalTime = endTime - startTime;
        System.out.println("Time to QuickSort = " + totalTime + "ns"); 

    }
}
