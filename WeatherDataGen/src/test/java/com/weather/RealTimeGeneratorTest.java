package com.weather;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.io.File;
import java.io.IOException;
import java.util.Map;

import org.json.JSONObject;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

public class RealTimeGeneratorTest {

	private static RealTimeGenerator realTimeGenerator;
	
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		realTimeGenerator = new RealTimeGenerator("E:\\MyApps");
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
	}

	@Before
	public void setUp() throws Exception {
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testGenerateData() {
		  assertTrue(realTimeGenerator.generateData());
	}

	@Test
	public void testGetWeatherData() {
		JSONObject weatherJson = RealTimeGenerator.getWeatherData("Melbourne");
		assertNotNull(weatherJson);
	}

	@Test
	public void testGetOutputFile() throws IOException {
		assertNotNull(RealTimeGenerator.getOutputFile("E:\\MyApps"));
		assertEquals(new File("E:\\MyApps\\weatherdata.txt").getAbsolutePath(), RealTimeGenerator.getOutputFile("E:\\MyApps").getAbsolutePath());
	}

	@Test
	public void testFahrenheitToCelsius() {
		assertNotNull(RealTimeGenerator.fahrenheitToCelsius(86));
		assertEquals("30.0", RealTimeGenerator.fahrenheitToCelsius(86)+"");
	}

	@Test
	public void testGetCities() {
		Map<String,String> citiesMap  = RealTimeGenerator.getCities();
		assertNotNull(citiesMap);
		assertEquals(7, citiesMap.size());
	}

	@Test
	public void testGetOutputDataDir() {
		assertNotNull(realTimeGenerator.getOutputDataDir());
		assertEquals(new File("E:\\MyApps\\").getAbsolutePath(), realTimeGenerator.getOutputDataDir());
	}

	
	@Test
	public void testGetCitiesArr() {
		 String[] citiesArr = RealTimeGenerator.getCitiesArr();
		 assertNotNull(citiesArr);
		 assertEquals(7, citiesArr.length);
	}

}
