import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Document implements IWithName{
	private static final int MODVALUE=100000000;
	public String name;
	public BST<Link> link;
	public Document(String name) {
		this.name = name.toLowerCase();
		link = new BST<Link>();
	}
	@Override
	public String getName() {
		return name;
	}
	public Document(String name, Scanner scan) {
		this.name=name.toLowerCase();
		link=new BST<Link>();
		load(scan);
	}
	public void load(Scanner scan) {
		Pattern pattern = Pattern.compile("(link=)([a-z]\\w*)(\\()?(\\d+)?(\\))?", Pattern.CASE_INSENSITIVE);
		Matcher matcher;
		while (scan.hasNextLine()) {
			String data = scan.nextLine();
			if (data.equals("eod") && data.length() == 3) {
				return;
			}
			matcher = pattern.matcher(data);
			while (matcher.find()) {
				if (matcher.group(3) == null && matcher.group(4) == null && matcher.group(5) == null) {
					link.add(new Link(matcher.group(2).toLowerCase()));
				} else {
					if (matcher.group(3) == null || matcher.group(4) == null || matcher.group(5) == null)
						continue;
					int weight = Integer.valueOf(matcher.group(4));
					if (weight > 0)
						link.add(new Link(matcher.group(2).toLowerCase(), weight));
				}
			}
		}
	}
	public int hashCode() {
		int[] sequence = {7,11,13,17,19};
		int hashCode=this.name.charAt(0);
		for(int i=0;i<this.name.length()-1;i++) {
			hashCode=(hashCode*sequence[i%sequence.length]+name.charAt(i+1))%MODVALUE;
		}
		return hashCode;
	}
	public boolean equals(Object ob) {
		if(!(ob instanceof Document)) {
			return false;
		}else {
			return ((Document)ob).getName().equals(this.name);
		}
	}
	
	public static boolean isCorrectId(String id) {
		Pattern pattern = Pattern.compile("[a-z]\\w*");
		Matcher matcher = pattern.matcher(id);

		return matcher.find();

	}
	// accepted only small letters, capitalic letter, digits nad '_' (but not on the
	// begin)
	public static Link createLink(String link) {
		Pattern pattern = Pattern.compile("([a-z]\\w*)(\\()?(\\d+)?(\\))?", Pattern.CASE_INSENSITIVE);
		Matcher matcher = pattern.matcher(link);
		if (!matcher.find()) {
			return null;
		} else {
			if (matcher.group(2) == null && matcher.group(3) == null && matcher.group(4) == null) {
				return new Link(matcher.group(1).toLowerCase());
			} else {
				if (matcher.group(2) == null || matcher.group(3) == null || matcher.group(4) == null)
					return null;
				int weight = Integer.valueOf(matcher.group(3));
				if (weight > 0)
					return new Link(matcher.group(1).toLowerCase(), weight);
			}
		}
		return null;
	}

	@Override
	public String toString() {
		String retStr="Document: "+name+"\n";
		retStr+=link.toStringInOrder();		
		return retStr;
	}

	public String toStringPreOrder() {
		String retStr="Document: "+name+"\n";
		retStr+=link.toStringPreOrder();
		return retStr;
	}

	public String toStringPostOrder() {
		String retStr="Document: "+name+"\n";
		retStr+=link.toStringPostOrder();
		return retStr;
	}

}
