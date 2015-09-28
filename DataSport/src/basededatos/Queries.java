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

    public Queries() {
       
    }
    
    
    
    static Connection cn;
    static Statement s;
    static ResultSet rs;
    
    public void AgregarBD(float vueltas,float incprom,float velprom,float calquem,float kms,float tiempo){
    
        try{
        cn = Conexion.Enlace(cn);
        Statement s=cn.createStatement();
        String query = "insert into modolibre values(null,"+vueltas+", "+incprom+", "+velprom+", "+calquem+", "+kms+","+tiempo+")";
        s.executeUpdate(query);
        s.close();
        cn.close();
        System.out.println("Agregado");
        }catch (SQLException ex) {
             ex.printStackTrace();
            
        }
    }
       public void eliminarTabla(){
    
        try{
        cn = Conexion.Enlace(cn);
        Statement s=cn.createStatement();
        String query = "drop table modolibre";
        s.executeUpdate(query);
        s.close();
        cn.close();
        System.out.println("Datos eliminados");
        }catch (SQLException ex) {
             ex.printStackTrace();
            
        }
    }
       public void crearTabla()
       {
       try{
        cn = Conexion.Enlace(cn);
        Statement s=cn.createStatement();
        String query = "create table modolibre(id_sesion integer primary key autoincrement,"
                + "vueltas real,incprom real,velprom real,calquem real,kms real,tiempo real)";
        s.executeUpdate(query);
        s.close();
        cn.close();
        System.out.println("creo la tabla");
        }catch (SQLException ex) {
             ex.printStackTrace();
            
        }
       }
}
        