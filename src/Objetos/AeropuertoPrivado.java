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
public class AeropuertoPrivado extends Aeropuerto{
    private List<String>Empresas= new ArrayList<>();
    private int numEmpresa;
    private List<AeropuertoPrivado> listaAeroPrivado = new ArrayList<>();
    
    public AeropuertoPrivado(){}
    
    public AeropuertoPrivado(String nombre,String ciudad,String pais){
        super(nombre,ciudad,pais);
      
        
    }
    public AeropuertoPrivado(String nombre,String ciudad,Compañia compañia,String empresa){
       super(nombre,ciudad,empresa);
        this.InsertarCompañia(compañia);
        this.InsertarEmpresa(empresa);
        
    }
    public void InsertarAeropuerto(AeropuertoPrivado ap){
        listaAeroPrivado.add(ap);
        
    }
    
    public void InsertarEmpresa(String e){
        getEmpresas().add(e);
    }

    /**
     * @return the Empresas
     */
    public List<String> getEmpresas() {
        return Empresas;
    }

    /**
     * @return the numEmpresa
     */
    public int getNumEmpresa() {
        return numEmpresa;
    }

    /**
     * @return the listaAeroPrivado
     */
    public List<AeropuertoPrivado> getListaAeroPrivado() {
        return listaAeroPrivado;
    }
    
}
