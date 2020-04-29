/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Formulario;

import java.applet.AudioClip;

/**
 *
 * @author TAMY
 */
public class Sonido {
    AudioClip correo, validacion;
    
    public Sonido(){
        correo=java.applet.Applet.newAudioClip(getClass().getResource("/Sonidos/alarma.wav"));
        validacion=java.applet.Applet.newAudioClip(getClass().getResource("/Sonidos/ValidacionDenegada.wav"));
    }//FIN DEL CONSTRUCTOR
    
    public void Correo(){
        correo.play();
    }//FIN CORREO
    
    public void ValidacionDenegada(){
        validacion.play();
    }//FIN VALIDACION DENEGADA
    
    public void Stop(){
        validacion.stop();
    }//FIN STOP
}
