package com.weather;

import java.util.Date;

public class WeatherGeneratorFactory {
	
	
	public static IWeatherGenerator getGenerator(boolean isMock,String outputfile,int recordcount,Date startdate,Date enddate){
		
		if(isMock){
			return  new WeatherDataGenerator( outputfile,recordcount,startdate,enddate);
		}else{
			return new RealTimeGenerator(outputfile);
		}
	}

}
