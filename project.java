import java.util.Random;
import java.util.ArrayList;
import java.lang.System.*;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.PriorityQueue;

class MaxHeapComparator implements Comparator<Integer> {
	public int compare(Integer x, Integer y) {
		return y-x;
	}
}

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
        public static ArrayList<Integer> QuickSort_Random(ArrayList<Integer> list, int pivot) {
        ArrayList<Integer> greater_or_equal = new ArrayList<>();
        ArrayList<Integer> less = new ArrayList<>();
        ArrayList<Integer> sorted = new ArrayList<>();
        //base case -- we've recursed to the bottom
        if(list.size() == 1) {
            return list;
        }
        //divide
        else { //sort into two lists -- one greater than, one less than the pivot
           Random r = new Random();
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
                    greater_or_equal = QuickSort(greater_or_equal, greater_or_equal.get(r.nextInt(greater_or_equal.size())));   
            }
            if(less.size() >= 1) {
                less = QuickSort(less,less.get(r.nextInt(less.size())));
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

	public static ArrayList<Integer> InsertionSort(ArrayList<Integer> list){
		for(int i = 1; i <list.size(); i++){
			//Move current back until it hits an element that is less than it
			for(int j = i; j > 0; j--) {
				if(list.get(j) > list.get(j-1)){
					int temp = list.get(j);
					list.set(j, list.get(j-1));
					list.set(j-1, temp);
				}else{
					break;
				}
			}
		}
		return list;
	}

	public static ArrayList<Integer> HeapSort(ArrayList<Integer> list) {
		PriorityQueue<Integer> maxHeap = new PriorityQueue(list.size(), new MaxHeapComparator());
		ArrayList<Integer> sortedList = new ArrayList<Integer>();

		//Add elements into heap
		for(Integer num : list){
			maxHeap.add(num);
		}
		//Get elements from heap into list
		while(maxHeap.size()!=0){
			sortedList.add(maxHeap.poll());
		}

		return sortedList;
	}
    
    public static ArrayList<Integer> Calc_and_print(ArrayList<Integer> list) {
        Random r = new Random();
        ArrayList<Integer> sorted;
        
        long startTime = System.nanoTime();
        sorted = QuickSort((ArrayList<Integer>)list.clone(), list.get(0));
        long endTime   = System.nanoTime();
        long totalTimeQS = endTime - startTime;
        
        startTime = System.nanoTime();
        sorted = QuickSort((ArrayList<Integer>)list.clone(), list.get(r.nextInt(list.size())));
        endTime   = System.nanoTime();
        long totalTimeQSR = endTime - startTime;       
        
        startTime = System.nanoTime();
        sorted = MergeSort((ArrayList<Integer>) list.clone());
        endTime   = System.nanoTime();
        long totalTimeMS = endTime - startTime;
        
        startTime = System.nanoTime();
        sorted = InsertionSort((ArrayList<Integer>) list.clone());
        endTime   = System.nanoTime();
        long totalTimeIS = endTime - startTime;
        

        startTime = System.nanoTime();
        sorted = HeapSort((ArrayList<Integer>) list.clone());
        endTime   = System.nanoTime();
        long totalTimeHS = endTime - startTime;

        System.out.println("Time to InsertionSort  \t= " + totalTimeIS +  " ns");
       /* System.out.println("Time to QuickSort  \t= " + totalTimeQS +  " ns");
        System.out.println("Time to QuickSortR \t= " + totalTimeQSR + " ns");
        System.out.println("Time to MergeSort  \t= " + totalTimeMS +  " ns");
        */
        System.out.println("Time to HeapSort \t= " + totalTimeHS +  " ns");
        
        System.out.println("Time to QuickSort \t= " + totalTimeQS +  " ns");
        System.out.println("Time to QuickSortR \t= " + totalTimeQSR + " ns");
        System.out.println("Time to MergeSort  \t= " + totalTimeMS +  " ns");
        
        System.out.println(); 
        
        return sorted;
    }
    
    public static void main(String[] args) {
         //for a random list
        int list_size = Integer.parseInt(args[0]);
        int max = list_size*10;
        Random r = new Random();
        ArrayList<Integer> list = new ArrayList<>();
        ArrayList<Integer> sorted;
      
        for(int i = 0; i < list_size; i++) {
            list.add(r.nextInt(max));
        }
        //System.out.println(list);
        System.out.println("Randomly sorted list");
        sorted = Calc_and_print(list);
       
        //for a list with relatively few unique values
        ArrayList<Integer> list_repeats = new ArrayList<>();
        max = list_size / 5; //so on average, 5 of each value
        for(int i = 0; i < list_size; i++) {
            list_repeats.add(r.nextInt(max));
        }
        
        System.out.println("Few Uniques");
        sorted = Calc_and_print(list_repeats);
        //fix max
        max = list_size * 10;
       //for a list that is already sorted -- note that running this on large lists requires increasing the size of the call stack
       //java -Xss258m project is enough for 100k elements
       System.out.println("Already sorted");
       sorted = Calc_and_print(sorted);
       
       //for a list that is already reverse sorted
       ArrayList<Integer> list_reverse = new ArrayList<>();
       for(int i =list_size; i > 0; i--){
           list_reverse.add(i);
       }
      
       System.out.println("Reverse sorted");
       sorted = Calc_and_print(list_reverse);
       
       //mostly sorted
       ArrayList<Integer> list_mostly_sorted = sorted;
       //swap a bunch of numbers
       for(int i = 0; i <list_size/10; i++) {
           int index1 = r.nextInt(list_size);
           int index2 = r.nextInt(list_size);
           
           int val1 = list_mostly_sorted.get(index1);
           int val2 = list_mostly_sorted.get(index2);
           list_mostly_sorted.set(index1, val2);
           list_mostly_sorted.set(index2, val1);
        }
        
        System.out.println("~20% of items out of place");
        sorted = Calc_and_print(list_mostly_sorted);
       
       
                
    }
}
