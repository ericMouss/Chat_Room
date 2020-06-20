/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.SQLFeatureNotSupportedException;
import java.util.logging.Logger;
import javax.sql.DataSource;

/**
 *
 * @author Manfo
 */
public class SocketServer {
    private static int cpt=0;
	 public static void main (String[] args) throws SQLException, ClassNotFoundException, IOException {
		 // Définition de la socket serveur	
		 	ServerSocket server = null;
		    int port = AccessConfig.getPORT_SERVER();
		    DataSource dt = new DataSource() {
                             @Override
                             public Connection getConnection() throws SQLException {
                                 throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
                             }

                             @Override
                             public Connection getConnection(String string, String string1) throws SQLException {
                                 throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
                             }

                             @Override
                             public PrintWriter getLogWriter() throws SQLException {
                                 throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
                             }

                             @Override
                             public void setLogWriter(PrintWriter writer) throws SQLException {
                                 throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
                             }

                             @Override
                             public void setLoginTimeout(int i) throws SQLException {
                                 throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
                             }

                             @Override
                             public int getLoginTimeout() throws SQLException {
                                 throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
                             }

                             @Override
                             public Logger getParentLogger() throws SQLFeatureNotSupportedException {
                                 throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
                             }

                             @Override
                             public <T> T unwrap(Class<T> type) throws SQLException {
                                 throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
                             }

                             @Override
                             public boolean isWrapperFor(Class<?> type) throws SQLException {
                                 throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
                             }
                         };
		    
		    try {//PORT OF SERVER
			      server = new ServerSocket(port);
			      System.out.println("Server  Ok");
			   }catch(IOException e) { 
				   System.out.println("Error server "+e);
			   }
		    
		    if(server!=null) {
			      while ( true ) {
			    	  Connection connDB=null;
			    	  try {
			    	  connDB = DataSource.getConnection();
			    	  System.out.println("Connection DB ok");
			    	  }catch(SQLException e1) {
			    		  System.out.println("Connection DB Refused "+e1);
			    	  }
			    	  if(connDB != null) {
				        System.out.println("Waiting client") ;
				        Socket client = null;
						try {
							client = server.accept();
						} catch (IOException e) {System.out.println("Error connection client");}
						cpt++;
				        System.out.println("Connection established "+cpt);      
				        //creation RequsetHandlre
				        RequestHandler req = new RequestHandler(client, connDB);
				        Thread service = new Thread(req);
				        service.start();
			    	  }
			      } 
		      }
	 }
    
}
