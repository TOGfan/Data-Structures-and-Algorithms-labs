import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Queue;
import java.util.Set;
import java.util.SortedMap;
import java.util.concurrent.LinkedTransferQueue;

public class Graph {
	int arr[][];
	//TODO? Collection to map Document to index of vertex 
	// You can change it
	HashMap<String,Integer> name2Int;
	@SuppressWarnings("unchecked")
	//TODO? Collection to map index of vertex to Document
	// You can change it
	String[] arrDoc;



	// The argument type depend on a selected collection in the Main class
	public Graph(SortedMap<String,Document> internet){
		Document temp[]= internet.values().toArray(new Document[0]);
		int size=internet.size();
		arr=new int[size][size];
		name2Int = new HashMap<String, Integer>(temp.length);
		arrDoc = new String[temp.length];
		for (int i = 0; i < temp.length; i++) {
			name2Int.put(temp[i].getName(),i);
			arrDoc[i]= temp[i].getName();
			for (int j = 0; j < size; j++) {
				arr[i][j]=-1;
			}
			arr[i][i]=0;
		}
		for (int i = 0; i < temp.length; i++) {
			for (int j = 0; j < temp[i].link.size(); j++) {
				if(name2Int.get(temp[i].link.get(j).ref)==null){
					continue;
				}
				arr[i][name2Int.get(temp[i].link.get(j).ref)]=temp[i].link.get(j).weight;
			}
		}

	}
	public String bfs(String start) {
		if (name2Int.get(start)==null){
			return null;
		}
		String output= "";
		int s= name2Int.get(start);
		boolean[]isWhite = new boolean[arr.length];
		for(int i=0;i<arr.length;i++) {
			isWhite[i] = true;
		}
		isWhite[s]=false;
		LinkedList<Integer> queue = new LinkedList<Integer>();
		queue.add(s);
		while (!queue.isEmpty()){
			int u=queue.poll();
			for (int i=0;i<arr.length;i++){
				if(arr[u][i]>0&&isWhite[i]){
					isWhite[i]=false;
					queue.add(i);
				}
			}
			isWhite[u]=false;
			output+=arrDoc[u]+", ";
		}
		if(output.length()>2){
			output=output.substring(0,output.length()-2);
		}
		return output;
	}

	private String dfsRecursive(boolean[] visited, int current){
		String s=arrDoc[current]+", ";
		for (int i = 0; i < arr.length; i++) {
			if(arr[current][i]>0&&visited[i]==false){
				visited[i]=true;
				s+=dfsRecursive(visited,i);
			}
		}
	return s;
	}
	public String dfs(String start) {//depth first search
		boolean [] b = new boolean[arr.length];
		for (int i = 0; i < b.length; i++) {
			b[i]=false;
		}
		b[name2Int.get(start)]=true;
		String output= dfsRecursive(b,name2Int.get(start));
		if(output.length()>2){
			output=output.substring(0,output.length()-2);
		}
		return output;
	}

	public int connectedComponents() {
		int num=0;
		DisjointSetForest temp = new DisjointSetForest(arr.length);
		for (int i = 0; i < arr.length; i++) {
			temp.makeSet(i);
		}
		for (int i = 0; i < arr.length; i++) {
			for (int j = 0; j < arr.length; j++) {
				if(arr[i][j]>0){
					temp.union(i,j);
				}
			}
		}
		return temp.countSets();
	}
	private void DijikstraRecursive(String[] docStrings, int[] pathWeights,int start){
		for (int i = 0; i < arr.length; i++) {
			if(arr[start][i]>0&&arr[start][i]+pathWeights[start]<pathWeights[i]){
				pathWeights[i]=arr[start][i]+pathWeights[start];
				docStrings[i]=docStrings[start]+"->"+arrDoc[i];
				DijikstraRecursive(docStrings,pathWeights,i);
			}
		}
	}
	public String DijkstraSSSP(String startVertexStr) {
		if(name2Int.get(startVertexStr)==null){
			return null;
		}
		String[] docStrings = new String[arr.length];
		for (int i = 0; i < docStrings.length; i++) {
			docStrings[i]="";
		}
		docStrings[name2Int.get(startVertexStr)]=startVertexStr;
		int[] pathWeights = new int[arr.length];
		for (int i = 0; i < pathWeights.length; i++) {
			pathWeights[i]=Integer.MAX_VALUE;
		}
		pathWeights[name2Int.get(startVertexStr)]=0;
		DijikstraRecursive(docStrings,pathWeights,name2Int.get(startVertexStr));
		String output="";
		for (int i = 0; i < docStrings.length; i++) {
			if(docStrings[i]==""){
				output+="no path to "+arrDoc[i]+"\n";
			}
			else{
				output+=docStrings[i]+"="+pathWeights[i]+"\n";
			}

		}
		return output;
	}
}
/*
#Test for Lab11
ld x
link=y(9)
eod
ld y
eod
ld a
link=b(2)
link=c(5)
eod
ld c
link=d(5)
link=f(6)
eod
ld b
link=d(3)
link=e(4)
eod
ld e
link=d(3)
link=f(4)
link=h(2)
link=g(8)
eod
ld d
link=e(3)
link=f(1)
eod
ld h
link=e(2)
link=g(1)
eod
ld g
link=h(1)
link=f(7)
eod
ld f
link=d(1)
link=e(4)
link=g(7)
eod
sssp a
sssp d
sssp w
ha

 */