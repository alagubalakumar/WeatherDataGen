package com.weather;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;

import org.junit.Test;

public class WeatherGeneratorFactoryTest {	
	
	private static DateFormat dateFormater = new SimpleDateFormat("dd-MM-yyyy");
	
	

	/**
	 * Test to check the factory is returning WeatherDataGenerator when the argument -r flag is set to true.
	 * @throws ParseException 
	 */
	@Test
	public void testGetGeneratorWeatherDataGenerator() throws ParseException {
		IWeatherGenerator weatherGenerator = WeatherGeneratorFactory.getGenerator(true, "E:\\MyApps", 200, dateFormater.parse("01-01-2016"), dateFormater.parse( "29-06-2016"));
		assertNotNull(weatherGenerator);
		assertTrue(weatherGenerator instanceof WeatherDataGenerator);	
		
	}
	
	/**
	 * Test to check the factory is returning RealTimeGenerator when the argument -r flag is set to false.
	 * @throws ParseException 
	 */
	@Test
	public void testGetGeneratorRealTimeGenerator() throws ParseException {
		IWeatherGenerator weatherGenerator = WeatherGeneratorFactory.getGenerator(false, "E:\\MyApps", 0,null,null);
		assertNotNull(weatherGenerator);
		assertTrue(weatherGenerator instanceof RealTimeGenerator);	
		
	}

}
