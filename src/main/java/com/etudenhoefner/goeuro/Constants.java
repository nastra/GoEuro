package com.etudenhoefner.goeuro;

/**
 * Encapsulates all constants within the project.
 * 
 * @author Eduard Tudenhoefner
 * 
 */
public interface Constants {
	static final String REGEX_CITYNAME = "^[a-zA-ZäüöÄÜÖ]+(?:(?:\\.\\s[a-zA-ZäüöÄÜÖ]+)*|(?:\u0020{0,1}(?:-\\s{0,1}){0,1}[a-zA-ZäüöÄÜÖ]+)*)*$";

	static final String ENDPOINT_API = "https://api.goeuro.com/GoEuroAPI/rest/api/v2/position/suggest/en/";

	static final String CSV_NAME = "out.csv";

	static final String CSV_DELIMITER = ";";

	static final String ISO_8859_1 = "ISO-8859-1";

	static final String UTF8 = "UTF8";

	static final String APPLICATION_JSON = "application/json";

	// field names that should be exported to the csv file
	static final String EXPORT_LONGITUDE = "longitude";

	static final String EXPORT_LATITUDE = "latitude";

	static final String EXPORT_ID = "_id";

	static final String EXPORT_TYPE = "type";

	static final String EXPORT__TYPE = "_type";

	static final String EXPORT_NAME = "name";

	static final String EXPORT_GEO_POSITION = "geo_position";

}
