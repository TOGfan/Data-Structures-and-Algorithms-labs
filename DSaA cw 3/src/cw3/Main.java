package cw3;

public class Main {

	public static void main(String[] args) {
		Integer[] testNumbers= {1,2,3,4,5};
		VTS<Integer> vts = new VTS<Integer>();
		Queue<Integer> queue = new Queue<Integer>();
		SinkingStack<Integer> sinkingStack = new SinkingStack<Integer>(testNumbers.length);
		for(int i=0;i<testNumbers.length;i++) {
			vts.push(testNumbers[i]);
			queue.push(testNumbers[i]);
			sinkingStack.push(testNumbers[i]);
		}
		for(int i=0;i<testNumbers.length;i++) {
			System.out.println(vts.pop());
			System.out.println(queue.pop());
			System.out.println(sinkingStack.pop());
		}
	}

}
