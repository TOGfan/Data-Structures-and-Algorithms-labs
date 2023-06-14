import java.util.NoSuchElementException;

public class BST<T> {
	private class Node{
		T value;
		Node left,right,parent;
		public Node(T v) {
			value=v;
		}
		public Node(T value, Node left, Node right, Node parent) {
			super();
			this.value = value;
			this.left = left;
			this.right = right;
			this.parent = parent;
		}
	}		
	private Node root=null;

	public BST() {
	}
	@SuppressWarnings({ "unchecked", "rawtypes" })
	private Node getElement(T toFind, Node temp) {
		if(temp==null) {
			return null;
		}
		if (((Comparable)toFind).compareTo(temp.value)==0) {
			return temp;
		}
		if (((Comparable)toFind).compareTo(temp.value)<0) {
			return getElement(toFind,temp.left);
		}
		else return getElement(toFind,temp.right);
	}
	
	public T getElement(T toFind) {
		Node n = getElement(toFind,root);
		if(n==null) {
			return null;
		}
		return n.value;
	}
	
	private Node TreeMinimum(Node n){
		while(n.left!=null) {
			n=n.left;
		}
		return n;
	}
	
	private Node successorNode(T elem) {
		Node z = getElement(elem,root);
		if(z==null) {
			return null;
		}
		if (z.right != null) {
			return TreeMinimum(z.right);
		}
		Node y = z.parent;
		while (y != null&&z == y.right){
			z = y;
			y = y.parent;
		}
		return y;
	}
	
	public T successor(T elem) {
	    Node n = successorNode(elem);
	    if(n==null){
	        return null;
	    }
		return n.value;
	}
	
	private String toStringInOrder(Node n) {
		if(n==null) {
			return "";
		}
		else return toStringInOrder(n.left)+n.value.toString()+", "+toStringInOrder(n.right);
	}
	
	private String toStringPreOrder(Node n) {
		if(n==null) {
			return "";
		}
		else return n.value.toString()+", "+toStringPreOrder(n.left)+toStringPreOrder(n.right);
	}
	
	public String toStringPostOrder(Node n) {
		if(n==null) {
			return "";
		}
		else return toStringPostOrder(n.left)+toStringPostOrder(n.right)+n.value.toString()+", ";
	}
	
	public String toStringInOrder() {
		String s = toStringInOrder(root);
		if(!(s.equals(""))){
		    s=s.substring(0, s.length()-2);    
		}
		return s;
	}

	public String toStringPreOrder() {
		String s = toStringPreOrder(root);
		if(!(s.equals(""))){
		    s=s.substring(0, s.length()-2);    
		}
		return s;
	}

	public String toStringPostOrder() {
		String s = toStringPostOrder(root);
		if(!(s.equals(""))){
		    s=s.substring(0, s.length()-2);    
		}
		return s;
	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	private Node next(Node temp, T value) throws Exception {
		if(((Comparable)value).compareTo(temp.value)==-1){
			return temp.left;
		}else if(((Comparable)value).compareTo(temp.value)==0){
			    throw new Exception();
		}else{
			return temp.right;
		}
	}
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public boolean add(T elem) {
		if(root==null) {
			root = new Node(elem);
		}else {
			Node temp=root;
			try{
			while(next(temp,elem)!=null) {
				temp=next(temp,elem);
			}
			}catch(Exception ex){
			    return false;
			}
			if(((Comparable)elem).compareTo(temp.value)==-1){
				temp.left=new Node(elem,null,null,temp);
			}else if(((Comparable)elem).compareTo(temp.value)==0){
			    return false;
			}else{
				temp.right=new Node(elem,null,null,temp);
			}
		}
		return true;
	}


	public T remove(T value) {
		Node z = getElement(value,root);
		if(z==null) {
			return null;
		}
		Node y,x;
		if(z.left==null||z.right==null) {
			y=z;
		}
		else {
			y=successorNode(value);
		}
		if(y.left!=null) {
			x=y.left;
		}else {
			x=y.right;
		}
		if(x!=null) {
			x.parent=y.parent;
		}
		if(y.parent==null) {
			root=x;
		}
		else if(y==y.parent.left) {
			y.parent.left=x;
		}else {
			y.parent.right=x;
		}
		if(y!=z) {
			T temp=z.value;
			z.value=y.value;
			y.value=temp;
		}
		return y.value;
	}
	
	public void clear() {
		root=null;
	}
	private int size(Node n) {
		if(n==null) {
			return 0;
		}
		return size(n.left)+1+size(n.right);
	}
	public int size() {
		return size(root);
	}

}
