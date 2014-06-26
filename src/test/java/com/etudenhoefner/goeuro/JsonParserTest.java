package com.etudenhoefner.goeuro;

import java.util.List;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.testng.Assert;
import org.testng.annotations.Test;

import com.etudenhoefner.goeuro.model.City;

/**
 * Tests the {@link JsonParser}. In practice, rather than creating new objects
 * within the test methods (such as {@link JSONArray}, {@link JSONObject}), we
 * would use a mocking framework. However, I decided not to use one for this
 * small example.
 * 
 * @author Eduard Tudenhoefner
 * 
 */
public class JsonParserTest {

	@Test(expectedExceptions = {IllegalArgumentException.class})
	public void testNull() {
		JsonParser.parseFrom(null);
	}

	@Test
	public void testEmpty() {
		JSONArray array = new JSONArray();
		List<City> cities = JsonParser.parseFrom(array);
		Assert.assertNotNull(cities);
		Assert.assertTrue(cities.isEmpty());
	}

	@SuppressWarnings("unchecked")
	@Test
	public void testJsonWithMissingFields() {
		JSONArray array = new JSONArray();
		JSONObject obj = new JSONObject();
		obj.put("someKey", "someValue");
		array.add(obj);

		List<City> cities = JsonParser.parseFrom(array);
		Assert.assertNotNull(cities);
		Assert.assertFalse(cities.isEmpty());
		Assert.assertTrue(cities.size() == 1);

		City city = cities.get(0);
		Assert.assertNull(city.get_Type());
		Assert.assertNull(city.getName());
		Assert.assertNull(city.getType());
		Assert.assertNotNull(city.getGeoPosition());
		Assert.assertNull(city.getGeoPosition().getLatitude());
		Assert.assertNull(city.getGeoPosition().getLongitude());
		Assert.assertNull(city.getId());
	}

	@SuppressWarnings("unchecked")
	@Test
	public void testValidJson() {
		JSONArray array = new JSONArray();
		JSONObject obj = new JSONObject();

		obj.put(Constants.EXPORT__TYPE, "someType");
		obj.put(Constants.EXPORT_ID, -1L);
		obj.put(Constants.EXPORT_NAME, "someName");
		obj.put(Constants.EXPORT_TYPE, "anotherType");

		JSONObject geo = new JSONObject();
		geo.put(Constants.EXPORT_LATITUDE, 123d);
		geo.put(Constants.EXPORT_LONGITUDE, 456d);
		obj.put(Constants.EXPORT_GEO_POSITION, geo);

		array.add(obj);

		List<City> cities = JsonParser.parseFrom(array);
		Assert.assertNotNull(cities);
		Assert.assertFalse(cities.isEmpty());
		Assert.assertTrue(cities.size() == 1);

		City city = cities.get(0);
		Assert.assertNotNull(city.get_Type());
		Assert.assertNotNull(city.getName());
		Assert.assertNotNull(city.getType());
		Assert.assertNotNull(city.getGeoPosition());
		Assert.assertNotNull(city.getId());

		Assert.assertEquals("someType", city.get_Type());
		Assert.assertEquals("someName", city.getName());
		Assert.assertEquals("anotherType", city.getType());
		Assert.assertEquals(Long.valueOf(-1L), city.getId());
		Assert.assertEquals(Double.valueOf(123d), city.getGeoPosition().getLatitude());
		Assert.assertEquals(Double.valueOf(456d), city.getGeoPosition().getLongitude());
	}
}
