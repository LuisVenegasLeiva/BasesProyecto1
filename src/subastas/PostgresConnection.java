/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package subastas;

import java.sql.*;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author lalem
 */
public class PostgresConnection {
    private static PostgresConnection PGc;
    private Connection db;
//    private String username ;
//    private String password ;
    
    private PostgresConnection(){
        
    }
    
    public static PostgresConnection getInstance(){
        if(PGc == null){
            PGc = new PostgresConnection();
        }
        return PGc;
    }
    
    private boolean connect(String user, String password){
        try { 
            Class.forName("org.postgresql.Driver");
        } catch (ClassNotFoundException ex) {
            System.out.println("Error al registrar el driver de PostgreSQL: " + ex);
        }
        
        try {
            // Database connect
            // Conectamos con la base de datos
            db = DriverManager.getConnection(
                    "jdbc:postgresql://localhost:5432/subastasdb",
                    user, password);
            boolean valid = db.isValid(50000);
            System.out.println(valid ? "TEST OK" : "TEST FAIL");
            return valid;
        } catch (SQLException ex) {
            Logger.getLogger(PostgresConnection.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }
    
    public boolean iniciarSesion(String user, String password){
        return connect(user, password);
    }
    
    
    
    
    
    
    
    

    
}
