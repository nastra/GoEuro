package com.etudenhoefner.goeuro;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;

import org.apache.http.client.ClientProtocolException;
import org.json.simple.JSONArray;
import org.json.simple.parser.ParseException;
import org.testng.Assert;
import org.testng.annotations.Test;

/**
 * Tests some basic functionality of the {@link JsonDownloader}.
 * 
 * @author Eduard Tudenhoefner
 * 
 */
public class JsonDownloaderTest {

	@Test
	public void testValidCity() throws ClientProtocolException, IOException, ParseException, URISyntaxException {
		URI uri = new URI(Constants.ENDPOINT_API + "Hamburg");
		JSONArray array = JsonDownloader.getJSONArrayFromUri(uri);
		Assert.assertNotNull(array);
		Assert.assertFalse(array.isEmpty());
	}

	@Test
	public void testInvalidCity() throws URISyntaxException, ClientProtocolException, IOException, ParseException {
		URI uri = new URI(Constants.ENDPOINT_API + "InvalidCity");
		JSONArray array = JsonDownloader.getJSONArrayFromUri(uri);
		Assert.assertNotNull(array);
		Assert.assertTrue(array.isEmpty());
	}

	@Test
	public void endpointDoesNotReturnJson() throws URISyntaxException, ClientProtocolException, IOException,
			ParseException {
		URI uri = new URI("https://www.google.de/?#q=goeuro");
		JSONArray array = JsonDownloader.getJSONArrayFromUri(uri);
		Assert.assertNull(array);
	}

}
