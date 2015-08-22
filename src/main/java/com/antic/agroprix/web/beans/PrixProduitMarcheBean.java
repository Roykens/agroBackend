/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.antic.agroprix.web.beans;

import com.royken.antic.agroprix.entities.PrixProduitMarche;
import com.royken.antic.agroprix.entities.EtatPrix;
import com.royken.antic.agroprix.entities.Marche;
import com.royken.antic.agroprix.entities.Produit;
import com.royken.antic.agroprix.service.IPrixService;
import com.royken.antic.agroprix.service.IMarcheService;
import com.royken.antic.agroprix.service.IProduitService;
import com.royken.antic.agroprix.service.ServiceException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;

/**
 *
 * @author root
 */
@ManagedBean
@SessionScoped
public class PrixProduitMarcheBean {

    @EJB
    IMarcheService marcheService;
    @EJB
    IPrixService prixProduitMarcheService;
    @EJB
    IProduitService produitService;
    PrixProduitMarche prixProduitMarche = new PrixProduitMarche();
    List<PrixProduitMarche> prixProduitMarches;
    List<Produit> produits = new ArrayList<Produit>();
    Long produitId = null;
    List<Marche> marches = new ArrayList<Marche>();
    Long marcheId = null;
    Long marchesId = null;
    List<EtatPrix> etatPrixs;
    Long produitsId = null;

    public PrixProduitMarcheBean() {
        prixProduitMarche.setDatePrix(new Date());
    }

    public void ajouterOuMettreajourPrixProduitMarche(Long id) throws ServiceException {
        marcheId = id;
        if (prixProduitMarche != null) {
            if (produitId != null) {
                Produit produit = produitService.findProduitById(produitId);
                prixProduitMarche.setProduit(produit);
                marchesId = marcheId;
            }
            if (marcheId != null) {
                Marche marche = marcheService.findMarcheById(marcheId);
                prixProduitMarche.setMarche(marche);
                produitsId = produitId;
            }
            System.out.println("------------------------------------------" + prixProduitMarche);
            if (prixProduitMarche.getId() == null && prixProduitMarche.getPrix() != null && prixProduitMarche.getPrix() != 0) {
                prixProduitMarche = prixProduitMarcheService.saveOrUpdatePrix(prixProduitMarche);
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "opération reussie", prixProduitMarche.getPrix() + " a été ajouté "));
            }
        }
        produitId = null;
        marcheId = null;
        prixProduitMarche = new PrixProduitMarche();
    }

    public void ajouterOuMettreajourPrixProduitMarche(ActionEvent event) throws ServiceException {

        if (prixProduitMarche != null) {
            if (produitId != null) {
                Produit produit = produitService.findProduitById(produitId);
                prixProduitMarche.setProduit(produit);
                marchesId = marcheId;
            }
            if (marcheId != null) {
                Marche marche = marcheService.findMarcheById(marcheId);
                prixProduitMarche.setMarche(marche);
                produitsId = produitId;
            }
            System.out.println("------------------------------------------" + prixProduitMarche);
            if (prixProduitMarche.getId() == null && prixProduitMarche.getPrix() != null && prixProduitMarche.getPrix() != 0) {
                prixProduitMarche = prixProduitMarcheService.saveOrUpdatePrix(prixProduitMarche);
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "opération reussie", prixProduitMarche.getPrix() + " a été ajouté "));
            } else if (prixProduitMarche.getId() != null && prixProduitMarche.getPrix() != null && prixProduitMarche.getPrix() != 0) {
                prixProduitMarche = prixProduitMarcheService.saveOrUpdatePrix(prixProduitMarche);
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "opération reussie", prixProduitMarche.getPrix() + " a été mis à jour "));
            } else {
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Echec", " l'operation à échouer "));
            }
        }
        produitId = null;
        marcheId = null;
        prixProduitMarche = new PrixProduitMarche();
    }

    public String afficheNomProduit(Produit produit) {
        if (produit != null) {
            return produit.getNom();
        } else {
            return "";
        }

    }

//    public void supprimerPrixProduitMarche(ActionEvent event) throws ServiceException {
//        if (prixProduitMarche != null && prixProduitMarche.getNom() != null && prixProduitMarche.getId() != null) {
//            prixProduitMarcheService.deletePrixProduitMarche(prixProduitMarche.getId());
//            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "opération reussie", prixProduitMarche.getNom() + " a été supprimé "));
//        }
//        prixProduitMarche = new PrixProduitMarche();
//        System.out.println("" + prixProduitMarche);
//    }
    public void annuler(ActionEvent event) throws ServiceException {
        prixProduitMarche = new PrixProduitMarche();
    }

    public IMarcheService getMarcheService() {
        return marcheService;
    }

    public void setMarcheService(IMarcheService marcheService) {
        this.marcheService = marcheService;
    }

    public IPrixService getPrixProduitMarcheService() {
        return prixProduitMarcheService;
    }

    public void handleMarchChange() throws ServiceException {
        if (marcheId != null) {
            produits = produitService.findProduitByMarche(marcheId);
        }
    }

    public void setPrixProduitMarcheService(IPrixService prixProduitMarcheService) {
        this.prixProduitMarcheService = prixProduitMarcheService;
    }

    public IProduitService getProduitService() {
        return produitService;
    }

    public void setProduitService(IProduitService produitService) {
        this.produitService = produitService;
    }

    public PrixProduitMarche getPrixProduitMarche() {
        prixProduitMarche.setDatePrix(new Date());
        return prixProduitMarche;
    }

    public void setPrixProduitMarche(PrixProduitMarche prixProduitMarche) {
        this.prixProduitMarche = prixProduitMarche;
    }

    public List<PrixProduitMarche> getPrixProduitMarches() throws ServiceException {
        prixProduitMarches = prixProduitMarcheService.findAll();
        if (marchesId != null) {
            prixProduitMarches = prixProduitMarcheService.findByMarche(marchesId);
        }
//        if (marchesId != null && produitsId != null) {
//            prixProduitMarches = prixProduitMarcheService.findByMarche(marcheId, produitId);
//        }
        return prixProduitMarches;
    }

    public void setPrixProduitMarches(List<PrixProduitMarche> prixProduitMarches) {
        this.prixProduitMarches = prixProduitMarches;
    }

    public List<Produit> getProduits() throws ServiceException {
//        produits = produitService.findAllProduit();
//        if (idMarche != null) {            
//            produits = produitService.findProduitByMarche(idMarche);
//        }
        return produits;
    }

    public void setProduits(List<Produit> produits) {
        this.produits = produits;
    }

    public List<Marche> getMarches() throws ServiceException {
        marches = marcheService.findAllMarche();
        return marches;
    }

    public void setMarches(List<Marche> marches) {
        this.marches = marches;
    }

    public List<EtatPrix> getEtatPrixs() {
        etatPrixs = new ArrayList<EtatPrix>();
        etatPrixs.add(EtatPrix.Hausse);
        etatPrixs.add(EtatPrix.Stable);
        etatPrixs.add(EtatPrix.Baisse);
        return etatPrixs;
    }

    public void setEtatPrixs(List<EtatPrix> etatPrixs) {
        this.etatPrixs = etatPrixs;
    }

    public Long getProduitId() {
        if (prixProduitMarche != null && prixProduitMarche.getId() != null && prixProduitMarche.getProduit() != null) {
            produitId = prixProduitMarche.getProduit().getId();
        }
        return produitId;
    }

    public void setProduitId(Long produitId) {
        this.produitId = produitId;
    }

    public Long getMarcheId() {
        if (prixProduitMarche != null && prixProduitMarche.getId() != null && prixProduitMarche.getMarche() != null) {
            marcheId = prixProduitMarche.getMarche().getId();
        }
        return marcheId;
    }

    public void setMarcheId(Long marcheId) {
        this.marcheId = marcheId;
    }

    public Long getMarchesId() {
        return marchesId;
    }

    public void setMarchesId(Long marchesId) {
        this.marchesId = marchesId;
    }

    public Long getProduitsId() {
        return produitsId;
    }

    public void setProduitsId(Long produitsId) {
        this.produitsId = produitsId;
    }

}
