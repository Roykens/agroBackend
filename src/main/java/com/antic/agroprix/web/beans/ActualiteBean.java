package com.antic.agroprix.web.beans;

import com.royken.antic.agroprix.entities.Actualite;
import com.royken.antic.agroprix.service.IActualiteService;
import com.royken.antic.agroprix.service.ServiceException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;
import org.apache.commons.io.IOUtils;
import org.primefaces.model.UploadedFile;

/**
 *
 * @author Kenfack Valmy-Roi <roykenvalmy@gmail.com>
 */
@ManagedBean
@RequestScoped
public class ActualiteBean {

    @EJB
    private IActualiteService actualiteService;

    private Actualite actualite;

    private List<Actualite> actualites;
    
    private UploadedFile image;

    /**
     * Creates a new instance of ActualiteBean
     */
    public ActualiteBean() {
        actualite = new Actualite();
        actualites = new ArrayList<Actualite>();
    }

    public IActualiteService getActualiteService() {
        return actualiteService;
    }

    public void setActualiteService(IActualiteService actualiteService) {
        this.actualiteService = actualiteService;
    }

    public Actualite getActualite() {
        return actualite;
    }
    
    

    public void setActualite(Actualite actualite) {
        this.actualite = actualite;
    }

    public UploadedFile getImage() {
        return image;
    }

    public void setImage(UploadedFile image) {
        this.image = image;
    }
    
    

    public List<Actualite> getActualites() {
        try {
            actualites = actualiteService.findAllActivite();
            return actualites;
        } catch (ServiceException ex) {
            Logger.getLogger(ActualiteBean.class.getName()).log(Level.SEVERE, null, ex);
        }
        return Collections.EMPTY_LIST;
    }

    public void setActualites(List<Actualite> actualites) {
        this.actualites = actualites;
    }

    public void saveOrUpdateActualite() throws IOException {
        try {
            if (actualite != null && actualite.getId() == null) {
                 System.out.println("L'image\n");
                System.out.println(image);
                byte[] img = image.getContents();
                System.out.println("Le contenu");
                System.out.println(img);
                //byte[] img = IOUtils.toByteArray(image.getInputstream());
                //actualiteService.saveOrUpdateActuaite(actualite);
               
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Opération réussie", " L'info a été ajoutée "));
            }
            if(actualite != null && actualite.getId() != null){
                actualiteService.saveOrUpdateActuaite(actualite);
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Opération réussie", " L'info a été modifiée "));
            }
            else{
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Echec", " l'operation à échouer "));
            }
            actualite = new Actualite();
        } catch (ServiceException ex) {
            Logger.getLogger(ActualiteBean.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    public void deleteActualite() {

    }

}
