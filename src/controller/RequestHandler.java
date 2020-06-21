/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.Socket;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import javafx.scene.control.Alert;
import javax.sql.DataSource;

/**
 *
 * @author Manfo
 */
class RequestHandler implements Runnable {
    private static int cpt=0;
	private int num;
	private Socket client= null;
	private Connection connDB;
	//private JsonReader reader;
	//private JsonWriter writer;
	private static Map<Integer, ArrayList<Historical>> CACHE = new HashMap<>();


	public RequestHandler(Socket client, Connection connDB) {
		num=cpt++;
		this.client = client;
		this.connDB = connDB;


		try {
			reader = new JsonReader(new InputStreamReader(client.getInputStream(), "UTF-8"));
			writer = new JsonWriter(new OutputStreamWriter(client.getOutputStream(), "UTF-8"));
		} catch (IOException e) {}

	}

	@Override
	public void run(){
		//Communication Json
		try {
			System.out.println("Thread:"+num+" "+requestHandler(reader));
			if(!taskBrokenSensors.isAlive()) {
				taskBrokenSensors.start();
			}
		} catch (IOException e) {
			System.out.println("Error communication to client "+e);
		} catch (SQLException e) {
			System.out.println("Error DB "+e);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		finally{
			try {
				stopConnection();
			} catch (IOException e) {}
		}

	}

	

    private static class JsonReader {

        public JsonReader(InputStreamReader inputStreamReader) {
        }
    }
}
