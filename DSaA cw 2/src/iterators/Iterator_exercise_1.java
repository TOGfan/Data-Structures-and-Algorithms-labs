package iterators;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class Iterator_exercise_1 implements Iterator<Integer> {
	private Integer pos;
	private FilterGenerator range;
	public Iterator_exercise_1() {
		pos=0;
	}

	public boolean hasNext() {
		return pos < 
	}

	public Integer next() throws NoSuchElementException {
		if (hasNext()) {
			pos=pos+2;
			return pos;
		}
		else
			throw new NoSuchElementException();
	}

	public void remove() {
		throw new UnsupportedOperationException();
	}

}
