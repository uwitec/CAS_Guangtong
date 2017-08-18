package cn.guangtong.utils;

import java.util.HashMap;
import java.util.Map;

public class DistanceUtil {

	private static final double EARTH_RADIUS = 6378137;// 赤道半径

	private static double rad(double d) {
		return d * Math.PI / 180.0;
	}

	// lon1经度 lat1纬度
	// lon2经度 lat2纬度
	public static double GetDistance(double lon1, double lat1, double lon2,
			double lat2) {
		double radLat1 = rad(lat1);
		double radLat2 = rad(lat2);
		double a = radLat1 - radLat2;
		double b = rad(lon1) - rad(lon2);
		double s = 2 * Math.asin(Math.sqrt(Math.pow(Math.sin(a / 2), 2)
				+ Math.cos(radLat1) * Math.cos(radLat2)
				* Math.pow(Math.sin(b / 2), 2)));
		s = s * EARTH_RADIUS;
		return s;// 单位米
	}

	/**
	 * @param raidus
	 *            单位米 return minLat,minLng,maxLat,maxLng
	 */
	public static Map<String,Double> getAround(double lon, double lat, int raidus) {

		Double latitude = lat;
		Double longitude = lon;

		Double degree = (24901 * 1609) / 360.0;
		double raidusMile = raidus;

		Double dpmLat = 1 / degree;
		Double radiusLat = dpmLat * raidusMile;
		Double minLat = latitude - radiusLat;
		Double maxLat = latitude + radiusLat;

		Double mpdLng = degree * Math.cos(latitude * (Math.PI / 180));
		Double dpmLng = 1 / mpdLng;
		Double radiusLng = dpmLng * raidusMile;
		Double minLng = longitude - radiusLng;
		Double maxLng = longitude + radiusLng;
		Map<String,Double> map = new HashMap<String,Double>();
		map.put("minLat", minLat);
		map.put("minLng", minLng);
		map.put("maxLat", maxLat);
		map.put("maxLng", maxLng);
		return map;
	}
}
