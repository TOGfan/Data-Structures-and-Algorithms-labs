import java.util.Scanner;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Document implements IWithName{
	public String name;
	// TODO? You can change implementation of Link collection
	public LinkedList<Link> link;
	
	public Document(String name) {
		this.name=name.toLowerCase();
		link=new LinkedList<Link>();
	}

	public Document(String name, Scanner scan) {
		this.name=name.toLowerCase();
		link=new LinkedList<Link>();
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

	public static boolean isCorrectId(String id) {
		Pattern pattern = Pattern.compile("[a-z]\\w*");
		Matcher matcher = pattern.matcher(id);
		return matcher.find();
	}

	// accepted only small letters, capitalic letter, digits nad '_' (but not on the begin)
	static Link createLink(String link) {
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
		String retStr = "Document: " + name + "\n";
		int i = 1;
		for (Link link : link) {
			retStr = retStr + link.toString();
			if (i % 10 == 0) {
				retStr = retStr + "\n";
			} else {
				retStr = retStr + " ";
			}
			i++;
		}
		return retStr.trim();
	}

	@Override
	public int hashCode() {
		return name.hashCode();
	}

	@Override
	public String getName() {
		return name;
	}
}
