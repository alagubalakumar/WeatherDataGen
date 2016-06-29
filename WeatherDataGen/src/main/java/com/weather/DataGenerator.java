package com.weather;

import java.util.Date;

import org.fluttercode.datafactory.impl.DataFactory;

public class DataGenerator {
	  static String[] values = {"SYD","MEL","BNE","PER","ADL","CBR","HBA","DRW"};
	  static String[] conditons = {"Rain","Sunny","Cold","Snow","Hot"};
	  static DataFactory stationDataFactory = new DataFactory();
	  static DataFactory dataFactory = new DataFactory();
	  DataFactory df = new DataFactory();
	 
	  public static String getStation(){
		 return  stationDataFactory.getItem(values,100,"SYD");
	  }	
	  
	  public static Date getDate(Date startDate,Date endDate){
		  return dataFactory.getDateBetween(startDate, endDate);
	  }
	  
	  public static String getConditions(){
		  return dataFactory.getItem(conditons,100,"Sunny");
	  }
	  
	  public static int getTemperature(String condition){
		  int temp = 0;
		  if(condition.equalsIgnoreCase("sunny")){
			  temp = dataFactory.getNumberBetween(28, 35);
		  }else if(condition.equalsIgnoreCase("hot")){
			 temp = dataFactory.getNumberBetween(35, 40);
		  }else if(condition.equalsIgnoreCase("snow")){
			 temp = dataFactory.getNumberBetween(-15, 0);
		  }else if(condition.equalsIgnoreCase("rain")){
				 temp = dataFactory.getNumberBetween(20, 30);
		  }else if(condition.equalsIgnoreCase("cold")){
				 temp = dataFactory.getNumberBetween(-12, 10);
		 }
		 return temp;
	  }
	  
	  public static int getHumidity(String condition){
		  int humid = 0;
		  if(condition.equalsIgnoreCase("sunny")){
			  humid = dataFactory.getNumberBetween(10, 40);
		  }else if(condition.equalsIgnoreCase("hot")){
			  humid = dataFactory.getNumberBetween(35, 40);
		  }else if(condition.equalsIgnoreCase("snow")){
			  humid = dataFactory.getNumberBetween(30, 60);
		  }else if(condition.equalsIgnoreCase("rain")){
			  humid = dataFactory.getNumberBetween(50, 100);
		  }else if(condition.equalsIgnoreCase("cold")){
			  humid = dataFactory.getNumberBetween(50, 100);
		 }
		 return humid;
	  }
	  
	  public static int getPressure(String condition){
		  if(condition.equalsIgnoreCase("rain")){
			 return  dataFactory.getNumberBetween(899, 1000);
		  }
		  return dataFactory.getNumberBetween(1000, 1150);
	  }
}