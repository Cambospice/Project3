package project3;

import java.util.LinkedList;
import java.util.List;

import javax.xml.soap.Node;

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

	private List<String>[] chain;
	private int currentSize;

	/**
	 * construct the hash table
	 */
	public HashTable() {
		this(DEFAULT_TABLE_SIZE);
	}

	public HashTable(int size) {
		chain = new List[size];
		currentSize = 0;

	}

	public class List<T> {
		private String element;
		private List next;
		private double freq;

		public List() {
			freq = 1;
		}

		public List(String element, List next) {
			this.element = element;
			this.freq = 1;
			this.next = next;
		}

	}

	/**
	 * grow the hash table
	 */
	public void rehash() {
		List[] list = new List[chain.length * 2];
		for (List node : chain) {
			for (; node != null; node = node.next) {
				insert(node.element, node.freq);
			}
		}
		chain = list;

	}

	// String hash function from online
	public int myhash(String s) {
		int intLength = s.length() / 4;
		long sum = 0;
		for (int j = 0; j < intLength; j++) {
			char c[] = s.substring(j * 4, (j * 4) + 4).toCharArray();
			long mult = 1;
			for (int k = 0; k < c.length; k++) {
				sum += c[k] * mult;
				mult *= 256;
			}
		}

		char c[] = s.substring(intLength * 4).toCharArray();
		long mult = 1;
		for (int k = 0; k < c.length; k++) {
			sum += c[k] * mult;
			mult *= 256;
		}

		return (int) (Math.abs(sum) % chain.length);

	}

	public boolean contain(String x) {
		List<String> whichList = chain[myhash(x)];
		boolean isContain = false;
		if (whichList == null) {
			chain[myhash(x)] = new List<>();
			isContain = true;
		} else {
			System.out.println(whichList.element);
			while (whichList != null && !whichList.element.equals(x)) {
				whichList = whichList.next;
			}

			if (whichList == null) {
				chain[myhash(x)] = new List(x, chain[myhash(x)]);
				isContain = true;
			} else {
				whichList.freq++;
				isContain = false;
			}
		}
		return isContain;
	}

	public void insert(String x, double count) {
		List<String> whichList = chain[myhash(x)];
		if (whichList == null) {
			chain[myhash(x)] = new List<>();
			chain[myhash(x)].freq = count;
		} else {
			while (whichList != null && !whichList.element.equals(x)) {
				whichList = whichList.next;
			}

			if (whichList == null) {
				chain[myhash(x)] = new List(x, chain[myhash(x)]);

			} else {
				whichList.freq++;

			}
		}
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

		DataCount<String>[] dataCounts = new DataCount[currentSize];
		int i = 0;
		for (List theList : chain) {
			for (; theList != null; theList = theList.next) {
				dataCounts[i++] = new DataCount(theList.element,
						(int) theList.freq);
			}
		}
		return dataCounts;
	}

	/**
	 * The number of unique data elements in the structure.
	 * 
	 * @return the number of unique data elements in the structure.
	 */
	public int getSize() {

		return currentSize;
	}

	public double loadFactor() {
		double lf = currentSize / chain.length;
		return lf;
	}

	/**
	 * Increment the count for a particular data element.
	 * 
	 * @param data
	 *            data element whose count to increment.
	 */
	public void incCount(String data) {
		if (contain(data))
			currentSize++;
		if (loadFactor() > 1)
			rehash();
	}

}
