public class Link implements Comparable<Link>{
	public String ref;
	public int weight;
	public Link(String ref) {
		this.ref=ref;
		weight=1;
	}
	public Link(String ref, int weight) {
		this.ref=ref;
		this.weight=weight;
	}
	@Override
	public boolean equals(Object obj) {
		if(!(obj instanceof Link)) {
			return false;
		}else {
			return ((Link)obj).ref.equals(this.ref);
		}
	}
	@Override
	public String toString() {
		return ref+"("+weight+")";
	}
	@Override
	public int compareTo(Link another) {	//-1 if this is smaller, 1 if is bigger, 0 if equals
		for(int i=0;i<ref.length()&&i<another.ref.length();i++) {
			if(ref.charAt(i)<another.ref.charAt(i)) {
				return -1;
			}else if(ref.charAt(i)>another.ref.charAt(i)) {
				return 1;
			}
		}
		if(this.ref.length()>another.ref.length()){
		    return 1;
		}else if(this.ref.length()>another.ref.length()){
		    return -1;
		}
		return 0;
	}
}