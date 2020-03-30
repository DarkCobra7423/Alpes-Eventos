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
            
            String url="www.db4free.net:3306";
            String bd="alpes_eventos";
            String usu="angelpadilla1234";
            String pass="QWERTY12345c";
            
            Class.forName("com.mysql.cj.jdbc.Driver");
            //conect = DriverManager.getConnection("jdbc:mysql://" + url + "/" + bd, usu, pass);
            conect = DriverManager.getConnection("jdbc:mysql://" + "localhost" + "/" + bd, "root", "");
            System.out.println("Connection established!");
        }catch(Exception ex){
            JOptionPane.showMessageDialog(null, "Base de datos Alpes_Eventos no encontrada \n"+ex);
        }
        return conect;
    }
    
}
