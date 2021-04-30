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
/**
 * 
 */
    public ArrayList<T> sort(SortStats stats, ArrayList<T> data) {
      if(data.size()>1) {
        ArrayList<T> small = new ArrayList<>();
        ArrayList<T> large = new ArrayList<>();
        T pivot = data.get(0);
        for(int i = 1; i<data.size(); i++) {
          T val  = data.get(i);
          if(val.compareTo(pivot)<0) {
            small.add(val);
          }else if (val.compareTo(pivot)>0) {
            large.add(val);
          }else {
            if(i>0) {
              large.add(val);
            }else {
              small.add(val);
            }
          }
        }
        sort(stats,small);
        sort(stats,large);
        data.clear();
        data.addAll(small);
        data.add(pivot);
        data.addAll(large);
        
      }
      return data;
    }

}