package com.etudenhoefner.goeuro;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;

import com.etudenhoefner.goeuro.model.City;
import com.etudenhoefner.goeuro.model.GeoPosition;

/**
 * Tests the export functionality of the {@link CsvExporter}. In practice,
 * rather than creating new objects within the test methods (such as
 * {@link City}, ...), we would use a mocking framework. However, I decided not
 * to use one for this small example.
 * 
 * @author Eduard Tudenhoefner
 * 
 */
public class CsvExporterTest {
	private static final String FILENAME = new File("").getAbsolutePath() + File.separator + Constants.CSV_NAME;

	@AfterMethod
	public void afterMethod() {
		// perform some cleanup after each test method
		File file = new File(FILENAME);
		if (file.exists()) {
			boolean deleted = file.delete();
			Assert.assertTrue(deleted, "File could not be deleted!");
		}
	}

	@Test
	public void exportOneCity() throws IOException {
		List<City> cities = new ArrayList<City>();
		City city = new City(123L, "someType", "cityName", "anotherType", new GeoPosition(123d, 456d));
		cities.add(city);

		CsvExporter.exportToCsv(FILENAME, cities);
		File exportedFile = new File(FILENAME);
		Assert.assertTrue(exportedFile.exists(), "The exported file does not exist!");
		Assert.assertTrue(exportedFile.isFile(), "The exported file is not a file, but maybe a directory!");
		Assert.assertTrue(exportedFile.length() > 0, "The exported file was empty!");
		exportedFile.delete();
	}

	@Test(expectedExceptions = {IOException.class})
	public void fileNotValid() throws IOException {
		List<City> cities = new ArrayList<City>();
		City city = new City(123L, "someType", "cityName", "anotherType", new GeoPosition(123d, 456d));
		cities.add(city);
		CsvExporter.exportToCsv("//someInvalidFileName/", cities);
	}

	@Test
	public void emptyCities() throws IOException {
		CsvExporter.exportToCsv(FILENAME, new ArrayList<City>());
		File exportedFile = new File(FILENAME);
		Assert.assertTrue(exportedFile.length() == 0, "The exported file was not empty!");
	}

	@Test
	public void testNull() throws IOException {
		CsvExporter.exportToCsv(FILENAME, null);
		File exportedFile = new File(FILENAME);
		Assert.assertTrue(exportedFile.length() == 0, "The exported file was not empty!");
	}
}
