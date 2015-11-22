package com.antic.agroprix.web.beans;

import com.royken.antic.agroprix.entities.Actualite;
import com.royken.antic.agroprix.service.IActualiteService;
import com.royken.antic.agroprix.service.ServiceException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.EJB;
import javax.faces.event.ActionEvent;
import org.apache.commons.io.IOUtils;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;
import org.apache.commons.io.FilenameUtils;
import org.primefaces.model.UploadedFile;

/**
 *
 * @author dorimène
 */
@ManagedBean
@RequestScoped
public class ActualiteBean {

    @EJB
    private IActualiteService actualiteService;

    private Actualite actualite;

    private List<Actualite> actualites;

    UploadedFile file;

    private final DateFormat df = new SimpleDateFormat("dd-MM-yyyy");
    //private final String destination = new File("actualite").getAbsoluteFile().toString();

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

    public UploadedFile getFile() {
        return file;
    }

    public void setFile(UploadedFile file) {
        this.file = file;
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

    private void copyFile(UploadedFile uploadedPhoto, String filePath) {
        byte[] bytes = null;

        if (null != uploadedPhoto) {
            try {
                bytes = uploadedPhoto.getContents();
                String filename = FilenameUtils.getName(uploadedPhoto.getFileName());
                BufferedOutputStream stream = new BufferedOutputStream(new FileOutputStream(new File(filePath + filename)));
                stream.write(bytes);
                stream.close();
            } catch (IOException ex) {
                Logger.getLogger(ActualiteBean.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    String notreChemin() {
        String view = FacesContext.getCurrentInstance().getViewRoot().getViewId();
        javax.faces.application.ViewHandler vh = FacesContext.getCurrentInstance().getApplication().getViewHandler();
        String pageURI = vh.getActionURL(FacesContext.getCurrentInstance(), view);
        int index = pageURI.lastIndexOf("/") + 1;
        pageURI = pageURI.substring(0, index);
        return pageURI + "PhotoActualites/";
    }

    public void saveOrUpdateActualite() throws IOException, ServiceException {
        if (actualite != null && actualite.getInfo() != null && actualite.getInfo().length() != 0) {
            System.out.println("Le fichier = " + file);
            if (file != null) {
                System.out.println("Le nom du fichier = " + file.getFileName());
                //byte[] img = file.getContents();
                byte[] img = IOUtils.toByteArray(file.getInputstream());
                actualite.setNomImage(file.getFileName());
                
                copyFile(file, "/home/dorimene/Bureau/projet agro/agroBackend/src/main/webapp/PhotoActualites/");
                //actualite.setPhoto(img);
            }
            actualite.setDateInfo(new Date());
            actualiteService.saveOrUpdateActuaite(actualite);
        }
        actualite = new Actualite();
        file = null;
    }

    public void annuler() throws ServiceException {
        actualite = new Actualite();
    }

    public void deleteActualite(ActionEvent event) throws ServiceException {
        System.err.println("------------" + actualite.getId());
        if (actualite != null && actualite.getInfo() != null && actualite.getId() != null) {
            actualiteService.deleteActualite(actualite.getId());
        }
        actualite = new Actualite();
    }

    public String afficherDate(Date date) {
        if (date != null) {
            return "publié le " + df.format(date);
        }
        return "";
    }

    public String dateAvis(Date date) {
        if (date != null) {
            return "Le " + df.format(date);
        }
        return "";
    } 
}
