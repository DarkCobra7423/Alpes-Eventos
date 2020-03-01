/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Formulario;

import Conectar.Conectar;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

/**
 *
 * @author TAMY
 */
public class ControlAcceso {
    
    //VARIABLES GLOBALES
    public static String idGlobal="";
    public static String nomGlobal="";
    public static String apellidoGlobal="";
    public static String edadGlobal="";
    public static String direccionGlobal="";
    public static String usuarioGlobal="";
    public static String passGlobal="";
    public static String tipoGlobal="";
    /////////////////////
    
    void NivelAcceso(String Usuario){
        
        String id="", nom="", apellido="", edad="", direccion="", usuario="", pass="";
        String tipo="";
        
        String nivel="SELECT * FROM login WHERE usuario_usuario='"+Usuario+"'";
        
        try{
            Statement st =cn.createStatement();
            ResultSet rs=st.executeQuery(nivel);
            
            while(rs.next()){
                id=rs.getString(1);
                nom=rs.getString(2);
                apellido=rs.getString(3);
                edad=rs.getString(4);
                direccion=rs.getString(5);
                usuario=rs.getString(6);
                pass=rs.getString(7);
                tipo=rs.getString(8);
            }//FIN WHILE
            
            ///////VALIDACION GLOBAL///////////////////////////////
            idGlobal=id;
            nomGlobal=nom;
            apellidoGlobal=apellido;
            edadGlobal=edad;
            direccionGlobal=direccion;
            usuarioGlobal=usuario;
            passGlobal=pass;
            
            if(Usuario.equals("admin")){
                tipoGlobal="Administrador";
                System.out.println("Condicion equeals controlacceso ---->"+tipoGlobal);
            }else{
                tipoGlobal=tipo;
                System.out.println("Condicion else controlacceso ---->"+tipoGlobal);
               
            }
            
            IniciarSistema();
            
        }catch(Exception ex){
            System.out.println("Metodo NivelAcceso clase ControlAcceso \n"+ex);
        }
        
    }//FIN DEL METODO NIVEL USUARIO
    
    void IniciarSistema(){//METODO PARA INICIAR EL SISTEMA
        Principal princ=new Principal();
        princ.setVisible(true);
        
        princ.pack();
    }//FIN DEL METODO INICIAR SISTEMA
  
    Conectar cc=new Conectar();
    Connection cn=cc.conexion();
}
