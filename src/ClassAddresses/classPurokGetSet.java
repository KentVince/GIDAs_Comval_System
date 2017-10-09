/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ClassAddresses ;

/**
 *
 * @author ITCDD-PROGRAMMING
 */
public class classPurokGetSet {
    
    
    
    private int idPurok;
     private int idBarangay;
    private String desPurok;
  
   
    
    
    
     public classPurokGetSet(int id_purok, String name_purok){
        this.idPurok = id_purok;
        this.desPurok = name_purok;
    }

    /**
     * @return the idDistrito
     */
    public int getIdPurok() {
        return idPurok;
    }

    /**
     * @param idDistrito the idDistrito to set
     */
    public void setIdPurok(int idPurok) {
        this.idPurok = idPurok;
    }

    /**
     * @return the idProvincia
     */
    public int getIdBarangay() {
        return idBarangay;
    }

    /**
     * @param idProvincia the idProvincia to set
     */
    public void setIdBarangay(int idBarangay) {
        this.idBarangay = idBarangay;
    }

    /**
     * @return the distrito
     */
    public String getPurok() {
        return desPurok;
    }

    /**
     * @param distrito the distrito to set
     */
    public void setPurok(String desPurok) {
        this.desPurok = desPurok;
    }
    
    public String toString(){
        return this.desPurok;
    }
    
    
    
    
    
    
    
    
    
    
    
    
}
