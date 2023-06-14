
public class DisjointSetForest implements DisjointSetDataStructure {
	
	private class Element{
		int rank;
		int parent;
		private Element(){
			rank=0;
			parent=-1;
		}
	}

	Element []arr;
	
	public DisjointSetForest(int size) {
		arr = new Element[size];
		for(int i=0;i<size;i++){
			arr[i]=new Element();
		}
	}
	
	@Override
	public void makeSet(int item) {
		arr[item].parent=item;
	}

	@Override
	public int findSet(int item) {
		if (item!=arr[item].parent)
		arr[item].parent = findSet(arr[item].parent);
		return arr[item].parent;
	}

	@Override
	public boolean union(int itemA, int itemB) {
		itemA=findSet(itemA);
		itemB=findSet(itemB);
		if(itemA==itemB){
			return false;
		}
		if (arr[itemA].rank>arr[itemB].rank)
			arr[itemB].parent=itemA;
		else {
			arr[itemA].parent = itemB;
			if (arr[itemA].rank == arr[itemB].rank)
				arr[itemB].rank = arr[itemB].rank + 1;
		}
		return true;
	}

	
	@Override
	public String toString() {
		String s = "Disjoint sets as forest:\n";
		for (int i = 0; i < arr.length; i++) {
			s=s+i+" -> "+arr[i].parent+"\n";
		}
		return s.substring(0,s.length()-1);
	}
}
