/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.antic.agroprix.web.beans;

import com.royken.antic.agroprix.entities.Avis;
import com.royken.antic.agroprix.entities.Ville;
import com.royken.antic.agroprix.service.IAvisService;
import com.royken.antic.agroprix.service.IVilleService;
import com.royken.antic.agroprix.service.ServiceException;
import java.util.Date;
import java.util.List;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;

/**
 *
 * @author dorimene
 */
@ManagedBean
@RequestScoped
public class AvisBean {

    @EJB
    IAvisService avisService;
    @EJB
    IVilleService villeService;
    Avis avis = new Avis();
    List<Avis> aviss;

    public AvisBean() {

    }

    public void ajouterOuMettreajourAvis() throws ServiceException {
        if (avis != null && avis.getAvis() != null && avis.getAvis().length() != 0 && avis.getNom() != null && avis.getNom().length() != 0) {
            avis.setDateAvis(new Date());
            avis = avisService.saveOrUpdateAvis(avis);
        }
        avis = new Avis();
    }

    public String afficherVille(String ville) {
        if (ville != null && ville.length() != 0) {
            return "de " + ville;
        }
        return "";
    }

    public void supprimerAvis() throws ServiceException {
        if (avis != null && avis.getNom() != null && avis.getId() != null) {

            //avisService.d(avis.getId());
        }
        avis = new Avis();
    }

    public void annuler() throws ServiceException {
        avis = new Avis();
    }

    public IAvisService getAvisService() {
        return avisService;
    }

    public void setAvisService(IAvisService avisService) {
        this.avisService = avisService;
    }

    public Avis getAvis() {
        return avis;
    }

    public void setAvis(Avis avis) {
        this.avis = avis;
    }

    public List<Avis> getAviss() throws ServiceException {
        aviss = avisService.findAll();
        return aviss;
    }

    public void setAviss(List<Avis> aviss) {
        this.aviss = aviss;
    }

    public IVilleService getVilleService() {
        return villeService;
    }

    public void setVilleService(IVilleService villeService) {
        this.villeService = villeService;
    }

}
