package project2;

import java.util.Comparator;

/**
 * Model Binary Search Tree class
 *
 * @param <T>
 *            is for Generic
 */
public class BinarySearchTree<T extends Comparable<T>> {

	private BinaryNode<T> root;
	private Comparator<T> cmp;

	/**
	 * construct the class
	 */
	public BinarySearchTree() {
		this(null);
	}

	/**
	 * construct the class
	 * 
	 * @param c
	 *            is the input
	 */
	public BinarySearchTree(Comparator<T> c) {
		root = null;
		cmp = c;
	}

	/**
	 * make the class empty
	 */
	public void makeEmpty() {
		root = null;
	}

	/**
	 * check for empty
	 * 
	 * @return true or false
	 */
	public boolean isEmpty() {
		return root == null;
	}

	/**
	 * get the root from the class
	 * 
	 * @return the root
	 */
	public BinaryNode<T> getRoot() {
		return root;
	}

	/**
	 * get the height of the class
	 * 
	 * @return height
	 */
	public int height() {
		if (this.isEmpty()) {
			return -1;
		} else {
			return height(root);
		}
	}

	/**
	 * get the height of the class
	 * 
	 * @param t
	 *            is the input node
	 * @return the height
	 */
	public int height(BinaryNode<T> t) {
		if (t == null)
			return -1;
		else
			return 1 + Math.max(height(t.left), height(t.right));

	}

	/**
	 * get min node
	 * 
	 * @return the min node
	 * @throws UnderflowException
	 */
	public T findMin() throws UnderflowException {
		if (isEmpty()) {
			throw new UnderflowException("There are no Node");
		}
		return findMin(root).element;
	}

	/**
	 * get max node
	 * 
	 * @return max node
	 * @throws UnderflowException
	 */
	public T findMax() throws UnderflowException {
		if (isEmpty())
			throw new UnderflowException("There are no Node");
		return findMax(root).element;
	}

	/**
	 * compare method
	 * 
	 * @param lhs
	 * @param rhs
	 * @return compare number
	 */
	private int myCompare(T lhs, T rhs) {
		if (cmp != null)
			return cmp.compare(lhs, rhs);
		else
			return ((Comparable) lhs).compareTo(rhs);
	}

	/**
	 * insert the node
	 * 
	 * @param x
	 *            is the node
	 */
	public void insert(T x) {
		root = insert(x, root);
	}

	/**
	 * insert the node
	 * 
	 * @param x
	 *            is the node
	 * @param t
	 * @return node
	 */
	public BinaryNode<T> insert(T x, BinaryNode<T> t) {
		if (t == null) {
			return new BinaryNode<>(x, null, null);
		}

		int compareResult = x.compareTo(t.element);

		if (compareResult < 0)
			t.left = insert(x, t.left);
		else if (compareResult > 0)
			t.right = insert(x, t.right);
		else
			;
		return t;
	}

	/**
	 * find the min node
	 * 
	 * @param t
	 *            is the node
	 * @return min node
	 */
	public BinaryNode<T> findMin(BinaryNode<T> t) {
		if (t == null)
			return null;
		else if (t.left == null)
			return t; // found the leftmost node
		return findMin(t.left);
	}

	/**
	 * find max node
	 * 
	 * @param t
	 *            is the node
	 * @return the max node
	 */
	public BinaryNode<T> findMax(BinaryNode<T> t) {
		if (t != null)
			while (t.right != null)
				t = t.right; // found the rightmost node
		return t;
	}

	/**
	 * remove the node
	 * 
	 * @param x
	 *            is the node
	 */
	public void remove(T x) {
		root = remove(x, root);
	}

	/**
	 * remove the node
	 * 
	 * @param x
	 *            is the node
	 * @param t
	 * @return the node that is remove
	 */
	public BinaryNode<T> remove(T x, BinaryNode<T> t) {
		if (t == null)
			return t;

		int compareResult = x.compareTo(t.element);
		if (compareResult < 0) {
			t.left = remove(x, t.left);
		} else if (compareResult > 0) {
			t.right = remove(x, t.right);
		} else if (t.left != null && t.right != null) {
			t.element = findMin(t.right).element;
			t.right = remove(t.element, t.right);
		} else {
			t = (t.left != null) ? t.left : t.right;
		}
		return t;
	}

	/**
	 * search for the node
	 * 
	 * @param x
	 *            is the node
	 * @return true or false that is the node is contain
	 */
	public boolean contains(T x) {
		return contains(x, root);
	}

	/**
	 * search for the node
	 * 
	 * @param x
	 *            is the node
	 * @param t
	 * @return true or false if it contain
	 */
	private boolean contains(T x, BinaryNode<T> t) {
		if (t == null)
			return false;

		int compareResult = myCompare(x, t.element);

		if (compareResult < 0)
			return contains(x, t.left);
		else if (compareResult > 0)
			return contains(x, t.right);
		else
			return true; // match
	}

}
