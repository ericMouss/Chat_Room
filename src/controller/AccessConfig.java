/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import java.io.FileInputStream;
import java.io.InputStream;

/**
 *
 * @author Manfo
 */
class AccessConfig {
    //singleton

	private static AccessConfig dbAccess = new AccessConfig();
	private static  String DB_DRIVER_CLASS;
	private static String DB_URL;
	private static String DB_USERNAME;
	private static String DB_PASSWORD;
	private static int INITIAL_SIZE;
	private static int MAX_SIZE;
	private static int PORT_SERVER;
	private static long INITIAL_DELAY; 
	private static long LAST_SIGNAL_DELAY;
	private static long DEFAULT_DELAY;
	//private Constructor
	private AccessConfig(){
		Properties prop = new Properties();
		InputStream input = null;
		
		try {
			
			input = new FileInputStream("./ressources/server.properties");

			// load a properties file
			prop.load(input);
			
			// get the property value and print it out
			DB_DRIVER_CLASS = prop.getProperty("DB_DRIVER_CLASS");
			DB_URL = prop.getProperty("DB_URL");
			DB_USERNAME = prop.getProperty("DB_USERNAME");
			DB_PASSWORD = prop.getProperty("DB_PASSWORD");
			INITIAL_SIZE = Integer.parseInt(prop.getProperty("INITIAL_POOL_SIZE"));
			MAX_SIZE = Integer.parseInt(prop.getProperty("MAX_POOL_SIZE"));
			PORT_SERVER =  Integer.parseInt(prop.getProperty("PORT_SERVER"));
			INITIAL_DELAY=Long.parseLong(prop.getProperty("INITIAL_DELAY"));
			LAST_SIGNAL_DELAY=Long.parseLong(prop.getProperty("LAST_SIGNAL_DELAY"));
			DEFAULT_DELAY=Long.parseLong(prop.getProperty("DEFAULT_DELAY"));
			
		} catch (IOException ex) {
			ex.printStackTrace();
		} finally {
			if (input != null) {
				try {
					input.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
	}
	   
	//getters
	public static AccessConfig getInstance(){
	if(dbAccess == null)
		dbAccess = new AccessConfig();  
	    return dbAccess;
	}
	
	public static String getDB_DRIVER_CLASS() {
		return DB_DRIVER_CLASS;
	}
	
	public static String getDB_URL() {
		return DB_URL;
	}

	public static String getDB_USERNAME() {
		return DB_USERNAME;
	}

	public static String getDB_PASSWORD() {
		return DB_PASSWORD;
	}
	
	public static int getINITIAL_SIZE() {
		return INITIAL_SIZE;
	}

	public static int getMAX_SIZE() {
		return MAX_SIZE;
	}
	
	@Override
	public String toString() {
		return "DBAccess [dbType=" + DB_DRIVER_CLASS + ", url=" + DB_URL + ", user=" + DB_USERNAME + ", password=" + DB_PASSWORD + "]";
	}


	public static int getPORT_SERVER() {
		return PORT_SERVER;
	}

	public static long getINITIAL_DELAY() {
		return INITIAL_DELAY;
	}

	public static long getLAST_SIGNAL_DELAY() {
		return LAST_SIGNAL_DELAY;
	}

	public static long getDEFAULT_DELAY() {
		return DEFAULT_DELAY;
	}
    
}
