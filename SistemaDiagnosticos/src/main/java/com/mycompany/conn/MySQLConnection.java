/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.conn;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 *
 * @author manuel
 */
public class MySQLConnection {
    private static MySQLConnection instance;   
    private Connection connection;

    private final String url = "jdbc:mysql://localhost:3306/genomic";
    private final String user = "root";
    private final String password = "";

    private MySQLConnection() {
        try {
            connection = DriverManager.getConnection(url, user, password);
            System.out.println("Connected to MySQL!");
        } catch (SQLException e) {
            throw new RuntimeException("Cannot connect to database", e);
        }
    }

    public static MySQLConnection getInstance() {
        if (instance == null) {
            instance = new MySQLConnection();
        }
        return instance;
    }

    public Connection getConnection() {
        return connection;
    }
    
}
