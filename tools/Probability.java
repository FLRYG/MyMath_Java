package tools;

import java.util.ArrayList;

public class Probability {

	public static void main(String[] args) {
		String[] nameList = { "g", "i", "t", "h", "u", "b" };
		Permutation(nameList);
	}

	public static void Permutation(String[] str) {
		int size = str.length;
		StringBuilder name = new StringBuilder();
		ArrayList<Integer> n = new ArrayList<>();

		calc(size, str, name, n);
	}

	public static void Permutation(String[] str, int r) {
		int size = str.length;
		StringBuilder name = new StringBuilder();
		ArrayList<Integer> n = new ArrayList<>();

		if(0 < r && r <= size) {
			calc(r, str, name, n);
		} else {
			System.err.println("rの値が適切でありません");
		}
	}

	private static void calc(int r, String[] str, StringBuilder name, ArrayList<Integer> n) {
		for(int i = 0; i < str.length; i++) {
			if(equal(i, n)) {
				continue;
			}
			name.append(str[i]);
			n.add(i);

			if(n.size() < r) {
				calc(r, str, name, n);
			} else {
				System.out.println(name);
			}

			n.remove(n.size() - 1);
			name.deleteCharAt(name.length() - 1);
		}
	}

	private static boolean equal(int i, ArrayList<Integer> n) {
		boolean b = false;
		for(int j = 0; j < n.size(); j++) {
			if(i == n.get(j)) {
				b = true;
				break;
			}
		}
		return b;
	}

	public static int factorial(int n) {
		int num = 1;

		if(n < 0) {
			System.err.println("nが適切ではありません");
			return 0;
		} else {
			for(int i = 1; i <= n; i++) {
				num *= i;
			}
			return num;
		}
	}

	public static int Permutation(int n, int r) {
		if(n < r || n < 0 || r < 0) {
			System.err.println("nまたはrが適切ではありません");
			return 0;
		} else {
			return factorial(n) / factorial(n - r);
		}
	}

	public static int Combination(int n, int r) {
		if(n < r || n < 0 || r < 0) {
			System.err.println("nまたはrが適切ではありません");
			return 0;
		} else {
			return Permutation(n, r) / factorial(r);
		}
	}

}
