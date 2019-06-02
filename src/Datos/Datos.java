/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Datos;

import Formularios.AeropuertoPrivadoForm;
import Metodos.Metodosdb;
import Metodos.Seleccion;
import java.awt.event.ActionEvent;


/**
 *
 * @author criss
 */
public class Datos {
   
    
    public static Metodosdb metodosdb = new Metodosdb();
    public static Seleccion seleccion = new Seleccion();
    public static Seleccion seleccionvuelo = new Seleccion();
    public static Seleccion seleccioncompañia = new Seleccion();

    public void CmdSalirActionPerformed(ActionEvent evt, AeropuertoPrivadoForm aeropuertoPrivadoForm) {
        aeropuertoPrivadoForm.dispose();
        // TODO add your handling code here:
    }
    
    
    
    
}




