package com.etudenhoefner.goeuro;

import java.util.ArrayList;
import java.util.List;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import com.etudenhoefner.goeuro.model.City;
import com.etudenhoefner.goeuro.model.GeoPosition;

/**
 * Parses a given JSON array and extracts relevant city information
 * 
 * @author Eduard Tudenhoefner
 * 
 */
public class JsonParser {

	/**
	 * Parses relevant data out of the given json array
	 * 
	 * @param array The item where to retrieve the information from.
	 * @return A list of {@link City} objects.
	 */
	public static List<City> parseFrom(JSONArray array) {
		if (null == array) {
			throw new IllegalArgumentException("The passed JSON Array must not be null!");
		}

		List<City> cities = new ArrayList<City>();
		for (int i = 0; i < array.size(); i++) {
			JSONObject jsonObj = (JSONObject) array.get(i);
			Long id = extractId(jsonObj);
			GeoPosition geo = extractGeoPosition(jsonObj);
			String type = getStringValueByKey(jsonObj, Constants.EXPORT_TYPE);
			String name = getStringValueByKey(jsonObj, Constants.EXPORT_NAME);
			String _type = getStringValueByKey(jsonObj, Constants.EXPORT__TYPE);
			cities.add(new City(id, _type, name, type, geo));
		}

		return cities;
	}

	private static String getStringValueByKey(JSONObject jsonObj, String key) {
		Object obj = jsonObj.get(key);
		if (obj instanceof String) {
			return (String) obj;

		}
		return null;
	}

	private static Long extractId(JSONObject jsonObj) {
		Object obj = jsonObj.get(Constants.EXPORT_ID);
		if (obj instanceof Long) {
			return (Long) obj;
		}
		return null;
	}

	private static GeoPosition extractGeoPosition(JSONObject jsonObj) {
		JSONObject obj = (JSONObject) jsonObj.get(Constants.EXPORT_GEO_POSITION);
		if (null != obj) {
			Object lat = obj.get(Constants.EXPORT_LATITUDE);
			Object lon = obj.get(Constants.EXPORT_LONGITUDE);
			if ((lat instanceof Double) && (lon instanceof Double)) {
				Double latitude = (Double) lat;
				Double longitude = (Double) lon;
				return new GeoPosition(latitude, longitude);
			}

		}
		return new GeoPosition(null, null);
	}
}
