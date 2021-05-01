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
  			
  			ArrayList<T> small = new ArrayList<T>();
  			ArrayList<T> large = new ArrayList<T>();
  			int mid = data.size()/2;
  			//pick median as a pivot
  			T pivot = data.get(mid);
  			for(int i = 0; i<data.size(); i++) {
  				//if not reaching to the median index
  				if(i!=mid) {
  				T val  = data.get(i);
  				
  					//elements to the left of pivot
  					stats.comparisons++;
  					if(val.compareTo(pivot)<0) {
  						small.add(val);
  					//elements to the right of pivot	
  					}else if (val.compareTo(pivot)>0) {
  						large.add(val);
  					}else {
  						//elements with the identical value of pivot
  						//stay at its same side.
  						if(i<mid) {
  							small.add(val);
  						}else {
  							large.add(val);
  							 
  						}
  					}
  				}
  			}
  			
  			ArrayList<T> smallsub = sort(stats,small);
  			
  			
  			ArrayList<T> largesub = sort(stats,large);
  			
  			
  			data.clear();
  			//set up the sorted data list
  			for(T left : smallsub) {
  				data.add(left);
  			}
  			data.add(pivot);
  			
  			for(T right : largesub ) {
  				data.add(right);
  			}
  			
  			
  		}
  		return data;
  		
  	}

}