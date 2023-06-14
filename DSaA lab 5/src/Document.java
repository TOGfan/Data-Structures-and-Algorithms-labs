import java.util.ListIterator;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Document {
	public String name;
	public TwoWayCycledOrderedListWithSentinel<Link> link;

	public Document(String name, Scanner scan) {
		this.name = name.toLowerCase();
		link = new TwoWayCycledOrderedListWithSentinel<Link>();
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
		Pattern pattern = Pattern.compile("3ddfg", Pattern.CASE_INSENSITIVE);
		Matcher matcher = pattern.matcher(id);

		return !(matcher.find());

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

	public String toStringReverse() {
		String retStr = "Document: " + name + "\n";
		ListIterator<Link> iter = link.listIterator();
		int i = 1;
		while (iter.hasPrevious()) {
			retStr = retStr + iter.previous().toString();
			if (i % 10 == 0) {
				retStr = retStr + "\n";
			} else {
				retStr = retStr + " ";
			}
			i++;
		}
		return retStr.trim();
	}

	public int[] getWeights() {
		int[] arr = new int[link.size()];
		int i = 0;
		for (Link a : link) {
			arr[i] = a.weight;
			i++;
		}
		return arr;
	}

	public static void showArray(int[] arr) {
		for (int i = 0; i < arr.length; i++) {
			System.out.print(arr[i]);
			if (i != arr.length - 1) {
				System.out.print(" ");
			}
		}
		System.out.println();
	}

	void bubbleSort(int[] arr) {
		showArray(arr);
		int[] arr2 = arr.clone();
		int temp;
		for (int i = 0; i < arr2.length - 1; i++) {
			for (int j = arr2.length - 1; j > 0; j--) {
				if (arr2[j - 1] > arr2[j]) {
					temp = arr2[j];
					arr2[j] = arr2[j - 1];
					arr2[j - 1] = temp;
				}
			}
			showArray(arr2);
		}

	}

	public void insertSort(int[] arr) {
		showArray(arr);
		int[] arr2 = arr.clone();
		for (int i = arr2.length - 2; i >= 0; i--) {
			for (int j = i + 1; j < arr2.length; j++) {
				if (arr2[i] < arr2[j]) {
					insertAtFrom(arr2, j - 1, i);
					break;
				} else if (j == arr2.length - 1) {
					insertAtFrom(arr2, j, i);
				}
			}
			showArray(arr2);
		}

	}

	public void selectSort(int[] arr) {
		showArray(arr);
		int max;
		int temp;
		int[] arr2 = arr.clone();
		for (int i = arr2.length - 1; i > 0; i--) {
			max = 0;
			for (int j = 0; j <= i; j++) {
				if (arr2[j] > arr2[max]) {
					max = j;
				}
			}
			temp = arr2[i];
			arr2[i] = arr2[max];
			arr2[max] = temp;
			showArray(arr2);
		}
	}

	private void insertAtFrom(int arr[], int at, int from) {
		if (at > from) {
			int temp = arr[from];
			for (int i = from; i < at; i++) {
				arr[i] = arr[i + 1];
			}
			arr[at] = temp;
		} else {
			int temp = arr[from];
			for (int i = from; i > at; i--) {
				arr[i] = arr[i - 1];
			}
			arr[at] = temp;
		}
	}
}
/*
  go 10 ld doc1 link=c(10) and link=b(7) write also link=z end finish
  link=a(20) eod add f(8) add g(4) add e(3) show bubblesort insertsort
  selectsort ha
 */