import java.util.Iterator;
import java.util.ListIterator;
import java.util.NoSuchElementException;


public class TwoWayUnorderedListWithHeadAndTail<E> implements IList<E>{
	
	private class Element{
		public Element(E e) {
			object=e;
		}
		public Element(E e, Element next, Element prev) {
			object=e;
			this.next=next;
			this.prev=prev;
		}
		E object;
		Element next=null;
		Element prev=null;
	}
	
	Element head;
	Element tail;
	private int size;
	
	private class InnerIterator implements Iterator<E>{
		Element pos;	
		public InnerIterator() {
			pos=head;
		}
		@Override
		public boolean hasNext() {
			return pos!=null;
		}
		
		@Override
		public E next() {
			if(pos==null) {
				throw new NoSuchElementException();
			}
			E temp = pos.object;
			pos=pos.next;
			return temp;
		}
	}
	
	private class InnerListIterator implements ListIterator<E>{
		
		Element p;

		public InnerListIterator() {
			p=head;
		}
		
		@Override
		public void add(E e) {
			throw new UnsupportedOperationException();
		}

		@Override
		public boolean hasNext() {
			if(p==null)
					return false;
			return p.next!=null;
		}

		@Override
		public boolean hasPrevious() {
			return p!=null;
		}

		@Override
		public E next() {
			if(p.next==null) {
				throw new NoSuchElementException();
			}
			p=p.next;
			return p.object;
		}

		@Override
		public int nextIndex() {
			throw new UnsupportedOperationException();
		}

		@Override
		public E previous() {
			if(p==null) {
				throw new NoSuchElementException();
			}
			E temp = p.object;
			p=p.prev;
			return temp;
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
		public void set(E e) {
			p.object=e;
		}
	}
	
	public TwoWayUnorderedListWithHeadAndTail() {	
		head=null;
		tail=null;
		size=0;
	}
	
	private Element goTo(int pos) {
		if(pos<0||pos>=size) {
			throw new NoSuchElementException();
		}
		Element temp;
		if(pos>size/2) {
			temp=tail;
			for(int i=this.size-1;i>pos;i--) {
				temp=temp.prev;
			}			
		}else {
			temp=head;
			for(int i=0;i<pos;i++) {
				temp=temp.next;
			}
		}
		return temp;
	}
	
	@Override
	public boolean add(E e) {
		if(tail==null) {
			tail=new Element(e);
			head=tail;
			size++;
			return true;
		}
		tail.next=new Element(e,null,tail);
		tail=tail.next;
		size++;
		return true;
	}

	@Override
	public void add(int index, E element) {
		if(index==size) {
			add(element);
			return;
		}
		Element temp=new Element(element);
		temp.next=goTo(index);
		temp.prev=temp.next.prev;
		temp.prev.next=temp;
		temp.next.prev=temp;
		size++;
	}

	@Override
	public void clear() {
		head=null;
		tail=null;
		size=0;
	}

	@Override
	public boolean contains(E element) {
		return indexOf(element)!=-1;
	}

	@Override
	public E get(int index) {
		return goTo(index).object;
	}

	@Override
	public E set(int index, E element) {
		E temp=goTo(index).object;
		goTo(index).object=element;
		return temp;
	}

	@Override
	public int indexOf(E element) {
		if(isEmpty()) {
			return -1;
		}
		Element temp=head; 
		for(int i=0;temp.next!=null;i++) {
			if(element.equals(temp.object)) {
				return i;
			}
			temp=temp.next;
		}
		return -1;
	}

	@Override
	public boolean isEmpty() {
		return head==null;
	}

	@Override
	public Iterator<E> iterator() {
		return new InnerIterator();
	}

	@Override
	public ListIterator<E> listIterator() {
		throw new UnsupportedOperationException();
	}

	@Override
	public E remove(int index) {
		if(index<0||index>=size) {
			throw new NoSuchElementException();
		}
		Element temp=goTo(index);
		if(size<2) {
			clear();
			return temp.object;
		}
		if(index==size-1) {
			tail=tail.prev;
			tail.next=null;
		}else {
			temp.next.prev=temp.prev;
		}
		if(index==0) {
			head=head.next;
			head.prev=null;
		}else {
			temp.prev.next=temp.next;
		}
		size--;
		return temp.object;
	}

	@Override
	public boolean remove(E e) {
		int pos=indexOf(e);
		if(pos==-1) {
			return false;
		}
		remove(pos);
		return true;
	}

	@Override
	public int size() {
		return size;
	}
	
	public String toStringReverse() {
		ListIterator<E> iter=new InnerListIterator();
		while(iter.hasNext())
			iter.next();
		String retStr="";
		while(iter.hasPrevious()) {
			retStr=retStr+"\n"+iter.previous().toString();
		}
		return retStr;
	}

	public void add(TwoWayUnorderedListWithHeadAndTail<E> anotherList) {
		if(anotherList.equals(this)) {
			return;
		}
		if(anotherList.isEmpty()){
		    return;
		}
		if(!this.isEmpty()){
		    	this.tail.next=anotherList.head;
		    	anotherList.head.prev=this.tail;
		}else{
		    this.head=anotherList.head;
		}
		this.size=this.size+anotherList.size();
		this.tail=anotherList.tail;
		anotherList.clear();
	}
}

