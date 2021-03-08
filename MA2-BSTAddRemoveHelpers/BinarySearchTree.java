/*
 *	Microassignment: BST Add and Remove Helpers
 *
 *  BinarySearchTree: A Binary Tree Implementation
 */

 import java.util.ArrayList;

public class BinarySearchTree<T extends Comparable<T>> extends Collection<T> {

	// Keeps track of the remove direction (left or right sub tree)
	private int _remove_counter = 0;
	
	// Keeps track of BST size (count of nodes)
	private int _size_counter = 0;
	
	// Root node of tree, can be null
	protected BinaryNode<T> _root = null;
	
	// Removes the largest element from the SUBTREE starting at root
	// Uses a recursive algorithm to implement this behavior
	protected BinaryNode<T> removeLargest(BinaryNode<T> root)
	{
		// NULL tree/empty tree
		if (root == null) { return null; }
		
		// BASE CASE: root is the largest
		if (root.getRightChild() == null)
		{
			return root.getLeftChild();
		}

		BinaryNode<T> new_right = removeLargest(root.getRightChild());

		// Reconfigure our right pointer to the returned value
		root.setRightChild(new_right);				
		
		return root;
	}
	
	// Removes the smallest element from the tree
	// Uses an iterative algorithm to implement this behavior
	protected BinaryNode<T> removeSmallest(BinaryNode<T> root)
	{
		// NULL tree/empty tree
		if (root == null) return null;
		
		// Without recursion
		BinaryNode<T> pre = null;
		
		// root is the smallest value
		while (root.getLeftChild() != null)
		{
			pre = root;
			root = root.getLeftChild();
		}
		
		// Check if pre is null
		if(pre != null)
			pre.setLeftChild(root.getRightChild());
		
		return pre;
	}
	
	// Finds the largest value in tree starting at root and returns reference to it
	protected BinaryNode<T> findLargest(BinaryNode<T> root)
	{
		while(root != null && root.getRightChild() != null)
			root = root.getRightChild();
		return root;
	}
	
	// Finds the smallest value in tree starting at root and returns reference to it
	protected BinaryNode<T> findSmallest(BinaryNode<T> root)
	{
		while(root != null && root.getLeftChild() != null)
			root = root.getLeftChild();
		return root;
	}
	
	// Adds a new Element to the tree starting at root
	// Microassignment: MA TODO
	protected BinaryNode<T> addElementHelper(BinaryNode<T> root, T item)
	{
		// Pseudo code:
		//  Check for null first
		//  If null, create new node return pointer to that node	
		//  If not null, compare value, add to correct place
		//  You can choose whether to use recursion or not to compare, use this
		//   method of the item: item.compareTo(/*arguments to compare to*/)
		//	 We can know the item is of a Comparable type so it *must* have a
		//   compareTo() interface
		if (root == null) {
			
			_size_counter ++; //track the size
			
			return new BinaryNode<T>(item); //return pointer to new node if null
			
		}else if (root.getValue().compareTo(item) < 0) {  //if the item value > root value, add to the right subtree
			
			root._right_child = addElementHelper(root._right_child,item); //use recursion add the item to the appropriate place
			
		}else if (root.getValue().compareTo(item) > 0) { //if the item value < root value, add to the left subtree
			root._left_child= addElementHelper(root._left_child,item); //use recursion add the item to the appropriate place
		}

		

		// MA TODO

		//  Always return root because we don't know where the recursion ends
		return root;
	}
	
	// Removes an Element by value from the tree starting at root
	// Microassignment: MA TODO
	protected BinaryNode<T> removeElementHelper(BinaryNode<T> root, T item)
	{
		// Recursion base case - empty tree, return
		if (root == null) { 
			return root; 
			}
		
		// Pseudo code:
		//  Three possibilities:
		//   We found the item (root value == item)
		//   Item is less than root (item < root)
		//   Item is greater than root (item > root)
		if (item.compareTo(root.getValue()) == 0)
		{
			// Increment removal counter
			_remove_counter++;
		
			if (_remove_counter % 2 == 0)
			{
				// Let's assume we are removing from the left when it's an even number
				// MA TODO
				
				//reference from "Data Structures and Algorithm Analysis in Java 3rd", Chapter 4.3.4 Remove
				if (root._left_child != null && root._right_child != null) { // two children
					
					BinaryNode<T> left_max = findLargest(root._left_child); // find the leftmost element of the root if _remove_counter is even
					
					root.setValue(left_max.getValue()); //set the root value to the left max
					
					root._left_child = removeElementHelper(root._left_child, left_max.getValue()); //removing from left recursively
					
				}
				else {
					root = (root._left_child != null) ? root._left_child : root._right_child;
					_remove_counter--; // track the _remove_counter variable
				}
				

			}
			else
			{
				// Remove from the right subtree when it's an odd number
				// MA TODO

				if (root._left_child != null && root._right_child != null) { // two children
					
					BinaryNode<T> right_min = findSmallest(root._right_child); // find the rightmost of the root if the _remove_counter is odd
					
					root.setValue(right_min.getValue()); // set the removed root
					
					root._right_child = removeElementHelper(root._right_child, right_min.getValue()); // removing from right recursively
					
				}
				else {
					root = (root._left_child != null) ? root._left_child : root._right_child;
					_remove_counter--; // track the _remove_counter variable
				}

			}
		}
		else if (item.compareTo(root.getValue()) < 0)
		{
			// Item is less than root - go on finding it in the left subtree
			BinaryNode<T> result = removeElementHelper(root.getLeftChild(),item);
			
			// The recursive call *might* have altered our
			//  left child's structure. Inform root of this change
			root.setLeftChild(result);
		}
		else 
		{
			// Item is greater than root
			//  finding it in the right subtree
			BinaryNode<T> result = removeElementHelper(root.getRightChild(),item);
			root.setRightChild(result);
		}
		
		return root;
	}

	public void printPostorder() {
		printPostorderHelper(_root);
		System.out.println();
	}

	void printPostorderHelper(BinaryNode<T> root)
	{
		if (root == null) return;
		
		printPostorderHelper(root.getLeftChild());
		printPostorderHelper(root.getRightChild());
		System.out.print(" " + root.getValue());
	}

	public void printPreorder() {
		printPreorderHelper(_root);
		System.out.println();
	}

	void printPreorderHelper(BinaryNode<T> root)
	{
		if (root == null) return;
		
		System.out.print(" " + root.getValue());
		printPreorderHelper(root.getLeftChild());
		printPreorderHelper(root.getRightChild());
	}

	public void printInorder() {
		printInorderHelper(_root);
		System.out.println();
	}

	void printInorderHelper(BinaryNode<T> root)
	{
		if (root == null) return;
		
		printInorderHelper(root.getLeftChild());
		System.out.print(" " + root.getValue());
		printInorderHelper(root.getRightChild());
	}

	public void removeElement(T item) {
		_root = removeElementHelper(_root, item);
		_size_counter--;
	}

	boolean containsElementHelper(BinaryNode<T> root, T item) {
		if( root == null ) { return false; }
		if( item.compareTo(root.getValue()) < 0 ) {
			return containsElementHelper( root.getLeftChild(), item );
		} else if( item.compareTo(root.getValue()) > 0 ) {
			return containsElementHelper( root.getRightChild(), item );
		} else {
			return true;
		}
	}

	public boolean containsElement(T item) {
		return containsElementHelper(_root, item);
	}

	public ArrayList<T> getAsArrayListPreOrder() {
		ArrayList<T> list = new ArrayList<>();
		getAsArrayListPreOrderHelper(_root, list);
		return list;
	}

	void getAsArrayListPreOrderHelper(BinaryNode<T> root, ArrayList<T> list)
	{
		if (root == null) return;
		
		list.add(root.getValue());
		getAsArrayListPreOrderHelper(root.getLeftChild(), list);
		getAsArrayListPreOrderHelper(root.getRightChild(), list);
	}

	// Since this inherits from Collection (see Collection.java), must do overrides for API
	@Override
	public void addElement(T item) {
		_root = addElementHelper(_root, item);
	}

	@Override
	public boolean isEmpty() {
		return _root == null;
	}

	@Override
	public int getSize() {
		return _size_counter;
	}

}	// End of BinarySearchTree class
