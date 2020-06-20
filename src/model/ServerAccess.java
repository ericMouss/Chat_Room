/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

/**
 *
 * @author Manfo
 */
public class ServerAccess {
    
    //singleton

	private static ServerAccess serverAccess = new ServerAccess();
	private static String SERVER;
	private static int PORT_SERVER;
	
	//private Constructor
	private ServerAccess(){
		Properties prop = new Properties();
		InputStream input = null;
		
		try {
			
			input = new FileInputStream("./ressources/client.properties");

			// load a properties file
			prop.load(input);
			
			// get the property value and print it out
			SERVER = prop.getProperty("SERVER");
			PORT_SERVER = Integer.valueOf(prop.getProperty("PORT_SERVER"));

			
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
	public static ServerAccess getInstance(){
	if(serverAccess == null)
		serverAccess = new ServerAccess();  
	    return serverAccess;
	}
	
	
	public static String getSERVER() {
		return SERVER;
	}

	public static int getPORT_SERVER() {
		return PORT_SERVER;
	}
    
}
