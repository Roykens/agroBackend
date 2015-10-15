/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.antic.agroprix.web.beans;

import com.royken.antic.agroprix.entities.Categorie;
import com.royken.antic.agroprix.service.ICategorieService;
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
public class CategorieBean {

    @EJB
    ICategorieService categorieService;
    Categorie categorie = new Categorie();
    List<Categorie> categories;

    public CategorieBean() {

    }

    public void ajouterOuMettreajourCategorie() throws ServiceException {
        if (categorie != null && categorie.getNom() != null && categorie.getNom().length() != 0) {
            categorie = categorieService.saveOrUpdateCategorie(categorie);
        }
        categorie = new Categorie();
    }

    public void supprimerCategorie() throws ServiceException {
        if (categorie != null && categorie.getNom() != null && categorie.getId() != null) {
            categorieService.deleteCategorie(categorie.getId());
        }
        categorie = new Categorie();
    }

    public void annuler(ActionEvent event) throws ServiceException {
        categorie = new Categorie();
    }

    public ICategorieService getCategorieService() {
        return categorieService;
    }

    public void setCategorieService(ICategorieService categorieService) {
        this.categorieService = categorieService;
    }

    public Categorie getCategorie() {
        return categorie;
    }

    public void setCategorie(Categorie categorie) {
        this.categorie = categorie;
    }

    public List<Categorie> getCategories() throws ServiceException {
        categories = categorieService.findAllCategorie();
        return categories;
    }

    public void setCategories(List<Categorie> categories) {
        this.categories = categories;
    }

}
