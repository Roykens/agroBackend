/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.antic.agroprix.web.beans;

import com.royken.antic.agroprix.entities.Produit;
import com.royken.antic.agroprix.entities.Categorie;
import com.royken.antic.agroprix.entities.Marche;
import com.royken.antic.agroprix.service.IProduitService;
import com.royken.antic.agroprix.service.ICategorieService;
import com.royken.antic.agroprix.service.IMarcheService;
import com.royken.antic.agroprix.service.ServiceException;
import java.util.ArrayList;
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
public class ProduitBean {

    @EJB
    IMarcheService marcheService;
    @EJB
    ICategorieService categorieService;
    @EJB
    IProduitService produitService;
    Produit produit = new Produit();
    List<Produit> produits;
    List<Categorie> categories;
    List<Marche> marches = new ArrayList<Marche>();
    List<Marche> marchesProduit = new ArrayList<Marche>();
    Long id = null;
    private Long[] idMarches = new Long[1000];

    public ProduitBean() {

    }

    public void ajouterOuMettreajourProduit() throws ServiceException {
//        System.out.println(" " + produit); 
        if (produit != null && id != null) {
            List<Marche> marcheSelectionner = new ArrayList<Marche>();
            Categorie categorie = categorieService.findCategorieById(id);
            produit.setCategorie(categorie);
            for (Long idM : idMarches) {
                if (idM != 0L) {
                    Marche m = marcheService.findMarcheById(idM);
                    marcheSelectionner.add(m);
                }

            }

            produit.setMarches(marcheSelectionner);
            if (produit.getNom() != null && produit.getNom().length() != 0) {
                produit = produitService.saveOrUpdateProduit(produit);
            }
        }
        id = null;
        produit = new Produit();
        idMarches = new Long[1000];
    }

    public String afficheNomCategorie(Categorie categorie) {
        if (categorie != null) {
            return categorie.getNom();
        } else {
            return "";
        }

    }

    public void supprimerProduit() throws ServiceException {
        if (produit != null && produit.getNom() != null && produit.getId() != null) {
            produitService.deleteProduit(produit.getId());
        }
        produit = new Produit();
    }

    public void annuler(ActionEvent event) throws ServiceException {
        produit = new Produit();
    }

    public IProduitService getProduitService() {
        return produitService;
    }

    public void setProduitService(IProduitService produitService) {
        this.produitService = produitService;
    }

    public Produit getProduit() {
        return produit;
    }

    public void setProduit(Produit produit) {
        this.produit = produit;
    }

    public List<Produit> getProduits() throws ServiceException {
        produits = produitService.findAllProduit();
        return produits;
    }

    public void setProduits(List<Produit> produits) {
        this.produits = produits;
    }

    public List<Categorie> getCategories() throws ServiceException {
        categories = categorieService.findAllCategorie();
        return categories;
    }

    public void setCategories(List<Categorie> categories) {
        this.categories = categories;
    }

    public Long getId() {
        if (produit != null && produit.getCategorie() != null) {
            id = produit.getCategorie().getId();
        }
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public ICategorieService getCategorieService() {
        return categorieService;
    }

    public void setCategorieService(ICategorieService categorieService) {
        this.categorieService = categorieService;
    }

    public List<Marche> getMarches() throws ServiceException {
        marches = marcheService.findAllMarche();
        return marches;
    }

    public void setMarches(List<Marche> marches) {
        this.marches = marches;
    }

    public List<Marche> getMarchesProduit() {
        if (produit != null) {
            marchesProduit = produit.getMarches();
        }
        return marchesProduit;
    }

    public void setMarchesProduit(List<Marche> marchesProduit) {
        this.marchesProduit = marchesProduit;
    }

    public IMarcheService getMarcheService() {
        return marcheService;
    }

    public void setMarcheService(IMarcheService marcheService) {
        this.marcheService = marcheService;
    }

    public Long[] getIdMarches() {
        if (produit != null && produit.getMarches() != null) {
            int i = 0;
            for (Marche next : produit.getMarches()) {
                idMarches[i] = next.getId();
                i++;
            }
        }
        return idMarches;
    }

    public void setIdMarches(Long[] idMarches) {
        this.idMarches = idMarches;
    }

}
