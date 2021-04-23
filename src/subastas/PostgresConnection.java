/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package subastas;

import Modelo.Puja;
import Modelo.Subasta;
import java.math.BigDecimal;
import java.sql.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
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
    
    public boolean agregarParticipante(int cedula, String alias, String nombre, String direccion, int telCelular, int telCasa, int telTrabajo, int telOtro, String pass){
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
            statement.setString(9, pass);
            ResultSet res = statement.executeQuery();
            return res.next();
            
        }
        catch(SQLException e){
            System.err.println(e.getMessage());
        }
        return false;
    }
    
    public boolean agregarAdmin(int cedula, String alias, String nombre, String direccion, int telCelular, int telCasa, int telTrabajo, int telOtro, String pass){
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
            statement.setString(9, pass);
            ResultSet res = statement.executeQuery();
            return res.next();
            
        }
        catch(SQLException e){
            System.err.println(e.getMessage());
        }
        return false;
    }
    
     public void iniciarSubasta( int subcategoria, String descripcion, double precio, Date fechaMaxima){
         try{
             statement = db.prepareStatement("call iniciarSubasta(?, ?, ?, ?)");
             statement.setInt(1, subcategoria);
             statement.setString(2,descripcion);
             statement.setDouble(3, precio);
             statement.setDate(4,new java.sql.Date(fechaMaxima.getTime()));
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
     
    public ArrayList<Subasta> getSubastas(){
         try{
            statement=db.prepareStatement("select listarsubastas()");
            //pst = (OraclePreparedStatement) conn.prepareStatement("select listarhistorialpujas (?) from dual;");
            //pst.setInt(1,Integer.parseInt(this.jTextField1.getText()));
            ResultSet rs = statement.executeQuery();
            System.out.println("excecute");
            ArrayList<Subasta> subastas = new ArrayList<Subasta>();
            while(rs.next()){
                String d= rs.getString(1);
                d = d.replace("(", "");
                d = d.replace(")", "");
                String[] res = d.split(",");
                int id = Integer.parseInt(res[0]);
                int idItem = Integer.parseInt(res[1]);
                float precioFinal = Float.parseFloat(res[2]);
                SimpleDateFormat formato = new SimpleDateFormat("yyyy-MM-dd");
                Date fechaInicial = null;
                Date fechaFinal = null;
                try {
                    fechaInicial = formato.parse(res[3]);
                    fechaFinal = formato.parse(res[4]);
                } 
                catch (ParseException ex) 
                {
                    System.out.println(ex);
                } 
                //int vendido = Integer.parseInt(res[5]);
                float precioInicial = Float.parseFloat(res[6]);
                String categoria = res[7];
                String subcategoria = res[8];
                String descripcion = res[9];
                Subasta s = new Subasta(id, idItem, fechaInicial, fechaFinal,false,  precioInicial, precioFinal, categoria, subcategoria, descripcion);
                subastas.add(s);
            }
            return subastas;
        }catch(SQLException e){
            //JOptionPane.showMessageDialog(null, e);
            System.out.println(e.getMessage());
        }
         return null;
    }
     
    public ArrayList<Puja> getPujas(int subasta){
    try{
            System.out.println(subasta);
             statement=db.prepareStatement("select listarhistorialpujas (?)");
            statement.setInt(1,subasta);
            //pst = (OraclePreparedStatement) conn.prepareStatement("select listarhistorialpujas (?) from dual;");
            //pst.setInt(1,Integer.parseInt(this.jTextField1.getText()));
            ResultSet rs = statement.executeQuery();
            ArrayList<Puja> pujas = new ArrayList<Puja>();
            while(rs.next()){
                String d= rs.getString(1);
                d = d.replace("(", "");
                d = d.replace(")", "");
                String[] res = d.split(",");
                int id = Integer.parseInt(res[0]);
                int idSubasta = Integer.parseInt(res[1]);
                int idC = Integer.parseInt(res[2]);
                String comprador = res[3];
                double monto = Double.parseDouble(res[4]);
                SimpleDateFormat formato = new SimpleDateFormat("yyyy-MM-dd");
                Date fecha = null;
                try {
                    fecha = formato.parse(res[5]);
                } 
                catch (ParseException ex) 
                {
                    System.out.println(ex);
                } 
                pujas.add(new Puja(id, idSubasta, comprador, monto, fecha));
            }
            return pujas;
        }catch(SQLException e){
            System.out.println(e.getMessage());
        }  
        return null;
    }
    
    public void getUsuario(int cedula){
        
    }
    
    public ArrayList<String[]> getCategoria(){
          try{
             statement=db.prepareStatement("select listarcategorias ()");
            ResultSet rs = statement.executeQuery();
            ArrayList<String[]> categorias = new ArrayList<String[]>();
            while(rs.next()){
                String d= rs.getString(1);
                d = d.replace("(", "");
                d = d.replace(")", "");
                String[] res = d.split(",");
                categorias.add(res);
            }
            return categorias;
        }catch(SQLException e){
            System.out.println(e.getMessage());
        }  
        return null;
    }
    
    public ArrayList<String[]> getSubCategoria(String nombre){
        ArrayList<String[]> categorias = new ArrayList<String[]>();
          try{
             statement=db.prepareStatement("select listarsubcategorias(?)");
             statement.setString(1,nombre);
            ResultSet rs = statement.executeQuery();
            
            while(rs.next()){
                String d= rs.getString(1);
                d = d.replace("(", "");
                d = d.replace(")", "");
                String[] res = d.split(",");
                categorias.add(res);
            }
            return categorias;
        }catch(SQLException e){
            System.out.println(e.getMessage());
        }  
        return categorias;
    }
    
    public ArrayList<String[]> getMisSubastas(){
        ArrayList<String[]> categorias = new ArrayList<String[]>();
          try{
             statement=db.prepareStatement("select subastasusuarioactual()");
            ResultSet rs = statement.executeQuery();
            
            while(rs.next()){
                String d= rs.getString(1);
                d = d.replace("(", "");
                d = d.replace(")", "");
                String[] res = d.split(",");
                categorias.add(res);
            }
            return categorias;
        }catch(SQLException e){
            System.out.println(e.getMessage());
        }  
        return categorias;
    }
    
    
    public ArrayList<String[]> getSubastasGanadas(String nombre){
        ArrayList<String[]> categorias = new ArrayList<String[]>();
          try{
             statement=db.prepareStatement("select subastasganadas()");
            ResultSet rs = statement.executeQuery();
            
            while(rs.next()){
                String d= rs.getString(1);
                d = d.replace("(", "");
                d = d.replace(")", "");
                String[] res = d.split(",");
                categorias.add(res);
            }
            return categorias;
        }catch(SQLException e){
            System.out.println(e.getMessage());
        }  
        return categorias;
    }
    
    public ArrayList<String[]> getSubastasGanadas(){
        ArrayList<String[]> categorias = new ArrayList<String[]>();
          try{
             statement=db.prepareStatement("select subastasganadas()");
            ResultSet rs = statement.executeQuery();
            
            while(rs.next()){
                String d= rs.getString(1);
                d = d.replace("(", "");
                d = d.replace(")", "");
                String[] res = d.split(",");
                categorias.add(res);
            }
            return categorias;
        }catch(SQLException e){
            System.out.println(e.getMessage());
        }  
        return categorias;
    }
    
    
    
    
    
    
    
    

    
}
