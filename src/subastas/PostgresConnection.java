/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package subastas;

import java.math.BigDecimal;
import java.sql.*;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import sun.security.util.Password;

/**
 *
 * @author lalem
 */
public class PostgresConnection {
    private static PostgresConnection PGc;
    private Connection db;
    private PreparedStatement statement;
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
    
    public String iniciarSesion(String user, String password){
        try{
            connect(user, password);
            PreparedStatement st = db.prepareStatement("select isAdmin()");
            ResultSet res = st.executeQuery();
            res.next();
            System.out.println(res.getString(1));
            if(res.getString(1).equals("t")){
                System.out.println("es admin");
                return "a";
            }   
            else{
                return "p";
            }
        }
        catch(SQLException e){
            Logger.getLogger(PostgresConnection.class.getName()).log(Level.SEVERE, null, e);
            return "err";
        }
        
    }
    
    public boolean agregarParticipante(int cedula, String alias, String nombre, String direccion, int telCelular, int telCasa, int telTrabajo, int telOtro, String usuario, String pass){
        try {
            statement = db.prepareStatement("call agregarParticipante (?,?,?,?,?,?,?,?,?,?)");
            statement.setInt(1, cedula);
            statement.setString(2, alias);
            statement.setString(3,nombre);
            statement.setString(4, direccion);
            statement.setInt(5, telCelular);
            statement.setInt(6, telCasa);
            statement.setInt(7, telTrabajo);
            statement.setInt(8, telOtro);
            statement.setString(9, usuario);
            statement.setString(10, pass);
            ResultSet res = statement.executeQuery();
            
            
        }
        catch(SQLException e){
            System.err.println(e.getMessage());
        }
        return false;
    }
    
    public void agregarAdmin(int cedula, String alias, String nombre, String direccion, int telCelular, int telCasa, int telTrabajo, int telOtro, String usuario, String pass){
        try {
            statement = db.prepareStatement("call agregarAdministrador (?,?,?,?,?,?,?,?,?,?)");
            statement.setInt(1, cedula);
            statement.setString(2, alias);
            statement.setString(3,nombre);
            statement.setString(4, direccion);
            statement.setInt(5, telCelular);
            statement.setInt(6, telCasa);
            statement.setInt(7, telTrabajo);
            statement.setInt(8, telOtro);
            statement.setString(9, usuario);
            statement.setString(10, pass);
            ResultSet res = statement.executeQuery();
            
            
        }
        catch(SQLException e){
            System.err.println(e.getMessage());
        }
        //return false;
    }
    
     public void iniciarSubasta(String categoria, String subcategoria, String descripcion, double precio, Date fechaMaxima){
         try{
             statement = db.prepareStatement("call iniciarSubasta(?, ?, ?, ?, ?)");
             statement.setString(1,categoria);
             statement.setString(2, subcategoria);
             statement.setString(3,descripcion);
             statement.setDouble(4, precio);
             statement.setDate(5,new java.sql.Date(fechaMaxima.getTime()));
             statement.executeQuery(); 
         }catch(SQLException e){
             System.err.println(e.getMessage());
         }
    }
     
     public void pujar(int subasta, double monto ){
         try{
             statement = db.prepareStatement("call pujar (?, ?)");
             statement.setInt(1, subasta);
             statement.setBigDecimal(2, BigDecimal.valueOf(monto));            
             statement.executeQuery(); 
         }catch(SQLException e){
             System.err.println(e.getMessage());
         }
         
     }
     
    public void getPujas(int subasta){
    try{
            CallableStatement statement=db.prepareCall("{call ?:= listarhistorialpujas (?)}");
            statement.registerOutParameter(1, Types.NUMERIC);
            statement.setInt(2,subasta);
            statement.executeUpdate();
            
            //pst = (OraclePreparedStatement) conn.prepareStatement("select listarhistorialpujas (?) from dual;");
            //pst.setInt(1,Integer.parseInt(this.jTextField1.getText()));
            ResultSet rs = statement.executeQuery();
            if(rs.next()){
                System.out.println(rs.getInt(1));
                //this.jTextArea1.setText(rs.toString());
            }else{
                 //JOptionPane.showMessageDialog(null,"El dato ingresado no es correcto");
            }
        }catch(Exception e){
            //JOptionPane.showMessageDialog(null, e);
        }   
    }
    
    
    
    
    
    
    
    
    

    
}
