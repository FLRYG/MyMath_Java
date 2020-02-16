package tools;

import java.util.ArrayList;

public class Probability {

	public static void main(String[] args) {
		String[] nameList = { "g", "i", "t", "h", "u", "b" };
		Permutation(nameList);
		//Probability
		//new BigDecimal("34").;
	}

	private Probability() {
	}

	/**
	 * {@code n} の階乗の値を返します。
	 * 
	 * @param n
	 *            0≦n≦12 を満たす整数
	 * 
	 * @return n! の値
	 * 
	 * @exception IllegalAgumentException
	 *                n が条件を満たさないとき
	 */
	public static int factorial(int n) {
		int num = 1;
		if(n < 0 || 12 < n) {
			throw new IllegalArgumentException("(int) n は 0≦n≦12 である必要があります。");
		} else {
			for(int i = 1; i <= n; i++) {
				num *= i;
			}
			return num;
		}
	}

	/**
	 * {@code n} 個から {@code r} 個選んだときの順列の総数を返します。
	 * 
	 * @param n
	 *            任意の正の整数
	 * @param r
	 *            n以下の任意の正の整数
	 * 
	 * @return nPr の値
	 * 
	 * @exception IllegalAgumentException
	 *                n,r が条件を満たさないとき
	 */
	public static int Permutation(int n, int r) {
		if(n < 0 || r < 0) {
			throw new IllegalArgumentException("n, r は共に、正の整数である必要があります。");
		} else if(n < r) {
			throw new IllegalArgumentException("r≦n である必要があります。");
		} else {
			return factorial(n) / factorial(n - r);
		}
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

		if(size < r || r < 0) {
			throw new IllegalArgumentException("0≦r≦" + size + " である必要があります。");
		} else {
			calc(r, str, name, n);
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

	/**
	 * {@code n} 個から {@code r} 個選んだときの組み合わせの総数を返します。
	 * 
	 * @param n
	 *            任意の正の整数
	 * @param r
	 *            n以下の任意の正の整数
	 * 
	 * @return nCr の値
	 * 
	 * @exception IllegalAgumentException
	 *                n,r が条件を満たさないとき
	 */
	public static int Combination(int n, int r) {
		if(n < 0 || r < 0) {
			throw new IllegalArgumentException("n, r は共に、正の整数である必要があります。");
		} else if(n < r) {
			throw new IllegalArgumentException("r≦n である必要があります。");
		} else {
			return Permutation(n, r) / factorial(r);
		}
	}

}
