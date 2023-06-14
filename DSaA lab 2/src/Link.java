public class Link{
	public String ref;
	public Link(String ref) {
		this.ref=ref;
	}
	public boolean equals(Object ob){
	    if(!(ob instanceof Link)) return false;
	    return this.ref.equals(((Link)ob).ref);
	}
	// in the future there will be more fields
}