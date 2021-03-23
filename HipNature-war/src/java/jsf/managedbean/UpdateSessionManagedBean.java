package jsf.managedbean;


import ejb.stateless.SessionEntitySessionBeanLocal;
import ejb.stateless.TagEntitySessionBeanLocal;
import entity.SessionEntity;
import entity.TagEntity;
import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import javax.inject.Named;
import javax.faces.view.ViewScoped;
import util.exception.SessionNotFoundException;



@Named(value = "updateProductManagedBean")
@ViewScoped

public class UpdateSessionManagedBean implements Serializable
{

    @EJB
    private SessionEntitySessionBeanLocal sessionEntitySessionBean;

    @EJB
    private TagEntitySessionBeanLocal tagEntitySessionBean;

    private Long sessionIdToUpdate;


    private SessionEntity sessionEntityToUpdate;
    private List<Long> tagIds;
    private List<TagEntity> tagEntities;
    
    
    
    public UpdateSessionManagedBean() 
    {        
    }
    
    
    
    @PostConstruct
    public void postConstruct()
    {
        sessionIdToUpdate = (Long)FacesContext.getCurrentInstance().getExternalContext().getFlash().get("sessionIdToUpdate");
        
        try
        {
            if(sessionIdToUpdate != null)
            {
                sessionEntityToUpdate = sessionEntitySessionBean.retrieveSessionBySessionId(sessionIdToUpdate);
                tagIds = new ArrayList<>();

                for(TagEntity tagEntity:sessionEntityToUpdate.getClassEntity().getTagEntities())
                {
                    tagIds.add(tagEntity.getTagId());
                }

                tagEntities = tagEntitySessionBean.retrieveAllTags();
            }
            else
            {
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN, "No product has been selected", null));
            }
        }
        catch(SessionNotFoundException ex)
        {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "An error has occurred while retrieving the product details: " + ex.getMessage(), null));
        }
        catch(Exception ex)
        {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "An unexpected error has occurred: " + ex.getMessage(), null));
        }
    }
    
    
    
    public void back(ActionEvent event) throws IOException
    {
        FacesContext.getCurrentInstance().getExternalContext().getFlash().put("sessionIdToView", sessionIdToUpdate);
        FacesContext.getCurrentInstance().getExternalContext().redirect("viewClasses.xhtml");
    }
    
    
    
    public void foo()
    {        
    }
    
    
    
    public void updateProduct()
    {
                  
        
        try
        {
            sessionEntitySessionBean.updateSession(sessionEntityToUpdate, tagIds);

            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Product updated successfully", null));
        }
        catch(SessionNotFoundException ex)
        {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "An error has occurred while updating product: " + ex.getMessage(), null));
        }
        catch(Exception ex)
        {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "An unexpected error has occurred: " + ex.getMessage(), null));
        }
    }
    
    
    public Long getSessionIdToUpdate() {
        return sessionIdToUpdate;
    }

    public void setSessionIdToUpdate(Long sessionIdToUpdate) {
        this.sessionIdToUpdate = sessionIdToUpdate;
    }

    public SessionEntity getSessionEntityToUpdate() {
        return sessionEntityToUpdate;
    }

    public void setSessionEntityToUpdate(SessionEntity sessionEntityToUpdate) {
        this.sessionEntityToUpdate = sessionEntityToUpdate;
    }
   

    public List<Long> getTagIds() {
        return tagIds;
    }

    public void setTagIds(List<Long> tagIds) {
        this.tagIds = tagIds;
    }

   

    public List<TagEntity> getTagEntities() {
        return tagEntities;
    }

    public void setTagEntities(List<TagEntity> tagEntities) {
        this.tagEntities = tagEntities;
    }
}
