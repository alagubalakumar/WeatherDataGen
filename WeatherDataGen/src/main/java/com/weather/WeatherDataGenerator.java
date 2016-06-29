package com.weather;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;

public class WeatherDataGenerator {
	
	private static final Logger log = Logger.getLogger(WeatherDataGenerator.class.getName());
	
	/**
	 * This method will generate weather bean by calling data generator which has the datafactory 
	 * also its writes the data to the output file
	 * @param outputDataDir
	 * @param noOFRecords
	 * @return
	 */
	public boolean generateData(String outputDataDir,int noOFRecords,Date startDate,Date endDate) {
		try(Writer fileWriter = new FileWriter(getOutputFile(outputDataDir),false)){
		for(int i = 0;i < noOFRecords;i++){
		      WeatherBean weather = new WeatherBean();
		      weather.setStation(DataGenerator.getStation());
		      weather.setLocaltime(DataGenerator.getDate(startDate, endDate));
		      weather.setConditions(DataGenerator.getConditions());
		      weather.setTemperature(DataGenerator.getTemperature(weather.getConditions()));
		      weather.setHumidity(DataGenerator.getHumidity(weather.getConditions()));
		      weather.setPressure(DataGenerator.getPressure(weather.getConditions()));
		      fileWriter.write(weather.getString());
		      fileWriter.write("\n");
			}}catch (Exception e) {
				log.log(Level.SEVERE,e.getMessage());
			}		
		return true;
	}
	
	/**
	 * Method will check and create output file in the supplied directory
	 * @param outputDataDir
	 * @return
	 * @throws IOException
	 */
	private static File getOutputFile(String outputDataDir) throws IOException{
		File opFile = new File(outputDataDir+File.separator+"weatherdata.txt");
		if(!opFile.exists()){
		opFile.createNewFile();
		}
		return opFile;
	}
}