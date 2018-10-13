import java.util.Collection;
import java.util.Iterator;
import java.util.Queue;

public class ArrayQueue<E> implements Queue<E> {
	private final static int INITCAP = 4; 
	private E[] elements; 
	private int first, size; 
	public ArrayQueue() { 
		elements = (E[]) new Object[INITCAP]; 
		first = 0; 
		size = 0; 
	}
	public int size() {
		return size;
	}

	public boolean isEmpty() {
		return size == 0;
	}

	public E first() {
		if (isEmpty()) return null; 
		return elements[first]; 
	}

	public E dequeue() {
		if (isEmpty()) return null;
		E etr = elements[first]; elements[first] = null;
		first = (first + 1) % elements.length;
		size--;
		// adjust capacity if too many empty slots in the array, but never to a value < INITCAP
		if (size <= elements.length/4 && elements.length > INITCAP)
		changeCapacity(elements.length/2); // adjust capacity to 1/2 of current capacity
		return etr;
		}
	

	public void enqueue(E e) {
		if (size == elements.length) // adjust capacity if needed
		changeCapacity(2*size);
		elements[(first + size) % elements.length] = e; // place in first empty slot after last element
		size++;
		}

	private void changeCapacity(int newCapacity) { // change capacity to newCapacity
		E[] temp = (E[]) new Object[newCapacity];
		for (int i=0; i<size; i++) { 
		int itm = (first + i) % elements.length;
		temp[i] = elements[itm];
		elements[itm] = null;
		}
		elements = temp; 
		first = 0;
	}
	@Override
	public boolean addAll(Collection<? extends E> c) {
		// TODO Auto-generated method stub
		return false;
	}
	@Override
	public void clear() {
		// TODO Auto-generated method stub
		
	}
	@Override
	public boolean contains(Object o) {
		// TODO Auto-generated method stub
		return false;
	}
	@Override
	public boolean containsAll(Collection<?> c) {
		// TODO Auto-generated method stub
		return false;
	}
	@Override
	public Iterator<E> iterator() {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public boolean remove(Object o) {
		// TODO Auto-generated method stub
		return false;
	}
	@Override
	public boolean removeAll(Collection<?> c) {
		// TODO Auto-generated method stub
		return false;
	}
	@Override
	public boolean retainAll(Collection<?> c) {
		// TODO Auto-generated method stub
		return false;
	}
	@Override
	public Object[] toArray() {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public <T> T[] toArray(T[] a) {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public boolean add(E e) {
		// TODO Auto-generated method stub
		return false;
	}
	@Override
	public E element() {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public boolean offer(E e) {
		// TODO Auto-generated method stub
		return false;
	}
	@Override
	public E peek() {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public E poll() {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public E remove() {
		// TODO Auto-generated method stub
		return null;
	}
}
