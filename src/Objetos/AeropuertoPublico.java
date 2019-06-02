/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Objetos;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author criss
 */
public class AeropuertoPublico extends Aeropuerto {
    private double subvencion;
    private List<AeropuertoPublico> listaAeroPublicos = new ArrayList<>();
    
    public AeropuertoPublico(){}
    public AeropuertoPublico(String nombre,String ciudad,String pais){
        this.setNombre(nombre);
        this.setCiudad(ciudad);
        this.setPais(pais);
    }
    public AeropuertoPublico(String nombre,String ciudad,String pais,double subvencion){
        this.setNombre(nombre);
        this.setCiudad(ciudad);
        this.setPais(pais);
        this.subvencion=subvencion;
    }
    
    /*public AeropuertoPublico(String nombre,String ciudad,String pais,Compañia compañia,double subvencion){
        this.setNombre(nombre);
        this.setCiudad(ciudad);
        this.setPais(pais);
        this.insertarCompañia(compañia);
        this.subvencion=subvencion;
    }/*

    /**
     * @return the subvencion
     */
    public void InsertaAeropuerto(AeropuertoPublico ap){
        listaAeroPublicos.add(ap);
    }
    public double getSubvencion() {
        return subvencion;
    }

    /**
     * @param subvencion the subvencion to set
     */
    public void setSubvencion(double subvencion) {
        this.subvencion = subvencion;
    }

    /**
     * @return the listaAeroPublicos
     */
    public List<AeropuertoPublico> getListaAeroPublicos() {
        return listaAeroPublicos;
    }
    
}
