/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ejb.stateless;

import entity.ClassTypeEntity;
import java.util.List;
import javax.ejb.Local;
import util.exception.InputDataValidationException;

/**
 *
 * @author User
 */
@Local
public interface ClassTypeEntitySessionBeanLocal {

    public ClassTypeEntity createClassType(ClassTypeEntity newClassTypeEntity) throws InputDataValidationException;

    public List<ClassTypeEntity> retrieveAllClassTypeEntity();
    
    public ClassTypeEntity retrieveClassTypeByClassId(Long classId) throws ClassNotFoundException ;

}
