package com.example.category.utils;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class DBConnect {
    private static Connection connection;

   public static Connection getConnection() throws SQLException, IOException{
       if(connection == null || connection.isClosed()){
           Properties properties = new Properties();
           InputStream inputStream = DBConnect.class.getClassLoader().getResourceAsStream("db.properties");
           if (inputStream == null) {
               throw new FileNotFoundException("Property file 'db.properties' not found in classpath");
           }
           properties.load(inputStream);
           String url = properties.getProperty("db.url");
           String username = properties.getProperty("db.username");
           String password = properties.getProperty("db.password");
           
           try {
               // Explicitly register MySQL driver
               Class.forName("com.mysql.cj.jdbc.Driver");
           } catch (ClassNotFoundException e) {
               throw new SQLException("MySQL JDBC Driver not found", e);
           }
           
           connection = DriverManager.getConnection(url, username, password);
           System.out.println("Connection established");
       }
       return connection;
   }


    public static void closeConnection(CallableStatement cs) {
        try {
            if (cs != null && !cs.isClosed()) {
                cs.close();
                System.out.println("CallableStatement closed");
            }
        } catch (SQLException e) {
            System.err.println("Error while closing CallableStatement");
        }

        try {
            if (connection != null && !connection.isClosed()) {
                connection.close();
                System.out.println("Connection closed");
            }
        } catch (SQLException e) {
            System.err.println("Error while closing connection");
        } finally {
            connection = null;
        }
    }

}
