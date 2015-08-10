/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.antic.agroprix.web.beans;

import com.royken.antic.agroprix.entities.Produit;
import com.royken.antic.agroprix.service.IProduitService;
import com.royken.antic.agroprix.service.ServiceException;
import java.util.List;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;

/**
 *
 * @author root
 */
@ManagedBean
@RequestScoped
public class ProduitBean {

    @EJB
    IProduitService produitService;
    Produit produit;
    List<Produit> produits;

    public ProduitBean() {
        produit = new Produit();
    }

    public void ajouterProduit() throws ServiceException {
        if (produit != null && produit.getNom().length() != 0) {
            produit = produitService.saveOrUpdateProduit(produit);
            produit = new Produit();
        }        
    }

    public void mettreajourProduit() throws ServiceException {
        if (produit != null && produit.getId() != 0) {
            produit = produitService.saveOrUpdateProduit(produit);
            produit = new Produit();
        }        
    }

    public void supprimerProduit() throws ServiceException {
        if (produit != null && produit.getId() != 0) {
            produitService.deleteProduit(produit.getId());
            produit = new Produit();
        }               
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

}
