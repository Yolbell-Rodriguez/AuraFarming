package com.cpifppiramide.ofertas.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnection {


    private static Connection connection;

    private DBConnection(){}

    public static Connection getInstance() {
        if(connection == null) {
            try {
                connection = DriverManager.getConnection(
                        "jdbc:mysql://prueba1.cn7jhssqpwgs.us-east-1.rds.amazonaws.com:3306/aura",
                        "admin", "YOLBELL2005");
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }
        return connection;
    }

}
