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
    public static String NacGlobal="";
    public static String usuarioGlobal="";
    public static String passGlobal="";
    public static String tipoGlobal="";
    /////////////////////
    
    void NivelAcceso(String Usuario){
        
        String id="", nom="", nacimiento="", usuario="", pass="", tipo="";
        
        String nivel="SELECT * FROM Usuario WHERE `usuario`='"+Usuario+"'";
        
        try{
            Statement st =cn.createStatement();
            ResultSet rs=st.executeQuery(nivel);
            
            while(rs.next()){
                id=rs.getString(1);
                nom=rs.getString(2);
                tipo=rs.getString(3);
                nacimiento=rs.getString(4);
                usuario=rs.getString(5);
                pass=rs.getString(6);
            }//FIN WHILE
            
            ///////VALIDACION GLOBAL///////////////////////////////
            idGlobal=id;
            nomGlobal=nom;
            NacGlobal=nacimiento;
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
