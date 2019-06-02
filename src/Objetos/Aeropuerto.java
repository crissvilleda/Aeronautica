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
public class Aeropuerto {
    private String nombre;
    private String ciudad;
    private String pais;
    private List<Compañia> listaCompañias= new ArrayList<>();
    private int numCompañia;
    
    public Aeropuerto(){}
    public Aeropuerto(String nombre, String ciudad,String pais){
        this.nombre=nombre;
        this.ciudad=ciudad;
        this.pais=pais;
        
    }
    public Aeropuerto(String nombre, String ciudad,String pais,Compañia compañia){
        this.nombre=nombre;
        this.ciudad=ciudad;
        this.pais=pais;
        this.InsertarCompañia(compañia);
    }
    public boolean VerificarCompañia(String compañia){
        boolean comprobador =false;
        for (int i=0; i<getListaCompañias().size();i++){
            
            if (getListaCompañias().get(i).getNombre()==compañia){
                comprobador= true;
                break;
            }else
                comprobador= false;
            
        }
        return comprobador;
    }
    
    public int getIndexCompañia(String compañia){
        int index=0;
        for (int i=0; i<getListaCompañias().size();i++){
            
            if (getListaCompañias().get(i).getNombre()==compañia){
                index = getListaCompañias().indexOf(getListaCompañias().get(i));
                break;
            }
        }
        return index;
    }
    
    
    
    public void InsertarCompañia(Compañia c){
        listaCompañias.add(c);
    }
   
    /**
     * @return the nombre
     */
    public String getNombre() {
        return nombre;
    }

    /**
     * @return the ciudad
     */
    public String getCiudad() {
        return ciudad;
    }

    /**
     * @return the pais
     */
    public String getPais() {
        return pais;
    }

    /**
     * @return the listaCompañias
     */
    public List<Compañia> getListaCompañias() {
        return listaCompañias;
    }

    /**
     * @return the numCompañia
     */
    public int getNumCompañia() {
        return numCompañia;
    }

    /**
     * @param nombre the nombre to set
     */
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    /**
     * @param ciudad the ciudad to set
     */
    public void setCiudad(String ciudad) {
        this.ciudad = ciudad;
    }

    /**
     * @param pais the pais to set
     */
    public void setPais(String pais) {
        this.pais = pais;
    }

    /**
     * @param numCompañia the numCompañia to set
     */
    public void setNumCompañia(int numCompañia) {
        this.numCompañia = numCompañia;
    }
    
    
}
