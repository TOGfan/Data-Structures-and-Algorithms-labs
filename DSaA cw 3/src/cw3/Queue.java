package cw3;

import java.util.NoSuchElementException;

public class Queue<E> {
	VTS<E> stack1;
	VTS<E> stack2;
	public Queue(){
		stack1 = new VTS<E>();
		stack2 = new VTS<E>();
	}
	public void push(E object) {
		stack1.push(object);
	}
	public E pop() {
		if(stack1.isEmpty()) {
			throw new NoSuchElementException();
		}
		while(!stack1.isEmpty())
			stack2.push(stack1.pop());
		E temp = stack2.pop();
		while(!stack2.isEmpty())
			stack1.push(stack2.pop());
		return temp;
	} 
}
