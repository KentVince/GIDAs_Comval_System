package gidas_comval_system;
import java.sql.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

public class classDbExecute {
  
    public static Statement s = null;    
    public static String msg1 = "";    
    public static String msg2 = "";   
    public static ResultSet rs = null;
    
    public static void dquery(String sql, String tmode) {
        if (tmode == "Add"){
            msg1 = "Save Record, Done.....";
            msg2 = "Saving Record Notification";
        }

        if (tmode == "Edit"){
            msg1 = "Update Record, Done.....";
            msg2 = "Record Updating Notification";
        }
        
        if (tmode == "Delete"){
            msg1 = "Delete Record, Done.....";
            msg2 = "Record Delete Notification";
        }
        
        classDb.dbconnect();
        
        try {
            s = classDb.conn.createStatement();
            s.executeUpdate(sql);
            JOptionPane.showMessageDialog(null, msg1, msg2, JOptionPane.INFORMATION_MESSAGE);
            s.close();
            classDb.conn.close();
        } catch (SQLException ex) {

            JOptionPane.showMessageDialog(null, ex.getLocalizedMessage(), "Error report", JOptionPane.OK_OPTION);
        }
    }
    
}
