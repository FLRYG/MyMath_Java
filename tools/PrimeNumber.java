package tools;

import java.util.ArrayList;

public class PrimeNumber {

	/* テスト用 */
	public static void main(String[] args) {
		long start = System.currentTimeMillis();
		PrimeNumber p = new PrimeNumber();
		//		p.Search(10000);
		System.out.println(p.generala(10));

		long end = System.currentTimeMillis();
		System.out.println((end - start) + "[ms]");
	}

	private int count = 2;

	public PrimeNumber() {
	}

	public boolean Judge(int number) {
		if(number == 2) {
			return true;
		} else if(number % 2 == 0 || number < 2) {
			return false;
		} else {
			for(int i = 3; i < number; i += 2) {
				if(number % i == 0) {
					return false;
				}
			}
			return true;
		}
	}

	public void Search(int number) {
		ArrayList<Integer> p = new ArrayList<Integer>();
		p.add(2);
		System.out.println(1 + "  " + 2);

		for(int n = (int) Math.pow(2, count - 1); n <= (int) Math.pow(2, count); n++) {
			if(n > number) {
				break;
			}
			int flag = 0;
			for(int i = 0; i < count - 1; i++) {
				if(n % p.get(i) == 0) {
					flag = 1;
					break;
				}
			}
			if(flag == 0) {
				p.add(n);
				System.out.println(count + "  " + n);
				count++;
			}

		}
	}

	public int generala(int k) {
		if(k <= 0) {
			System.err.print("Error");
			System.exit(0);
		}
		int p = 0;
		for(int m = 2; m <= pow(2, k); m++) {
			p += m * Math.floor(
					1 / (1 + Math.abs(
							k - A(m) * B(m))));
		}
		return p;
	}

	private int pow(int n1, int n2) {
		return (int) Math.pow(n1, n2);
	}

	private int A(int n) {
		int a = 0;
		for(int i = 1; i <= n - 1; i++) {
			a += Math.floor(Math.floor(n / i) / (n / i));
		}
		return (int) Math.floor(1 / a);
	}

	private int B(int m) {
		int b = 0;
		for(int n = 2; n <= m; n++) {
			b += A(n);
		}
		return b;
	}

}
