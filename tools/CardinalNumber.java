package tools;

public class CardinalNumber {

	public static void main(String[] args) {
		long a = System.nanoTime();
		for(int i = 2; i <= 62; i++) {
			System.out.println(i + ":\t"
					+ CardinalNumber.baseConversion("3e5d8cc", 16, i));
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

	public static String baseConversion(String val, int n0, int n1) {
		StringBuilder sb = new StringBuilder();
		if(n0 == 10) {
			int x = Integer.parseInt(val);
			while(x != 0) {
				sb.insert(0, cardinalNumberTable[x % n1]);
				x = x / n1;
			}
			return sb.toString();
		} else {
			int n = 0;
			int digit = val.length();
			for(int i = 0; i < digit; i++) {
				int k = -1;
				for(int j = 0; j < n0; j++) {
					if(val.charAt(i) == cardinalNumberTable[j]) {
						k = j;
						break;
					}
				}
				if(k == -1) {
					throw new IllegalArgumentException("");
				}
				n += k * Math.pow(n0, digit - i - 1);
			}
			sb.append(n);
			return baseConversion(sb.toString(), 10, n1);
		}
	}

}
