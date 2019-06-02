/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Objetos;

import java.util.ArrayList;
import java.util.List;
import java.util.Iterator;
/**
 *
 * @author criss
 */
public class Usuario {
    private String nombres;
    private String apellidos;
    private String nombreUsuario;
    private String Contraseña;
    private List<Usuario> listaUsuarios = new ArrayList<>();

    public void InsertarUsuario(Usuario usuario){
        getListaUsuarios().add(usuario);
    }
    /**
     * @return the nombres
     */
    public String getNombres() {
        return nombres;
    }
    public boolean revisarUsiario(String usuario, String contraseña){
        boolean comprobador = false;
        for (int i= 0; i<getListaUsuarios().size();i++){
            if(getListaUsuarios().get(i).getNombreUsuario().equals(usuario)==true)
                if(getListaUsuarios().get(i).getContraseña().equals(contraseña)==true)
                    comprobador = true;
                else
                    comprobador = false;
                
            else
                comprobador = false;
            
        }
        return comprobador;
    }

    /**
     * @param nombres the nombres to set
     */
    public void setNombres(String nombres) {
        this.nombres = nombres;
    }

    /**
     * @return the apellidos
     */
    public String getApellidos() {
        return apellidos;
    }

    /**
     * @param apellidos the apellidos to set
     */
    public void setApellidos(String apellidos) {
        this.apellidos = apellidos;
    }

    /**
     * @return the nombreUsuario
     */
    public String getNombreUsuario() {
        return nombreUsuario;
    }

    /**
     * @param nombreUsuario the nombreUsuario to set
     */
    public void setNombreUsuario(String nombreUsuario) {
        this.nombreUsuario = nombreUsuario;
    }

    /**
     * @return the Contraseña
     */
    public String getContraseña() {
        return Contraseña;
    }

    /**
     * @param Contraseña the Contraseña to set
     */
    public void setContraseña(String Contraseña) {
        this.Contraseña = Contraseña;
    }

    /**
     * @return the listaUsuarios
     */
    public List<Usuario> getListaUsuarios() {
        return listaUsuarios;
    }
    
}
