package com.etudenhoefner.goeuro;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.List;

import org.json.simple.JSONArray;

import com.etudenhoefner.goeuro.model.City;

/**
 * The main entry point for the application where the input is parsed and passed
 * to the GoEuro API. It also triggers data extraction and data storage.
 * 
 * @author Eduard Tudenhoefner
 * 
 */
public class Main {

	public static void main(String[] args) {
		String cityName = parseInputAndExtractCityName(args);

		URI uri = encode(Constants.ENDPOINT_API + cityName);

		List<City> cities = retrieveCities(uri);

		export(getOutputDirectory() + Constants.CSV_NAME, cities);
	}

	private static String parseInputAndExtractCityName(String[] args) {
		if (args.length > 1) {
			throw new IllegalArgumentException(
					"Too many arguments were provided! Please provide only a single argument.");
		}
		if (args.length == 0) {
			throw new IllegalArgumentException(
					"Please provide a single argument! If the argument contains whitespace, enclose it with \".");
		}
		String argument = args[0];
		if (!argument.matches(Constants.REGEX_CITYNAME)) {
			throw new IllegalArgumentException("The input must be a valid city name!");
		}
		return argument.trim();
	}

	/**
	 * Encodes the given url string.
	 * 
	 * @param urlString The url string to encode.
	 * @return An encoded {@link URI}.
	 */
	private static URI encode(String urlString) {
		URI uri = null;
		try {
			URL url = new URL(urlString);
			uri = new URI(url.getProtocol(), url.getUserInfo(), url.getHost(), url.getPort(), url.getPath(),
					url.getQuery(), url.getRef());
		} catch (MalformedURLException e) {
			exitWithMessage("The URL was malformed. " + e.getMessage());
		} catch (URISyntaxException e) {
			exitWithMessage("The was a problem with the URI syntax. " + e.getMessage());
		}
		return uri;
	}

	/**
	 * Downloads a list of {@link City} objects from the given {@link URI}.
	 * 
	 * @param uri The GoEuro endpoint where to download the cities from.
	 * @return A list of {@link City} objects from the given {@link URI}.
	 */
	private static List<City> retrieveCities(URI uri) {
		JSONArray array = null;
		try {
			array = JsonDownloader.getJSONArrayFromUri(uri);
		} catch (Exception e) {
			exitWithMessage("There was a problem retrieving data from the GoEuro API. " + e.getMessage());
		}
		return JsonParser.parseFrom(array);
	}

	/**
	 * Exports the given cities to the given file.
	 * 
	 * @param absoluteFilePath The final output file.
	 * @param cities The cities to export.
	 */
	private static void export(String absoluteFilePath, List<City> cities) {
		try {
			CsvExporter.exportToCsv(absoluteFilePath, cities);
		} catch (IOException e) {
			exitWithMessage("There was a problem exporting the data to the CSV file. " + e.getMessage());
		}
	}

	/**
	 * @return The absolute path of the current directory
	 */
	private static String getOutputDirectory() {
		return new File("").getAbsolutePath() + File.separator;
	}

	/**
	 * Prints an error message to the standard error output stream and exits.
	 * 
	 * @param message The message to be printed.
	 */
	private static void exitWithMessage(String message) {
		System.err.println(message);
		System.exit(1);
	}
}
