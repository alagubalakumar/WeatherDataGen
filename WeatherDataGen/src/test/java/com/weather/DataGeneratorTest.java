package com.weather;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;

import org.junit.Test;

public class DataGeneratorTest {
	private static DateFormat dateFormater = new SimpleDateFormat("dd-MM-yyyy");
	
	@Test
	public void testGetStation() {
		 String[] values = {"SYD","MEL","BNE","PER","ADL","CBR","HBA","DRW"};
		assertNotNull(DataGenerator.getStation());
		assertTrue(Arrays.asList(values).contains(DataGenerator.getStation()));
	}

	@Test
	public void testGetDate() throws ParseException {
		Date startDate = dateFormater.parse("01-01-2016");
		Date endDate = dateFormater.parse("31-01-2016");
		Date resultDate = DataGenerator.getDate(startDate, endDate);
		assertNotNull(resultDate);
		int diffInDays = (int) ((resultDate.getTime() - endDate.getTime()) / (1000 * 60 * 60 * 24));
		assertTrue(diffInDays < 30);
	}

	@Test
	public void testGetConditions() {
		String[] conditons = {"Rain","Sunny","Cold","Snow","Hot"};
		assertTrue(Arrays.asList(conditons).contains(DataGenerator.getConditions()));
	}

	@Test
	public void testGetTemperature() {
		String condition = "sunny";
		assertTrue(DataGenerator.getTemperature(condition) > 28 && DataGenerator.getTemperature(condition) < 35);
	}

	@Test
	public void testGetHumidity() {
		String condition = "sunny";
		assertTrue(DataGenerator.getTemperature(condition) > 10 && DataGenerator.getTemperature(condition) < 40);
	}

	@Test
	public void testGetPressure() {
		String condition = "rain";
		assertTrue(DataGenerator.getPressure(condition) > 899 && DataGenerator.getTemperature(condition) < 1000);
		String condition2 = "sunny";
		assertTrue(DataGenerator.getPressure(condition2) > 1000 && DataGenerator.getTemperature(condition2) < 1150);
	}

}
