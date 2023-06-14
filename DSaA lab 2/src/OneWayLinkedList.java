import java.util.Iterator;
import java.util.ListIterator;
import java.util.NoSuchElementException;

public class OneWayLinkedList<E> implements IList<E>{
	
	private class Element{
		public Element(E e) {
			this.object=e;
		}
		E object;
		Element next=null;
	}
	
	private Element sentinel;
	private int size;
	
	private class InnerIterator implements Iterator<E>{
		private Element cursor;
		public InnerIterator() {
			cursor=sentinel;
		}
		@Override
		public boolean hasNext() {
			return cursor.next!=null;
		}
		
		@Override
		public E next() {
			if(!hasNext()) {
				throw new NoSuchElementException();
			}
			cursor=cursor.next;
			return cursor.object;
		}
	}
	
	public OneWayLinkedList() {
		sentinel = new Element(null);
        size=0;
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
	public boolean add(E e) {
	    Element last=sentinel;
		while(last.next!=null){
		    last=last.next;
		}
		last.next = new Element(e);
		size++;
		return true;
	}

	@Override
	public void add(int index, E element) throws NoSuchElementException {
	    if(index<0||index>size)throw new NoSuchElementException();
		Element newElement=new Element(element);
		newElement.next=sentinel.next;
		Element prev = sentinel;
		for(int i=0;i<index;i++) {
			prev=newElement.next;
			newElement.next=newElement.next.next;
		}
		prev.next=newElement;
        size++;
	}

	@Override
	public void clear() {
		sentinel.next=null;
		size=0;
	}

	@Override
	public boolean contains(E element) {
	    Element temp=sentinel;
		for(int i=0;i<size;i++) {
		    temp=temp.next;
			if(element.equals(temp.object)){
				return true;
			}
		}
		return false;
	}

	@Override
	public E get(int index) throws NoSuchElementException {
	    if(index<0||index>=size)throw new NoSuchElementException();
		E temp=null;
		Iterator<E> iter=iterator();
		for(int i=0;i<=index;i++) {
			temp=iter.next();
		}
		if(temp==null){
		    throw new NoSuchElementException();
		}
		return temp;
	}

	@Override
	public E set(int index, E element) throws NoSuchElementException {
	    if(index<0||index>=size)throw new NoSuchElementException();
		Element temp = sentinel;
		for(int i=0;i<=index;i++) {
			temp=temp.next;
		    if(temp==null){
		        throw new NoSuchElementException();
		    }
		}
		E obj = temp.object;
		temp.object=element;
		
		return obj;
	}

	@Override
	public int indexOf(E element) {
		Element temp=sentinel;
		for(int i=0;i<size;i++) {
		    temp=temp.next;
			if(element.equals(temp.object)){
				return i;
			}
		}
		return -1;
	}

	@Override
	public boolean isEmpty() {
		return sentinel.next==null;
	}

	@Override
	public E remove(int index) throws NoSuchElementException {
	    if(index<0||index>=size)throw new NoSuchElementException();
		Element temp = sentinel;
		if(temp.next==null){
		    throw new NoSuchElementException();
		}
		for(int i=0;i<index;i++) {
			temp=temp.next;
			if(temp.next==null){
		        throw new NoSuchElementException();
		    }
		}
		Element temp2 = temp.next;
		temp.next=temp.next.next;
		size--;
		return temp2.object;
	}

	@Override
	public boolean remove(E e) {
		int index=indexOf(e);
		if(index==-1){
		    return false;
		}
		try{
		    this.remove(index);
		    return true;
		}catch(NoSuchElementException ex){
		    return false;
		}
	}

	@Override
	public int size() {
		Element temp=sentinel;
		int counter=0;
		while(temp.next!=null) {
			counter++;
			temp=temp.next;
		}
		return counter;
	}
	
}
/*
go 10
ld doc1
link=a and link=b
write also link=c end finish.
eod
show
size
add d
size
show
rem a
remi 2
show
get 0
set 1 w
show
ch 1
ld doc2
eod
show
remi 0
index x
ha

*/
