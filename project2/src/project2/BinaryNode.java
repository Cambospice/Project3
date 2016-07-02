package project2;

/**
 * Model BinaryNode class
 *
 * @param <T>
 *            is for Generic
 */
public class BinaryNode<T> {
	T element;
	BinaryNode<T> left;
	BinaryNode<T> right;
	int height;

	/**
	 * construct the class
	 * 
	 * @param theElement
	 *            is the element from the class
	 */
	BinaryNode(T theElement) {
		this(theElement, null, null);
	}

	/**
	 * construct the class
	 * 
	 * @param theElement
	 *            is the element from the class
	 * @param lt
	 *            is left node from the class
	 * @param rt
	 *            is right node from the class
	 */
	BinaryNode(T theElement, BinaryNode<T> lt, BinaryNode<T> rt) {
		element = theElement;
		left = lt;
		right = rt;
	}

	/**
	 * get the data from the node in the class
	 * 
	 * @return the element inside the node
	 */
	public T getData() {
		return element;
	}

	/**
	 * get the left node from the class
	 * 
	 * @return the node from the left
	 */
	public BinaryNode<T> getLeft() {
		return left;
	}

	/**
	 * get the right node from the class
	 * 
	 * @return the node from the right
	 */
	public BinaryNode<T> getRight() {
		return right;
	}

	/**
	 * get the height of the tree
	 * 
	 * @return height from the class
	 */
	public int getHeight() {
		return height;
	}

}
