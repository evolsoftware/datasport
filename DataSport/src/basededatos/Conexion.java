/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package basededatos;

import java.sql.*;

/**
 *
 * @author Reinaldo Pabon
 */


public class Conexion {
    static Connection cn = null;
    public static Connection Enlace(Connection cn)throws SQLException{
        String ruta = "E:\\datasport\\DataSport\\src\\basededatosBDdatasport1";
        try{
        Class.forName("org.sqlite.JDBC");
        cn = DriverManager.getConnection("jdbc:sqlite:"+ruta);        
        }catch(ClassNotFoundException ex){
        System.err.println("No se ha podido conectar a la BD en conexion-java\n"+ex.getMessage());
        }
        return cn;
    }
}
