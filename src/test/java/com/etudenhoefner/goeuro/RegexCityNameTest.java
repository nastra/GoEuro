package com.etudenhoefner.goeuro;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.etudenhoefner.goeuro.Constants;

/**
 * Tests the regex that is used for matching city names.
 * 
 * @author Eduard Tudenhoefner
 * 
 */
public class RegexCityNameTest {

	@Test
	public void matches() {
		Assert.assertTrue("Überlingen".matches(Constants.REGEX_CITYNAME));
		Assert.assertTrue("München".matches(Constants.REGEX_CITYNAME));
		Assert.assertTrue("Berlin".matches(Constants.REGEX_CITYNAME));
		Assert.assertTrue("New York".matches(Constants.REGEX_CITYNAME));
		Assert.assertTrue("San Francisco".matches(Constants.REGEX_CITYNAME));
		Assert.assertTrue("St. Petersburg".matches(Constants.REGEX_CITYNAME));
		Assert.assertTrue("San-Francisco".matches(Constants.REGEX_CITYNAME));
		Assert.assertTrue("Carmel-by-the-Sea".matches(Constants.REGEX_CITYNAME));
		Assert.assertTrue("St. Mary-St. Heights".matches(Constants.REGEX_CITYNAME));
		Assert.assertTrue("Münster-Neustädt".matches(Constants.REGEX_CITYNAME));
	}

	@Test
	public void notMatches() {
		Assert.assertFalse("Überlingen ".matches(Constants.REGEX_CITYNAME));
		Assert.assertFalse("San-".matches(Constants.REGEX_CITYNAME));
		Assert.assertFalse("St .Petersburg".matches(Constants.REGEX_CITYNAME));
		Assert.assertFalse("St . Petersburg".matches(Constants.REGEX_CITYNAME));
	}
}
