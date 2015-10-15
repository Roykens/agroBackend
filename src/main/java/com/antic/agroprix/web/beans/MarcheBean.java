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
    Long id = null;

    public MarcheBean() {

    }

    public void ajouterOuMettreajourMarche() throws ServiceException {
//        System.out.println(" " + marche); 
        if (marche != null && id != null) {
            Ville ville = villeService.findVilleById(id);
            marche.setVille(ville);
            if (marche.getNom() != null && marche.getNom().length() != 0) {
                marche = marcheService.saveOrUpdateMarche(marche);
            }
        }
        marche = new Marche();
        id = null;
    }

    public String afficheNomVille(Ville ville) {
        if (ville != null) {
            return ville.getNom();
        } else {
            return "";
        }

    }

    public void supprimerMarche() throws ServiceException {
        if (marche != null && marche.getNom() != null && marche.getId() != null) {
            marcheService.deleteMarche(marche.getId());
        }
        marche = new Marche();        
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
