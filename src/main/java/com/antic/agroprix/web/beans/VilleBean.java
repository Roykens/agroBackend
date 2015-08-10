/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.antic.agroprix.web.beans;

import com.royken.antic.agroprix.entities.Ville;
import com.royken.antic.agroprix.service.IVilleService;
import com.royken.antic.agroprix.service.ServiceException;
import java.util.List;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;

/**
 *
 * @author root
 */
@ManagedBean
@RequestScoped
public class VilleBean {

    @EJB
    IVilleService villeService;
    Ville ville = new Ville();
    List<Ville> villes;

    public VilleBean() {

    }

    public void ajouterOuMettreajourVille(ActionEvent event) throws ServiceException {        
        if (ville != null && ville.getId() == null && ville.getNom() != null && ville.getNom().length() != 0) {
            ville = villeService.saveOrUpdateVille(ville);
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "opération reussie", ville.getNom() + " a été ajouté "));
        } else if (ville != null && ville.getId() != null && ville.getNom() != null && ville.getNom().length() != 0) {
            ville = villeService.saveOrUpdateVille(ville);
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "opération reussie", ville.getNom() + " a été mis à jour "));
        } else {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Echec", " l'operation à échouer "));
        }
        ville = new Ville();
    }

    public void supprimerVille(ActionEvent event) throws ServiceException {        
        if (ville != null && ville.getNom() != null && ville.getId() != null) {            
            villeService.deleteVille(ville.getId());
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "opération reussie", ville.getNom() + " a été supprimé "));
            ville = new Ville();
        }
    }

    public void annuler(ActionEvent event) throws ServiceException {
        ville = new Ville();
    }

    public IVilleService getVilleService() {
        return villeService;
    }

    public void setVilleService(IVilleService villeService) {
        this.villeService = villeService;
    }

    public Ville getVille() {
        return ville;
    }

    public void setVille(Ville ville) {
        this.ville = ville;
    }

    public List<Ville> getVilles() throws ServiceException {
        villes = villeService.findAllVille();
        return villes;
    }

    public void setVilles(List<Ville> villes) {
        this.villes = villes;
    }

}
