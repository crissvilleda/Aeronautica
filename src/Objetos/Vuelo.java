/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Objetos;

import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;

/**
 *
 * @author criss
 */
public class Vuelo {
    private String identificador;
    private String ciudadOrigen;
    private String ciudadDestino;
    private double precio;
    private int numMaxPasajeros;
    private int numActualPasajeros=0;
    private List<Pasajero> listaPasajeros = new ArrayList<>();
    public Vuelo(){}
    
    public Vuelo(String identificador,String ciudadOrigen,String ciudadDestino,
            double precio, int numMaxPasajeros){
        this.identificador=identificador;
        this.ciudadOrigen=ciudadOrigen;
        this.ciudadDestino=ciudadDestino;
        this.precio =precio;
        this.numMaxPasajeros=numMaxPasajeros;
        
        
        
    }
    
    public void EliminarPasajero(int i){
        if(numActualPasajeros>0){
            getListaPasajeros().remove(i);
            JOptionPane.showMessageDialog(null,"Pasajero Eliminado Exitosamente");
            numActualPasajeros--;
        }
    }
    public void InsertarPasajero(Pasajero p){
        if (numActualPasajeros<numMaxPasajeros){
            getListaPasajeros().add(p);
            numActualPasajeros++;   
        }
    }
  
    /**
     * @return the      */
    public String getIdentificador() {
        return identificador;
    }

    /**
     * @return the ciudadOrigen
     */
    public String getCiudadOrigen() {
        return ciudadOrigen;
    }

    /**
     * @return the ciudadDestino
     */
    public String getCiudadDestino() {
        return ciudadDestino;
    }

    /*
     * @return the precio
     */
    public double getPrecio() {
        return precio;
    }

    /**
     * @return the numMaxPasajeros
     */
    public int getNumMaxPasajeros() {
        return numMaxPasajeros;
    }

    /**
     * @return the numActualPasajeros
     */
    public int getNumActualPasajeros() {
        return numActualPasajeros;
    }

    /**
     * @return the listaPasajeros
     */
    public List<Pasajero> getListaPasajeros() {
        return listaPasajeros;
    }

    
}
