package com.example.ss6.utils;

import jakarta.servlet.ServletContextEvent;
import jakarta.servlet.ServletContextListener;
import jakarta.servlet.annotation.WebListener;

import java.sql.Driver;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Enumeration;

/**
 * Listener to properly clean up MySQL resources when the application shuts down.
 * This prevents "Illegal access: this web application instance has been stopped already" errors.
 */
@WebListener
public class MySQLCleanupListener implements ServletContextListener {

    @Override
    public void contextDestroyed(ServletContextEvent sce) {
        // Deregister JDBC drivers
        Enumeration<Driver> drivers = DriverManager.getDrivers();
        while (drivers.hasMoreElements()) {
            Driver driver = drivers.nextElement();
            try {
                DriverManager.deregisterDriver(driver);
                System.out.println("Deregistered JDBC driver: " + driver);
            } catch (SQLException ex) {
                System.err.println("Error deregistering driver: " + driver);
                ex.printStackTrace();
            }
        }

        // Shutdown the MySQL AbandonedConnectionCleanupThread
        try {
            // Force using reflection to get the class since the package has sometimes changed
            Class<?> cls = Class.forName("com.mysql.cj.jdbc.AbandonedConnectionCleanupThread");
            // Call checkedShutdown method
            cls.getMethod("checkedShutdown").invoke(null);
            System.out.println("MySQL AbandonedConnectionCleanupThread shutdown complete");
        } catch (Exception e) {
            System.err.println("Error shutting down MySQL cleanup thread: " + e.getMessage());
            e.printStackTrace();
        }
    }

    @Override
    public void contextInitialized(ServletContextEvent sce) {
        // Nothing to do here
    }
}