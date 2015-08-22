/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.antic.agroprix.web.beans;

import com.royken.antic.agroprix.entities.Categorie;
import com.royken.antic.agroprix.entities.Marche;
import com.royken.antic.agroprix.entities.PrixProduitMarche;
import com.royken.antic.agroprix.entities.Produit;
import com.royken.antic.agroprix.entities.Ville;
import com.royken.antic.agroprix.service.ICategorieService;
import com.royken.antic.agroprix.service.IMarcheService;
import com.royken.antic.agroprix.service.IPrixService;
import com.royken.antic.agroprix.service.IProduitService;
import com.royken.antic.agroprix.service.IVilleService;
import com.royken.antic.agroprix.service.ServiceException;
import java.util.ArrayList;
import java.util.List;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.event.ActionEvent;

/**
 *
 * @author root
 */
@ManagedBean
@RequestScoped
public class PrixDesProduitBean {

    @EJB
    IVilleService villeService;
    @EJB
    IMarcheService marcheService;
    @EJB
    ICategorieService categorieService;
    @EJB
    IProduitService produitService;
    @EJB
    IPrixService prixService;
    Long villeId = null;
    Long marcheId = null;
    Long categorieId = null;
    Long produitId = null;
    List<Produit> produits;
    List<Marche> marches;
    List<Ville> villes;
    List<Categorie> categories;
    List<PrixProduitMarche> prixProduitMarches;

    public void handleVilleChange(ActionEvent event) throws ServiceException {
        marches = marcheService.findMarcheByVille(villeId);        
        produits = new ArrayList<Produit>();
        prixProduitMarches = new ArrayList<PrixProduitMarche>();
        System.out.println(""+marches);
    }

    public void handleMarcheChange() throws ServiceException {
        Marche marche = marcheService.findMarcheById(marcheId);
        produits = produitService.findProduitByMarche(marche);
    }

    public void handleCategorieChange() throws ServiceException {
        produits = produitService.findByCategorie(categorieId);
    }

    public void voirPrixProduit() throws ServiceException {
        //prixProduitMarches = prixService.findByMarche(marcheId, produitId);
    }

    public IVilleService getVilleService() {
        return villeService;
    }

    public void setVilleService(IVilleService villeService) {
        this.villeService = villeService;
    }

    public IMarcheService getMarcheService() {
        return marcheService;
    }

    public void setMarcheService(IMarcheService marcheService) {
        this.marcheService = marcheService;
    }

    public ICategorieService getCategorieService() {
        return categorieService;
    }

    public void setCategorieService(ICategorieService categorieService) {
        this.categorieService = categorieService;
    }

    public IProduitService getProduitService() {
        return produitService;
    }

    public void setProduitService(IProduitService produitService) {
        this.produitService = produitService;
    }

    public List<Produit> getProduits() {
        return produits;
    }

    public void setProduits(List<Produit> produits) {
        this.produits = produits;
    }

    public List<Marche> getMarches() {
        return marches;
    }

    public void setMarches(List<Marche> marches) {
        this.marches = marches;
    }

    public List<Ville> getVilles() {
        return villes;
    }

    public void setVilles(List<Ville> villes) {
        this.villes = villes;
    }

    public List<Categorie> getCategories() {
        return categories;
    }

    public void setCategories(List<Categorie> categories) {
        this.categories = categories;
    }

    public IPrixService getPrixService() {
        return prixService;
    }

    public void setPrixService(IPrixService prixService) {
        this.prixService = prixService;
    }

    public Long getVilleId() {
        return villeId;
    }

    public void setVilleId(Long villeId) {
        this.villeId = villeId;
    }

    public Long getMarcheId() {
        return marcheId;
    }

    public void setMarcheId(Long marcheId) {
        this.marcheId = marcheId;
    }

    public Long getCategorieId() {
        return categorieId;
    }

    public void setCategorieId(Long categorieId) {
        this.categorieId = categorieId;
    }

    public Long getProduitId() {
        return produitId;
    }

    public void setProduitId(Long produitId) {
        this.produitId = produitId;
    }

    public List<PrixProduitMarche> getPrixProduitMarches() {
        return prixProduitMarches;
    }

    public void setPrixProduitMarches(List<PrixProduitMarche> prixProduitMarches) {
        this.prixProduitMarches = prixProduitMarches;
    }

}
