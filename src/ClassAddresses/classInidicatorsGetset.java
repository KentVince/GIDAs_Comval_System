/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ClassAddresses ;

/**
 *
 * @author User
 */
public class classInidicatorsGetset {
    private int id;
 private String need;
 
 public classInidicatorsGetset(int id,String need){
 this.id = id;
 this.need = need;
 
 
 }
 
 /*public classMunicipalityGetSet(int id, String name){
     this.idDepartamento = id;
     this.departamento = name;
 }*/

    /**
     * @return the idDepartamento
     */
    public int getidneeds() {
        return id;
    }

    public void setidneeds(int id) {
        this.id = id;
    }

      public String getneeds() {
        return need;
    }
  

    public void setneeds(String need) {
        this.need = need;
    }
    
    public String toString(){
        return this.need;
    }

}
