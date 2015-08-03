package com.royken.antic.agroprix.resource.impl;

import com.royken.antic.agroprix.entities.Categorie;
import com.royken.antic.agroprix.entities.Produit;
import com.royken.antic.agroprix.resource.ICategoriesResource;
import com.royken.antic.agroprix.service.ICategorieService;
import com.royken.antic.agroprix.service.IProduitService;
import com.royken.antic.agroprix.service.ServiceException;
import java.util.Collections;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.EJB;
import javax.ws.rs.Path;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.Response;

/**
 *
 * @author Kenfack Valmy-Roi <roykenvalmy@gmail.com>
 */
@Path("/categories")
public class CategoriesResource implements ICategoriesResource{
    
    @EJB
    private ICategorieService categorieService;
    
    @EJB
    private IProduitService produitService;

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

   
    
    

    @Override
    public Categorie createCategorie(Categorie categorie) {
        try {
            return categorieService.saveOrUpdateCategorie(categorie);
        } catch (ServiceException ex) {
            Logger.getLogger(CategoriesResource.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
    }

    @Override
    public Categorie getCategorie(long id) {
        try {
            return categorieService.findCategorieById(id);
        } catch (ServiceException ex) {
            Logger.getLogger(CategoriesResource.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
    }

    @Override
    public Categorie updateCategorie(long id, Categorie categorie) {
        try {
            Categorie categorie1 = categorieService.findCategorieById(id);
            if(categorie1 != null){
                categorie1.setNom(categorie.getNom());
                return categorieService.saveOrUpdateCategorie(categorie1);
            }
            else{
                throw new WebApplicationException(Response.Status.NOT_FOUND);
            }
        } catch (ServiceException ex) {
            Logger.getLogger(CategoriesResource.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
    }

    @Override
    public List<Categorie> getAllCategorie() {
        try {
            return categorieService.findAllCategorie();
        } catch (ServiceException ex) {
            Logger.getLogger(CategoriesResource.class.getName()).log(Level.SEVERE, null, ex);
        }
        return Collections.EMPTY_LIST;
    }

    @Override
    public void deleteCategorie(long id) {
        try {
            categorieService.deleteCategorie(id);
        } catch (ServiceException ex) {
            Logger.getLogger(CategoriesResource.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public List<Produit> getAllProduit(long idCategorie) {
        try {
            return produitService.findByCategorie(idCategorie);
        } catch (ServiceException ex) {
            Logger.getLogger(CategoriesResource.class.getName()).log(Level.SEVERE, null, ex);
        }
        return Collections.EMPTY_LIST;
    }
    

 
}
