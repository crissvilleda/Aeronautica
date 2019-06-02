/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Metodos;

import static Datos.Datos.metodosdb;
import Objetos.AeropuertoPublico;
import Objetos.AeropuertoPrivado;
import Objetos.Compañia;
import Objetos.Pasajero;
import Objetos.Vuelo;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Denis
 */

public class Metodosdb {
    
    Pool metodospool = new Pool();
  
    public Connection getConnection(){
        return metodospool.getConnection();
    }
    public void limpiarTabla(JTable tabla){
        DefaultTableModel model = (DefaultTableModel)tabla.getModel();
        int a = tabla.getRowCount()-1;
        for (int i=a; i>=0; i--){
            model.removeRow(model.getRowCount()-1);
        }
    }
    
                
    public void InsertarAeropuertoPublico(AeropuertoPublico ap){
        Connection conect = null;
        
        try{
            conect = metodospool.getConnection();
            
            if (conect !=null){
                PreparedStatement ps = conect.prepareStatement("INSERT INTO aeropuerto_publico"
                    + "(nombre, ciudad, pais, subvencion) VALUES"
                    + "('"+ap.getNombre()+"','"+ap.getCiudad()+"',"
                    + "'"+ap.getPais()+"','"+ap.getSubvencion()+"')");
                ps.executeUpdate();
                
                
                

                JOptionPane.showMessageDialog(null, "Registro Guardado");
                
            }
  
        }catch(SQLException e){
            JOptionPane.showMessageDialog(null,e,"Error durante la consulta"
                + "",JOptionPane.ERROR_MESSAGE);
        }finally{
            if (conect!=null){
            try {
                conect.close();
            } catch (SQLException ex) {
                JOptionPane.showMessageDialog(null,ex,"Error de desconexion"
                        + "",JOptionPane.ERROR_MESSAGE);
                }
            }

        }   
        
    }
    
    
    
    
    public void InsertarAeropuertoPrivado(AeropuertoPrivado ap){
        Connection conect = null;
        
        try{
            conect = metodospool.getConnection();
            
            if (conect !=null){
                PreparedStatement ps = conect.prepareStatement("INSERT INTO aeropuerto_privado"
                    + "(nombre, ciudad, pais) VALUES"
                    + "('"+ap.getNombre()+"','"+ap.getCiudad()+"',"
                    + "'"+ap.getPais()+"')");
              
                ps.executeUpdate();
                JOptionPane.showMessageDialog(null, "Registro Guardado");
                
            }
  
        }catch(SQLException e){
            JOptionPane.showMessageDialog(null,e,"Error durante la consulta"
                + "",JOptionPane.ERROR_MESSAGE);
        }finally{
            if (conect!=null){
            try {
                conect.close();
            } catch (SQLException ex) {
                JOptionPane.showMessageDialog(null,ex,"Error de desconexion"
                        + "",JOptionPane.ERROR_MESSAGE);
                }
            }

        }
    }
    
    public void InsertarPasajeros(Pasajero p,int indexV,int num){
        Connection conect = null;
        
        String ssql="UPDATE vuelo SET num_actual_pasajeros = '"+num+"' "
                + "WHERE vuelo.id_vuelo = '"+indexV+"' ";
        
        try{
            conect = metodospool.getConnection();
            
            if (conect !=null){
                PreparedStatement ps = conect.prepareStatement("INSERT INTO pasajero"
                    + "(id_vuelo,nombres, apellidos, pasaporte,nacionalidad) VALUES"
                    + "('"+indexV+"','"+p.getNombres()+"','"+p.getApellidos()+"',"
                    + "'"+p.getPasaporte()+"','"+p.getNacionalidad()+"')");
                ps.executeUpdate();
                ps = conect.prepareStatement(ssql);
                ps.executeUpdate();

                JOptionPane.showMessageDialog(null, "Registro Guardado");
                
            }
  
        }catch(SQLException e){
            JOptionPane.showMessageDialog(null,e,"Error durante la consulta"
                + "",JOptionPane.ERROR_MESSAGE);
        }finally{
            if (conect!=null){
            try {
                conect.close();
            } catch (SQLException ex) {
                JOptionPane.showMessageDialog(null,ex,"Error de desconexion"
                        + "",JOptionPane.ERROR_MESSAGE);
                }
            }

        }   
        
    }
    
    public boolean verificadorAero_compañia(int index, int indexC, int num){
        boolean verificacion = true;
        String ssql=null;
    
        Connection conect = null;
        if(num==0){
            ssql = "SELECT * FROM aeropuerto_publico INNER JOIN aero_compañia "
                + "ON aeropuerto_publico.id_aeropuerto=aero_compañia.aeropuerto_id "
                + "WHERE aeropuerto_publico.id_aeropuerto='"+index+"' AND "
                + "aero_compañia.compañia_id='"+indexC+"' ";
            
        }else{
            ssql = "SELECT * FROM aeropuerto_privado INNER JOIN aero_compañia_privada "
                + "ON aeropuerto_privado.id_aeropuerto=aero_compañia_privada.aeropuerto_id "
                + "WHERE aeropuerto_privado.id_aeropuerto='"+index+"' AND "
                + "aero_compañia_privada.compañia_id='"+indexC+"' ";
            
        }
        
        try{
            conect = metodospool.getConnection();
            PreparedStatement st = conect.prepareStatement(ssql);
            ResultSet rs = st.executeQuery();
            
         
            int x = 0;
            while (rs.next()){

                x+=1;
            }
            if (x==0){
                verificacion= false;
                
            }
        }catch(SQLException e){
            JOptionPane.showMessageDialog(null,e,"Error durante la consulta"
                    + "",JOptionPane.ERROR_MESSAGE);
            
        }finally{
            if (conect!=null){
                try {
                    conect.close();
                } catch (SQLException ex) {
                    JOptionPane.showMessageDialog(null,ex,"Error de desconexion"
                            + "",JOptionPane.ERROR_MESSAGE);
                }
            }
          
        
        }
        return verificacion;
    }
    
    public void InsertarVuelos(Vuelo v,int index, int indexC, int num){
        Connection conect = null;
        
        String ssql=null;
        String ssql2= null;
        
  
        if(num==0){
            ssql="INSERT INTO vuelo"
                    + "(identificador, ciudad_origen, ciudad_destino,precio,"
                        + "num_max_pasajeros,id_compañia,aeropuerto_id) VALUES"
                    + "('"+v.getIdentificador()+"','"+v.getCiudadOrigen()+"',"
                    + "'"+v.getCiudadDestino()+"','"+v.getPrecio()+"',"
                            + "'"+v.getNumMaxPasajeros()+"','"+indexC+"','"+index+"')";
            ssql2="INSERT INTO aero_compañia"
                            + "(aeropuerto_id,compañia_id) VALUES ('"+index+"',"
                            + "'"+indexC+"')";
            
        }else{
            ssql="INSERT INTO vuelo"
                    + "(identificador, ciudad_origen, ciudad_destino,precio,"
                        + "num_max_pasajeros,id_compañia,aeropuerto_privado_id) VALUES"
                    + "('"+v.getIdentificador()+"','"+v.getCiudadOrigen()+"',"
                    + "'"+v.getCiudadDestino()+"','"+v.getPrecio()+"',"
                            + "'"+v.getNumMaxPasajeros()+"','"+indexC+"','"+index+"')";
            
            ssql2="INSERT INTO aero_compañia_privada "
                            + "(aeropuerto_id,compañia_id) VALUES ('"+index+"',"
                            + "'"+indexC+"')";
            
            
        }
        
        try{
            conect = metodospool.getConnection();
            
            if (conect !=null){
                if (metodosdb.verificadorAero_compañia(index, indexC, num)==true){
                    PreparedStatement ps = conect.prepareStatement(ssql);
                    ps.executeUpdate();

                    JOptionPane.showMessageDialog(null, "Registro Guardado");
                    
                }else{
                    PreparedStatement ps =conect.prepareStatement(ssql);
                    ps.executeUpdate();
                    
                    ps = conect.prepareStatement(ssql2);
                    ps.executeUpdate();
                    

                    JOptionPane.showMessageDialog(null, "Registro Guardado");
                    
                    
                }
                
                
            }
  
        }catch(SQLException e){
            JOptionPane.showMessageDialog(null,e,"Error durante la consulta"
                + "",JOptionPane.ERROR_MESSAGE);
        }finally{
            if (conect!=null){
            try {
                conect.close();
            } catch (SQLException ex) {
                JOptionPane.showMessageDialog(null,ex,"Error de desconexion"
                        + "",JOptionPane.ERROR_MESSAGE);
                }
            }

        }   
        
    }
    
    
    public void InsertarCompañia(Compañia c){
        Connection conect = null;
        
        try{
            conect = metodospool.getConnection();
            
            if (conect !=null){
                PreparedStatement ps = conect.prepareStatement("INSERT INTO compañia"
                    + "(nombre) VALUES"
                    + "('"+c.getNombre()+"')");
                ps.executeUpdate();

                JOptionPane.showMessageDialog(null, "Registro Guardado");
                
            }
  
        }catch(SQLException e){
            JOptionPane.showMessageDialog(null,e,"Error durante la consulta"
                + "",JOptionPane.ERROR_MESSAGE);
        }finally{
            if (conect!=null){
            try {
                conect.close();
            } catch (SQLException ex) {
                JOptionPane.showMessageDialog(null,ex,"Error de desconexion"
                        + "",JOptionPane.ERROR_MESSAGE);
                }
            }

        }   
        
    }
    public String[] getAeropuertoInfo(int index, int num){
        String registros[] = new String[3];
        Connection conect = null;
        String ssql;
        if (num==0){
            ssql = "SELECT * FROM aeropuerto_publico WHERE aeropuerto_publico.id_aeropuerto="
                    + "'"+index+"'";
            
        }else{
            ssql = "SELECT * FROM aeropuerto_privado WHERE aeropuerto_privado.id_aeropuerto="
                    + "'"+index+"'";
            
        }
        try{
            conect = metodospool.getConnection();
            if (conect!=null){
                
            PreparedStatement st = conect.prepareStatement(ssql);
            ResultSet rs = st.executeQuery();
            while (rs.next()){
                registros[0] = rs.getString("nombre");
                registros[1] = rs.getString("ciudad");
                registros[2] = rs.getString("pais");
                }
            
            }
            
            
        }catch(SQLException e){
            JOptionPane.showMessageDialog(null,e,"Error durante la consulta"
                    + "",JOptionPane.ERROR_MESSAGE);
            
        }finally{
            if (conect!=null){
                try {
                    conect.close();
                } catch (SQLException ex) {
                    JOptionPane.showMessageDialog(null,ex,"Error de desconexion"
                            + "",JOptionPane.ERROR_MESSAGE);
                }
            }
        
        
        }
        
        return registros;
        
        
    };
    
    public String[] getVueloInfo(int indexC,int indexV){
        String registros[] = new String[6];
        
        Connection conect = null;
        String ssql = "SELECT * FROM compañia INNER JOIN vuelo ON vuelo.id_compañia="
                + "compañia.id_compañia WHERE compañia.id_compañia='"+indexC+"' AND "
                + "vuelo.id_vuelo='"+indexV+"' ";
            
        
        try{
            conect = metodospool.getConnection();
            if (conect!=null){
                
            PreparedStatement st = conect.prepareStatement(ssql);
            ResultSet rs = st.executeQuery();
            while (rs.next()){
                registros[0] = rs.getString("nombre");
                registros[1] = rs.getString("identificador");
                registros[2] = rs.getString("ciudad_origen");
                registros[3] = rs.getString("ciudad_destino");
                registros[4] = rs.getString("num_max_pasajeros");
                registros[5] = rs.getString("num_actual_pasajeros");
                }
            
            }
            
            
        }catch(SQLException e){
            JOptionPane.showMessageDialog(null,e,"Error durante la consulta"
                    + "",JOptionPane.ERROR_MESSAGE);
            
        }finally{
            if (conect!=null){
                try {
                    conect.close();
                } catch (SQLException ex) {
                    JOptionPane.showMessageDialog(null,ex,"Error de desconexion"
                            + "",JOptionPane.ERROR_MESSAGE);
                }
            }
        
        
        }
        
        return registros;
        
        
    };
    
    
    public int[] getVueloIndex(String vuelo,int indexA, int num){
        int index[] = new int[2];
        Connection conect = null;
        String ssql = null;
        if(num==0){
            ssql = "SELECT vuelo.id_vuelo, vuelo.id_compañia "
                    + "FROM vuelo WHERE "
                    + "vuelo.identificador = '"+vuelo+"' AND vuelo.aeropuerto_id='"+indexA+"' ";
            
        }else{
            ssql = "SELECT vuelo.id_vuelo, vuelo.id_compañia "
                    + "FROM vuelo WHERE "
                    + "vuelo.identificador = '"+vuelo+"' AND vuelo.aeropuerto_privado_id='"+indexA+"' ";
        }
            
        
            
        try{
            conect = metodospool.getConnection();
            if (conect!=null){
                
            PreparedStatement st = conect.prepareStatement(ssql);
            ResultSet rs = st.executeQuery();
            while (rs.next()){
                index[0] = Integer.parseInt(rs.getString("id_vuelo"));
                index[1] = Integer.parseInt(rs.getString("id_compañia"));
                 }
            
            }
            
            
        }catch(SQLException e){
            JOptionPane.showMessageDialog(null,e,"Error durante la consulta"
                    + "",JOptionPane.ERROR_MESSAGE);
            
        }finally{
            if (conect!=null){
                try {
                    conect.close();
                } catch (SQLException ex) {
                    JOptionPane.showMessageDialog(null,ex,"Error de desconexion"
                            + "",JOptionPane.ERROR_MESSAGE);
                }
            }
        
        }
        
        return  index;
    }
    public int getAeropuertoIndex(String aeropuerto,int num){
        int index =0;
        Connection conect = null;
        String ssql;
        if (num==0){
            ssql = "SELECT aeropuerto_publico.id_aeropuerto "
                    + "FROM aeropuerto_publico WHERE "
                    + "aeropuerto_publico.nombre = '"+aeropuerto+"' ";
            
        }else{
            ssql = "SELECT aeropuerto_privado.id_aeropuerto "
                    + "FROM aeropuerto_privado WHERE "
                    + "aeropuerto_privado.nombre = '"+aeropuerto+"' ";
            
        }
        try{
            conect = metodospool.getConnection();
            if (conect!=null){
                
            PreparedStatement st = conect.prepareStatement(ssql);
            ResultSet rs = st.executeQuery();
            while (rs.next()){
                index = Integer.parseInt(rs.getString("id_aeropuerto"));
                }
            
            }
            
            
        }catch(SQLException e){
            JOptionPane.showMessageDialog(null,e,"Error durante la consulta"
                    + "",JOptionPane.ERROR_MESSAGE);
            
        }finally{
            if (conect!=null){
                try {
                    conect.close();
                } catch (SQLException ex) {
                    JOptionPane.showMessageDialog(null,ex,"Error de desconexion"
                            + "",JOptionPane.ERROR_MESSAGE);
                }
            }
        
        }
        
        return  index;
    }
    public JTable getAeropuertos(JTable tabla, int num){
        DefaultTableModel model = (DefaultTableModel)tabla.getModel();
        Connection conect = null;
        String registros[] = new String[2];
        String ssql;
        if (num==0){
            ssql = "SELECT * FROM aeropuerto_publico";
            
        }else{
            ssql = "SELECT * FROM aeropuerto_privado";
            
        }
        
        
        try{
            conect = metodospool.getConnection();
            if (conect!=null){
                
            PreparedStatement st = conect.prepareStatement(ssql);
            ResultSet rs = st.executeQuery();
            while (rs.next()){
                registros[0] = rs.getString("id_aeropuerto");
                registros[1] = rs.getString("nombre");
                model.addRow(registros);
                }
            
            }
            tabla.setModel(model);
            
            
        }catch(SQLException e){
            JOptionPane.showMessageDialog(null,e,"Error durante la consulta"
                    + "",JOptionPane.ERROR_MESSAGE);
            
        }finally{
            if (conect!=null){
                try {
                    conect.close();
                } catch (SQLException ex) {
                    JOptionPane.showMessageDialog(null,ex,"Error de desconexion"
                            + "",JOptionPane.ERROR_MESSAGE);
                }
            }
        
        
        }
        
        return tabla;
    
    }
    
    public JTable getCompañias(JTable tabla){
        DefaultTableModel model = (DefaultTableModel)tabla.getModel();
        Connection conect = null;
        String registros[] = new String[2];
        
        String ssql = "SELECT * FROM compañia";
        
        try{
            conect = metodospool.getConnection();
            if (conect!=null){
                
            PreparedStatement st = conect.prepareStatement(ssql);
            ResultSet rs = st.executeQuery();
            while (rs.next()){
                registros[0] = rs.getString("id_compañia");
                registros[1] = rs.getString("nombre");
                model.addRow(registros);
                }
            
            }
            tabla.setModel(model);
            
            
        }catch(SQLException e){
            JOptionPane.showMessageDialog(null,e,"Error durante la consulta"
                    + "",JOptionPane.ERROR_MESSAGE);
            
        }finally{
            if (conect!=null){
                try {
                    conect.close();
                } catch (SQLException ex) {
                    JOptionPane.showMessageDialog(null,ex,"Error de desconexion"
                            + "",JOptionPane.ERROR_MESSAGE);
                }
            }
        
        
        }
        
        return tabla;
    
    }
    
    
        
    
    public JTable getVuelos(JTable tabla, int index, int num){
        DefaultTableModel model = (DefaultTableModel)tabla.getModel();
        Connection conect = null;
        String registros[] = new String[7];
        String ssql;
        if (num==0){
            ssql = "SELECT vuelo.id_vuelo, vuelo.identificador,vuelo.ciudad_origen,"
                + "vuelo.ciudad_destino,vuelo.precio, compañia.id_compañia ,compañia.nombre"
                + " FROM ((( aeropuerto_publico INNER JOIN "
                + "aero_compañia ON aeropuerto_publico.id_aeropuerto=aero_compañia.aeropuerto_id)"
                + "INNER JOIN compañia ON aero_compañia.compañia_id=compañia.id_compañia)"
                + "INNER JOIN vuelo ON aero_compañia.compañia_id=vuelo.id_compañia) "
                + "WHERE aeropuerto_publico.id_aeropuerto='"+index+"' AND vuelo.aeropuerto_id="
                    + "'"+index+"'";
            
        }else{
            ssql = "SELECT vuelo.id_vuelo, vuelo.identificador,vuelo.ciudad_origen,"
                + "vuelo.ciudad_destino,vuelo.precio, compañia.id_compañia ,compañia.nombre"
                + " FROM ((( aeropuerto_privado INNER JOIN "
                + "aero_compañia_privada ON aeropuerto_privado.id_aeropuerto=aero_compañia_privada.aeropuerto_id)"
                + "INNER JOIN compañia ON aero_compañia_privada.compañia_id=compañia.id_compañia)"
                + "INNER JOIN vuelo ON aero_compañia_privada.compañia_id=vuelo.id_compañia) "
                + "WHERE aeropuerto_privado.id_aeropuerto='"+index+"' AND vuelo.aeropuerto_privado_id="
                    + "'"+index+"'";
            
        }
        
        
        try{
            conect = metodospool.getConnection();
            if (conect!=null){
                
            PreparedStatement st = conect.prepareStatement(ssql);
            ResultSet rs = st.executeQuery();
            while (rs.next()){
                registros[0] = rs.getString("id_vuelo");
                registros[1] = rs.getString("identificador");
                registros[2] = rs.getString("id_compañia");
                registros[3] = rs.getString("nombre");
                registros[4] = rs.getString("ciudad_origen");
                registros[5] = rs.getString("ciudad_destino");
                registros[6] = rs.getString("precio");
                model.addRow(registros);
                }
            
            }
            tabla.setModel(model);
            
            
        }catch(SQLException e){
            JOptionPane.showMessageDialog(null,e,"Error durante la consulta"
                    + "",JOptionPane.ERROR_MESSAGE);
            
        }finally{
            if (conect!=null){
                try {
                    conect.close();
                } catch (SQLException ex) {
                    JOptionPane.showMessageDialog(null,ex,"Error de desconexion"
                            + "",JOptionPane.ERROR_MESSAGE);
                }
            }
        
        
        }
        
        return tabla;
    
    }

    
    public JTable getPasajeros(JTable tabla, int index, int indexC, int indexV, int num){
        DefaultTableModel model = (DefaultTableModel)tabla.getModel();
        Connection conect = null;
        String registros[] = new String[5];
        String ssql= null;
        if (num==0){
            ssql = "SELECT pasajero.id_pasajero,pasajero.nombres, pasajero.apellidos, "
                + "pasajero.pasaporte, pasajero.nacionalidad FROM ((((aeropuerto_publico "
                + "INNER JOIN aero_compañia ON aeropuerto_publico.id_aeropuerto="
                + "aero_compañia.aeropuerto_id) INNER JOIN compañia ON compañia.id_compañia ="
                + " aero_compañia.compañia_id)INNER JOIN vuelo ON vuelo.id_compañia="
                + "aero_compañia.compañia_id)INNER JOIN pasajero ON pasajero.id_vuelo = vuelo.id_vuelo) "
                + "WHERE aeropuerto_publico.id_aeropuerto='"+index+"' AND vuelo.id_vuelo='"+indexV+"' AND "
                + "compañia.id_compañia='"+indexC+"' ";
            
        }else{
            ssql = "SELECT pasajero.id_pasajero,pasajero.nombres, pasajero.apellidos, "
                + "pasajero.pasaporte, pasajero.nacionalidad FROM ((((aeropuerto_privado "
                + "INNER JOIN aero_compañia_privada ON aeropuerto_privado.id_aeropuerto="
                + "aero_compañia_privada.aeropuerto_id) INNER JOIN compañia ON compañia.id_compañia ="
                + " aero_compañia_privada.compañia_id)INNER JOIN vuelo ON vuelo.id_compañia="
                + "aero_compañia_privada.compañia_id)INNER JOIN pasajero ON pasajero.id_vuelo = vuelo.id_vuelo) "
                + "WHERE aeropuerto_privado.id_aeropuerto='"+index+"' AND vuelo.id_vuelo='"+indexV+"' AND "
                + "compañia.id_compañia='"+indexC+"' ";
            
        }
        
        
        try{
            conect = metodospool.getConnection();
            if (conect!=null){
                
            PreparedStatement st = conect.prepareStatement(ssql);
            ResultSet rs = st.executeQuery();
            while (rs.next()){
                registros[0] = rs.getString("id_pasajero");
                registros[1] = rs.getString("nombres");
                registros[2] = rs.getString("apellidos");
                registros[3] = rs.getString("pasaporte");
                registros[4] = rs.getString("nacionalidad");
                model.addRow(registros);
                }
            
            }
            tabla.setModel(model);
            
            
        }catch(SQLException e){
            JOptionPane.showMessageDialog(null,e,"Error durante la consulta"
                    + "",JOptionPane.ERROR_MESSAGE);
            
        }finally{
            if (conect!=null){
                try {
                    conect.close();
                } catch (SQLException ex) {
                    JOptionPane.showMessageDialog(null,ex,"Error de desconexion"
                            + "",JOptionPane.ERROR_MESSAGE);
                }
            }
        
        
        }
        
        return tabla;
    
    }
    
    public void EliminarPasajero(JTable tabla,int row,int indexV,int num){
        DefaultTableModel model = (DefaultTableModel)tabla.getModel();
        int selected = Integer.parseInt(model.getValueAt(row, 0).toString());
        Connection conect = null;
        
        String ssql2="UPDATE vuelo SET num_actual_pasajeros = '"+num+"' "
                + "WHERE vuelo.id_vuelo = '"+indexV+"' ";
        
        
        try{
            conect = metodospool.getConnection();
            
            if (conect!=null){
                PreparedStatement ps = conect.prepareStatement("DELETE FROM "
                    + "pasajero WHERE id_pasajero = "+selected+"");
                ps.executeUpdate();
                ps = conect.prepareStatement(ssql2);
                ps.executeUpdate();
                model.removeRow(row);
                
                JOptionPane.showMessageDialog(null, "Registro Eliminado");
                
            }
            
        }catch(SQLException e){
            JOptionPane.showMessageDialog(null,e,"Error durante la consulta"
                + "",JOptionPane.ERROR_MESSAGE);
        }finally{
            if (conect!=null){
            try {
                conect.close();
            } catch (SQLException ex) {
                JOptionPane.showMessageDialog(null,ex,"Error de desconexion"
                        + "",JOptionPane.ERROR_MESSAGE);
                }
            }

        }
        
}    
   /* 
    public void Buscar(String valor, String filtro, JTable tabla){
        String [] columnas = {"ID", "Nombres", "Apellidos","Direccion","Telefono"};
        String [] registros = new String[5];
        ModeloTabla = new DefaultTableModel(null, columnas);
        String ssql;
        Connection conect = null;
        if(filtro.equals("Nombres")){
            ssql ="SELECT idcliente, nombres, apellidos,direccion, telefono FROM clientes "
                    + "WHERE nombres = '"+valor+"'";
            
        }else{
            
            ssql ="SELECT idcliente, nombres, apellidos, direccion, telefono FROM clientes "
                    + "WHERE apellidos = '"+valor+"'";
        }
        
        try{
            conect = metodospool.dataSource.getConnection();
            PreparedStatement st = conect.prepareStatement(ssql);
            ResultSet rs = st.executeQuery();
            int x = 0;
            while (rs.next()){
                registros[0] = rs.getString("idcliente");
                registros[1] = rs.getString("nombres");
                registros[2] = rs.getString("apellidos");
                registros[3] = rs.getString("direccion");
                registros[4] = rs.getString("telefono");
                ModeloTabla.addRow(registros);
                x+=1;
            }
            if (x==0){
                JOptionPane.showMessageDialog(null,"Registro no existe en la "
                        + "base de datos","ERROR",JOptionPane.ERROR_MESSAGE);
            }
            tabla.setModel(ModeloTabla);
            
            
        }catch(SQLException e){
            JOptionPane.showMessageDialog(null,e,"Error durante la consulta"
                    + "",JOptionPane.ERROR_MESSAGE);
            
        }finally{
            if (conect!=null){
                try {
                    conect.close();
                } catch (SQLException ex) {
                    JOptionPane.showMessageDialog(null,ex,"Error de desconexion"
                            + "",JOptionPane.ERROR_MESSAGE);
                }
            }
                    
        }
        
    }
    */ 
}