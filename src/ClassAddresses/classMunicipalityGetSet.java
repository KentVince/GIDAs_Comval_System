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
public class classMunicipalityGetSet {

   

    
    private int id;
 private String description;
 
 public classMunicipalityGetSet(int id,String name){
 this.id = id;
 this.description = name;
 
 
 }
 
 /*public classMunicipalityGetSet(int id, String name){
     this.idDepartamento = id;
     this.departamento = name;
 }*/

    /**
     * @return the idDepartamento
     */
    public int getidMunicipal() {
        return id;
    }

    /**
     * @param idDepartamento the idDepartamento to set
     */
    public void setidMunicipal(int id) {
        this.id = id;
    }

      public String getdesMunicipal() {
        return description;
    }
  

    /**
     * @param departamento the departamento to set
     */
    public void setdesMunicipal(String description) {
        this.description = description;
    }
    
    public String toString(){
        return this.description;
    }
    
    
    
}
