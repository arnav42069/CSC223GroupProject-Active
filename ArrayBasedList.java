import java.util.Iterator;
import java.util.NoSuchElementException;

@SuppressWarnings("ALL")
public class ArrayBasedList<E> implements ListInterface<E> {

	//instance variables
	private int numberOfItems;
	private E[] list; //reference to an array of type E, where is filled in with a type
					  //argument
	
	//default constructor
	public ArrayBasedList() {
		super();
		numberOfItems = 0;
		list =  (E[])new Object[5];
	}
	
	//overloaded constructor
	public ArrayBasedList(int size) {
		super();
		numberOfItems = 0;
		if(size > 0) {
			list = (E[])new Object[size];
		}
		else {
			list = (E[])new Object[5];
		}
	}
	
	@Override
	public void add(E item) {
		if(numberOfItems < list.length) { //if there is room in the array
			list[numberOfItems] = item; //place the item in the list
			numberOfItems++;
		}
		else { //create an array of double the size of the original list
			E[] largerList = (E[])new Object[list.length + list.length]; //dynamic array 
			for(int index = 0; index < numberOfItems; index++) {
				largerList[index] = list[index]; //copy the items from the list
			}
			list = largerList;
			largerList[numberOfItems] = item;
			numberOfItems++;
			
		}
		
	}
	/**
	 * private methods are inaccessible outside of the class that they
	 * are defined or declared.  These methods are called helper methods
	 * which means they help other methods in the same do their work.
	 */
	private int locate(E item) {
		boolean found = false;
		int index = 0;
		while(!found && index < numberOfItems) { 
			if(list[index].equals(item)) {
				found = true;
			}
			else {
				index++;
			}
		}
		if(found == false) {
			index = -1; //-1 represents the item was not found
		}
		return index;
	}

	@Override
	public boolean remove(E item) {
		// TODO Auto-generated method stub
		/*boolean found = false;
		int index = 0;
		while(!found && index < numberOfItems) { 
			if(list[index].equals(item)) {
				found = true;
			}
			else {
				index++;
			}
		}*/
		int index = locate(item);
		boolean removed = false;
		if(index != -1) { //the item was found and now remove it
			list[index] = null;
			numberOfItems--;
			list[index] = list[numberOfItems];
			list[numberOfItems] = null;
			removed = true;
		}
		return removed;
	}

	@Override
	public boolean find(E item) {
		// TODO Auto-generated method stub
		/*boolean found = false;
		int index = 0;
		while(!found && index < numberOfItems) { 
			if(list[index].equals(item)) {
				found = true;
			}
			else {
				index++;
			}
		}
		return found;*/
		int index = locate(item);
		boolean found = false;
		if(index != -1) {
			found = true;
		}
		return found;
	}

	
	public String toString() {
		String allItems = "";
		for(int index = 0; index < numberOfItems; index++) {
			allItems = allItems + "\n" + list[index].toString();
		}
		return allItems;
	}

	@Override
	public E get(int i) {
		if(list[i] != null) {
			return (E)list[i];
		}
		else {
			return null;
		}
	}

	@Override
	public int getSize() {
		return locate((E) this) + 1;
	}

	/**
	 * Returns an object which from a class that implements
	 * the Iterator interface 
	 * @return
	 */
	
	public Iterator<E> iterator() {
		return new ArrayBasedListIterator();
	}
	
	/**
	 * ArrayBasedListIterator is an inner class defined completely inside of
	 * ArrayBasedList
	 */
	private class ArrayBasedListIterator extends Object implements Iterator {
		//instance variables
		private int currentLocation; //represents whihc part of the list you are on
		
		//default constructor
		public ArrayBasedListIterator() {
			super();
			currentLocation = 0;
		}

		@Override
		public boolean hasNext() {
			// TODO Auto-generated method stub
			return currentLocation < numberOfItems;
		}

		@Override
		public E next() throws NoSuchElementException {
			// TODO Auto-generated method stub
			if(currentLocation >= numberOfItems) {
				throw new NoSuchElementException("Reached the end of the list!");
			}
			else {
				return list[currentLocation++];
			}
		}
	}
	
	
}
