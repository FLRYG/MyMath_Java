package tools;

import java.math.BigInteger;

public class CardinalNumber {

	/* テスト用 */
	public static void main(String[] args) {
		long a = System.nanoTime();
		for(int i = 2; i <= 62; i++) {
			System.out.println(i + ":\t"
					+ CardinalNumber.baseConversion("37482346736527735817536185671628281561911", 10, i));
		}
		long b = System.nanoTime();

		System.out.println((b - a) / 1_000_000_000.0);
	}

	private final static char[] cardinalNumberTable = {
			'0', '1', '2', '3', '4', '5', '6', '7', '8', '9',
			'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j',
			'k', 'l', 'm', 'n', 'o', 'p', 'q', 'r', 's', 't',
			'u', 'v', 'w', 'x', 'y', 'z', 'A', 'B', 'C', 'D',
			'E', 'F', 'G', 'H', 'I', 'J', 'K', 'L', 'M', 'N',
			'O', 'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X',
			'Y', 'Z'
	};

	public static String baseConversion(int val, int n1) {
		return baseConversion(String.valueOf(val), 10, n1);
	}

	public static String baseConversion(long val, int n1) {
		return baseConversion(String.valueOf(val), 10, n1);
	}

	public static String baseConversion(String val, int n0, int n1) {
		if((n0 < 2 || 62 < n0) || (n1 < 2 || 62 < n1)) {
			throw new IllegalArgumentException("n0 または n1 が適切ではありません");
		}
		StringBuilder sb = new StringBuilder();
		BigInteger bn0 = new BigInteger(String.valueOf(n0));
		BigInteger bn1 = new BigInteger(String.valueOf(n1));
		if(n0 == 10) {
			BigInteger x = new BigInteger(val);
			while(!(x.equals(BigInteger.ZERO))) {
				sb.insert(0, cardinalNumberTable[x.mod(bn1).intValue()]);
				x = x.divide(bn1);
			}
			return sb.toString();
		} else {
			BigInteger n = new BigInteger("0");
			int digit = val.length();
			for(int i = 0; i < digit; i++) {
				BigInteger k = null;
				for(int j = 0; j < n0; j++) {
					if(val.charAt(i) == cardinalNumberTable[j]) {
						k = new BigInteger(String.valueOf(j));
						break;
					}
				}
				if(k == null) {
					throw new IllegalArgumentException("val は " + n0 + " 進数表記である必要があります");
				}
				n = n.add(k.multiply(bn0.pow(digit - i - 1)));
			}
			return baseConversion(n.toString(), 10, n1);
		}
	}

}
