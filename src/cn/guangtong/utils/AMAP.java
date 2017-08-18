package cn.guangtong.utils;

import java.io.IOException;
import java.nio.charset.Charset;
import java.util.HashMap;
import java.util.Map;

import org.apache.http.HttpEntity;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.springframework.transaction.interceptor.TransactionAspectSupport;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;

/**
 * 高德地图经纬度，逆向转实际的位置信息
 * 
 * @author SunTo
 * 
 */
public class AMAP {

	private static String key = "e1a30791ac935b5c2d677b1b55c49600";

	private static String httpUrl = "http://restapi.amap.com/v3/geocode/";

	public static void main(String[] args) {
		System.out.println(regeo("116.481488,39.990464"));
		System.out.println(geo("北京市朝阳区阜通东大街6号"));
	}

	/**
	 * 经纬度字符串转换成具体的地址信息（字符串）
	 * 
	 * @param location
	 * @return
	 */
	public static String regeo(String location) {
		try {
			String URL = httpUrl + "regeo?key=" + key + "&location=" + location;
			String JsonData = get(URL);
			Map<String, JSONObject> tempMap = JSON.parseObject(JsonData, new HashMap<String, JSONObject>().getClass());
			JSONObject regeocode = tempMap.get("regeocode");
			String formatted_address = (String) regeocode.get("formatted_address");
			return formatted_address;
		} catch (Exception e) {
			//System.out.println("*********经纬度转位置信息失败");
		}
		return null;
	}

	/**
	 * 位置信息转经纬度
	 * 
	 * @param address
	 * @return
	 */
	public static String geo(String address) {
		try {
			String URL = httpUrl + "geo?key=" + key + "&address=" + address;
			String JsonData = get(URL);
			JSONObject tempMap = JSON.parseObject(JsonData, new JSONObject().getClass());
			JSONArray geocodes = tempMap.getJSONArray("geocodes");
			// System.out.println(geocodes);

			JSONObject geocodes_0 = geocodes.getJSONObject(0);
			String location = (String) geocodes_0.get("location");
			// System.out.println(location);
			return location;
		} catch (Exception e) {
			//System.out.println("*********位置信息转经纬度失败");
		}
		return null;
	}

	public static String get(String url) {
		String html = "";
		CloseableHttpClient httpclient = HttpClients.createDefault();
		HttpGet httpget = new HttpGet(url);
		try {
			CloseableHttpResponse response = httpclient.execute(httpget);
			HttpEntity entity = response.getEntity();
			html = EntityUtils.toString(entity, Charset.forName("UTF-8"));
		} catch (Exception e) {
			e.printStackTrace();
		}
		return html;
	}
}
