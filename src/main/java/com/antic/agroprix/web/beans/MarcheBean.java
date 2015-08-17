/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.antic.agroprix.web.beans;

import com.royken.antic.agroprix.entities.Marche;
import com.royken.antic.agroprix.entities.Ville;
import com.royken.antic.agroprix.service.IMarcheService;
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
public class MarcheBean {

    @EJB
    IVilleService villeService;
    @EJB
    IMarcheService marcheService;
    Marche marche = new Marche();
    List<Marche> marches;
    List<Ville> villes;
    Long id = 0L;

    public MarcheBean() {

    }

    public void ajouterOuMettreajourMarche(ActionEvent event) throws ServiceException {
//        System.out.println(" " + marche); 
        if (marche != null && id != 0L) {
            Ville ville = villeService.findVilleById(id);
            marche.setVille(ville);
            if (marche.getId() == null && marche.getNom() != null && marche.getNom().length() != 0) {
                marche = marcheService.saveOrUpdateMarche(marche);
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "opération reussie", marche.getNom() + " a été ajouté "));
            } else if (marche.getId() != null && marche.getNom() != null && marche.getNom().length() != 0) {
                marche = marcheService.saveOrUpdateMarche(marche);
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "opération reussie", marche.getNom() + " a été mis à jour "));
            } else {
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Echec", " l'operation à échouer "));
            }
        }
        marche = new Marche();
    }

    public String afficheNomVille(Ville ville) {
        if (ville != null) {
            return ville.getNom();
        } else {
            return "";
        }

    }

    public void supprimerMarche(ActionEvent event) throws ServiceException {
        if (marche != null && marche.getNom() != null && marche.getId() != null) {
            marcheService.deleteMarche(marche.getId());
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "opération reussie", marche.getNom() + " a été supprimé "));
        }
        marche = new Marche();
        System.out.println("" + marche);
    }

    public void annuler(ActionEvent event) throws ServiceException {
        marche = new Marche();
    }

    public IMarcheService getMarcheService() {
        return marcheService;
    }

    public void setMarcheService(IMarcheService marcheService) {
        this.marcheService = marcheService;
    }

    public Marche getMarche() {
        return marche;
    }

    public void setMarche(Marche marche) {
        this.marche = marche;
    }

    public List<Marche> getMarches() throws ServiceException {
        marches = marcheService.findAllMarche();
        return marches;
    }

    public void setMarches(List<Marche> marches) {
        this.marches = marches;
    }

    public List<Ville> getVilles() throws ServiceException {
        villes = villeService.findAllVille();
        return villes;
    }

    public void setVilles(List<Ville> villes) {
        this.villes = villes;
    }

    public Long getId() {
        if (marche != null && marche.getVille() != null) {
            id = marche.getVille().getId();
        }
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

}
