package com.etudenhoefner.goeuro;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.util.List;

import com.etudenhoefner.goeuro.model.City;
import com.etudenhoefner.goeuro.model.GeoPosition;

/**
 * Exports the city information to a CSV file.
 * 
 * @author Eduard Tudenhoefner
 * 
 */
public class CsvExporter {

	/**
	 * Exports the given {@link City} objects to the given output file.
	 * 
	 * @param absoluteFileName The file where to write the {@link City}
	 *            information to.
	 * @param cities The {@link City} information to export.
	 * @throws IOException
	 */
	public static void exportToCsv(String absoluteFileName, List<City> cities) throws IOException {
		if (null == cities || cities.isEmpty()) {
			System.out.println("No lines were written as there were not cities to export.");
			return;
		}
		File file = new File(absoluteFileName);
		boolean alreadyExists = file.exists();

		OutputStreamWriter writer = new OutputStreamWriter(new FileOutputStream(file, alreadyExists), Constants.UTF8);
		if (!alreadyExists) {
			writerColumnHeaders(writer);
		}

		writeContent(cities, writer);

		writer.flush();
		writer.close();
		System.out.println(cities.size() + " lines were successfully written to " + file.getAbsolutePath());
	}

	/**
	 * Writes the city information to the output file.
	 * 
	 * @param cities The information to write.
	 * @param writer The used writer.
	 * @throws IOException
	 */
	private static void writeContent(List<City> cities, OutputStreamWriter writer) throws IOException {
		for (City city : cities) {
			writer.append(city.get_Type());
			writer.append(Constants.CSV_DELIMITER);
			writer.append(String.valueOf(city.getId()));
			writer.append(Constants.CSV_DELIMITER);
			writer.append(city.getName());
			writer.append(Constants.CSV_DELIMITER);
			writer.append(city.getType());
			writer.append(Constants.CSV_DELIMITER);
			GeoPosition geoPosition = city.getGeoPosition();
			if (null != geoPosition) {
				writer.append(String.valueOf(geoPosition.getLatitude()));
				writer.append(Constants.CSV_DELIMITER);
				writer.append(String.valueOf(geoPosition.getLongitude()));
			}
			writer.append(System.lineSeparator());
		}
	}

	/**
	 * Writes the column headers
	 * 
	 * @param writer The writer to use.
	 * @throws IOException
	 */
	private static void writerColumnHeaders(OutputStreamWriter writer) throws IOException {
		writer.append(Constants.EXPORT__TYPE);
		writer.append(Constants.CSV_DELIMITER);
		writer.append(Constants.EXPORT_ID);
		writer.append(Constants.CSV_DELIMITER);
		writer.append(Constants.EXPORT_NAME);
		writer.append(Constants.CSV_DELIMITER);
		writer.append(Constants.EXPORT_TYPE);
		writer.append(Constants.CSV_DELIMITER);
		writer.append(Constants.EXPORT_LATITUDE);
		writer.append(Constants.CSV_DELIMITER);
		writer.append(Constants.EXPORT_LONGITUDE);
		writer.append(System.lineSeparator());
	}
}
