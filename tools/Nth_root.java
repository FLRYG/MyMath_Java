package tools;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Nth_root {

	public static void main(String[] args) {
		System.out.println(Nth_root.sqrt(new BigDecimal("2738"), 10000));
	}

	public static BigDecimal TWO = new BigDecimal("2");

	/**
	 * {@code BigDecimal} の平方根を計算します。
	 * 
	 * @param x
	 *            平方数
	 * @return x の平方根
	 */
	public static BigDecimal sqrt(BigDecimal x) {
		return sqrt(x, 30);
	}

	/**
	 * {@code BigDecimal} の平方根を任意の精度で計算します。
	 * 
	 * @param x
	 *            平方数
	 * @param scale
	 *            精度 小数点第 {@code scale} 位まで
	 * @return x の平方根
	 */
	public static BigDecimal sqrt(BigDecimal x, int scale) {
		switch (x.signum()) {
		case -1:
			throw new ArithmeticException("Attempted square root of negative BigDecimal");
		case 0:
			return BigDecimal.ZERO;
		case 1:
			BigDecimal standard;
			if(scale >= 1) {
				standard = new BigDecimal("0." + pow10(scale - 1) + "1");
			} else {
				standard = new BigDecimal("1");
			}
			BigDecimal xn1 = new BigDecimal("5" + pow10(((digit(x) + 1) / 2) - 1));
			BigDecimal xn2;
			xn2 = (xn1.multiply(xn1).add(x)).divide(xn1.multiply(TWO), scale, RoundingMode.DOWN);
			//System.out.println(xn2);
			while(xn1.subtract(xn2).abs().subtract(standard).signum() != -1) {
				xn1 = xn2;
				xn2 = (xn1.multiply(xn1).add(x)).divide(xn1.multiply(TWO), scale, RoundingMode.DOWN);
				//System.out.println(xn2);
			}
			return xn2;
		default:
			throw new AssertionError("Bad value from signum");
		}
	}

	private static String pow10(int n) {                            //"0"をn回つける
		StringBuilder sb = new StringBuilder();
		for(int i = 0; i < n; i++) {
			sb.append("0");
		}
		return sb.toString();
	}

	private static int digit(BigDecimal bd) {               //BigDecimalの整数部分の桁数を求める
		Pattern pattern = Pattern.compile("[0-9]+\\.");
		Pattern pattern2 = Pattern.compile("[0-9]+");
		Matcher m = pattern.matcher(bd.toString());
		Matcher m2 = pattern2.matcher(bd.toString());
		if(m.find()) {
			//System.out.println(m.group());
			return m.group().length() - 1;
		} else if(m2.find()) {
			//System.out.println(m2.group());
			return m2.group().length();
		}
		return -1;
	}

}
