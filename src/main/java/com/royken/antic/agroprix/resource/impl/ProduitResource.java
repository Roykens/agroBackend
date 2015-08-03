package com.royken.antic.agroprix.resource.impl;

import com.royken.antic.agroprix.entities.Produit;
import com.royken.antic.agroprix.resource.IProduitResource;
import com.royken.antic.agroprix.service.ICategorieService;
import com.royken.antic.agroprix.service.IProduitService;
import com.royken.antic.agroprix.service.ServiceException;
import java.util.Collections;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.EJB;
import javax.ws.rs.Path;

/**
 *
 * @author Kenfack Valmy-Roi <roykenvalmy@gmail.com>
 */
@Path("/produits")
public class ProduitResource implements IProduitResource{
    
    @EJB
    private IProduitService produitService;
    
    @EJB
    private ICategorieService categorieService;

    public IProduitService getProduitService() {
        return produitService;
    }

    public void setProduitService(IProduitService produitService) {
        this.produitService = produitService;
    }

    public ICategorieService getCategorieService() {
        return categorieService;
    }

    public void setCategorieService(ICategorieService categorieService) {
        this.categorieService = categorieService;
    }
    
    
    
    

    @Override
    public Produit createProduit(Produit produit) {
        try {
            return produitService.saveOrUpdateProduit(produit);
        } catch (ServiceException ex) {
            Logger.getLogger(ProduitResource.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    @Override
    public List<Produit> getAllProduits() {
        try {
            return produitService.findAllProduit();
        } catch (ServiceException ex) {
            Logger.getLogger(ProduitResource.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    @Override
    public Produit getProduit(long id) {
        try {
            return produitService.findProduitById(id);
        } catch (ServiceException ex) {
            Logger.getLogger(ProduitResource.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    @Override
    public Produit updateProduit(long id, Produit produit) {
        try {
            Produit produit1 = produitService.findProduitById(id);
            if(produit1 != null){
                produit1.setConditionnement(produit.getConditionnement());
                produit1.setInfo(produit.getInfo());
                produit1.setNom(produit.getNom());
                return produitService.saveOrUpdateProduit(produit1);                
            }
        } catch (ServiceException ex) {
            Logger.getLogger(ProduitResource.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    @Override
    public void deleteProduit(long id) {
        try {
            produitService.deleteProduit(id);
        } catch (ServiceException ex) {
            Logger.getLogger(ProduitResource.class.getName()).log(Level.SEVERE, null, ex);
        }
      
    }


    
}
