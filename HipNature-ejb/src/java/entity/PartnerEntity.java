/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entity;

import com.sun.javafx.scene.control.skin.VirtualFlow;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Email;
import javax.validation.constraints.Size;
import util.security.CryptographicHelper;

/**
 *
 * @author User
 */
@Entity
public class PartnerEntity implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long PartnerEntityId;

    @Column(nullable = false, unique = true, length = 64)
    @NotNull
    @Size(max = 64)
    private String partnerEntityName;

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

    @Column(nullable = false, unique = true, length = 64)
    @NotNull
    @Size(min = 6, max = 32)
    private String username;

    @Column(nullable = false, length = 64)
    @NotNull
    @Size(min = 8, max = 32)
    private String password;

    @Column(columnDefinition = "CHAR(32) NOT NULL")
    private String salt;

    @OneToMany(mappedBy = "partnerEntity", fetch = FetchType.LAZY)
    private List<InstructorEntity> instructorEntity;

    
    public PartnerEntity() {
        this.salt = CryptographicHelper.getInstance().generateRandomString(32);
        instructorEntity= new ArrayList<>();
      
    }

    public PartnerEntity(String partnerEntityName, String phone, String email, String address, String username, String password) {
        this();
        this.partnerEntityName = partnerEntityName;
        this.phone = phone;
        this.email = email;
        this.address = address;
        this.username = username;
        setPassword(password);

    }

    
    // Newly added in v4.5
    public String getSalt() {
        return salt;
    }

    // Newly added in v4.5
    public void setSalt(String salt) {
        this.salt = salt;
    }

    public Long getPartnerEntityId() {
        return PartnerEntityId;
    }

    public void setPartnerEntityId(Long PartnerEntityId) {
        this.PartnerEntityId = PartnerEntityId;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (PartnerEntityId != null ? PartnerEntityId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the PartnerEntityId fields are not set
        if (!(object instanceof PartnerEntity)) {
            return false;
        }
        PartnerEntity other = (PartnerEntity) object;
        if ((this.PartnerEntityId == null && other.PartnerEntityId != null) || (this.PartnerEntityId != null && !this.PartnerEntityId.equals(other.PartnerEntityId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entity.PartnerEntity[ id=" + PartnerEntityId + " ]";
    }

    /**
     * @return the partnerEntityName
     */
    public String getPartnerEntityName() {
        return partnerEntityName;
    }

    /**
     * @param partnerEntityName the partnerEntityName to set
     */
    public void setPartnerEntityName(String partnerEntityName) {
        this.partnerEntityName = partnerEntityName;
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
        }
    }

    /**
     * @return the instructorEntity
     */
    public List<InstructorEntity> getInstructorEntity() {
        return instructorEntity;
    }

    /**
     * @param instructorEntity the instructorEntity to set
     */
    public void setInstructorEntity(List<InstructorEntity> instructorEntity) {
        this.instructorEntity = instructorEntity;
    }

}
