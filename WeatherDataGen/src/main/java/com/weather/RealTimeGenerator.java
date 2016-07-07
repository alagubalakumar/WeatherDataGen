package com.weather;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.TimeZone;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.codehaus.jackson.map.ObjectMapper;
import org.json.JSONObject;

import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.WebResource;

public class RealTimeGenerator implements IWeatherGenerator{

	private static final Logger log = Logger.getLogger(RealTimeGenerator.class.getName());
	private static SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	private static ObjectMapper objectMapper = new ObjectMapper();
	private static String[] citiesArr = {"Melbourne","Brisbane","Perth","Adelaide","Canberra","Hobart","Darwin"};
    private String outputDataDir;
    
    public RealTimeGenerator(String outputDataDir){
    	this.outputDataDir = outputDataDir;
    }

    /**
     * Method will gather and parse weather data and create a output file
     * @param outputDataDir
     */
	@SuppressWarnings("unchecked")
	public  boolean generateData() {
		try {

			try(Writer fileWriter = new FileWriter(getOutputFile(outputDataDir),false)){
			for (String city : citiesArr){
			JSONObject weatherJsonObj = getWeatherData(city);
			List<Map<String, Object>> dataList = objectMapper.readValue(weatherJsonObj.get("list").toString(), List.class);
			Map<String, Object> cityMap = objectMapper.readValue(weatherJsonObj.get("city").toString(), Map.class);
			for (Map<String, Object> data : dataList) {
				WeatherBean weatherBean = new WeatherBean();
				weatherBean.setStation(getCities().get(city));
				long unixSeconds = Long.valueOf(data.get("dt").toString());
				Date date = new Date(unixSeconds * 1000);
				sdf.setTimeZone(TimeZone.getTimeZone("GMT-4"));
				weatherBean.setLocaltime(sdf.parse(sdf.format(date)));
				Map<String, Double> tempDataMap = (Map<String, Double>) data.get("temp");
				double temp = ((Number)tempDataMap.get("day")).doubleValue();
				weatherBean.setTemperature(fahrenheitToCelsius(temp));
				Map<String, Object> weatherDataMap = ((List<Map<String, Object>>) data.get("weather")).get(0);
				weatherBean.setConditions(weatherDataMap.get("main").toString());
				Map<String, Double> coordinMap = (Map<String, Double>) cityMap.get("coord");
				weatherBean.setCoordinates(coordinMap.get("lon")+","+coordinMap.get("lat"));
				weatherBean.setPressure(Double.valueOf(data.get("pressure").toString()));
				weatherBean.setHumidity(Double.valueOf(data.get("humidity").toString()));
				fileWriter.write(weatherBean.getString());
			    fileWriter.write("\n");
			}}}
		} catch (Exception e) {
			log.log(Level.SEVERE,e.getMessage());
			return false;
		}
		log.log(Level.INFO, "Completed..");
		return true;
	}
	
	/**
	 * Method to get real time weather data from openweathermap api
	 * @param city
	 * @return
	 */
	public static JSONObject getWeatherData(String city){
		Client client = Client.create();
		WebResource webResource = client.resource("http://api.openweathermap.org/data/2.5/forecast/daily?q="+city+"&cnt=16&APPID=3a57afb8a29fd07d54b0cf26fe43b503");
		ClientResponse response = webResource.accept("application/json").get(ClientResponse.class);
		if (response.getStatus() != 200) {
			throw new RuntimeException("Failed : HTTP error code : " + response.getStatus());
		}
		return new JSONObject(response.getEntity(String.class));
	}
	
	/**
	 * Method will check and create output file in the supplied directory
	 * @param outputDataDir
	 * @return
	 * @throws IOException
	 */
	public static File getOutputFile(String outputDataDir) throws IOException{
		File opFile = new File(outputDataDir+File.separator+"weatherdata.txt");
		if(!opFile.exists()){
		opFile.createNewFile();
		}
		return opFile;
	}

	/**
	 * Method to convert fahrenheit to celsius
	 * @param ftemp
	 * @return
	 */
	public static double fahrenheitToCelsius(double ftemp) {
		double temperatue = ((ftemp - 32) * 5) / 9;
		return temperatue;
	}
	
	
	/**
	 * Method to get cities list,it is used to get cities label.
	 * @return
	 */
	public static Map<String,String> getCities(){
		Map<String,String> citiesMap = new HashMap<String, String>();
		citiesMap.put("Melbourne", "MEL");
		citiesMap.put("Brisbane", "BNE");
		citiesMap.put("Perth", "PER");
		citiesMap.put("Adelaide", "ADL");
		citiesMap.put("Canberra", "CBR");
		citiesMap.put("Hobart", "HBA");
		citiesMap.put("Darwin", "DRW");
		return citiesMap;
		
	}

	public String getOutputDataDir() {
		return outputDataDir;
	}

	public void setOutputDataDir(String outputDataDir) {
		this.outputDataDir = outputDataDir;
	}

	public static String[] getCitiesArr() {
		return citiesArr;
	}

	public static void setCitiesArr(String[] citiesArr) {
		RealTimeGenerator.citiesArr = citiesArr;
	}
}
