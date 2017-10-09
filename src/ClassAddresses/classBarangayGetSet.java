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
public class classBarangayGetSet {
    
    
    
    
    private int idBarangay;
    private String nameBarangay;
    private int idMunicipality;
    

    
    public classBarangayGetSet(int idbrgy, String namebrgy){
        this.idBarangay = idbrgy;
        this.nameBarangay = namebrgy;
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
     * @return the provincia
     */
    public String getNameBarangay() {
        return nameBarangay;
    }

    /**
     * @param provincia the provincia to set
     */
    public void setProvincia(String nameBarangay) {
        this.nameBarangay = nameBarangay;
    }

    /**
     * @return the idDepartamento
     */
    public int getIdMunicipality() {
        return idMunicipality;
    }

    /**
     * @param idDepartamento the idDepartamento to set
     */
    public void setIdMunicipality(int idMunicipality) {
        this.idMunicipality = idMunicipality;
    }
    
    public String toString(){
        return this.nameBarangay;
    }
    
    
    
    
    
    
    
    
    
    
    
    
}
