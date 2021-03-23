/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ejb.stateless;

import entity.TagEntity;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import util.exception.TagNotFoundException;

/**
 *
 * @author kelly
 */
@Stateless
public class TagEntitySessionBean implements TagEntitySessionBeanLocal {

    @PersistenceContext(unitName = "HipNature-ejbPU")
    private EntityManager em;

    @Override
    public List<TagEntity> retrieveAllTags() {
        Query query = em.createQuery("SELECT t FROM TagEntity t ORDER BY t.tagName ASC");
        List<TagEntity> list = query.getResultList();
//            for (TagEntity tag:list){
//                tag.getClassEntities().size();
//            }

        return list;
    }

    @Override
    public Long createNewTag(TagEntity newTag) {
        
        em.persist(newTag);
        em.flush();

        return newTag.getTagId();
        
    }
        
    @Override
    public TagEntity retrieveTagByTagId(Long tagId) throws TagNotFoundException {
        TagEntity tagEntity = em.find(TagEntity.class, tagId);
        
        if (tagEntity != null) {
            return tagEntity;
        } else {
            throw new TagNotFoundException("Tag ID " + tagId + " does not exist!");
        }
    }

    
    
}
