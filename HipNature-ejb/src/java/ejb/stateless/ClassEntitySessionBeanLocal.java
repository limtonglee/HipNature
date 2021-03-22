/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ejb.stateless;

import entity.ClassEntity;
import java.util.List;
import javax.ejb.Local;
import util.exception.CreateNewClassException;
import util.exception.InputDataValidationException;

/**
 *
 * @author kelly
 */
@Local
public interface ClassEntitySessionBeanLocal {

    public List<ClassEntity> retrieveAllClasses();

    public ClassEntity createNewClass(ClassEntity newClass, Long newClassTypeId, List<Long> newTagEntityId) throws InputDataValidationException, CreateNewClassException;

    public ClassEntity retrieveClassByClassId(Long classId) throws ClassNotFoundException;
    
}
