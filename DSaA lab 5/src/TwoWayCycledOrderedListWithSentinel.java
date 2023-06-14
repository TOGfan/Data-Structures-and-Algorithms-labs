import java.util.Iterator;
import java.util.ListIterator;
import java.util.NoSuchElementException;

public class TwoWayCycledOrderedListWithSentinel<E> implements IList<E> {

	private class Element {

		public Element(E e) {
			object = e;
			next = null;
			prev = null;
		}

		public Element(E e, Element next, Element prev) {
			object = e;
			this.next = next;
			this.prev = prev;
		}

		// add element e after this
		public void addAfter(Element elem) {
			next.prev = elem;
			elem.next = this.next;
			elem.prev = this;
			this.next = elem;

		}

		// assert it is NOT a sentinel
		public void remove() {
			if (this == sentinel) {
				return;
			}
			this.next.prev = this.prev;
			this.prev.next = this.next;
		}

		E object;
		Element next = null;
		Element prev = null;
	}

	Element sentinel;
	int size;

	private class InnerIterator implements Iterator<E> {
		Element cursor;

		public InnerIterator() {
			cursor = sentinel;
		}

		@Override
		public boolean hasNext() {
			return cursor.next != sentinel;
		}

		@Override
		public E next() {
			cursor = cursor.next;
			return cursor.object;
		}
	}

	private class InnerListIterator implements ListIterator<E> {
		Element cursor;

		public InnerListIterator() {
			cursor = sentinel;
		}

		@Override
		public boolean hasNext() {
			return cursor.next != sentinel;
		}

		@Override
		public E next() {
			cursor = cursor.next;
			return cursor.object;
		}

		@Override
		public void add(E arg0) {
			throw new UnsupportedOperationException();
		}

		@Override
		public boolean hasPrevious() {
			return cursor.prev != sentinel;
		}

		@Override
		public int nextIndex() {
			throw new UnsupportedOperationException();
		}

		@Override
		public E previous() {
			cursor = cursor.prev;
			return cursor.object;
		}

		@Override
		public int previousIndex() {
			throw new UnsupportedOperationException();
		}

		@Override
		public void remove() {
			throw new UnsupportedOperationException();
		}

		@Override
		public void set(E arg0) {
			throw new UnsupportedOperationException();
		}
	}

	public TwoWayCycledOrderedListWithSentinel() {
		sentinel = new Element(null);
		sentinel.next = sentinel;
		sentinel.prev = sentinel;
		size = 0;
	}

	// @SuppressWarnings("unchecked")
	@Override
	public boolean add(E e) {
		if (!(e instanceof Link)) {
			return false;
		}
		Element temp = sentinel.next;
		for (int i = 0; i < size; i++) {
			if ((((Link) temp.object).compareTo((Link) e)) > 0) {
				temp.prev.addAfter(new Element(e));
				size++;
				return true;
			}
			temp = temp.next;
		}
		sentinel.prev.addAfter(new Element(e));
		size++;
		return true;
	}

	private Element getElement(int index) {
		if (index < 0 || index >= size) {
			throw new NoSuchElementException();
		}
		Element temp;
		if (index > size / 2) {
			temp = sentinel.prev;
			for (int i = this.size - 1; i > index; i--) {
				temp = temp.prev;
			}
		} else {
			temp = sentinel.next;
			for (int i = 0; i < index; i++) {
				temp = temp.next;
			}
		}
		return temp;
	}

	private Element getElement(E obj) {
		Element temp = sentinel;
		for (int i = 0; i < size; i++) {
			temp = temp.next;
			if (temp.object.equals(obj)) {
				return temp;
			}
		}
		return null; // returns null if not found
	}

	@Override
	public void add(int index, E element) {
		throw new UnsupportedOperationException();
	}

	@Override
	public void clear() {
		sentinel.next = sentinel;
		sentinel.prev = sentinel;
		size = 0;
	}

	@Override
	public boolean contains(E element) {
		return getElement(element) != null;
	}

	@Override
	public E get(int index) {
		return getElement(index).object;
	}

	@Override
	public E set(int index, E element) {
		throw new UnsupportedOperationException();
	}

	@Override
	public int indexOf(E element) {
		Element temp = sentinel;
		for (int i = 0; i < size; i++) {
			temp = temp.next;
			if (temp.object.equals(element)) {
				return i;
			}
		}
		return -1;
	}

	@Override
	public boolean isEmpty() {
		return size == 0;
	}

	@Override
	public Iterator<E> iterator() {
		return new InnerIterator();
	}

	@Override
	public ListIterator<E> listIterator() {
		return new InnerListIterator();
	}

	@Override
	public E remove(int index) {
		Element temp = getElement(index);
		temp.remove();
		size--;
		return temp.object;
	}

	@Override
	public boolean remove(E e) {
		Element temp = getElement(e);
		if (temp == null) {
			return false;
		}
		temp.remove();
		size--;
		return true;
	}

	@Override
	public int size() {
		return size;
	}

	// @SuppressWarnings("unchecked")
	public void add(TwoWayCycledOrderedListWithSentinel<E> other) {

		Element temp1 = this.sentinel.next;
		Element temp2 = other.sentinel.next;
		Element temp3=null;
		//int j=0;
		/*for (int i = 0; i < other.size; i++) {
			for (; j < this.size; j++) {
				if ((((Link) temp1.object).compareTo((Link) temp2.object)) > 0) {
					temp1.prev.addAfter(temp2);
					this.size++;
				}
				temp1 = temp1.next;
			}
			temp2=temp2.next;
		}*/
		while(temp1!=sentinel) {
			while(temp2!=other.sentinel&&(((Link) temp1.object).compareTo((Link) temp2.object)) > 0) {
				temp3=temp2.next;
				temp2.remove();
				temp1.prev.addAfter(temp2);
				temp2=temp3;
				size++;
			}
			temp1=temp1.next;
		}
		while(temp2!=other.sentinel) {
			temp3=temp2.next;
			temp2.remove();
			temp1.prev.addAfter(temp2);
			temp2=temp3;
			size++;
		}
		

		// return true;
		
/*		for (int j=0; j < this.size; j++) {
			for (int i = 0; i < other.size; i++) {
				if ((((Link) temp1.object).compareTo((Link) temp2.object)) > 0) {
					temp2=temp2.next;
					temp1.prev.addAfter(temp2);
					this.size++;
				}
			

			temp1 = temp1.next;
		}*/
	}

	// @SuppressWarnings({ "unchecked", "rawtypes" })
	public void removeAll(E e) {
		while (remove(e));
	}

}
