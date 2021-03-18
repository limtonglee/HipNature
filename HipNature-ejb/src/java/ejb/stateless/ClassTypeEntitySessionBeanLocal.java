/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ejb.stateless;

import entity.ClassTypeEntity;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author User
 */
@Local
public interface ClassTypeEntitySessionBeanLocal {

    public Long createClassType(ClassTypeEntity newClassTypeEntity);

    public List<ClassTypeEntity> retrieveAllClassTypeEntity();
    
}
