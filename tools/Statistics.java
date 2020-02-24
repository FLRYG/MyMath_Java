package tools;

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

}
