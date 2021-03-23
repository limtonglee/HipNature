package jsf.managedbean;

import ejb.stateless.SessionEntitySessionBeanLocal;
import entity.SessionEntity;
import java.io.Serializable;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.inject.Named;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;



@Named(value = "searchSessionsByNameManagedBean")
@ViewScoped
public class SearchSessionsByNameManagedBean implements Serializable
{

    @EJB
    private SessionEntitySessionBeanLocal sessionEntitySessionBean;
    
    private String searchString;
    private List<SessionEntity> sessionEntities;
    
    
    
    public SearchSessionsByNameManagedBean() 
    {
    }
    
    
    
    @PostConstruct
    public void postConstruct()
    {
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
}
