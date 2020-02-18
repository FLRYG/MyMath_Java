package tools;

import java.math.BigDecimal;
import java.math.RoundingMode;

public class Pi {

	public static void main(String[] args) {
		System.out.println(leibniz(10000));
	}

	private static final BigDecimal FOUR = new BigDecimal("4");

	/**
	 * ライプニッツの公式による円周率の計算
	 */
	public static BigDecimal leibniz(int n) {
		if(n < 0) {
			throw new IllegalArgumentException();
		}
		BigDecimal pi = new BigDecimal("0");

		for(int i = 0; i <= n; i++) {
			if(i % 2 == 0) {
				pi = pi.add(FOUR.divide(
						new BigDecimal(String.valueOf(2 * i + 1)), 1000, RoundingMode.DOWN));
			} else {
				pi = pi.subtract(FOUR.divide(
						new BigDecimal(String.valueOf(2 * i + 1)), 1000, RoundingMode.DOWN));
			}
		}
		return pi;
	}

}
