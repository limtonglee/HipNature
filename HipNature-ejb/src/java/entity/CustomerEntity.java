/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entity;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import util.enumeration.CustomerTypeEnum;
import java.util.List;

/**
 *
 * @author User
 */
@Entity
public class CustomerEntity implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long customerId;
    @Column(nullable = false, unique = true, length = 64)
    @NotNull
    @Size(max = 64)
    private String customerName;
    
    @Column(nullable = false, unique = true, length = 8)
    @NotNull
    @Size(max = 8)
    private String phone;
    
    @Column(nullable = false, unique = true, length = 64)
    @NotNull
    @Size(max = 64)
    @Email
    private String email;
    
    @Column(nullable = false, length = 64)
    @NotNull
    @Size(max = 128)
    private String address;
    
    @Column(nullable = false,unique = true, length = 64)
    @NotNull
    @Size(min = 8, max = 32)
    private String username;
    
    @Column(nullable = false, length = 64)
    @NotNull
    @Size(min = 8, max = 32)
    private String password;
    
    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    @NotNull
    private CustomerTypeEnum customerTypeEnum;
   
    
    @OneToMany(mappedBy = "customerEntity", fetch = FetchType.LAZY)
    private List<CreditCardEntity> creditCardEntity;
    
    @OneToMany(mappedBy = "customerEntity", fetch = FetchType.LAZY)
    private List<ReviewEntity> reviewEntity;
    
    @OneToMany(mappedBy = "customer", fetch = FetchType.LAZY)
    private List<PurchasedPlan> purchasedPlans;
    
    
    public Long getCustomerId() {
        return customerId;
    }

    public void setCustomerId(Long customerId) {
        this.customerId = customerId;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (customerId != null ? customerId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the customerId fields are not set
        if (!(object instanceof CustomerEntity)) {
            return false;
        }
        CustomerEntity other = (CustomerEntity) object;
        if ((this.customerId == null && other.customerId != null) || (this.customerId != null && !this.customerId.equals(other.customerId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entity.CustomerEntity[ id=" + customerId + " ]";
    }

    /**
     * @return the customerName
     */
    public String getCustomerName() {
        return customerName;
    }

    /**
     * @param customerName the customerName to set
     */
    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    /**
     * @return the phone
     */
    public String getPhone() {
        return phone;
    }

    /**
     * @param phone the phone to set
     */
    public void setPhone(String phone) {
        this.phone = phone;
    }

    /**
     * @return the email
     */
    public String getEmail() {
        return email;
    }

    /**
     * @param email the email to set
     */
    public void setEmail(String email) {
        this.email = email;
    }

    /**
     * @return the address
     */
    public String getAddress() {
        return address;
    }

    /**
     * @param address the address to set
     */
    public void setAddress(String address) {
        this.address = address;
    }

    /**
     * @return the username
     */
    public String getUsername() {
        return username;
    }

    /**
     * @param username the username to set
     */
    public void setUsername(String username) {
        this.username = username;
    }

    /**
     * @return the password
     */
    public String getPassword() {
        return password;
    }

    /**
     * @param password the password to set
     */
    public void setPassword(String password) {
        this.password = password;
    }

    /**
     * @return the customerTypeEnum
     */
    public CustomerTypeEnum getCustomerTypeEnum() {
        return customerTypeEnum;
    }

    /**
     * @param customerTypeEnum the customerTypeEnum to set
     */
    public void setCustomerTypeEnum(CustomerTypeEnum customerTypeEnum) {
        this.customerTypeEnum = customerTypeEnum;
    }

    /**
     * @return the creditCardEntity
     */
    public List<CreditCardEntity> getCreditCardEntity() {
        return creditCardEntity;
    }

    /**
     * @param creditCardEntity the creditCardEntity to set
     */
    public void setCreditCardEntity(List<CreditCardEntity> creditCardEntity) {
        this.creditCardEntity = creditCardEntity;
    }

    /**
     * @return the reviewEntity
     */
    public List<ReviewEntity> getReviewEntity() {
        return reviewEntity;
    }

    /**
     * @param reviewEntity the reviewEntity to set
     */
    public void setReviewEntity(List<ReviewEntity> reviewEntity) {
        this.reviewEntity = reviewEntity;
    }

    /**
     * @return the purchasedPlans
     */
    public List<PurchasedPlan> getPurchasedPlans() {
        return purchasedPlans;
    }

    /**
     * @param purchasedPlans the purchasedPlans to set
     */
    public void setPurchasedPlans(List<PurchasedPlan> purchasedPlans) {
        this.purchasedPlans = purchasedPlans;
    }

    
}
