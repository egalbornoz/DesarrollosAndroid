package com.example.egalbornoz.pedidosapp;

import android.os.StrictMode;
import android.util.Log;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Conectar {
    String server, database, user, pass, driver;
    Conectar conectar;

    public Connection ConexionDB(String sr, String db, String us, String ps) {
        server = sr;
        database = db;
        user = us;
        pass = ps;
        /*--------------------------------------------------------------------------------------------------
        Método para iniciar la conexíon
        --------------------------------------------------------------------------------------------------*/
        Connection conexion = null;
        /*--------------------------------------------------------------------------------------------------
           Recuperar los parámetros de configuración
        --------------------------------------------------------------------------------------------------*/
        try {
            StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
            StrictMode.setThreadPolicy(policy);
            Class.forName("net.sourceforge.jtds.jdbc.Driver");
            //conexion = DriverManager.getConnection("jdbc:jtds:sqlserver://192.168.1.2;databaseName=Desarrollos;user=appuser;password=123456;");
            String driver = "jdbc:jtds:sqlserver://" + server + ";databaseName=" + database + ";user=" + user + ";password=" + pass;
            conexion = DriverManager.getConnection(driver);

        } catch (SQLException e) {
            Log.e("ERROR:1", e.getMessage());
        } catch (ClassNotFoundException e) {
            Log.e("ERROR:2", e.getMessage());
        } catch (Exception e) {
            Log.e("ERROR:3", e.getMessage());
        }
        return conexion;
    }
}