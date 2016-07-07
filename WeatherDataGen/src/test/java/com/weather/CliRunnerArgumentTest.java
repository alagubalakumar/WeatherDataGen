package com.weather;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.io.IOException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;

import org.junit.Assert;
import org.junit.Test;

public class CliRunnerArgumentTest {
	
	private static String[] arguments1 = {"-r", "true", "-c", "200", "-f", "E:\\MyApps", "-sd", "01-01-2016", "-ed", "29-06-2016"};

	private static String[] arguments2 = {"-r", "false",  "-f", "E:\\MyApps"};
	static CliRunner runner;
	private static DateFormat dateFormater = new SimpleDateFormat("dd-MM-yyyy");

	

	@Test
	public void test() throws ParseException, IOException{
		runner = new CliRunner(arguments1);
		assertNotNull(runner);
		assertArrayEquals(arguments1,runner.getArgs());
		assertEquals("E:\\MyApps",runner.getOutputfile());
		assertEquals(dateFormater.parse("01-01-2016"),runner.getStartdate());
		assertEquals(dateFormater.parse("29-06-2016"),runner.getEnddate());
		assertEquals(200,runner.getRecordcount());
		assertTrue(runner.isMock());		
	}
	
	@Test
	public void testGenWeatherDataGenerator() throws ParseException, IOException{
		runner = new CliRunner(arguments1);
		IWeatherGenerator weatherGenerator = runner.getWeatherGenerator();	
		Assert.assertNull(weatherGenerator);
		
		assertTrue(runner.generateData());
		weatherGenerator = runner.getWeatherGenerator();
		assertNotNull(weatherGenerator);
		assertTrue(weatherGenerator instanceof WeatherDataGenerator);	
	}	
	
	@Test
	public void testGenRealTimeGenerator() throws ParseException, IOException{
		runner = new CliRunner(arguments2);
		IWeatherGenerator weatherGenerator = runner.getWeatherGenerator();	
		Assert.assertNull(weatherGenerator);
		assertTrue(runner.generateData());
		weatherGenerator = runner.getWeatherGenerator();
		assertNotNull(weatherGenerator);
		assertTrue(weatherGenerator instanceof RealTimeGenerator);	
	}	
	
}
