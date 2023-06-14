import java.util.LinkedList;

public class HashTable{
	@SuppressWarnings("rawtypes")
	LinkedList arr[]; // use pure array
	private final static int defaultInitSize=8;
	private final static double defaultMaxLoadFactor=0.7;
	private int size;	
	private final double maxLoadFactor;
	public HashTable() {
		this(defaultInitSize);
	}
	public HashTable(int size) {
		this(size,defaultMaxLoadFactor);
	}

	@SuppressWarnings("rawtypes")
	public HashTable(int initCapacity, double maxLF) {
		this.arr = new LinkedList[initCapacity];
		this.maxLoadFactor=maxLF;
		this.size=0;
		for(int i=0;i<arr.length;i++) {
			arr[i] = new LinkedList();
		}
	}

	@SuppressWarnings("unchecked")
	public boolean add(Object elem) {
		if(this.arr[elem.hashCode()%arr.length].contains(elem)) {
			return false;
		}
		this.arr[elem.hashCode()%arr.length].add(elem);
		this.size++;
		if(((double)this.size/arr.length)>maxLoadFactor) {
			doubleArray();
		}
		return true;
	}

	
	@SuppressWarnings("rawtypes")
	private void doubleArray() {
		LinkedList[] temp = this.arr.clone();
		this.arr = new LinkedList[temp.length*2];
		this.size=0;
		for(int i=0;i<arr.length;i++) {
			arr[i] = new LinkedList();
		}
		for(int i=0;i<temp.length;i++) {
			for(int j=0;j<temp[i].size();j++) {
				this.add(temp[i].get(j));
			}
		}
	}


	@Override
	public String toString() {
		String s="";
		for(int i=0;i<arr.length;i++) {
			s=s+i+":";
			if(arr[i]!=null) {
			for(int j=0;j<arr[i].size();j++) {
				s=s+" "+((IWithName)(arr[i].get(j))).getName();
				if(j!=arr[i].size()-1) {
					s=s+",";
				}
			}
			s=s+"\n";
			}
		}
		// use	IWithName x=(IWithName)elem;
		return s;
	}

	public Object get(Object toFind) {
		if(arr[toFind.hashCode()%this.arr.length].indexOf(toFind)==-1) {
			return null;
		}
		return this.arr[toFind.hashCode()%this.arr.length].get(this.arr[toFind.hashCode()%this.arr.length].indexOf(toFind));
	}
	
}

