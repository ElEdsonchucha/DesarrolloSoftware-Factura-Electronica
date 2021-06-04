/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Modelo;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.*;
import com.mysql.jdbc.Driver;
import com.mysql.cj.*;

public class ConexionSQL { 
    Connection cn;
    public static String db = "facturas";
    public static String driver = "com.mysql.cj.jdbc.Driver";
    public static String url = "jdbc:mysql://localhost:3306/"+db;
    public static String user = "root";
    public static String password = "";
    
    
    public Connection getConnection(){
        cn = null;
        try{
            Class.forName(driver);
            cn = (Connection) DriverManager.getConnection(url,user,password);
            if (cn!=null){
             //   System.out.println("Conexion establecida a: "+db);
            }
        }catch(ClassNotFoundException | SQLException ex){
            System.out.println(ex.getMessage());
        }
        return cn;
    }
    
    public void desconectar(){
        cn = null;
        if (cn == null){
            System.out.println("Conexion terminada");
        }
    }
  
}
