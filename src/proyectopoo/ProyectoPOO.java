/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package proyectopoo;

import Formularios.*;
import Metodos.Reportes;
import Objetos.Pasajero;
import Objetos.*;

/**
 *
 * @author criss
 */
public class ProyectoPOO {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
       
        
        
        //UsuariosForm form = new UsuariosForm();
        AeropuertoForm form = new AeropuertoForm();
        form.setVisible(true);
        
        
        Usuario usuario = new Usuario();
        usuario.setNombreUsuario("denis");
        usuario.setContraseña("12345");
       
        
      
        
       
        
        
        
    }
    
}
