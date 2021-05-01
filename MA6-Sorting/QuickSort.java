/*
 *  Assignment: Implementing Quick, Merge, and Radix sort
 *
 *  [this file]: QuickSort Implementation
 * 
 *  Copyright:
 *   For academic use only under the Creative Commons
 *   Attribution-NonCommercial-NoDerivatives 4.0 International License
 *   http://creativecommons.org/licenses/by-nc-nd/4.0
 */

import java.util.ArrayList;

public class QuickSort<T extends Comparable<T>> extends Sorter<T> {
    QuickSort() {
        name = "QuickSort";
    }

    public ArrayList<T> sort(SortStats stats, ArrayList<T> data) {
      quickSort(stats,data);
      return data;
    }
    
    
    private ArrayList<T> quickSort(SortStats stats, ArrayList<T> data){
      if(data.size()>1) {
        //sublist small for elements smaller than pivot
        //sublist large for elements larger than pivot
        ArrayList<T> small = new ArrayList<>();
        ArrayList<T> large = new ArrayList<>();
        
        int m = data.size()/2;
        
        //pick median as a pivot
        T pivot = data.get(m);
        for(int i = 0; i<data.size(); i++) {
          //if not reaching to the median index
          if(i!=m) {
          T val  = data.get(i);
          
            //elements to the left of pivot
            if(val.compareTo(pivot)<0) {
              small.add(val);
            //elements to the right of pivot  
            }else if (val.compareTo(pivot)>0) {
              large.add(val);
            }else {
              //elements with the identical value of pivot
              //stay at its same side.
              if(i>0) {
                large.add(val);
              }else {
                small.add(val);
              }
            }
          }
        }
        
        ArrayList<T> smallPart = sort(stats,small);
        ArrayList<T> largePart = sort(stats,large);
        
        //set the DATA list
        setData(data, smallPart, pivot, largePart);

      }
      return data;
      
    }
    
    private void setData(ArrayList<T> data ,ArrayList<T>small, T pivot, ArrayList<T> large) {
      data.clear();
      data.addAll(small);
      data.add(pivot);
      data.addAll(large);
    }

}