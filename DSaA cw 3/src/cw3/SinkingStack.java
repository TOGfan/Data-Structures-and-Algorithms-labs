package cw3;

import java.util.NoSuchElementException;

public class SinkingStack<E> {
	E[] arr;
	int beg;
	int end;
	int currentSize;
	@SuppressWarnings("unchecked")
	public SinkingStack(int initialSize) {
		arr = (E[])(new Object[initialSize]);
		beg=0;
		end=0;
		currentSize=0;
	}
	private int moveForward(int n) {
		if(n==arr.length-1) {
			return 0;
		}else {
			return n+1;
		}
	}
	private int moveBackward(int n) {
		if(n==0) {
			return arr.length-1;
		}else {
			return n-1;
		}
	}
	public void push(E object) {
		beg=moveBackward(beg);
		arr[beg]=object;
		if(currentSize<arr.length) {
			currentSize++;
		}
		else {
			end=moveBackward(end);
		}
	}
	public E pop() {
		if(currentSize==0) {
			throw new NoSuchElementException();
		}
		E temp = arr[beg];
		arr[beg]=null;
		beg=moveForward(beg);
		currentSize--;
		return temp;
	}
	
}
