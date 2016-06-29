package com.weather;

import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.apache.commons.cli.BasicParser;
import org.apache.commons.cli.CommandLine;
import org.apache.commons.cli.CommandLineParser;
import org.apache.commons.cli.HelpFormatter;
import org.apache.commons.cli.Options;
import org.apache.commons.cli.ParseException;

public class CliRunner {
	private static final Logger log = Logger.getLogger(CliRunner.class.getName());
	private String[] args = null;
	private Options options = new Options();
	private DateFormat dateFormater = new SimpleDateFormat("dd-MM-yyyy");
	private String outputfile;
	private int recordcount;
	private Date startdate;
	private Date enddate;
	private boolean isMock = false;

	public CliRunner(String[] args) {
		this.args = args;
		options.addOption("h", "help", false, "show help.");
		options.addOption("f", "outputfile", true, "Here you can set the outputfile");
		options.addOption("c", "recordcount", true, "Here you can set no of records to generate");
		options.addOption("sd", "startdate", true, "Here you can set the start date of the records");
		options.addOption("ed", "enddate", true, "Here you can set the end date of the records");
		options.addOption("r", "realtime", true,
				"set it to true|false to generate mock data or real time data.by default it is false");

	}

	public static void main(String[] args) throws IOException, InterruptedException, java.text.ParseException {
		log.log(Level.INFO, "Parsing the arguments");
		new CliRunner(args).parse();
	}

	/***
	 * Method will parse the command and call the weather data generator
	 * 
	 * @throws IOException
	 * @throws InterruptedException
	 * @throws java.text.ParseException
	 */
	public void parse() throws IOException, InterruptedException, java.text.ParseException {
		CommandLineParser parser = new BasicParser();
		CommandLine cmd = null;
		try {
			cmd = parser.parse(options, args);
			if (cmd.hasOption("h")) {
				help();
			}

			if (cmd.hasOption("f")) {
				outputfile = checkNull(cmd.getOptionValue("f"));
			}

			if (cmd.hasOption("r")) {
				System.out.println(cmd.getOptionValue("r"));
				isMock = Boolean.valueOf(cmd.getOptionValue("r"));
			}

			log.log(Level.INFO, "Done parsing the args..Started to generate data");
			
				if(isMock){
					if (cmd.hasOption("c")) {
						recordcount = Integer.valueOf(checkNull(cmd.getOptionValue("c")));
					}

					if (cmd.hasOption("sd")) {
						startdate = dateFormater.parse(checkNull(cmd.getOptionValue("sd")));
					}

					if (cmd.hasOption("ed")) {
						enddate = dateFormater.parse(checkNull(cmd.getOptionValue("ed")));
					}
					if ((recordcount != 0) && (startdate != null) && (enddate != null)) {
					// Calling the mock weather data generator
					WeatherDataGenerator mockGenerator = new WeatherDataGenerator();
					mockGenerator.generateData(outputfile, recordcount, startdate, enddate);
					}else{
						help();
					}
				
				} else {
					RealTimeGenerator realTimeGenerator = new RealTimeGenerator();
					realTimeGenerator.getData(outputfile);
				}
				log.log(Level.INFO, "Completed..");
		} catch (ParseException e) {
			log.log(Level.SEVERE, "Failed to parse comand line properties", e);
			help();
		}
	}
	
	private String checkNull(String val){
		if(val == null){
			help();
		}
		return val;
	}

	/**
	 * Method to print help option in the console
	 */
	private void help() {
		// This prints out some help
		HelpFormatter formater = new HelpFormatter();
		formater.printHelp("Main", options);
		System.exit(0);
	}
}