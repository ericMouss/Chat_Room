/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import java.sql.Connection;
import java.sql.SQLException;

/**
 *
 * @author Manfo
 */

// Interface pour la pool de connexion JDBC
public class JDBCConnectionPoolInterface {
    public Connection getConnection() throws SQLException;
    public boolean releaseConnection(Connection connection)throws SQLException;
    public void shutdownConnections()throws SQLException;
}
