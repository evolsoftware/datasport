/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package basededatos;
import java.sql.*;



/**
 *
 * @author rafael
 */
public class Queries {
    
    
    
    static Connection cn;
    static Statement s;
    static ResultSet rs;
    
    public void AgregarBD(String kilometros, String calorias, String velocidad, String vueltas){
    
        try{
        cn = Conexion.Enlace(cn);
        Statement s=cn.createStatement();
        String query = "insert into modolibre values(null,"+kilometros+", "+calorias+", "+velocidad+", "+vueltas+")";
        s.executeUpdate(query);
        s.close();
        cn.close();
        System.out.println("Agregado");
        }catch (SQLException ex) {
             ex.printStackTrace();
            
        }
    }
}
        