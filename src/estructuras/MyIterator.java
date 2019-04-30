package estructuras;

import java.util.Iterator;

public class MyIterator implements Iterator<Object> {

	private Node node;
	
	public MyIterator(Node node) {
		this.node = node;
	}
	
	@Override
	public boolean hasNext() {
		if (node != null) {
			return true;
		}
		return false;
	}

	@Override
	public Object next() {
		Object value = node.getInfo();
		node = node.getNext();
		return value;
	}

}
