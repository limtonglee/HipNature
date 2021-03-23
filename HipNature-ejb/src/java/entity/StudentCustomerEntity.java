/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entity;

import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import util.enumeration.CustomerTypeEnum;

/**
 *
 * @author leahj
 */
@Entity
public class StudentCustomerEntity  extends CustomerEntity implements Serializable {

    private String verificationNRIC;

    public StudentCustomerEntity(){
        
    }

    public StudentCustomerEntity(String verificationNRIC, String customerName, String phone, String email, String address, String username, String password, CustomerTypeEnum customerTypeEnum) {
        super(customerName, phone, email, address, username, password, customerTypeEnum);
        this.verificationNRIC = verificationNRIC;
    }
    
    public String getVerificationNRIC() {
        return verificationNRIC;
    }

    public void setVerificationNRIC(String verificationNRIC) {
        this.verificationNRIC = verificationNRIC;
    }
    



    

//    public Long getId() {
//        return id;
//    }
//
//    public void setId(Long id) {
//        this.id = id;
//    }
//
//    @Override
//    public int hashCode() {
//        int hash = 0;
//        hash += (id != null ? id.hashCode() : 0);
//        return hash;
//    }
//
//    @Override
//    public boolean equals(Object object) {
//        // TODO: Warning - this method won't work in the case the id fields are not set
//        if (!(object instanceof StudentCustomerEntity)) {
//            return false;
//        }
//        StudentCustomerEntity other = (StudentCustomerEntity) object;
//        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
//            return false;
//        }
//        return true;
//    }
//
//    @Override
//    public String toString() {
//        return "entity.StudentCustomerEntity[ id=" + id + " ]";
//    }
//    
}
