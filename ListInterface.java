public interface ListInterface<E> {
	/**
	 * adds an item to the list
	 */
	public abstract void add(E item);
	
	/**
	 * removes the item from the list if it exists as
	 * part of the list and return true otherwise
	 * return false without changing the list
	 * @param item
	 * @return
	 */
	public abstract boolean remove(E item);
	
	/**
	 * returns true if the item exists on the list otherwise
	 * returns false and in either case this method does
	 * not change the list
	 * @param item
	 * @return
	 */
	public abstract boolean find(E item);
	
	/**
	 * This heading is not necessary because all method headings
	 * of the Object class are already included in every interface,
	 * but we are adding it to make it more clear that this method
	 * should be part of the class
	 */
	public abstract String toString();

	public abstract E get(int i);
	public abstract int getSize();
}
