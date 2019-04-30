package estructuras;

import java.util.Iterator;

public class MyLinkedList implements Iterable<Object>{ 
	
	private Node first;
	private int size;
	
	public MyLinkedList() { 
		first = null;
		size = 0;
	}
	
	@Override
	public Iterator<Object> iterator() {
		return new MyIterator(first);
	}
	
	public void insertFront(Object o) { 
		Node tmp = new Node(o, first); 
		first = tmp;
		size = size + 1;
	}
	
	public boolean isEmpty() {
		if (first == null) {
			return true;
		}
		return false;
	}
	
	public int size () {
		return size;
	}
	
	public Object get(int index) {
		Node tmp = first;
		for (int i = 0; i < index; i++) {
			tmp = tmp.getNext();
		}
		return tmp.getInfo();
	}
	
	public boolean contains(Object elem) {
		Node tmp = first;
		boolean result = false;
		for (int i = 0; i < size; i++) {
			if (tmp.getInfo().equals(elem)) {
				result = true;
				break;
			}
			tmp = tmp.getNext();
		}
		return result;
	}

}