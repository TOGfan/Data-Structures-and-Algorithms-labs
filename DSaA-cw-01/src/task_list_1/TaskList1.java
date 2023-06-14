package task_list_1;

import java.lang.reflect.Array;

public class TaskList1 {
	public static int[] nextPascalLine(int arr[]) {
		int arr2[] = new int[arr.length + 1];
		arr2[0] = 1;
		for (int i = 1; i < arr.length; i++) {
			arr2[i] = arr[i] + arr[i - 1];
		}
		arr2[arr2.length - 1] = 1;
		return arr2;
	}

	public static int getSecondSmallest(int arr[]) throws Exception {
		int min = arr[0];
		int min2 = Integer.MAX_VALUE;
		boolean exists = false;
		for (int i = 1; i < arr.length; i++) {
			if (arr[i] < min) {
				min2 = min;
				min = arr[i];
				exists = true;
			}
			if (arr[i] < min2 && arr[i] > min) {
				min2 = arr[i];
				exists = true;
			}
		}
		if (!exists) {
			throw new Exception();
		}
		return min2;
	}

	public static boolean nextPermutation(int arr[]) {
		int i = arr.length - 1;
		while (i > 0 && arr[i - 1] >= arr[i]) {
			i--;
		}
		if (i <= 0) {
			return false;
		}
		int j = arr.length - 1;
		while (arr[j] <= arr[i - 1]) {
			j--;
		}
		int temp = arr[i - 1];
		arr[i - 1] = arr[j];
		arr[j] = temp;
		j = arr.length - 1;
		while (i < j) {
			temp = arr[i];
			arr[i] = arr[j];
			arr[j] = temp;
			i++;
			j--;
		}
		return true;
	}

	public static void main(String[] args) {
		int arr[] = { 1, 3, 3, 1 };
		arr = nextPascalLine(arr);
		for (int i = 0; i < arr.length; i++) {
			System.out.print(arr[i]);
		}
		System.out.println();
		int arr2[] = { 9, 3, 5, 4, 7, 1, 5, 1, 9 };
		try {
			System.out.print(getSecondSmallest(arr2));
		} catch (Exception ex) {
		}
		int[] arr3 = { 3, 4, 2, 5, 1 };
		while (nextPermutation(arr3)) {
			System.out.println();
			for (int i = 0; i < arr3.length; i++) {
				System.out.print(arr3[i]);
			}
		}
	}

}
