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

  	
		return quickSort(data,0,data.size()-1,stats);
		
  	}
  	
  	/**
  	 * Internal quicksort method that makes recursive calls
  	 * @param data an array list of Comparable items
  	 * @param left  the left-most index of the (sub)array list
  	 * @param right the right-most index of the (sub)array list
  	 * @param stats a tiny data object to snag stats during a sort
  	 * @return the sorted array list
  	 */
  	private ArrayList<T> quickSort(ArrayList<T> data,int left, int right,SortStats stats) {
  		
		if (left < right) {
			int partitionIndex = partition(data,left,right,stats);
			quickSort(data,left,partitionIndex - 1,stats);
			quickSort(data,partitionIndex + 1, right, stats);
		}
		
		return data;
  		
  	}
  	
  	/**
  	 * Internal partition method that pick a pivot point
  	 * Move all elements that are less than pivot point to the left side of the partition
  	 * Move all elements that are greater than pivot point to the right side of the partition
  	 * @param data an array list of Comparable items
  	 * @param left  the left-most index of the (sub)array list
  	 * @param right the right-most index of the (sub)array list
  	 * @param stats a tiny data object to snag stats during a sort
  	 * @return the index of the pivot point
  	 */
  	private int partition(ArrayList<T> data, int left, int right,SortStats stats) {
  		
  		// set the leftmost of the array list as pivot
  		int pivot = left;
  		
  		// initialize the index greater than the pivot position by 1
  		int index = pivot + 1;
  		
  		// use for loop to traverses the array list elements
  		for (int i = index; i <= right; i++) {
  			
  			// i position element less than pivot element
  			if (data.get(i).compareTo(data.get(pivot)) < 0) {  			
  				
  				// move the smaller element in i position to the left of pivot
  				// move the larger element in i position to the right of pivot
  				swap(data,i,index,stats);
  				index++;
  				
  				// increase the count of comparisons
  				stats.comparisons++;
  			}
  		}
  		
  		// use swap method to set up the pivot's new position
		swap(data,pivot,index-1,stats);
		
		return index - 1;
  		
  	}
  	
  	private void swap(ArrayList<T> data, int i, int j,SortStats stats) {
  		// store the element in index i
  		T temp =  data.get(i);
  		
  		// swap the elements using set() method
  		data.set(i, data.get(j));
  		data.set(j, temp);
  		
  		// increase the count of swaps
  		stats.swaps++;
  	}

}