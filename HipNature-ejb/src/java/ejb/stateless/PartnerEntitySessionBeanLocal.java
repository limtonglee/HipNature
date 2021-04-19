/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ejb.stateless;

import entity.PartnerEntity;
import java.util.List;
import javax.ejb.Local;
import util.exception.InputDataValidationException;
import util.exception.InstructorNotFoundException;
import util.exception.InvalidLoginCredentialException;
import util.exception.PartnerNotFoundException;

/**
 *
 * @author kelly
 */
@Local
public interface PartnerEntitySessionBeanLocal {

    public PartnerEntity createNewPartner(PartnerEntity newPartnerEntity) throws InputDataValidationException;

    public List<PartnerEntity> retrieveAllPartners();

    public PartnerEntity retrievePartnerByPartnerId(Long partnerId) throws PartnerNotFoundException;

    public PartnerEntity retrievePartnerByUsername(String username) throws PartnerNotFoundException;
    
    public PartnerEntity partnerLogin(String username, String password) throws InvalidLoginCredentialException;
    
    public PartnerEntity retrievePartnerByInstructor(Long instructorId) throws InstructorNotFoundException;
    
    public List<PartnerEntity> retrieveAllPartnerLessCurrent(Long partnerId);
    
    public void updatePartner(PartnerEntity partner) throws PartnerNotFoundException, InputDataValidationException;
 
    public void setProfilePicString(PartnerEntity pa, String s) throws PartnerNotFoundException;
    
    public void setImages(PartnerEntity pa, List<String> img) throws PartnerNotFoundException;
    
    public void setImage(PartnerEntity pa, String img) throws PartnerNotFoundException;
}
