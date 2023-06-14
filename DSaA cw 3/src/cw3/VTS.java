package cw3;


public class VTS<E> {
	
	private class Element{
		E object;
		Element next;
		public Element(E object, Element next){
			this.object=object;
			this.next=next;
		}	
	}
	private Element top;
	private Element cursor;
	public VTS() {
		top=null;
		cursor=null;
		
	}
	public boolean isEmpty() {
		return top==null;
	}
	public void push(E object) {
		top=new Element(object, top);
		cursor=top;
	}
	public E pop() {
		
		Element temp=top;
		top=top.next;
		cursor=top;
		return temp.object;
	}
	public void top() {
		cursor=top;
	}
	public boolean down() {
		if(cursor.next==null) {
			return false;
		}
		else {
			cursor=cursor.next;
			return true;
		}
	}
	public E peek() {
		return cursor.object;
	}
	public void reverse(){
		VTS<E> temp=new VTS<E>();
		while(!isEmpty()) {
			temp.push(this.pop());
		}
		temp.top();
		VTS<E> temp2 = new VTS<E>();
		while(!temp.isEmpty()) {
			temp2.push(temp.pop());
		}
		while(!temp2.isEmpty()) {
			this.push(temp.pop());
		}
	}
	
}
