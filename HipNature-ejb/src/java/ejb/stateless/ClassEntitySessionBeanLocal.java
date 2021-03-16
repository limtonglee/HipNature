/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ejb.stateless;

import entity.ClassEntity;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author kelly
 */
@Local
public interface ClassEntitySessionBeanLocal {

    public List<ClassEntity> retrieveAllClasses();

    public Long createNewClass(ClassEntity newClass);

    public ClassEntity retrieveClassByClassId(Long classId) throws ClassNotFoundException;
    
}
