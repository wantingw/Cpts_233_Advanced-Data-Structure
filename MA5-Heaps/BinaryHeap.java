/*
 *  Assignment: Implementing Percolates
 *
 *  [this file]: Main Heap class
 *   Heaps Microassignment
 * 
 *  Copyright:
 *   For academic use only under the Creative Commons
 *   Attribution-NonCommercial-NoDerivatives 4.0 International License
 *   http://creativecommons.org/licenses/by-nc-nd/4.0
 */

import java.util.ArrayList;

public class BinaryHeap<AnyType extends Comparable<? super AnyType>>
{
    private ArrayList<AnyType> data = new ArrayList<>();
    private Integer currentSize = 0;
    private static final int DEFAULT_CAPACITY = 10;

    // Default constructor reserves 10 items worth of space
    public BinaryHeap () {
        this(DEFAULT_CAPACITY);
    }

    public BinaryHeap (int reserve_size) {
        ensureHeapSize(reserve_size);
        currentSize = 0;
    }

    // Array initializer-based constructor
    //  Added every item in items to heap
    public BinaryHeap( AnyType [ ] items ) {
        this();             // Call default constructor
        for (AnyType item : items) {
            insert(item);
        }
    }

    // Quick hack of a print out of the heap's data
    public void dump() {
        for (AnyType val : data) {
            System.out.print(val + ", ");
        }
    }

    // Returns a SHALLOW COPY of the data arraylist
    public ArrayList<AnyType> getData() {
        ArrayList<AnyType> newData = new ArrayList<>();
        for (AnyType item : data) {
            if( item != null) {
                newData.add(item);
            }
        }
        return newData;
    }

    // Expands the heap's internal sized ArrayList
    public void ensureHeapSize(int newSize) {
        data.ensureCapacity(newSize);    // Prevent excessive copying while we're adding
        while (data.size() < newSize) {
            data.add(null);
        }
    }

    public AnyType findMin() {
        if( isEmpty( ) ) {
            throw new IndexOutOfBoundsException( );
        }
        return data.get(0);
    }

    public boolean isEmpty() {
        return (currentSize <= 0);
    }

    public void makeEmpty() {
        while( !isEmpty() ) {
            deleteMin();
        }
    }

    public int size() {
        return currentSize;
    }

    public AnyType deleteMin() {
        if( isEmpty( ) ) {
            throw new IndexOutOfBoundsException( );
        }

        AnyType minItem = findMin( );
        data.set(0, data.get(--currentSize));
        data.set(currentSize, null);
        percolateDown( 0 );

        return minItem;
    }

    // ********************************************************************* //
    //  Microassignment Section: Implement percolations
    // ********************************************************************* //
    public void insert(AnyType x) {
        // MA TODO: Write some kind of heap/percolate insert function
    	
    	// Dynamically expand the heap size to avoid index out of bounds
    	if (currentSize == data.size() - 1) {
    		ensureHeapSize(data.size() * 2 + 1);
    	}
    	
    	
    	// Percolate up
    	int hole = currentSize;
    	
    	// Insert the element X, create a hole in the (next) available location
    	// The parent index: (hole - 1) / 2
    	for (data.set(currentSize, x); x.compareTo(data.get((hole-1)/2)) < 0; hole = (hole - 1) / 2) {
    		
    		// Store the parent element
    		AnyType a = data.get((hole - 1) / 2);
    		
    		// Perform repeated swaps until the correct order was established
    		data.set((hole - 1)/2, x);
    		data.set(hole, a);
    		
    		
    		

    	}
    	// Size updates
    	currentSize++;
    }

	private void percolateDown(int hole) {
		// MA TODO: Write some kind of heap/percolateUp function

		int child;

		// Stored the root element
		AnyType tmp = data.get(hole);

		for (; hole * 2 <= currentSize; hole = child) {

			// Track child index
			child = hole * 2 + 1;

			// Case 1: if both left and right children are null, then stop
			if (data.get(child) == null && data.get(child + 1) == null) {
				break;
			}

			// Case 2: if right child is null but left child is not null
			// compare the child index element to the root element, then swap or stop
			if (data.get(child) != null && data.get(child + 1) == null) {
				if (data.get(child).compareTo(tmp) < 0) {
					AnyType childElement = data.get(child);
					data.set(child, tmp);
					data.set(hole, childElement);
				} else
					break;
			}

			// Case 3: if right & left children are not null
			// compare these two children, obtain the smaller element
			// compare the smaller element with the root element, then swap or stop
			if (data.get(child) != null && data.get(child + 1) != null) {

				if (child != currentSize && data.get(child + 1).compareTo(data.get(child)) < 0)
					child++;

				if (data.get(child).compareTo(tmp) < 0) {

					AnyType childElement = data.get(child);
					data.set(child, tmp);
					data.set(hole, childElement);

				} else
					break;

			}
		}

		data.set(hole, tmp);

	}
    
   
}