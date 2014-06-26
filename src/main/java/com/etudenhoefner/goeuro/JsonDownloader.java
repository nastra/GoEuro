package com.etudenhoefner.goeuro;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URI;

import org.apache.http.Header;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.json.simple.JSONArray;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

/**
 * The main responsibility of this class is to download a JSON array from a
 * given uri.
 * 
 * @author Eduard Tudenhoefner
 * 
 */
public class JsonDownloader {

	/**
	 * Downloads a JSON array from the given uri.
	 * 
	 * @param uri The uri to call using a HTTP GET request.
	 * @return A {@link JSONArray} in case the endpoint returned a json file,
	 *         otherwise <code>null</code> is returned.
	 * @throws ClientProtocolException
	 * @throws IOException
	 * @throws ParseException
	 */
	public static JSONArray getJSONArrayFromUri(URI uri) throws ClientProtocolException, IOException, ParseException {
		HttpGet get = new HttpGet(uri);
		CloseableHttpClient client = HttpClientBuilder.create().build();
		HttpResponse response = client.execute(get);
		Header contentType = response.getEntity().getContentType();

		if (contentType.getValue().contains(Constants.APPLICATION_JSON)) {
			InputStream is = response.getEntity().getContent();
			JSONParser parser = new JSONParser();
			Object obj = parser.parse(new InputStreamReader(is));
			if (obj instanceof JSONArray) {
				return (JSONArray) obj;
			}
		}

		client.close();
		return null;
	}
}
