import java.util.LinkedList;

public class DisjointSetLinkedList implements DisjointSetDataStructure {

	private class Element{
		int representant;
		int next;
		int length;
		int last;
		private Element(){
			representant=NULL;
			next=NULL;
			length=0;
			last=NULL;
		}
	}

	private static final int NULL=-1;
	
	Element arr[];
	
	public DisjointSetLinkedList(int size) {
		arr = new Element[size];
		for (int i = 0; i < arr.length; i++) {
			arr[i]=new Element();
		}
	}
	
	@Override
	public void makeSet(int item) {
		arr[item].representant=item;
		arr[item].length=1;
		arr[item].last=item;
	}

	@Override
	public int findSet(int item) {
		return arr[item].representant;
	}

	@Override
	public boolean union(int itemA, int itemB) {
		if(arr[itemA].representant!=itemA){
			itemA=arr[itemA].representant;
		}
		if(arr[itemB].representant!=itemB){
			itemB=arr[itemB].representant;
		}
		if(itemA==itemB){
			return false;
		}
		if(arr[itemA].length<arr[itemB].length){
			int temp=itemA;
			itemA=itemB;
			itemB=temp;
		}
		arr[arr[itemA].last].next=itemB;
		arr[itemA].last=arr[itemB].last;
		int i=itemB;
		while(i!=NULL){
			arr[i].representant=itemA;
			i=arr[i].next;
		}
		arr[itemA].length+=arr[itemB].length;
		return true;
	}

	
	@Override
	public String toString() {
		String s = "Disjoint sets as linked list:\n";
		for (int i = 0; i < arr.length; i++) {
			if(arr[i].representant==i) {
				int j = i;
				s=s+i;
				while (arr[j].next != NULL) {
					j = arr[j].next;
					s = s + ", " + j;
				}
				s = s + "\n";
			}
		}
		return s.substring(0,s.length()-1);
	}

}
