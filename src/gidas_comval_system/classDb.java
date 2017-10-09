package gidas_comval_system;
import java.sql.*;

public class classDb {
    
     // JDBC driver name and database URL
   static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";  
   static final String DB_URL = "jdbc:mysql://192.168.6.13/gida";

   //  Database credentials
   static final String USER = "root";
   static final String PASS = "145";
   public static Statement s = null; 
   public static Connection conn = null;
   
   public static void dbconnect() {
        
        try {
            Class.forName(JDBC_DRIVER).newInstance();
            if (conn == null) {
                conn = (Connection) DriverManager.getConnection(DB_URL, USER, PASS);
                System.out.println("Database connected...");
            } else if (conn.isClosed()) {
                conn = (Connection) DriverManager.getConnection(DB_URL, USER, PASS);
            }
        } catch (Exception x) {
            System.err.println("Cannot Connect to Database...");
        }
   }
}
