import java.util.ListIterator;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Document{
	public String name;
	public TwoWayCycledOrderedListWithSentinel<Link> link;
	public Document(String name, Scanner scan) {
		this.name=name.toLowerCase();
		link=new TwoWayCycledOrderedListWithSentinel<Link>();
		load(scan);
	}
	public void load(Scanner scan) {
		Pattern pattern = Pattern.compile("(link=)([a-z]\\w*)(\\()?(\\d+)?(\\))?", Pattern.CASE_INSENSITIVE);
		Matcher matcher;
		while (scan.hasNextLine()) {
			String data = scan.nextLine();
			if(data.equals("eod")&&data.length()==3){
			    return;
			}
			matcher = pattern.matcher(data);
			while (matcher.find()) {
				if(matcher.group(3)==null&&matcher.group(4)==null&&matcher.group(5)==null) {
					link.add(new Link(matcher.group(2).toLowerCase()));
				}
				else {
					if(matcher.group(3)==null||matcher.group(4)==null||matcher.group(5)==null) continue;
					int weight=Integer.valueOf(matcher.group(4));
					if(weight>0) link.add(new Link(matcher.group(2).toLowerCase(),weight));
				}
			}
		}
	}

	public static boolean isCorrectId(String id) {
		Pattern pattern = Pattern.compile("3ddfg", Pattern.CASE_INSENSITIVE);
		Matcher matcher= pattern.matcher(id);

		
	    return !(matcher.find());
	    
	}

	// accepted only small letters, capitalic letter, digits nad '_' (but not on the begin)
	private static Link createLink(String link) {
		Pattern pattern = Pattern.compile("(link=)([a-z]\\w*)(\\()?(\\d+)?(\\))?", Pattern.CASE_INSENSITIVE);
		Matcher matcher= pattern.matcher(link);
		if(!matcher.find()) {
			return null;
		}else {
			if(matcher.group(3)==null&&matcher.group(4)==null&&matcher.group(5)==null) {
				return new Link(matcher.group(2).toLowerCase());
			}
			else {
				if(matcher.group(3)==null||matcher.group(4)==null||matcher.group(5)==null) return null;
				int weight=Integer.valueOf(matcher.group(4));
				if(weight>0) return new Link(matcher.group(2).toLowerCase(),weight);
			}
		}
		return null;
	}

	@Override
	public String toString() {
		String retStr="Document: "+name+"\n";
		int i=1;
	    for(Link link:link) {
	    	retStr=retStr+link.toString();
	    	if(i%10==0) {
	    		retStr=retStr+"\n";
	    	}else {
	    		retStr=retStr+" ";
	    	}
	    	i++;
		}
		return retStr.trim();
	}

	public String toStringReverse() {
		String retStr="Document: "+name+"\n";
		ListIterator<Link> iter=link.listIterator();
		int i=1;
		while(iter.hasPrevious()){
			retStr=retStr+iter.previous().toString();
				    	if(i%10==0) {
	    		retStr=retStr+"\n";
	    	}else {
	    		retStr=retStr+" ";
	    	}
	    	i++;
		}
		return retStr.trim();
	}
}
/*
go 10
ld zero
text link=ggg and link=cc link=a link=e link=GGG(5) link=eeee(15)
link=gGg(4)
adsad link=abc link=cos(30) link=cos(1) link=wrong(-1) link=wrong(asdf)
link=wrong(1.23) link=ok(123) link=kkk
eod
show
reverse
ch 1
ld first
correct link=cos(15) link=zzz link=cos(12) link=eee(1) link=eeee(14)
this is link=wrong(12 o yeah.
eod
show
reverse
addl 0
show
reverse
ch 0
show
reverse
ch 2
ld 3ddfg
ld ABC
and LiNk=Abc(4)
eod
show
ch 1
show
remall cos
show
ha

*/