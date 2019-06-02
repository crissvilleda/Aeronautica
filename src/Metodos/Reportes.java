/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Metodos;
import static Datos.Datos.metodosdb;
import java.util.HashMap;
import java.util.Map;
import javax.swing.JFrame;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.view.JasperViewer;

/**
 *
 * @author criss
 */
public class Reportes {
    
    public void CrearReportePasajeros(Integer id_compañia,Integer id_vuelo, String vuelo){
        
        try{
           
       
        String report = "Reportes/Reporte_Pasajeros.jasper";
        String registros[] = metodosdb.getVueloInfo(id_compañia, id_vuelo);
        
        
        Map<String,Object> parameters = new HashMap<>();
        parameters.put("id_vuelo",id_vuelo);
        parameters.put("compañia", registros[0]);
        parameters.put("vuelo", vuelo);
        //se muestra en una ventana aparte para su descarga
        JasperPrint jasperPrintWindow = JasperFillManager.fillReport(report, parameters,metodosdb.getConnection());
        JasperViewer jasperViewer = new JasperViewer(jasperPrintWindow,false);
        jasperViewer.setVisible(true);
        
        }catch(JRException e){
            System.out.print(e);
            
        }
        
        
    }
    public void CrearReporteVuelos(Integer id_aeropuerto, int index){
        try{
        String report=null;
         if(index==0){
            report = "Reportes/Compañia_vuelos.jasper";
             
         } else{
             
             report = "Reportes/Compañia_vuelos2.jasper";
             
         }
           

       
        String registros[] = metodosdb.getAeropuertoInfo(id_aeropuerto, index);       
        
        Map<String,Object> parameters = new HashMap<>();
        parameters.put("id_aeropuerto",id_aeropuerto);
        parameters.put("nombre", registros[0]);
        
        //se muestra en una ventana aparte para su descarga
        JasperPrint jasperPrintWindow = JasperFillManager.fillReport(report, parameters,metodosdb.getConnection());
        JasperViewer jasperViewer = new JasperViewer(jasperPrintWindow,false);
        jasperViewer.setVisible(true);
        
        }catch(JRException e){
            System.out.print(e);
            
        }
        
    }
    
    public void CrearReporte(Integer id_aeropuerto){
        
        try{
           
     
        Pool pool = new Pool();

        String report = "Reportes/Compañia_vuelos.jasper";

       
        Map<String,Object> parameters = new HashMap<String,Object>();
        parameters.put("id_aeropuerto",id_aeropuerto);
        //se muestra en una ventana aparte para su descarga
        JasperPrint jasperPrintWindow = JasperFillManager.fillReport(report, parameters,pool.getConnection());
        JasperViewer jasperViewer = new JasperViewer(jasperPrintWindow);
        jasperViewer.setVisible(true);
        
        
        
        
        // First, compile jrxml file.
        //JasperReport jasperReport =    JasperCompileManager.compileReport(report);
        /*JasperPrint jasperPrint = JasperFillManager.fillReport(report, parameters,pool.getConnection());
        JRPdfExporter exp = new JRPdfExporter();
        exp.setExporterInput(new SimpleExporterInput(jasperPrint));
        exp.setExporterOutput(new SimpleOutputStreamExporterOutput("/home/criss/JaspersoftWorkspace/MyReports/ReporteAlumnos.pdf"));
        SimplePdfExporterConfiguration conf = new SimplePdfExporterConfiguration();
        exp.setConfiguration(conf);
        exp.exportReport();*/

        }catch(JRException e){
            System.out.print(e);
            
        }
        
        
    }
    
    
}
