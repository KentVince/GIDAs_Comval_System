/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gidas_comval_system;
import static gidas_comval_system.classDb.JDBC_DRIVER;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 *
 * @author ITCDD-PROGRAMMING
 */
public class CommonDB {
    
    
     static String us = "root";
   static String pass = "145";
   static String bd = "common_db";
   static String url = "jdbc:mysql://192.168.6.13/"+bd;
    
   public Connection con = null;
   public static java.sql.Connection conn = null;
   
   
   
   
    public CommonDB(){
        
        try{
            Class.forName("com.mysql.jdbc.Driver");
            con = DriverManager.getConnection(url, us, pass);
            
            if(con!=null){
                System.out.println("Database connected...");
            }
        }catch(SQLException ex){
            System.out.println(ex.getMessage());
        }catch(ClassNotFoundException ex){
            System.out.println(ex.getMessage());
        }catch(Exception ex){
            System.out.println(ex.getMessage());
        }
    }
    
    
  
    public Connection getConnect(){
        return con;
    }
    
    public void desconnect(){
        con = null;
    }
    
    public static void main(String[] args) {
        CommonDB c = new CommonDB();
        c.getConnect();
    }
    
    
    
    
    
      public static void dbconnect() {

        try {

            Class.forName(JDBC_DRIVER).newInstance();
            if (conn == null) {
                conn = (java.sql.Connection) DriverManager.getConnection(url, us, pass);
                System.out.println("Database connected...");
            } else if (conn.isClosed()) {
                conn = (java.sql.Connection) DriverManager.getConnection(url, us, pass);
            }
        } catch (Exception x) {
            System.err.println("Cannot Connect to Database...");
        }

    }
   
    
    
    
    
    
    
}
