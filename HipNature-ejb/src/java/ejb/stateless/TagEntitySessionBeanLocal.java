/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ejb.stateless;

import entity.TagEntity;
import java.util.List;
import javax.ejb.Local;
import util.exception.InputDataValidationException;
import util.exception.TagNotFoundException;

/**
 *
 * @author kelly
 */
@Local
public interface TagEntitySessionBeanLocal {

    public TagEntity createNewTag(TagEntity newTag) throws InputDataValidationException;

    public List<TagEntity> retrieveAllTags();

    public TagEntity retrieveTagByTagId(Long tagId) throws TagNotFoundException;
    
}
