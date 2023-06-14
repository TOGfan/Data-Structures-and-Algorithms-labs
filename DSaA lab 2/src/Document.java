import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Document{
	public String name;
	public OneWayLinkedList<Link> links;
	public Document(String name, Scanner scan) {
			this.name=name;
			links=new OneWayLinkedList<Link>();
			load(scan);
		}
	public void load(Scanner scan) {
		try {
		//	File file = new File(name);
		//	scan = new Scanner(file);
			Pattern pattern = Pattern.compile("(link=)([a-z]\\w*)", Pattern.CASE_INSENSITIVE);
			Matcher matcher;
			while (scan.hasNextLine()) {
				String data = scan.nextLine();
				if(data.equals("eod")&&data.length()==3){
				    return;
				}
				matcher = pattern.matcher(data);
				while (matcher.find()) {
					links.add(new Link(matcher.group(2).toLowerCase()));
				}
			}
			scan.close();
		} catch (Exception e) {
			System.out.println(e.getLocalizedMessage());
		} 
	}
	// accepted only small letters, capitalic letter, digits nad '_' (but not on the begin)
	public static boolean correctLink(String link) {
		Pattern pattern = Pattern.compile("(link=)([a-z]\\w*)", Pattern.CASE_INSENSITIVE);
		Matcher matcher=pattern.matcher(link);
		if (matcher.find()) {
			return true;
		}
		return false;
	}
	
	@Override
	public String toString() {
	    String s="Document: "+this.name;
	    for(Link link:links) {
			s=s+"\n"+link.ref;
		}
		return s;
	}

}


