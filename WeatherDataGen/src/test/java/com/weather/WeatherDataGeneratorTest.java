package com.weather;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;

import org.junit.BeforeClass;
import org.junit.Test;

public class WeatherDataGeneratorTest {

	private static DateFormat dateFormater = new SimpleDateFormat("dd-MM-yyyy");
	private static WeatherDataGenerator weatherDataGenerator = null;
	
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		weatherDataGenerator = new WeatherDataGenerator( "E:\\MyApps", 200, dateFormater.parse("01-01-2016"), dateFormater.parse( "29-06-2016"));
	}

	@Test
	public void testGenerateData() throws ParseException {
	   assertTrue(weatherDataGenerator.generateData());
	}

	@Test
	public void testGetOutputDataDir() {
		assertNotNull(weatherDataGenerator.getOutputDataDir());
		assertEquals("E:\\MyApps", weatherDataGenerator.getOutputDataDir());
	}

	
	@Test
	public void testGetNoOFRecords() {
		assertEquals(200, weatherDataGenerator.getNoOFRecords());
	}

	@Test
	public void testGetStartDate() {
		assertNotNull(weatherDataGenerator.getStartDate());
	}

	@Test
	public void testGetEndDate() {
		assertNotNull(weatherDataGenerator.getEndDate());
	}
}
