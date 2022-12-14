/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entity;

import java.io.Serializable;
import java.util.ArrayList;
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
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import util.security.CryptographicHelper;

/**
 *
 * @author User
 */
@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
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
   
    @Column(columnDefinition = "CHAR(32) NOT NULL")
    private String salt;
    
    @OneToMany(mappedBy = "customerEntity", fetch = FetchType.LAZY)
    private List<CreditCardEntity> creditCardEntity;
    
    @OneToMany(mappedBy = "customerEntity", fetch = FetchType.LAZY)
    private List<ReviewEntity> reviewEntity;
    
    @OneToMany(mappedBy = "customer", fetch = FetchType.LAZY)
    private List<PurchasedPlanEntity> purchasedPlans;
    
    
    public Long getCustomerId() {
        return customerId;
    }

    public void setCustomerId(Long customerId) {
        this.customerId = customerId;
    }

    public CustomerEntity() {
        this.salt = CryptographicHelper.getInstance().generateRandomString(32);
        creditCardEntity = new ArrayList<>();
        reviewEntity = new ArrayList<>();
        purchasedPlans = new ArrayList<>();
    }
    
    public CustomerEntity(String customerName, String phone, String email, String address, String username,  CustomerTypeEnum customerTypeEnum) {
        this();
        this.customerName = customerName;
        this.phone = phone;
        this.email = email;
        this.address = address;
        this.username = username;
        this.customerTypeEnum = customerTypeEnum;
    }
    

    public CustomerEntity(String customerName, String phone, String email, String address, String username, String password, CustomerTypeEnum customerTypeEnum) {
        this();
        this.customerName = customerName;
        this.phone = phone;
        this.email = email;
        this.address = address;
        this.username = username;
        setPassword(password);
        this.customerTypeEnum = customerTypeEnum;
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
         if (password != null) {
            this.password = CryptographicHelper.getInstance().byteArrayToHexString(CryptographicHelper.getInstance().doMD5Hashing(password + this.salt));
        } else {
            this.password = null;
        }    }

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
    public List<PurchasedPlanEntity> getPurchasedPlans() {
        return purchasedPlans;
    }

    /**
     * @param purchasedPlans the purchasedPlans to set
     */
    public void setPurchasedPlans(List<PurchasedPlanEntity> purchasedPlans) {
        this.purchasedPlans = purchasedPlans;
    }

    /**
     * @return the salt
     */
    public String getSalt() {
        return salt;
    }

    /**
     * @param salt the salt to set
     */
    public void setSalt(String salt) {
        this.salt = salt;
    }

    
}
