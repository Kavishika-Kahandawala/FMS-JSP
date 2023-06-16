/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.master.databaseLayer;

/**
 *
 * @author Kavishika
 */

import java.sql.*;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;


public class ConnectionPool {
    private static ConnectionPool pool = null;
    private static DataSource dataSource = null;
 
    private ConnectionPool() {
        try {
            System.out.println("Res:        connection Pool");
            InitialContext ic = new InitialContext();
            dataSource = (DataSource) ic.lookup("java:/comp/env/jdbc/FMS");
        } catch (NamingException e) {
            System.out.println(e);
        }
    }
 
    public static synchronized ConnectionPool getInstance() {
        if (pool == null) {
            pool = new ConnectionPool();
        }
        return pool;
    }
 
    public Connection getConnection() {
        try {
            System.out.println("Res:        got connection");
            return dataSource.getConnection();
        } catch (SQLException e) {
            System.out.println(e);
            return null;
        }
    }
 
    public void freeConnection(Connection c) {
        try {
            System.out.println("Res:        Connection Freed");
            c.close();
        } catch (SQLException e) {
            System.out.println(e);
        }
    }
}
