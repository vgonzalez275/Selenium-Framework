package com.platform.project.commons;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

import org.apache.log4j.Logger;

public class ReadPropertyFile {
	private static String configFileLocation="./src/test/resources/config.properties";
	private static Logger logger = Logger.getLogger(ReadPropertyFile.class);
	
	private static String readFile(String file, String key) {
		String value=null;
		try {
			Properties prop = new Properties();
			FileInputStream in = new FileInputStream(file);
			prop.load(in);
			
			//get values from the property file
			value = prop.getProperty(key);
		}catch(IOException ioe){
			ioe.printStackTrace();
			logger.info("Couldn't locate the property file");
		}
		logger.info("Value in property file for "+key+" is "+value);
		return value;
	}
	
	public static String getConfigPropertyVal(String key) {
		String configPropertyVal = readFile(configFileLocation, key);
		
//		if(configPropertyVal==null)
//			return configPropertyVal
//		else
//			return configPropertyVal.trim();
		return configPropertyVal != null ? configPropertyVal.trim() : configPropertyVal;
	}
}
