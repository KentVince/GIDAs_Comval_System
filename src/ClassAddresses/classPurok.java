/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ClassAddresses;
import gidas_comval_system.CommonDB;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JComboBox;

/**
 *
 * @author ITCDD-PROGRAMMING
 */
public class classPurok {
    
    
    
       public void list_purok(JComboBox box, int id){
        
        DefaultComboBoxModel value;
       
        CommonDB conec = new CommonDB();
        PreparedStatement ps = null;
        Connection con = null;
        ResultSet rs = null;
        try{
            con = conec.getConnect();
            ps = con.prepareStatement("SELECT * FROM puroks where barangay_id = ?");
            ps.setInt(1, id);
            rs = ps.executeQuery();
            value = new DefaultComboBoxModel();
            box.setModel(value);
            while(rs.next()){
                value.addElement(new classPurokGetSet(rs.getInt(1),rs.getString(4)));
            }
        }catch(Exception ex){
            System.out.println(ex.getMessage());
        }finally{
            try{
                ps.close();
                rs.close();
                con.close();
            }catch(Exception ex){
            }
        }
        
    }
    
    
    
}
