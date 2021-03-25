package jsf.managedbean;

import ejb.stateless.SessionEntitySessionBeanLocal;
import ejb.stateless.TagEntitySessionBeanLocal;
import entity.SessionEntity;
import entity.TagEntity;
import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.inject.Named;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import javax.faces.model.SelectItem;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import org.primefaces.model.TreeNode;



@Named(value = "searchSessionsByNameManagedBean")
@ViewScoped
public class SearchSessionsByNameManagedBean implements Serializable
{

 

    @EJB
    private SessionEntitySessionBeanLocal sessionEntitySessionBean;
    @EJB
    private TagEntitySessionBeanLocal tagEntitySessionBeanLocal;
  
    private String searchString;
    private List<SessionEntity> sessionEntities;
    private List<Long> selectedTagIds;
    private List<SelectItem> selectItems;
    private String location;

    
    public SearchSessionsByNameManagedBean() 
    {
        location = "ALL";
        
    }
    
    
    
    @PostConstruct
    public void postConstruct()            
    {
        if(location == null) {
            location="ALL";
        }
        searchString = (String)FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("sessionSearchString");
        
        if(searchString == null || searchString.trim().length() == 0)
        {
            sessionEntities = sessionEntitySessionBean.retrieveAllSessions();
                        System.out.println(sessionEntities.size());

        }
        else
        {
            sessionEntities = sessionEntitySessionBean.searchSessionByName(searchString);
            System.out.println(sessionEntities.size());
        }
        
        List<TagEntity> tagEntities = tagEntitySessionBeanLocal.retrieveAllTags();
        selectItems = new ArrayList<>();
        
        for(TagEntity tagEntity:tagEntities)
        {
            selectItems.add(new SelectItem(tagEntity.getTagId(), tagEntity.getTagName(), tagEntity.getTagName()));
        }
        
        
        // Optional demonstration of the use of custom converter
        // FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("TagEntityConverter_tagEntities", tagEntities);
        
        location = (String)FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("sessionLocation");        
        selectedTagIds = (List<Long>)FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("sessionFilterTags");
        
        searchSession();
    }
    
    
    
    public void searchSession()
    {
        if(searchString == null || searchString.trim().length() == 0)
        {
            sessionEntities = sessionEntitySessionBean.retrieveAllSessions();
                                    System.out.println(sessionEntities.size());

        }
        else
        {
            sessionEntities = sessionEntitySessionBean.searchSessionByName(searchString);
                                    System.out.println(sessionEntities.size());

        }
         
        if(location == null) {
             System.out.println("Location"+location);

                       location="ALL";
              System.out.println("Location"+location);

        }
        System.out.println("Location"+location);
           List<SessionEntity> sessionEntities2 = sessionEntitySessionBean.filterSessionsByTags(selectedTagIds, location);
       
//               List<SessionEntity> listTwoCopy = new ArrayList<>(sessionEntities2);
//        listTwoCopy.removeAll(sessionEntities);
//        sessionEntities.addAll(listTwoCopy);
            sessionEntities.retainAll(sessionEntities2);


    }

    
    public String getSearchString() {
        return searchString;
    }

    public void setSearchString(String searchString)
    {
        this.searchString = searchString;
        
        FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("sessionSearchString", searchString);
    }
    
    public List<SessionEntity> getSessionEntities() {
        return sessionEntities;
    }

    public void setSessionEntities(List<SessionEntity> productEntities) {
        this.sessionEntities = productEntities;
    }
    
    
       /**
     * @return the location
     */
    public String getLocation() {
        return location;
    }

    /**
     * @param location the location to set
     */
    public void setLocation(String location) {
        this.location = location;
       FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("sessionLocation", location);

    }
    
     public List<Long> getSelectedTagIds() {
        return selectedTagIds;
    }

    public void setSelectedTagIds(List<Long> selectedTagIds) 
    {
        this.selectedTagIds = selectedTagIds;
        
        FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("sessionFilterTags", selectedTagIds);
    }

    public List<SelectItem> getSelectItems() {
        return selectItems;
    }

    public void setSelectItems(List<SelectItem> selectItems) {
        this.selectItems = selectItems;
    }    
    
    public void updateProduct(ActionEvent event) throws IOException
    {
            Long sessionId = (Long)event.getComponent().getAttributes().get("sessionIdToUpdate");
        FacesContext.getCurrentInstance().getExternalContext().getFlash().put("sessionIdToUpdate", sessionId);    
    }
}
