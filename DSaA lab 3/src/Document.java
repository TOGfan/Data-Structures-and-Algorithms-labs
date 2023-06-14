import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Document{
	public String name;
	public TwoWayUnorderedListWithHeadAndTail<Link> link;
	public Document(String name, Scanner scan) {
		this.name=name;
		link=new TwoWayUnorderedListWithHeadAndTail<Link>();
		load(scan);
	}
	public void load(Scanner scan) {
		Pattern pattern = Pattern.compile("(link=)([a-z]\\w*)", Pattern.CASE_INSENSITIVE);
		Matcher matcher;
		while (scan.hasNextLine()) {
			String data = scan.nextLine();
			if(data.equals("eod")&&data.length()==3){
			    return;
			}
			matcher = pattern.matcher(data);
			while (matcher.find()) {
				link.add(new Link(matcher.group(2).toLowerCase()));
			}
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
	    String s="Document: "+name;
	    for(Link link:link) {
			s=s+"\n"+link.ref;
		}
		return s;
	}
	
	public String toStringReverse() {
		String retStr="Document: "+name;
		return retStr+link.toStringReverse();
	}

}
/*
go 10
ld doc1
link=a and link=b
write also link=c end finish.
eod
show
add d
show
rem a
remi 2
show
get 0
set 1 w
show
ch 1
ld doc2
eod
show
remi 0
index x
ha
*/
