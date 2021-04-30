/*
 *  Assignment: Implementing Quick, Merge, and Radix sort
 *
 *  [this file]: MergeSort Implementation
 * 
 *  Copyright:
 *   For academic use only under the Creative Commons
 *   Attribution-NonCommercial-NoDerivatives 4.0 International License
 *   http://creativecommons.org/licenses/by-nc-nd/4.0
 */

import java.util.ArrayList;

public class MergeSort<T extends Comparable<T>> extends Sorter<T> {
    MergeSort() {
        name = "MergeSort";
    }

    public ArrayList<T> sort(SortStats stats, ArrayList<T> data) {
        
        // Copy the source array list without changes by using subList() method
        ArrayList<T> arrlist = new ArrayList<T> (data.subList(0, data.size()));
    
        // Return the array list if the size is less than 2
        if (data.size() < 2) {
            return arrlist;
        }
        
        
        // Obtain the middle index
        int middle = arrlist.size()/2;
        
        // Divide and obtain the list into left and right sub-list by 2
        ArrayList<T> left  = new ArrayList<T> (arrlist.subList(0, middle));
        ArrayList<T> right = new ArrayList<T> (arrlist.subList(middle, arrlist.size()));
        
        // Recursively divide the array list until the size is 1
        // Call merge method to sort
        return merge(sort(stats, left),sort(stats,right),stats,data);
        
        
    }
    
    private ArrayList<T> merge(ArrayList<T> left, ArrayList<T> right, SortStats stats,ArrayList<T> data){
        
        // initial index
        int i = 0;
        
        // case 1: both sub-lists have elements
        while (left.size() > 0 && right.size() > 0) {
            
            // Every data comparison increment stats.comparisons
            stats.comparisons++;
            
            // Compare the first element,set the smaller element in the index i position
            // index i will dynamically increasing
            // sorted the smaller element then delete in the origin left/right array list
            if(left.get(0).compareTo(right.get(0)) <= 0) {
                data.set(i, left.get(0));
                i++;
                left = new ArrayList<T> (left.subList(1,left.size() ));
                
            } else {
                data.set(i,right.get(0));
                i++;
                right = new ArrayList<T> (right.subList(1,right.size() ));
                
                
            }
            
        }
    
        // case 2: only left sublist has elements
        // set the elements into the index i position
        // increase the index
        // sorted the elements then delete them from the left sublist
        while (left.size() > 0) {
            data.set(i, left.get(0));
            i++;
            left = new ArrayList<T> (left.subList(1,left.size()));
        }
        
        // case 3: only right sublist has elements
        // set the elements into the index i position
        // increase the index
        // sorted the elements then delete them from the right sublist
        while (right.size() > 0) {
            data.set(i, right.get(0));
            i++;
            right = new ArrayList<T> (right.subList(1,right.size()));
        }
        
        return data;
    }
    
}