/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Metodos;


import javax.sql.DataSource;
import org.apache.commons.dbcp2.BasicDataSource;
import java.sql.Connection;
import java.sql.SQLException;
import javax.swing.JOptionPane;
/**
 *
 * @author Denis
 */
public class Pool {
    private DataSource dataSource;
    private final String db = "aeronautica";
    private final String url ="jdbc:mysql://localhost:3306/"+db;
    private final String user = "root";
    private final String pass = "";
    
    
    public Pool(){
        this.inicializaDataSource();           
    }
    
    private void inicializaDataSource(){
            BasicDataSource basicDataSource = new BasicDataSource();
            basicDataSource.setDriverClassName("org.gjt.mm.mysql.Driver");
            basicDataSource.setUsername(user);
            basicDataSource.setPassword(pass);
            basicDataSource.setUrl(url);
            basicDataSource.setMaxTotal(50);
            dataSource = basicDataSource;
        }

    public Connection getConnection() {
        try {
            return dataSource.getConnection();
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null,"No se pudo conectar al servidor\n"+ex);
        }
        return null;
    }
}
