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
public class Compañia {
    
    private String nombre;
    private List<Vuelo> listaVuelos = new ArrayList<>();
    private int numVuelo;
    private List<Compañia> listaCompañias = new ArrayList<>();
    public Compañia(){}
    public Compañia(String nombre){
        this.nombre=nombre;
        
    }
    
    
    public Compañia(String nombre,Vuelo vuelo){
        this.nombre=nombre;
        this.listaVuelos.add(vuelo);
    }
    public void InsertarCompañia(Compañia compañia){
        getListaCompañias().add(compañia);
    }
    public void InsertaVuelo(Vuelo vuelo){
        getListaVuelos().add(vuelo);
        
    }
  
    public String getNombre() {
        return nombre;
    }

   
    public int getNumVuelo() {
        return numVuelo;
    }

 
    public List<Vuelo> getListaVuelos() {
        return listaVuelos;
    }

    /**
     * @param nombre the nombre to set
     */
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    /**
     * @param numVuelo the numVuelo to set
     */
    public void setNumVuelo(int numVuelo) {
        this.numVuelo = numVuelo;
    }

    /**
     * @return the listaCompañias
     */
    public List<Compañia> getListaCompañias() {
        return listaCompañias;
    }
    
}
