public class Drawer {
	private static void drawLine(int n, char ch) {
		for(int i=0;i<n;i++) {
			System.out.print(ch);
		}
	}


	public static void drawPyramid(int n) {
		for(int i=1;i<=n;i++) {
			drawLine(n-i,' ');
			drawLine(2*i-1,'X');
			System.out.println();
		}
	}
	public static void drawShiftedPyramid(int n, int shift) {
		for(int i=1;i<=n;i++) {
			drawLine(n-i+shift,' ');
			drawLine(2*i-1,'X');
			System.out.println();
		}
	}
	
	public static void drawChristmassTree(int n) {
		for(int i=1;i<=n;i++) {
			drawShiftedPyramid(i,n-i);
		}

	}

}
