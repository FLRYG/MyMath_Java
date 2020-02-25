package tools;

import java.util.Arrays;

public class Statistics {

	public static void main(String[] args) {
		final int SCALE = 1_000_000;
		double[] dataX = new double[SCALE];
		double[] dataY = new double[SCALE];
		for(int i = 0; i < SCALE; i++) {
			dataX[i] = 100 * Math.random();
			dataY[i] = 100 * Math.random();
		}

		System.out.println(Statistics.mean(dataX));
		System.out.println(Statistics.variance(dataX));
		System.out.println(Statistics.standardDeviation(dataX));
		System.out.println(Statistics.covariance(dataX, dataY));
		System.out.println(Statistics.correlation(dataX, dataY));
	}

	/**
	 * 四位分数
	 */
	public static double[] quantile(double[] data, int q) {
		if(q != 1 && q != 2 && q != 3) {
			throw new IllegalArgumentException();
		}
		double[] dataX = data.clone();
		Arrays.sort(dataX);
		int length = data.length;
		double[] Qq = new double[3];

		if(length % 4 == 0) {
			for(int i = 1; i <= 3; i++) {
				Qq[i] = (dataX[i * (length / 4)] + dataX[i * (length / 4) + 1]) / 2.0;
			}
		} else if(length % 4 == 1) {
			Qq[1] = dataX[length / 2 + 1];
			Qq[0] = (dataX[(length / 2) / 2] + dataX[(length / 2) / 2 + 1]) / 2;
			Qq[2] = (dataX[length / 2 + 1 + (length / 2) / 2] + dataX[length / 2 + 1 + (length / 2) / 2 + 1]) / 2;
		} else if(length % 4 == 2) {
			Qq[1] = (dataX[length / 2] + dataX[length / 2 + 1]) / 2;
			Qq[0] = dataX[(length / 2) / 2 + 1];
			Qq[2] = dataX[length / 2 + (length / 2) / 2 + 1];
		} else if(length % 4 == 3) {

		}

		return null;
	}

	/**
	 * 平均
	 */
	public static double mean(double[] data) {
		if(data.length == 0) {
			throw new IllegalArgumentException();
		}
		double m = 0;
		for(int i = 0; i < data.length; i++) {
			m += data[i];
		}
		return m / data.length;
	}

	/**
	 * 分散
	 */
	public static double variance(double[] data) {
		if(data.length == 0) {
			throw new IllegalArgumentException();
		}
		double v = 0;
		double m = mean(data);
		for(int i = 0; i < data.length; i++) {
			v += Math.pow(data[i] - m, 2);
		}
		return v / data.length;
	}

	/**
	 * 標準偏差
	 */
	public static double standardDeviation(double[] data) {
		if(data.length == 0) {
			throw new IllegalArgumentException();
		}
		return Math.sqrt(variance(data));
	}

	/**
	 * 共分散
	 */
	public static double covariance(double[] dataX, double[] dataY) {
		if(dataX.length != dataY.length || dataX.length == 0 || dataY.length == 0) {
			throw new IllegalArgumentException();
		}
		double vXY = 0;
		double mX = mean(dataX);
		double mY = mean(dataY);
		for(int i = 0; i < dataX.length; i++) {
			vXY += (dataX[i] - mX) * (dataY[i] - mY);
		}
		return vXY / dataX.length;
	}

	/**
	 * 相関係数
	 */
	public static double correlation(double[] dataX, double[] dataY) {
		return covariance(dataX, dataY) / (standardDeviation(dataX) * standardDeviation(dataY));
	}

	/**
	 * 正規化 (平均 → 0 , 標準偏差 → 1)
	 */
	public static double[] normalization(double[] data) {
		double m = mean(data);
		double s = standardDeviation(data);
		double[] x = new double[data.length];
		for(int i = 0; i < data.length; i++) {
			x[i] = (data[i] - m) / s;
		}
		return x;
	}

}
