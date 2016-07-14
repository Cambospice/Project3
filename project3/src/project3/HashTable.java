package project3;

import java.util.LinkedList;
import java.util.List;

/**
 * TODO Replace this comment with your own.
 * 
 * Stub code for an implementation of a DataCounter that uses a hash table as
 * its backing data structure. We included this stub so that it's very clear
 * that HashTable works only with Strings, whereas the DataCounter interface is
 * generic. You need the String contents to write your hashcode code.
 */
public class HashTable implements DataCounter<String> {
	private static final int DEFAULT_TABLE_SIZE = 101;

	private List<String>[] theLists;
	private int size;

	/**
	 * construct the hash table
	 */
	public HashTable() {
		this(DEFAULT_TABLE_SIZE);
	}

	public HashTable(int size) {
		theLists = new LinkedList[nextPrime(size)];
		for (int i = 0; i < theLists.length; i++)
			theLists[i] = new LinkedList<>();
	}

	/**
	 * grow the hash table
	 */
	private void rehash() {
		List<String>[] oldLists = theLists;

		theLists = new List[nextPrime(2 * theLists.length)];
		for (int j = 0; j < theLists.length; j++)
			theLists[j] = new LinkedList<>();

		size = 0;
		for (int i = 0; i < oldLists.length; i++)
			for (String item : oldLists[i])
				insert(item);
	}

	private int myhash(String x) {
		int hashVal = x.hashCode();

		hashVal %= theLists.length;
		if (hashVal < 0)
			hashVal += theLists.length;

		return hashVal;
	}

	public boolean contains(String x) {
		List<String> whichList = theLists[myhash(x)];
		return whichList.contains(x);
	}

	public void insert(String x) {
		List<String> whichList = theLists[myhash(x)];
		if (!whichList.contains(x)) {
			whichList.add(x);
			if (++size > theLists.length)
				rehash();
		}
	}

	/**
	 * Internal method to find a prime number at least as large as n.
	 * 
	 * @param n
	 *            the starting number (must be positive).
	 * @return a prime number larger than or equal to n.
	 */
	private static int nextPrime(int n) {
		if (n <= 0)
			n = 3;

		if (n % 2 == 0)
			n++;

		for (; !isPrime(n); n += 2)
			;

		return n;
	}

	/**
	 * Internal method to test if a number is prime. Not an efficient algorithm.
	 * 
	 * @param n
	 *            the number to test.
	 * @return the result of the test.
	 */
	private static boolean isPrime(int n) {
		if (n == 2 || n == 3)
			return true;

		if (n == 1 || n % 2 == 0)
			return false;

		for (int i = 3; i * i <= n; i += 2)
			if (n % i == 0)
				return false;

		return true;
	}

	/**
	 * Get an array of all of the data counts in the DataCounter structure. The
	 * array should contain exactly one DataCount instance for each unique
	 * element inserted into the structure. The elements do not need to be in
	 * any particular order.
	 * 
	 * @return an array of the data counts.
	 */
	public DataCount<String>[] getCounts() {

	}

	/**
	 * The number of unique data elements in the structure.
	 * 
	 * @return the number of unique data elements in the structure.
	 */
	public int getSize() {

		return size;
	}

	/**
	 * Increment the count for a particular data element.
	 * 
	 * @param data
	 *            data element whose count to increment.
	 */
	public void incCount(String data) {

	}
}
