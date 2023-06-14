import java.util.Scanner;
import java.util.regex.Pattern;
import java.util.regex.Matcher;

public class Document {

	public static void loadDocument(String name, Scanner scan) {
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
					System.out.println(matcher.group(2).toLowerCase());
				}
			}
			scan.close();
		} catch (Exception e) {
			System.out.println(e.getLocalizedMessage());
		}
	}

	// accepted only small letters, capitalic letter, digits and '_' (but not on the
	// begin)
	public static boolean correctLink(String link) {
		Pattern pattern = Pattern.compile("(link=)([a-z]\\w*)", Pattern.CASE_INSENSITIVE);
		Matcher matcher=pattern.matcher(link);
		if (matcher.find()) {
			return true;
		}
		return false;
	}

}