package dataProviders;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;

//File reader class for reading the config property file 
public class ConfigFileReader {

	public Properties properties;
	private final String propertyFilePath= "configs//Configuration.properties";


	public ConfigFileReader(){
		BufferedReader reader;
		try {
			reader = new BufferedReader(new FileReader(propertyFilePath));
			properties = new Properties();
			try {
				properties.load(reader);
				reader.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
			throw new RuntimeException("Configuration.properties not found at " + propertyFilePath);
		}		
	}
	
	public String getReportConfigPath(){
		String reportConfigPath = properties.getProperty("reportConfigPath");
		if(reportConfigPath!= null) return reportConfigPath;
		else throw new RuntimeException("Report Config Path not specified in the Configuration.properties file for the Key:reportConfigPath");		
	}

	public String getReportsFolderPath(){
		String reportsFolderPath = properties.getProperty("reportsFolderLocation");
		if(reportsFolderPath!= null) return reportsFolderPath;
		else throw new RuntimeException("Reports Folder Path not specified in the Configuration.properties file for the Key:reportsFolderLocation");		
	}
	
	public String getDEVAPIURL(){
		String dEVAPIURL = properties.getProperty("Dev_URI");
		if(dEVAPIURL!= null) return dEVAPIURL;
		else throw new RuntimeException("Dev URI not specified in the Configuration.properties file for the Key:Dev_URI");		
	}
	
	public String getApplicationURL(){
		String Application_URL = properties.getProperty("Application_URL");
		if(Application_URL!= null) return Application_URL;
		else throw new RuntimeException("Application URL not specified in the Configuration.properties file for the Key:Application_URL");		
	}
	
}