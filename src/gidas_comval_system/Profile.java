/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gidas_comval_system;


import ClassAddresses.classBarangay;
import ClassAddresses.classBarangayGetSet;
import ClassAddresses.classMunicipality;
import ClassAddresses.classMunicipalityGetSet;
import ClassAddresses.classPurok;
import ClassAddresses.classPurokGetSet;
import java.awt.Color;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.NumberFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.Locale;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.RowFilter;
import javax.swing.UIManager;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableRowSorter;

/**
 *
 * @author ITCDD-PROGRAMMING
 */
public class Profile extends javax.swing.JFrame {

    /**
     * Creates new form Profile
     * 
     */
    
    String ddate = "";
     public static Statement s = null;
    public static ResultSet rs = null;
    
      classMunicipality Municipal = new classMunicipality();
      classBarangay Barangay = new classBarangay();
      classPurok Purok = new classPurok();
      
      
      
    DefaultTableModel model;
     
        
    
    public Profile() throws SQLException {
        initComponents();
         
        displayData();
       
        
        txt_code.setEnabled(false);
         txt_code.setDisabledTextColor(Color.BLACK);
         
         
    }
    
    
     void auto_number()
    {
        
         classDb.dbconnect();
        try
        {
            
            s = classDb.conn.createStatement();
       rs = s.executeQuery("SELECT MAX(right(code,5)) AS no FROM pro");
            //rs.last(); // go to the last record to get total records
 
          
             while (rs.next()) {
                 
             if(rs.first()==false) {
                 
                 codec.setText("001");
             }
             
              else
                 {
                       rs.last(); 
                         int auto_id = rs.getInt(1)+1;
                         String no = String.valueOf(auto_id);
                          
                         int nolong = no.length();
                         for(int a=0;a<3-nolong;a++)
                         {
                         no = "00"+no;
                         }
                              
                         codec.setText(""+no);
                  }
       
             }
             
      
        }
        catch(Exception e)
        {
            
             JOptionPane.showMessageDialog(null,e);
        }
        
        
    }
    
     
      public void filter(String query)
    {
         
         TableRowSorter<DefaultTableModel> tr=new TableRowSorter<DefaultTableModel>(model);
      jTable_display.setRowSorter(tr);
        
        tr.setRowFilter(RowFilter.regexFilter(query));
        
       
        
        
    }
     
     
     
     
     
     
      public void displayData() throws SQLException {
          
        classDb.dbconnect();
         disabled();
        auto_number();
         Municipal.list_municipality(cbo_municipality);
       
//      Login log = new Login();

       
       
       
        
         model = new DefaultTableModel(new String[]{
          //0/2/4/7
          //1 "name_permitee"//3name_applicant //5kind_mineral //6tonnage //9buyer
            "id",
            "code",
            "last_name", //1
            "first_name",
            "middle_name", 
            "age",   
            "sex", 
            "civil_status",   
            "occupation",
            "employment_status",
            "spouse_name",
            "spouse_age",
            "spouse_occupation",
            "children_male",
            "children_female",
            "address",
            "ginikanan",
            "igsoon",
            "pag_umangkon",
             "created_at",
          //  "updated_at",
            "purok_id",
        }, 0);
      
     
        jTable_display.setModel(model);
       
      
         
         removeColumns();
        

        s = classDb.conn.createStatement();
        
        // rs = s.executeQuery("SELECT * FROM otpnm_table ORDER BY name_permitee");
         rs = s.executeQuery("SELECT * from pro ORDER BY code");
        //rs = s.executeQuery("SELECT otpnm_table.*, otpnm_total.otp_number,otpnm_total.total_tonnage,otpnm_total.total_value,otpnm_total.print FROM otpnm_table LEFT JOIN otpnm_total ON otpnm_table.id=otpnm_total.id ORDER BY otpnm_table.name_permitee");
    

     while (rs.next()) {

            model.addRow(new Object[]{
                rs.getString("id"),         //0
                rs.getString("code"), //1
                rs.getString("last_name"), //2     //1 "name_permitee"//3name_applicant //5kind_mineral //6tonnage //9buyer
                rs.getString("first_name"),        //3 
                rs.getString("middle_name"), //4
                rs.getString("age"), //5
                rs.getString("sex"),   //6
                rs.getString("civil_status"),        //7
                rs.getString("occupation"),          //8
                rs.getString("employment_status"),  //9
                rs.getString("spouse_name"),          //10
                rs.getString("spouse_age"),     //11
                rs.getString("spouse_occupation"),    //12
                rs.getString("children_male"),     //13
                rs.getString("children_female"),            //14
                rs.getString("address"), //15
                rs.getString("ginikanan"),            //16
                 
                rs.getString("igsoon"),       //17
                rs.getString("pag_umangkon"), //18
                 rs.getString("created_at"),           //19
                
               //  rs.getString("updated_at"), //20
               
                   rs.getString("purok_id"),

            });
           
        }
     
      
       
      auto_number();
      
        rs.close(); //close record set
        s.close(); // close statement
        classDb.conn.close(); // close connection

    }
      
      
      
       void removeColumns()
    {
        // jTable_display.getColumnModel().getColumn(23).setMinWidth(0); //date_app
       //  jTable_display.getColumnModel().getColumn(23).setMaxWidth(0);
        
       jTable_display.getColumnModel().getColumn(7).setMinWidth(0); //date_app
        jTable_display.getColumnModel().getColumn(7).setMaxWidth(0);
        
      //  jTable_display.getColumnModel().getColumn(22).setMinWidth(0); //date_app
     //   jTable_display.getColumnModel().getColumn(22).setMaxWidth(0);
        
       //  jTable_display.getColumnModel().getColumn(21).setMinWidth(0); //date_app
      //  jTable_display.getColumnModel().getColumn(21).setMaxWidth(0);
         
        
        
       //  jTable_display.getColumnModel().getColumn(20).setMinWidth(0); //date_app
     //   jTable_display.getColumnModel().getColumn(20).setMaxWidth(0);
        
           jTable_display.getColumnModel().getColumn(19).setMinWidth(0); //date_app
        jTable_display.getColumnModel().getColumn(19).setMaxWidth(0);
         
        jTable_display.getColumnModel().getColumn(18).setMinWidth(0); //date_app
        jTable_display.getColumnModel().getColumn(18).setMaxWidth(0);
        
        jTable_display.getColumnModel().getColumn(17).setMinWidth(0); //
        jTable_display.getColumnModel().getColumn(17).setMaxWidth(0);
        
        jTable_display.getColumnModel().getColumn(16).setMinWidth(0); //or3
        jTable_display.getColumnModel().getColumn(16).setMaxWidth(0);
        
        
        jTable_display.getColumnModel().getColumn(15).setMinWidth(0); //euf
        jTable_display.getColumnModel().getColumn(15).setMaxWidth(0);
        
        jTable_display.getColumnModel().getColumn(14).setMinWidth(0); //or2
        jTable_display.getColumnModel().getColumn(14).setMaxWidth(0);
        
        jTable_display.getColumnModel().getColumn(13).setMinWidth(0); //extraction
        jTable_display.getColumnModel().getColumn(13).setMaxWidth(0);
        
        jTable_display.getColumnModel().getColumn(12).setMinWidth(0); //or1
        jTable_display.getColumnModel().getColumn(12).setMaxWidth(0);
        
        jTable_display.getColumnModel().getColumn(11).setMinWidth(0); //excise
        jTable_display.getColumnModel().getColumn(11).setMaxWidth(0);
        
        jTable_display.getColumnModel().getColumn(9).setMinWidth(0); //num.vehicle
        jTable_display.getColumnModel().getColumn(9).setMaxWidth(0);
        
        jTable_display.getColumnModel().getColumn(6).setMinWidth(0); //buyer_mail
        jTable_display.getColumnModel().getColumn(6).setMaxWidth(0);
        
        jTable_display.getColumnModel().getColumn(5).setMinWidth(0); //buyer
        jTable_display.getColumnModel().getColumn(5).setMaxWidth(0);
        
        jTable_display.getColumnModel().getColumn(3).setMinWidth(0); //specification
        jTable_display.getColumnModel().getColumn(3).setMaxWidth(0);
        
         jTable_display.getColumnModel().getColumn(8).setMinWidth(0); //value
        jTable_display.getColumnModel().getColumn(8).setMaxWidth(0);
        
         jTable_display.getColumnModel().getColumn(0).setMinWidth(0); //specification
       jTable_display.getColumnModel().getColumn(0).setMaxWidth(0);
        
        //0/2/4/7
        
        ////////////////////////////////////////////////////////
        
    
        
        
    }
      
      
      
       void enabled()
       {
           
           cbo_municipality.setEnabled(true);
            cbo_barangay.setEnabled(true);
            cbo_purok.setEnabled(true);
           
           txt_family_head_firstname.setEnabled(true);
            txt_family_head_lastname.setEnabled(true);
            txt_family_head_middlename.setEnabled(true);
            txt_age.setEnabled(true);
            cbo_sex.setEnabled(true);
            cbo_status.setEnabled(true);
            txt_occupation.setEnabled(true);
            cbo_status_of_employment.setEnabled(true);
            txt_spouse_name.setEnabled(true);
            txt_spouse_age.setEnabled(true);
            txt_spouse_occupation.setEnabled(true);
            txt_no_of_children_male.setEnabled(true);
            txt_no_of_children_female.setEnabled(true);
             cbo_sex.setEnabled(true);
            cbo_status.setEnabled(true);
            cbo_status_of_employment.setEnabled(true);
           
       }
       
       void disabled()
       {
           
          
            
            cbo_municipality.setEnabled(false);
            cbo_barangay.setEnabled(false);
            cbo_purok.setEnabled(false);
            
            
            txt_family_head_firstname.setEnabled(false);
            txt_family_head_lastname.setEnabled(false);
            txt_family_head_middlename.setEnabled(false);
            txt_age.setEnabled(false);
            cbo_sex.setEnabled(false);
            cbo_status.setEnabled(false);
            txt_occupation.setEnabled(false);
            cbo_status_of_employment.setEnabled(false);
            txt_spouse_name.setEnabled(false);
            txt_spouse_age.setEnabled(false);
            txt_spouse_occupation.setEnabled(false);
            txt_no_of_children_male.setEnabled(false);
            txt_no_of_children_female.setEnabled(false);
            
            txt_family_head_firstname.setDisabledTextColor(Color.BLACK);
            txt_family_head_lastname.setDisabledTextColor(Color.BLACK);
            txt_family_head_middlename.setDisabledTextColor(Color.BLACK);
            
            txt_age.setDisabledTextColor(Color.BLACK);
            
            txt_occupation.setDisabledTextColor(Color.BLACK);
            txt_spouse_name.setDisabledTextColor(Color.BLACK);
            txt_spouse_age.setDisabledTextColor(Color.BLACK);
            txt_spouse_occupation.setDisabledTextColor(Color.BLACK);
            txt_no_of_children_male.setDisabledTextColor(Color.BLACK);
            txt_no_of_children_female.setDisabledTextColor(Color.BLACK);
            
           
       }
       
       
      void clear()
      {
          
           txt_family_head_firstname.setText("");
            txt_family_head_lastname.setText("");
            txt_family_head_middlename.setText("");
            txt_age.setText("");
            txt_occupation.setText("");
            txt_spouse_name.setText("");
            txt_spouse_age.setText("");
            txt_spouse_occupation.setText("");
            txt_no_of_children_male.setText("");
            txt_no_of_children_female.setText("");
          
      }
     
     
     
     
     

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel2 = new javax.swing.JPanel();
        jPanel1 = new javax.swing.JPanel();
        jLabel15 = new javax.swing.JLabel();
        jLabel18 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        txt_family_head_middlename = new javax.swing.JTextField();
        txt_family_head_lastname = new javax.swing.JTextField();
        txt_family_head_firstname = new javax.swing.JTextField();
        jLabel8 = new javax.swing.JLabel();
        txt_age = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        cbo_sex = new javax.swing.JComboBox<>();
        jLabel5 = new javax.swing.JLabel();
        cbo_status = new javax.swing.JComboBox<>();
        jLabel21 = new javax.swing.JLabel();
        jLabel22 = new javax.swing.JLabel();
        jLabel23 = new javax.swing.JLabel();
        jLabel17 = new javax.swing.JLabel();
        jLabel19 = new javax.swing.JLabel();
        cbo_municipality = new javax.swing.JComboBox<>();
        cbo_barangay = new javax.swing.JComboBox<>();
        cbo_purok = new javax.swing.JComboBox<>();
        jLabel10 = new javax.swing.JLabel();
        txt_occupation = new javax.swing.JTextField();
        jLabel7 = new javax.swing.JLabel();
        cbo_status_of_employment = new javax.swing.JComboBox<>();
        jLabel2 = new javax.swing.JLabel();
        txt_spouse_name = new javax.swing.JTextField();
        jLabel12 = new javax.swing.JLabel();
        txt_spouse_age = new javax.swing.JTextField();
        jLabel11 = new javax.swing.JLabel();
        txt_spouse_occupation = new javax.swing.JTextField();
        jLabel14 = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();
        txt_no_of_children_male = new javax.swing.JTextField();
        jLabel9 = new javax.swing.JLabel();
        txt_no_of_children_female = new javax.swing.JTextField();
        jLabel6 = new javax.swing.JLabel();
        cho_ginikanan = new javax.swing.JCheckBox();
        cho_igsoon = new javax.swing.JCheckBox();
        cho_pagumangkon = new javax.swing.JCheckBox();
        jLabel24 = new javax.swing.JLabel();
        txt_code = new javax.swing.JTextField();
        codec = new javax.swing.JLabel();
        codemun = new javax.swing.JLabel();
        codebar = new javax.swing.JLabel();
        lblpurokid = new javax.swing.JLabel();
        codepur = new javax.swing.JLabel();
        lbl_ids = new javax.swing.JLabel();
        btn_next = new javax.swing.JButton();
        btn_cancel = new javax.swing.JButton();
        jPanel4 = new javax.swing.JPanel();
        btn_delete = new javax.swing.JButton();
        btn_update1 = new javax.swing.JButton();
        btn_update = new javax.swing.JButton();
        btn_new = new javax.swing.JButton();
        btn_save = new javax.swing.JButton();
        lbl_ginikanan = new javax.swing.JLabel();
        lbl_igsoon = new javax.swing.JLabel();
        lbl_pagumangkon = new javax.swing.JLabel();
        jLabel16 = new javax.swing.JLabel();
        jPanel3 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable_display = new javax.swing.JTable();
        jLabel1 = new javax.swing.JLabel();
        txt_search = new javax.swing.JTextField();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel2.setBackground(new java.awt.Color(204, 204, 255));
        jPanel2.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel15.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel15.setText("Name of Family Head :");
        jPanel1.add(jLabel15, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 100, -1, -1));

        jLabel18.setFont(new java.awt.Font("Tahoma", 3, 12)); // NOI18N
        jLabel18.setText("(firstname)");
        jPanel1.add(jLabel18, new org.netbeans.lib.awtextra.AbsoluteConstraints(310, 70, -1, -1));

        jLabel3.setFont(new java.awt.Font("Tahoma", 3, 12)); // NOI18N
        jLabel3.setText("(middlename)");
        jPanel1.add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(430, 70, -1, -1));
        jPanel1.add(txt_family_head_middlename, new org.netbeans.lib.awtextra.AbsoluteConstraints(410, 90, 110, 30));
        jPanel1.add(txt_family_head_lastname, new org.netbeans.lib.awtextra.AbsoluteConstraints(290, 90, 110, 30));
        jPanel1.add(txt_family_head_firstname, new org.netbeans.lib.awtextra.AbsoluteConstraints(170, 90, 110, 30));

        jLabel8.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel8.setText("Age (edad) :");
        jPanel1.add(jLabel8, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 140, -1, -1));
        jPanel1.add(txt_age, new org.netbeans.lib.awtextra.AbsoluteConstraints(170, 130, 350, 30));

        jLabel4.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel4.setText("Sex :");
        jPanel1.add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 180, -1, -1));

        cbo_sex.setBackground(new java.awt.Color(204, 204, 255));
        cbo_sex.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        cbo_sex.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Male", "Female" }));
        jPanel1.add(cbo_sex, new org.netbeans.lib.awtextra.AbsoluteConstraints(170, 170, 350, 30));

        jLabel5.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel5.setText("Status :");
        jPanel1.add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 220, -1, -1));

        cbo_status.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        cbo_status.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Married", "Singel" }));
        jPanel1.add(cbo_status, new org.netbeans.lib.awtextra.AbsoluteConstraints(170, 210, 350, 30));

        jLabel21.setFont(new java.awt.Font("Tahoma", 3, 12)); // NOI18N
        jLabel21.setText("CODE #:");
        jPanel1.add(jLabel21, new org.netbeans.lib.awtextra.AbsoluteConstraints(220, 20, 60, 30));

        jLabel22.setFont(new java.awt.Font("Tahoma", 3, 12)); // NOI18N
        jLabel22.setText("(Municipality)");
        jPanel1.add(jLabel22, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 490, -1, -1));

        jLabel23.setFont(new java.awt.Font("Tahoma", 3, 12)); // NOI18N
        jLabel23.setText("(Barangay)");
        jPanel1.add(jLabel23, new org.netbeans.lib.awtextra.AbsoluteConstraints(280, 490, -1, -1));

        jLabel17.setFont(new java.awt.Font("Tahoma", 3, 12)); // NOI18N
        jLabel17.setText("(Purok)");
        jPanel1.add(jLabel17, new org.netbeans.lib.awtextra.AbsoluteConstraints(430, 490, -1, -1));

        jLabel19.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel19.setText("Address :");
        jPanel1.add(jLabel19, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 520, -1, -1));

        cbo_municipality.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        cbo_municipality.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                cbo_municipalityItemStateChanged(evt);
            }
        });
        jPanel1.add(cbo_municipality, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 510, 130, 30));

        cbo_barangay.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        cbo_barangay.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                cbo_barangayItemStateChanged(evt);
            }
        });
        jPanel1.add(cbo_barangay, new org.netbeans.lib.awtextra.AbsoluteConstraints(250, 510, 130, 30));

        cbo_purok.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        cbo_purok.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                cbo_purokItemStateChanged(evt);
            }
        });
        jPanel1.add(cbo_purok, new org.netbeans.lib.awtextra.AbsoluteConstraints(390, 510, 130, 30));

        jLabel10.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel10.setText("Occupation (Trabaho) :");
        jPanel1.add(jLabel10, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 260, 200, -1));
        jPanel1.add(txt_occupation, new org.netbeans.lib.awtextra.AbsoluteConstraints(170, 250, 350, 30));

        jLabel7.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel7.setText("Status of Employment :");
        jPanel1.add(jLabel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 300, 200, -1));

        cbo_status_of_employment.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        cbo_status_of_employment.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Wage", "Self-employed" }));
        jPanel1.add(cbo_status_of_employment, new org.netbeans.lib.awtextra.AbsoluteConstraints(170, 290, 350, 30));

        jLabel2.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel2.setText("Spouse Name (Pangalan sa asawa) :");
        jPanel1.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 340, 290, -1));
        jPanel1.add(txt_spouse_name, new org.netbeans.lib.awtextra.AbsoluteConstraints(240, 330, 280, 30));

        jLabel12.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel12.setText("Age (edad) :");
        jPanel1.add(jLabel12, new org.netbeans.lib.awtextra.AbsoluteConstraints(150, 380, 140, -1));
        jPanel1.add(txt_spouse_age, new org.netbeans.lib.awtextra.AbsoluteConstraints(240, 370, 280, 30));

        jLabel11.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel11.setText("Occupation (Trabaho) :");
        jPanel1.add(jLabel11, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 420, 210, -1));
        jPanel1.add(txt_spouse_occupation, new org.netbeans.lib.awtextra.AbsoluteConstraints(240, 410, 280, 30));

        jLabel14.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel14.setText("No. of Children (Pila kabuok ang anak) :");
        jPanel1.add(jLabel14, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 460, -1, -1));

        jLabel13.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel13.setText("lalaki/");
        jPanel1.add(jLabel13, new org.netbeans.lib.awtextra.AbsoluteConstraints(260, 460, -1, -1));
        jPanel1.add(txt_no_of_children_male, new org.netbeans.lib.awtextra.AbsoluteConstraints(300, 450, 70, 30));

        jLabel9.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel9.setText("babaye");
        jPanel1.add(jLabel9, new org.netbeans.lib.awtextra.AbsoluteConstraints(390, 460, -1, -1));
        jPanel1.add(txt_no_of_children_female, new org.netbeans.lib.awtextra.AbsoluteConstraints(440, 450, 80, 30));

        jLabel6.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel6.setText("Other dependents (Uban pang gibuhi) :");
        jPanel1.add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 570, -1, 20));

        cho_ginikanan.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        cho_ginikanan.setText("Ginikanan");
        cho_ginikanan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cho_ginikananActionPerformed(evt);
            }
        });
        jPanel1.add(cho_ginikanan, new org.netbeans.lib.awtextra.AbsoluteConstraints(250, 570, 90, -1));

        cho_igsoon.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        cho_igsoon.setText("Igsoon");
        cho_igsoon.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cho_igsoonActionPerformed(evt);
            }
        });
        jPanel1.add(cho_igsoon, new org.netbeans.lib.awtextra.AbsoluteConstraints(340, 570, -1, -1));

        cho_pagumangkon.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        cho_pagumangkon.setText("Pag-umangkon");
        cho_pagumangkon.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cho_pagumangkonActionPerformed(evt);
            }
        });
        jPanel1.add(cho_pagumangkon, new org.netbeans.lib.awtextra.AbsoluteConstraints(410, 570, -1, -1));

        jLabel24.setFont(new java.awt.Font("Tahoma", 3, 12)); // NOI18N
        jLabel24.setText("(lastname)");
        jPanel1.add(jLabel24, new org.netbeans.lib.awtextra.AbsoluteConstraints(190, 70, -1, -1));
        jPanel1.add(txt_code, new org.netbeans.lib.awtextra.AbsoluteConstraints(280, 20, 130, 30));

        codec.setText("code");
        jPanel1.add(codec, new org.netbeans.lib.awtextra.AbsoluteConstraints(360, 20, 40, 30));

        codemun.setText("municipal");
        jPanel1.add(codemun, new org.netbeans.lib.awtextra.AbsoluteConstraints(300, 20, 30, 30));

        codebar.setText("barangay");
        jPanel1.add(codebar, new org.netbeans.lib.awtextra.AbsoluteConstraints(290, 20, 50, 30));

        lblpurokid.setText("purokID");
        jPanel1.add(lblpurokid, new org.netbeans.lib.awtextra.AbsoluteConstraints(370, 30, 40, 20));

        codepur.setText("code purok");
        jPanel1.add(codepur, new org.netbeans.lib.awtextra.AbsoluteConstraints(350, 30, 40, 20));

        lbl_ids.setText("jLabel1");
        jPanel1.add(lbl_ids, new org.netbeans.lib.awtextra.AbsoluteConstraints(300, 30, -1, -1));

        btn_next.setText("NEXT");
        jPanel1.add(btn_next, new org.netbeans.lib.awtextra.AbsoluteConstraints(390, 610, 80, 40));

        btn_cancel.setText("CANCEL");
        btn_cancel.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_cancelActionPerformed(evt);
            }
        });
        jPanel1.add(btn_cancel, new org.netbeans.lib.awtextra.AbsoluteConstraints(390, 610, 80, 40));

        jPanel4.setBackground(new java.awt.Color(153, 153, 255));
        jPanel4.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        btn_delete.setText("DELETE");
        btn_delete.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_deleteActionPerformed(evt);
            }
        });
        jPanel4.add(btn_delete, new org.netbeans.lib.awtextra.AbsoluteConstraints(260, 10, 80, 40));

        btn_update1.setText("UPDATE 1");
        btn_update1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_update1ActionPerformed(evt);
            }
        });
        jPanel4.add(btn_update1, new org.netbeans.lib.awtextra.AbsoluteConstraints(150, 10, 80, 40));

        btn_update.setText("UPDATE");
        btn_update.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_updateActionPerformed(evt);
            }
        });
        jPanel4.add(btn_update, new org.netbeans.lib.awtextra.AbsoluteConstraints(150, 10, 80, 40));

        btn_new.setText("NEW");
        btn_new.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_newActionPerformed(evt);
            }
        });
        jPanel4.add(btn_new, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 10, 90, 40));

        btn_save.setText("SAVE");
        btn_save.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_saveActionPerformed(evt);
            }
        });
        jPanel4.add(btn_save, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 10, 90, 40));

        jPanel1.add(jPanel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 600, 490, 60));
        jPanel1.add(lbl_ginikanan, new org.netbeans.lib.awtextra.AbsoluteConstraints(420, 610, 30, 20));
        jPanel1.add(lbl_igsoon, new org.netbeans.lib.awtextra.AbsoluteConstraints(420, 610, 30, 30));
        jPanel1.add(lbl_pagumangkon, new org.netbeans.lib.awtextra.AbsoluteConstraints(430, 610, 30, 30));

        jLabel16.setText("jLabel16");
        jPanel1.add(jLabel16, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 544, 60, 30));

        jPanel2.add(jPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 20, 540, 670));

        jPanel3.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jTable_display.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {

            }
        ));
        jTable_display.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTable_displayMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(jTable_display);

        jPanel3.add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 90, -1, 500));

        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel1.setText("Search :");
        jPanel3.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(140, 30, 50, 30));

        txt_search.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txt_searchKeyReleased(evt);
            }
        });
        jPanel3.add(txt_search, new org.netbeans.lib.awtextra.AbsoluteConstraints(200, 30, 150, 30));

        jPanel2.add(jPanel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(580, 20, 490, 670));

        getContentPane().add(jPanel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 20, 1090, 700));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btn_saveActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_saveActionPerformed
        // TODO add your handling code here:
        //          int aInt = Integer.parseInt(txt_age.getText());

        //  classDbExecute.dquery("insert into pro(code,last_name,first_name,middle_name,age,sex,civil_status,address)values('"+txt_code.getText()+"','"+txt_family_head_firstname.getText()+"','"+txt_family_head_lastname.getText()+"','"+txt_family_head_middlename.getText()+"','"+  aInt   +"', '"+cbo_sex.getSelectedItem()+"','"+cbo_status.getSelectedItem()+"','"+txt_address.getText()+"')", "Add");

        String address = cbo_purok.getSelectedItem() +" ," + cbo_barangay.getSelectedItem() +" ,"+cbo_municipality.getSelectedItem();
        
        
       
       
        
        classDb.dbconnect();

        
        
        try {

            
            
             int ginikanan = 0,igsoonw = 0,pagumangkon = 0 ;
            
            if(cho_ginikanan.isSelected())
        {
            ginikanan = 1;
            
        }else
            {
                
               ginikanan = 0; 
            }
             if(cho_igsoon.isSelected())
        {
            igsoonw = 1;
            
        }else
            {
                
               igsoonw = 0; 
            }
             if(cho_pagumangkon.isSelected())
        {
            pagumangkon = 1;
            
        }else
            {
                
               pagumangkon = 0; 
            }
             
          
             
                 String timeString2="";
                  java.util.Date d2 = new java.util.Date();
                  SimpleDateFormat sdf2 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS");
                  timeString2 = sdf2.format(d2);
                  String mm = timeString2;
            
         
                  
                  
                  
            s =  classDb.conn.createStatement();
            s.executeUpdate("Insert INTO pro(code,last_name,first_name,middle_name,age,sex,civil_status,occupation,employment_status,spouse_name,spouse_age,spouse_occupation,children_male,children_female,address,ginikanan,igsoon,pag_umangkon,created_at,purok_id) "
                + "values('" + txt_code.getText() + "','" + txt_family_head_lastname.getText() + "','"+txt_family_head_firstname.getText()+"','"+txt_family_head_middlename.getText()+"','"+ Integer.parseInt(txt_age.getText().trim())+"','" + cbo_sex.getSelectedItem() + "','" + cbo_status.getSelectedItem() + "','"+ txt_occupation.getText()+"','"+ cbo_status_of_employment.getSelectedItem()+"','"+ txt_spouse_name.getText()+"','"+ txt_spouse_age.getText()+"','"+ txt_spouse_occupation.getText()+"','"+ txt_no_of_children_male.getText()+"','"+ txt_no_of_children_female.getText()+"','"+ address+"','"+ ginikanan +"','"+ igsoonw +"','"+ pagumangkon +"'," 
                + "'"+ mm +"', "
                + "'"+ lblpurokid.getText()+"')");
            JOptionPane.showMessageDialog(null, "Save Record, Done.....", "Saving Record Notification", JOptionPane.INFORMATION_MESSAGE);

            
             displayData();
            s.close();
            classDb.conn.close();

        } catch (SQLException ex) {
            //Logger.getLogger(DataEntry.class.getName()).log(Level.SEVERE, null, ex);
            JOptionPane.showMessageDialog(this, ex.getLocalizedMessage(), "Error report", JOptionPane.OK_OPTION);

        }

    }//GEN-LAST:event_btn_saveActionPerformed

    private void cbo_municipalityItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_cbo_municipalityItemStateChanged
        // TODO add your handling code here:

        classMunicipalityGetSet d_vo = (classMunicipalityGetSet)this.cbo_municipality.getSelectedItem();
       int id_de = d_vo.getidMunicipal();
       String n = d_vo.getdesMunicipal();
       codemun.setText(id_de+"");
     

        Barangay.list_barangay(cbo_barangay, id_de);

    }//GEN-LAST:event_cbo_municipalityItemStateChanged

    private void cbo_barangayItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_cbo_barangayItemStateChanged
        // TODO add your handling code here:
        classBarangayGetSet p_vo = (classBarangayGetSet)this.cbo_barangay.getSelectedItem();
        int id_di = p_vo.getIdBarangay();
        String n = p_vo.getNameBarangay();
        codebar.setText("-"+id_di);
     //   lbl_bars.setText(n);
        Purok.list_purok(cbo_purok, id_di);

    }//GEN-LAST:event_cbo_barangayItemStateChanged

    private void cbo_purokItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_cbo_purokItemStateChanged
        // TODO add your handling code here:

        classPurokGetSet p_vo = (classPurokGetSet)this.cbo_purok.getSelectedItem();
        int id_di = p_vo.getIdPurok();
        String n = p_vo.getPurok();
        lblpurokid.setText(id_di+"");
       codepur.setText("-"+id_di+"-");
       
       txt_code.setText(codemun.getText() + codebar.getText() + codepur.getText() + codec.getText());
      //  lbl_purs.setText(n);
    }//GEN-LAST:event_cbo_purokItemStateChanged

    private void cho_ginikananActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cho_ginikananActionPerformed


        // TODO add your handling code here:
      
        
        
        

    }//GEN-LAST:event_cho_ginikananActionPerformed

    private void cho_igsoonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cho_igsoonActionPerformed
        // TODO add your handling code here:

    }//GEN-LAST:event_cho_igsoonActionPerformed

    private void cho_pagumangkonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cho_pagumangkonActionPerformed
        // TODO add your handling code here:

    }//GEN-LAST:event_cho_pagumangkonActionPerformed

    private void jTable_displayMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTable_displayMouseClicked
        // TODO add your handling code here:
        
        
      //   account_del();
       //   disable();
       /* 
          btnnew.setEnabled(true);
          btn_close.setEnabled(true);
          btnup.setVisible(true);
           btn_appform.setEnabled(true);
        btn_report.setEnabled(true);
        btnnew.setVisible(true);
        lbl_datefile.setVisible(true);
          lbl_dateapp.setVisible(false);
           btnup.setEnabled(true);
        btnprint.setEnabled(true);
      
        btnclear.setVisible(true);
       // txt_swap_value.setVisible(false);
       */
       
       
       
        classDb.dbconnect();
        
       
          //  txt_address.setVisible(true);
            disabled();
            
            
            
         lbl_ids.setText(jTable_display.getValueAt(jTable_display.getSelectedRow(), 0).toString());
        txt_code.setText(jTable_display.getValueAt(jTable_display.getSelectedRow(), 1).toString());
        txt_family_head_firstname.setText(jTable_display.getValueAt(jTable_display.getSelectedRow(), 2).toString());
         txt_family_head_lastname.setText(jTable_display.getValueAt(jTable_display.getSelectedRow(), 3).toString());
        txt_family_head_middlename.setText(jTable_display.getValueAt(jTable_display.getSelectedRow(), 4).toString());
       txt_age.setText(jTable_display.getValueAt(jTable_display.getSelectedRow(), 5).toString());
        cbo_sex .setSelectedItem(jTable_display.getValueAt(jTable_display.getSelectedRow(), 6).toString());
       cbo_status.setSelectedItem(jTable_display.getValueAt(jTable_display.getSelectedRow(), 7).toString());
         
        txt_occupation.setText(jTable_display.getValueAt(jTable_display.getSelectedRow(), 8).toString());
        
       cbo_status_of_employment.setSelectedItem(jTable_display.getValueAt(jTable_display.getSelectedRow(), 9).toString());
       
        txt_spouse_name.setText(jTable_display.getValueAt(jTable_display.getSelectedRow(), 10).toString());
        txt_spouse_age.setText(jTable_display.getValueAt(jTable_display.getSelectedRow(),11).toString());
         txt_spouse_occupation.setText(jTable_display.getValueAt(jTable_display.getSelectedRow(), 12).toString());
          txt_no_of_children_male.setText(jTable_display.getValueAt(jTable_display.getSelectedRow(), 13).toString());
           txt_no_of_children_female.setText(jTable_display.getValueAt(jTable_display.getSelectedRow(), 14).toString());
         
         
           jLabel16.setText(jTable_display.getValueAt(jTable_display.getSelectedRow(), 20).toString());
           
          //txt_address.setText(jTable_display.getValueAt(jTable_display.getSelectedRow(), 15).toString());
          lbl_ginikanan.setText(jTable_display.getValueAt(jTable_display.getSelectedRow(), 16).toString());
          lbl_igsoon.setText(jTable_display.getValueAt(jTable_display.getSelectedRow(), 17).toString());
          lbl_pagumangkon.setText(jTable_display.getValueAt(jTable_display.getSelectedRow(), 18).toString());
          
          
          
          String myginikanan =lbl_ginikanan.getText();
          String igsoon = lbl_igsoon.getText();
          String pagumangkon = lbl_pagumangkon.getText();
          
          
          
          int i = Integer.parseInt(myginikanan);
          int k = Integer.parseInt(igsoon);
          int j = Integer.parseInt(pagumangkon);
          
          if(i==1)
          {
              
              cho_ginikanan.setSelected(true);
          }
          else
          {
              cho_ginikanan.setSelected(false);
              
          }
           if(k==1)
          {
              
              cho_igsoon.setSelected(true);
              
          }
           else
           {
               cho_igsoon.setSelected(false);
               
           }
           if(j==1)
          {
              
              cho_pagumangkon.setSelected(true);
              
          }
          
           else
           {
                 
                cho_pagumangkon.setSelected(false);
           }
          
          
            
             
           
          
           
           
           
          // cbo_municipality.setSelectedItem(jTable_display.getValueAt(jTable_display.getSelectedRow(), 15).toString());
         // cbo_barangay.setSelectedItem(jTable_display.getValueAt(jTable_display.getSelectedRow(), 16).toString());
      //    lblpuro.setText(jTable_display.getValueAt(jTable_display.getSelectedRow(), 20).toString());
        
         
         
      /*
      

        String bday = jTable_display.getValueAt(jTable_display.getSelectedRow(), 17).toString();

        //set bday code
        
        java.util.Date bdate = null;
        try {
            bdate = new SimpleDateFormat("yyyy-MM-dd").parse(bday);
        } catch (ParseException ex) {
            JOptionPane.showMessageDialog(this, ex.getLocalizedMessage(), "Error report", JOptionPane.OK_OPTION);
        }

        Calendar cal = Calendar.getInstance();
        cal.setTime(bdate);
        myDate.setCalendar(cal);

         
         Double valval = (Double.parseDouble(txt_value.getText()));

            
          String vally = NumberFormat.getNumberInstance(Locale.US).format(valval);
          txt_swap_value.setText(vally+".00");
        
        
          Double tot = (Double.parseDouble(txt_tonnage.getText()));
           String tony = NumberFormat.getNumberInstance(Locale.US).format(tot);
          txt_swap_ton.setText(tony);
          txt_tonnage.setVisible(false);
         txt_swap_ton.setVisible(true);
        
        
        
        
        */
        
        
        
    }//GEN-LAST:event_jTable_displayMouseClicked

    private void btn_newActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_newActionPerformed
           
            
        try {
            txt_family_head_firstname.grabFocus();
            
            displayData();
        } catch (SQLException ex) {
            Logger.getLogger(Profile.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        
            btn_cancel.setVisible(true);
            btn_new.setVisible(false);
          
            btn_next.setVisible(false);
           
            btn_update1.setEnabled(false);
            
            enabled();
            
            clear();
            
            
            
            cho_ginikanan.setSelected(false);
            cho_igsoon.setSelected(false);
            cho_pagumangkon.setSelected(false);
            
            btn_update.setEnabled(false);
            btn_delete.setEnabled(false);
            btn_next.setEnabled(false);
            
        
        
    }//GEN-LAST:event_btn_newActionPerformed

    private void btn_cancelActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_cancelActionPerformed
        // TODO add your handling code here:
        
        try {
            displayData();
            btn_update.setEnabled(true);
        btn_delete.setEnabled(true);
         btn_cancel.setVisible(false);
         btn_next.setVisible(true);
          btn_next.setEnabled(true);
        btn_new.setVisible(true);
        btn_update1.setEnabled(true);
        disabled();
            
        } catch (SQLException ex) {
            Logger.getLogger(Profile.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }//GEN-LAST:event_btn_cancelActionPerformed

    private void btn_updateActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_updateActionPerformed
        // TODO add your handling code here:
            
        
        
            
            
            classDb.dbconnect();

        String address = cbo_purok.getSelectedItem() +" ," + cbo_barangay.getSelectedItem() +" ,"+cbo_municipality.getSelectedItem();
        
        
        int ginikanan = 0,igsoonw = 0,pagumangkon = 0 ;
            
            if(cho_ginikanan.isSelected())
        {
            ginikanan = 1;
            
        }else
            {
                
               ginikanan = 0; 
            }
             if(cho_igsoon.isSelected())
        {
            igsoonw = 1;
            
        }else
            {
                
               igsoonw = 0; 
            }
             if(cho_pagumangkon.isSelected())
        {
            pagumangkon = 1;
            
        }else
            {
                
               pagumangkon = 0; 
            }
             
          
	
             String timeString2="";
                  java.util.Date d2 = new java.util.Date();
                  SimpleDateFormat sdf2 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS");
                  timeString2 = sdf2.format(d2);
                  String updated = timeString2;
        
        
        
        
        
        
         
        
        try {
            
           
            
            s = classDb.conn.createStatement();
            s.executeUpdate("UPDATE pro SET last_name = '" + txt_family_head_firstname.getText() + "', "
                    + "first_name = '" + txt_family_head_lastname.getText() + "', "
                    + "middle_name = '" + txt_family_head_middlename.getText() + "', "
                    + "age = '" + txt_age.getText() + "', "  
                    + "sex = '" + cbo_sex.getSelectedItem() + "', " 
                    + "civil_status = '" + cbo_status.getSelectedItem() + "', " 
                    + "occupation = '" + txt_occupation.getText() + "', " 
                    + "employment_status = '" + cbo_status_of_employment.getSelectedItem() + "', "  
                    + "spouse_name = '" + txt_spouse_name.getText() + "', " 
                    + "spouse_age = '" + txt_spouse_age.getText() + "', " 
                     + "spouse_occupation = '" + txt_spouse_occupation.getText() + "', " 
                     + "children_male = '" + txt_no_of_children_male.getText() + "', " 
                     + "children_female = '" + txt_no_of_children_female.getText() + "', "   
                    + "address = '" + address + "', "  
                     + "ginikanan = '" + ginikanan + "', "  
                   // + "euf = '" + Double.parseDouble(txt_euf.getText()) + "', "   
                  //    + "or3 = '" + txt_or3.getText() + "', "  
                      + "igsoon = '" + igsoonw + "', "  
                     + "pag_umangkon = '" + pagumangkon + "', "  
                   //  + "year = '" + pass + "', "  
                     + "updated_at = '" + updated + "', "  
                      + "purok_id = '" + lblpurokid.getText() + "' "         
                    + "WHERE code = '" + txt_code.getText() + "' "); 
            
            
            
            
       
            JOptionPane.showMessageDialog(null, "Record is Update, Done.....", "Updated Record Notification", JOptionPane.INFORMATION_MESSAGE);
     
            
            
              displayData();
              btn_update1.setVisible(true);
              disabled();
            s.close();   //close statement
            classDb.conn.close(); // close connection

            
            
            
            
        }
        
         catch (Exception e) {
           // Logger.getLogger(OTP.class.getName()).log(Level.SEVERE, null, ex);
            JOptionPane.showMessageDialog(this, e.getLocalizedMessage(), "Error report", JOptionPane.OK_OPTION);

           // JOptionPane.showMessageDialog(null, "WAAAAAAAAAAAAAAAAAAAAAAAAAAAA.....", "Updated Record Notification", JOptionPane.INFORMATION_MESSAGE);
           
        }
            
            
            
            
    }//GEN-LAST:event_btn_updateActionPerformed

    private void btn_update1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_update1ActionPerformed
        // TODO add your handling code here:
        
        
        
            txt_family_head_firstname.setEnabled(true);
            txt_family_head_lastname.setEnabled(true);
            txt_family_head_middlename.setEnabled(true);
            txt_age.setEnabled(true);
            cbo_sex.setEnabled(true);
            cbo_status.setEnabled(true);
            txt_occupation.setEnabled(true);
            cbo_status_of_employment.setEnabled(true);
            txt_spouse_name.setEnabled(true);
            txt_spouse_age.setEnabled(true);
            txt_spouse_occupation.setEnabled(true);
            txt_no_of_children_male.setEnabled(true);
            txt_no_of_children_female.setEnabled(true);
            btn_update1.setVisible(false);
        
    }//GEN-LAST:event_btn_update1ActionPerformed

    private void btn_deleteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_deleteActionPerformed
        // TODO add your handling code here:
         classDb.dbconnect();
        
          Object [] options = {"OK","CANCEL"};
       if(JOptionPane.showOptionDialog(null, "Do you really want to delete this records?","Confirm!!",JOptionPane.DEFAULT_OPTION, JOptionPane.QUESTION_MESSAGE, null, options, options[0])==1)
       {
     
           
       }
       
       else
       {
           
           
             try {
                 s = classDb.conn.createStatement();
                 
                 s.executeUpdate("DELETE FROM pro WHERE id = '" + lbl_ids.getText() + "' ");

          
            JOptionPane.showMessageDialog(null, "Record is Deleted, Done.....", "Delete Record Notification", JOptionPane.INFORMATION_MESSAGE);
          
            
              s.close();
                 
              displayData();
              
              
             } catch (SQLException ex) {
                 Logger.getLogger(Profile.class.getName()).log(Level.SEVERE, null, ex);
             }
            

  
           
           
       }
        
        
        
        
        
    }//GEN-LAST:event_btn_deleteActionPerformed

    private void txt_searchKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txt_searchKeyReleased
        // TODO add your handling code here:
        
          classDb.dbconnect();
        
       
        
        
         try {
             
              String query = txt_search.getText();
          filter(query);
        
             
             
             
             s = classDb.conn.createStatement();
              // TODO add your handling code here:
            
              //  rs = s.executeQuery("SELECT * FROM otpnm_table");
              rs = s.executeQuery("SELECT * FROM pro WHERE code = '" + txt_search.getText() + "' OR last_name = '" + txt_search.getText() + "' OR first_name = '" + txt_search.getText() + "'");

              rs.last(); // go to the last record to get total records
            
           if (rs.getRow() > 0) {  // if total rows is greater than zero , naay sulod
                rs.beforeFirst(); // go to top row...
 
                while (rs.next()) {
                    lbl_ids.setText(rs.getString(1));
                txt_code.setText(rs.getString(2));       
             txt_family_head_lastname.setText(rs.getString(3));
             txt_family_head_firstname.setText(rs.getString(4));
            txt_family_head_middlename.setText(rs.getString(5));
            txt_age.setText(rs.getString(6));
             cbo_sex.setSelectedItem(rs.getString(7));
            cbo_status.setSelectedItem(rs.getString(8));
            txt_occupation.setText(rs.getString(9));
       
         
      
       
       
        cbo_status_of_employment.setSelectedItem(rs.getString(10));
        txt_spouse_name.setText(rs.getString(11));
         txt_spouse_age.setText(rs.getString(12));
          txt_spouse_occupation.setText(rs.getString(13));
           txt_no_of_children_male.setText(rs.getString(14));
          txt_no_of_children_female.setText(rs.getString(15));
          
                lbl_ginikanan.setText(rs.getString(17));
                 lbl_igsoon.setText(rs.getString(18));
                lbl_pagumangkon.setText(rs.getString(19));
       
            String myginikanan =lbl_ginikanan.getText();
          String igsoon = lbl_igsoon.getText();
          String pagumangkon = lbl_pagumangkon.getText();
          
          
          
          int i = Integer.parseInt(myginikanan);
          int k = Integer.parseInt(igsoon);
          int j = Integer.parseInt(pagumangkon);
          
          if(i==1)
          {
              
              cho_ginikanan.setSelected(true);
          }
          else
          {
              cho_ginikanan.setSelected(false);
              
          }
           if(k==1)
          {
              
              cho_igsoon.setSelected(true);
              
          }
           else
           {
               cho_igsoon.setSelected(false);
               
           }
           if(j==1)
          {
              
              cho_pagumangkon.setSelected(true);
              
          }
          
           else
           {
                 
                cho_pagumangkon.setSelected(false);
           }
            
            
          
            
            
            
    
                } //While end
                
            } else {
              
               displayData();
              clear();
              
               
             cho_igsoon.setSelected(false);
              cho_ginikanan.setSelected(false);
              cho_pagumangkon.setSelected(false);
              
            // auto_number();
             
           
           }
           
           
         

          } catch (SQLException ex) {
              Logger.getLogger(Profile.class.getName()).log(Level.SEVERE, null, ex);
          }
         
         
         
     
         
           
        
        
        
    }//GEN-LAST:event_txt_searchKeyReleased

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(Profile.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Profile.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Profile.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Profile.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    new Profile().setVisible(true);
                } catch (SQLException ex) {
                    Logger.getLogger(Profile.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btn_cancel;
    private javax.swing.JButton btn_delete;
    private javax.swing.JButton btn_new;
    private javax.swing.JButton btn_next;
    private javax.swing.JButton btn_save;
    private javax.swing.JButton btn_update;
    private javax.swing.JButton btn_update1;
    private javax.swing.JComboBox<String> cbo_barangay;
    private javax.swing.JComboBox<String> cbo_municipality;
    private javax.swing.JComboBox<String> cbo_purok;
    private javax.swing.JComboBox<String> cbo_sex;
    private javax.swing.JComboBox<String> cbo_status;
    private javax.swing.JComboBox<String> cbo_status_of_employment;
    private javax.swing.JCheckBox cho_ginikanan;
    private javax.swing.JCheckBox cho_igsoon;
    private javax.swing.JCheckBox cho_pagumangkon;
    private javax.swing.JLabel codebar;
    private javax.swing.JLabel codec;
    private javax.swing.JLabel codemun;
    private javax.swing.JLabel codepur;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel19;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel21;
    private javax.swing.JLabel jLabel22;
    private javax.swing.JLabel jLabel23;
    private javax.swing.JLabel jLabel24;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jTable_display;
    private javax.swing.JLabel lbl_ginikanan;
    private javax.swing.JLabel lbl_ids;
    private javax.swing.JLabel lbl_igsoon;
    private javax.swing.JLabel lbl_pagumangkon;
    private javax.swing.JLabel lblpurokid;
    private javax.swing.JTextField txt_age;
    private javax.swing.JTextField txt_code;
    private javax.swing.JTextField txt_family_head_firstname;
    private javax.swing.JTextField txt_family_head_lastname;
    private javax.swing.JTextField txt_family_head_middlename;
    private javax.swing.JTextField txt_no_of_children_female;
    private javax.swing.JTextField txt_no_of_children_male;
    private javax.swing.JTextField txt_occupation;
    private javax.swing.JTextField txt_search;
    private javax.swing.JTextField txt_spouse_age;
    private javax.swing.JTextField txt_spouse_name;
    private javax.swing.JTextField txt_spouse_occupation;
    // End of variables declaration//GEN-END:variables
}
