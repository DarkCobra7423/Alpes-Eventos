/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Conectar;

import java.sql.Connection;
import java.sql.DriverManager;
import javax.swing.JOptionPane;

/**
 *
 * @author TAMY
 */
public class Conectar {
    
    Connection conect = null;
    
    public Connection conexion(){
        try{
            Class.forName("com.mysql.jdbc.Drive");
            conect=DriverManager.getConnection("","","");
            System.out.println("Conexion estable");
        }catch(Exception ex){
            JOptionPane.showMessageDialog(null, "Base de datos no encontrada \n"+ex);
        }
        return conect;
    }
    
}
