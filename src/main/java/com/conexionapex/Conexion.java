package com.conexionapex;

import java.sql.*;

public class Conexion {
    private static final String URL = "jdbc:oracle:thin:@//localhost:1521/MARVLPRD";;
    private static final String USUARIO = "oscar.bautista@correounivalle.edu.co";
    private static final String CONTRASENA = "oscar.BAUTISTA.90";

    public static Connection conectar() throws SQLException {
        DriverManager.registerDriver(new oracle.jdbc.driver.OracleDriver());
        return DriverManager.getConnection(URL, USUARIO, CONTRASENA);
    }
}
