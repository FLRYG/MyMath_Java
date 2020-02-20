package tools;

import java.math.BigInteger;
import java.util.ArrayList;

public class Probability {

	/* テスト用 */
	public static void main(String[] args) {
		String[] nameList = { "j", "a", "v", "a" };
		permutation(nameList);

		System.out.println(factorial(4));
		System.out.println(permutation(1000, 324));
		System.out.println(combination(1000, 324));
	}

	/**
	 * インスタンス化不可能
	 */
	private Probability() {
	}

	/**
	 * {@code n} の階乗の値を返します。
	 * 
	 * @param n
	 *            0≦n を満たす整数
	 * 
	 * @return n! の値
	 * 
	 * @exception IllegalArgumentException
	 *                n が条件を満たさないとき
	 */
	public static BigInteger factorial(int n) {
		BigInteger num = new BigInteger("1");
		if(n < 0) {
			throw new IllegalArgumentException("n は 0≦n である必要があります。");
		} else {
			for(int i = 1; i <= n; i++) {
				num = num.multiply(new BigInteger(String.valueOf(i)));
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
	 * @exception IllegalArgumentException
	 *                n,r が条件を満たさないとき
	 */
	public static BigInteger permutation(int n, int r) {
		if(n < 0 || r < 0) {
			throw new IllegalArgumentException("n, r は共に、正の整数である必要があります。");
		} else if(n < r) {
			throw new IllegalArgumentException("r≦n である必要があります。");
		}
		BigInteger x = new BigInteger("1");
		for(int i = 0; i < r; i++) {
			x = x.multiply(new BigInteger(String.valueOf(n - i)));
		}
		return x;
	}

	public static void permutation(String[] str) {
		int size = str.length;
		StringBuilder name = new StringBuilder();
		ArrayList<Integer> n = new ArrayList<>();

		calc(size, str, name, n);
	}

	public static void permutation(String[] str, int r) {
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
	public static BigInteger combination(int n, int r) {
		if(n < 0 || r < 0) {
			throw new IllegalArgumentException("n, r は共に、正の整数である必要があります。");
		} else if(n < r) {
			throw new IllegalArgumentException("r≦n である必要があります。");
		}
		if(n - r < r)
			r = n - r;
		BigInteger x = permutation(n, r).divide(factorial(r));

		return x;
	}

}
