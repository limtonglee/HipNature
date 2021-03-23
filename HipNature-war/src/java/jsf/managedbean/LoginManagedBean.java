package jsf.managedbean;

import ejb.stateless.PartnerEntitySessionBean;
import entity.InstructorEntity;
import entity.PartnerEntity;
import java.io.IOException;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.inject.Named;
import javax.enterprise.context.RequestScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import javax.servlet.http.HttpSession;
import util.exception.InvalidLoginCredentialException;

@Named(value = "loginManagedBean")
@RequestScoped

public class LoginManagedBean {

    @EJB
    private PartnerEntitySessionBean partnerEntitySessionBean;

    private String username;
    private String password;

    private List<InstructorEntity> instructorsToDisplay;
    private PartnerEntity loggedInPartner;

    public LoginManagedBean() {
    }

    @PostConstruct
    public void PostConstruct() {

        //System.out.println("******** in login managed bean does postconstruct allow us to set logged in partner?");
        setLoggedInPartner((PartnerEntity) ((HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(true)).getAttribute("loggedInPartner"));
        //System.out.println("******** partner name is " + getLoggedInPartner().getPartnerEntityName()); // gives null pointer
        instructorsToDisplay = (List<InstructorEntity>) ((HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(true)).getAttribute("instructorsToDisplay");
    }

    public void login(ActionEvent event) throws IOException {
        try {
            loggedInPartner = partnerEntitySessionBean.partnerLogin(username, password);
            FacesContext.getCurrentInstance().getExternalContext().getSession(true);
            FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("isLogin", true);
            FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("currentPartnerEntity", loggedInPartner);
            FacesContext.getCurrentInstance().getExternalContext().redirect(FacesContext.getCurrentInstance().getExternalContext().getRequestContextPath() + "/index.xhtml");
        } catch (InvalidLoginCredentialException ex) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Invalid login credential: " + ex.getMessage(), null));
        }
    }

    public void logout(ActionEvent event) throws IOException {
        ((HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(true)).invalidate();
        FacesContext.getCurrentInstance().getExternalContext().redirect(FacesContext.getCurrentInstance().getExternalContext().getRequestContextPath() + "/index.xhtml");
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    /**
     * @return the instructorsToDisplay
     */
    public List<InstructorEntity> getInstructorsToDisplay() {
        return instructorsToDisplay;
    }

    /**
     * @param instructorsToDisplay the instructorsToDisplay to set
     */
    public void setInstructorsToDisplay(List<InstructorEntity> instructorsToDisplay) {
        this.instructorsToDisplay = instructorsToDisplay;
    }

    /**
     * @return the loggedInPartner
     */
    public PartnerEntity getLoggedInPartner() {
        return loggedInPartner;
    }

    /**
     * @param loggedInPartner the loggedInPartner to set
     */
    public void setLoggedInPartner(PartnerEntity loggedInPartner) {
        this.loggedInPartner = loggedInPartner;
    }
}
