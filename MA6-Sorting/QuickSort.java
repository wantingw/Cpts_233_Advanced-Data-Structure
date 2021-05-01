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
      if(data.size()>1) {
        ArrayList<T> datas = quickSort(stats,data);
          data.clear();
          data.addAll(datas);
      }
      return data;
      
    }
    
    
    private ArrayList<T> quickSort(SortStats stats, ArrayList<T> data){
      if(data.size() <= 1) {
        
        return data;
        
      }else {
        //sublist small for elements smaller than pivot
        //sublist large for elements larger than pivot
        ArrayList<T> small = new ArrayList<T>();
        ArrayList<T> large = new ArrayList<T>();
        
        int mid = data.size() / 2;
        
        //pick median as a pivot
        T pivot = data.get(mid);
        for(int i = 0; i<data.size(); i++) {
          //if not reaching to the median index
          if(i!=mid) {
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
        ArrayList<T> result = new ArrayList<T>();
        ArrayList<T> smallPart = sort(stats,small);
        ArrayList<T> largePart = sort(stats,large);
        result.clear();
        result.addAll(smallPart);
        result.add(pivot);
        result.addAll(largePart);
        
        return result;
      }
      
    }

}