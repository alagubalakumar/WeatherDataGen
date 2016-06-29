package com.weather;

import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class WeatherBean {
	
	DateFormat dateFormater = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	NumberFormat plusMinusNF = new DecimalFormat("+##;-##");
	private String station;
	private Date localtime;
	private String conditions;
	private double temperature;
	private double pressure;
	private double humidity;
	private String coordinates;
	
	public String getStation() {
		return station;
	}
	public void setStation(String station) {
		this.station = station;
	}
	public Date getLocaltime() {
		return localtime;
	}
	public void setLocaltime(Date localtime) {
		this.localtime = localtime;
	}
	public String getConditions() {
		return conditions;
	}
	public void setConditions(String conditions) {
		this.conditions = conditions;
	}
	public double getTemperature() {
		return temperature;
	}
	public void setTemperature(double temperature) {
		this.temperature = temperature;
	}
	public double getPressure() {
		return pressure;
	}
	public void setPressure(double pressure) {
		this.pressure = pressure;
	}
	public double getHumidity() {
		return humidity;
	}
	public void setHumidity(double humidity) {
		this.humidity = humidity;
	}
	
	public String getCoordinates() {
		return coordinates;
	}
	public void setCoordinates(String coordinates) {
		this.coordinates = coordinates;
	}
	public String getString(){
		return station+"|"+coordinates+"|"+dateFormater.format(localtime)+"|"+conditions+"|"+plusMinusNF.format(temperature)+"|"+pressure+"|"+humidity;
	}

}
