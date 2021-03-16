/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ejb.stateless;

import entity.PartnerEntity;
import java.util.List;
import java.util.Set;
import javax.ejb.Stateless;
import javax.ejb.LocalBean;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.NonUniqueResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceException;
import javax.persistence.Query;
import javax.validation.ConstraintViolation;
import util.exception.InvalidLoginCredentialException;
import util.exception.PartnerNotFoundException;
import util.security.CryptographicHelper;

/**
 *
 * @author leahj
 */
@Stateless
@LocalBean
public class PartnerEntitySessionBean {

    @PersistenceContext(unitName = "HipNature-ejbPU")
    private EntityManager em;

    public void persist(Object object) {
        em.persist(object);
    }

    public Long createNewPartner(PartnerEntity newPartnerEntity) {

        em.persist(newPartnerEntity);
        em.flush();

        return newPartnerEntity.getPartnerEntityId();

    }

    public List<PartnerEntity> retrieveAllPartners() {
        Query query = em.createQuery("SELECT s FROM PartnerEntity s");

        return query.getResultList();
    }

    public PartnerEntity retrievePartnerByPartnerId(Long staffId) {
        PartnerEntity partnerEntity = em.find(PartnerEntity.class, staffId);

        //if(staffEntity != null)
        //{
        return partnerEntity;

    }

    public PartnerEntity retrievePartnerByUsername(String username) throws PartnerNotFoundException {

        Query query = em.createQuery("SELECT s FROM PartnerEntity s WHERE s.username = :inUsername");
        query.setParameter("inUsername", username);


        try {
            return (PartnerEntity) query.getSingleResult();
        } catch (NoResultException | NonUniqueResultException ex) {
            throw new PartnerNotFoundException("Partner Username " + username + " does not exist!");
        }
    }

    public PartnerEntity partnerLogin(String username, String password) throws InvalidLoginCredentialException {
        try {
            System.out.println("Username "+username);
            System.out.println("Password "+password);
            PartnerEntity partnerEntity = retrievePartnerByUsername(username);
                        System.out.println(partnerEntity.getSalt());

            String passwordHash = CryptographicHelper.getInstance().byteArrayToHexString(CryptographicHelper.getInstance().doMD5Hashing(password + partnerEntity.getSalt()));
            System.out.println(partnerEntity.getPassword().equals(passwordHash));
            System.out.println(passwordHash);
            System.out.println(partnerEntity.getPassword());
            if (partnerEntity.getPassword().equals(passwordHash)) {
                partnerEntity.getInstructorEntity().size();
                return partnerEntity;
            } else {
                throw new InvalidLoginCredentialException("Username does not exist or invalid password!");
            }
        } catch (PartnerNotFoundException ex) {
            throw new InvalidLoginCredentialException("Username does not exist or invalid password!");
        }
    }

    // Add business logic below. (Right-click in editor and choose
    // "Insert Code > Add Business Method")
}
