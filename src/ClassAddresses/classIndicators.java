/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ClassAddresses ;

import gidas_comval_system.classDb;
import gidas_comval_system.classDbcon;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JComboBox;

/**
 *
 * @author User
 */
public class classIndicators {
    
    public void list_of_needs(JComboBox box){
        
        DefaultComboBoxModel value;
        classDbcon conec = new classDbcon();
        Statement st = null;
        Connection con = null;
        ResultSet rs = null;
        try{
            
            con = conec.getConnect();
            st = con.createStatement();
            rs = st.executeQuery("SELECT * FROM basic_needs");
            value = new DefaultComboBoxModel();
            box.setModel(value);
            while(rs.next()){
                value.addElement(new classInidicatorsGetset(rs.getInt(1),rs.getString(2)));
            }
        }catch(SQLException ex){
            System.out.println(ex.getMessage());
        }catch(Exception ex){
            System.out.println(ex.getMessage());
        }finally{
            try{
                st.close();
                rs.close();
                conec.desconnect();
            }catch(Exception ex){
            }
        }
        
    }

    
}
