
import java.util.Random;
import java.util.ArrayList;
import java.lang.System.*;
import java.util.Arrays;

/**
 * Write a description of class project here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
public class project {

    public static ArrayList<Integer> QuickSort(ArrayList<Integer> list, int pivot) {
        ArrayList<Integer> greater_or_equal = new ArrayList<>();
        ArrayList<Integer> less = new ArrayList<>();
        ArrayList<Integer> sorted = new ArrayList<>();
        //base case -- we've recursed to the bottom
        if(list.size() == 1) {
            return list;
        }
        //divide
        else { //sort into two lists -- one greater than, one less than the pivot
            list.remove(0); //remove pivot --it's added back in combine step
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
                greater_or_equal = QuickSort(greater_or_equal, greater_or_equal.get(0));   
            }
            if(less.size() >= 1) {
                less = QuickSort(less,less.get(0));
            }
        }

        //combine
        sorted.addAll(less);
        sorted.add(pivot);
        sorted.addAll(greater_or_equal);
        return sorted;
    }
    //O(n)            
    private static ArrayList<Integer> Merge(ArrayList<Integer> left, ArrayList<Integer> right) {
        ArrayList<Integer> merged = new ArrayList(); 
        int vals_left = left.size()+right.size();
        //Keep going until we are out of values to merge in
        for(int i = vals_left; i>0; i--) {
            //if one list is empty, just dump the rest in merged and return
            if(left.size() == 0) {
                merged.addAll(right);
                break;
            }
            if(right.size() == 0) {
                merged.addAll(left);
                break;
            }
            //otherwise, add whichever first value is smaller to merged. O(1)
            if(left.get(0) <= right.get(0)) {
                merged.add(left.get(0));
                left.remove(0);
                continue;
            }
            else {
                merged.add(right.get(0));
                right.remove(0);
            }
        }
        
        return merged;   
    }
    
    public static ArrayList<Integer> MergeSort(ArrayList<Integer> list) {
        int half;
        ArrayList<Integer> left;
        ArrayList<Integer> right;
        //divide
        if(list.size() > 1) {
             half = list.size()/2;

             left = new ArrayList<>(list.subList(0, half));
             right = new ArrayList<>(list.subList(half, list.size()));
             
             //subList() returns an emptylsit if start = end. This fixes that
             if(half == list.size()) {
                  right = new ArrayList<>();
                  right.add(list.get(list.size()-1));
             }
             if(half == 0) {
                 left = new ArrayList<>();
                 right.add(list.get(0));
             }
             
             //recurse
             right =MergeSort(right);
             left = MergeSort(left);
        }
        
        else {
            return list;
        }
        //conquer and combine
        return Merge(left, right);
    }
    
    public static void main(String[] args) {
     
        int list_size = 10;
        int max = list_size*10;
        Random r = new Random();
        ArrayList<Integer> list = new ArrayList<>();
      
        for(int i = 0; i < list_size; i++) {
            list.add(r.nextInt(max));
        }
        System.out.println(list);
        
        ArrayList<Integer> sorted;
        //get start time
        long startTime = System.nanoTime();
        sorted = QuickSort((ArrayList<Integer>)list.clone(), list.get(0));
        long endTime   = System.nanoTime();
        long totalTimeQS = endTime - startTime;
        
        startTime = System.nanoTime();
        sorted = MergeSort((ArrayList<Integer>) list.clone());
        endTime   = System.nanoTime();
        long totalTimeMS = endTime - startTime;
        
        System.out.println(sorted);
        System.out.println("Time to QuickSort = " + totalTimeQS + " ns");
        System.out.println("Time to MergeSort = " + totalTimeMS + " ns");

    }
}
