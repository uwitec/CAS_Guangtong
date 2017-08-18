package cn.guangtong.utils;

import java.util.Date;

public class EvilTransform {

	final static double pi = 3.1415926535897932384626;

	//
	// a = 6378245.0, 1/f = 298.3
	// b = a * (1 - f)
	// ee = (a^2 - b^2) / a^2;
	final static double a = 6378245.0;
	final static double ee = 0.00669342162296594323;

	/**
	 * WGS-84 到 GCJ-02 的转换（即 GPS 加偏）
	 * 
	 * @param wgLon
	 * @param wgLat
	 * @return
	 */
	public static double[] transform(double wgLon, double wgLat) {

		double mgLat;
		double mgLon;
		double dLat = transformLat(wgLon - 105.0, wgLat - 35.0);
		double dLon = transformLon(wgLon - 105.0, wgLat - 35.0);
		double radLat = wgLat / 180.0 * pi;
		double magic = Math.sin(radLat);
		magic = 1 - ee * magic * magic;
		double sqrtMagic = Math.sqrt(magic);
		dLat = (dLat * 180.0) / ((a * (1 - ee)) / (magic * sqrtMagic) * pi);
		dLon = (dLon * 180.0) / (a / sqrtMagic * Math.cos(radLat) * pi);

		mgLat = wgLat + dLat;
		mgLon = wgLon + dLon;

		return new double[] { mgLon, mgLat };

	}

	/**
	 * 纬度转换
	 * 
	 * @param x
	 * @param y
	 * @return
	 */
	private static double transformLat(double x, double y) {
		double ret = -100.0 + 2.0 * x + 3.0 * y + 0.2 * y * y + 0.1 * x * y + 0.2 * Math.sqrt(Math.abs(x));
		ret += (20.0 * Math.sin(6.0 * x * pi) + 20.0 * Math.sin(2.0 * x * pi)) * 2.0 / 3.0;
		ret += (20.0 * Math.sin(y * pi) + 40.0 * Math.sin(y / 3.0 * pi)) * 2.0 / 3.0;
		ret += (160.0 * Math.sin(y / 12.0 * pi) + 320 * Math.sin(y * pi / 30.0)) * 2.0 / 3.0;
		return ret;
	}

	/**
	 * 经度转换
	 * 
	 * @param x
	 * @param y
	 * @return
	 */
	private static double transformLon(double x, double y) {
		double ret = 300.0 + x + 2.0 * y + 0.1 * x * x + 0.1 * x * y + 0.1 * Math.sqrt(Math.abs(x));
		ret += (20.0 * Math.sin(6.0 * x * pi) + 20.0 * Math.sin(2.0 * x * pi)) * 2.0 / 3.0;
		ret += (20.0 * Math.sin(x * pi) + 40.0 * Math.sin(x / 3.0 * pi)) * 2.0 / 3.0;
		ret += (150.0 * Math.sin(x / 12.0 * pi) + 300.0 * Math.sin(x / 30.0 * pi)) * 2.0 / 3.0;
		return ret;
	}

	public static void main(String[] args) {
		System.out.println(new Date().getTime());
		// PHP结果117.09417301741,39.077348951645
		// 117.094173017408,39.07734895164542
		double[] temp = transform(117.08775999999999, 39.076209999999996);
		System.out.println(temp[0] + "," + temp[1]);
		System.out.println(new Date().getTime());
	}

}
