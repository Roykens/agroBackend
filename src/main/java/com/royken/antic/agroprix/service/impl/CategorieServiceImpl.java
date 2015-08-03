package com.royken.antic.agroprix.service.impl;

import com.royken.antic.agroprix.entities.Categorie;
import com.royken.antic.agroprix.dao.ICategorieDao;
import com.royken.antic.agroprix.entities.Produit;
import com.royken.antic.agroprix.service.ICategorieService;
import com.royken.antic.agroprix.service.ServiceException;
import com.royken.generic.dao.DataAccessException;
import java.util.Collections;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.inject.Named;

/**
 *
 * @author Kenfack Valmy-Roi <roykenvalmy@gmail.com>
 */
@Stateless
@Named
public class CategorieServiceImpl implements ICategorieService {

    @Inject
    private ICategorieDao categorieDao;

    public ICategorieDao getCategorieDao() {
        return categorieDao;
    }

    public void setCategorieDao(ICategorieDao categorieDao) {
        this.categorieDao = categorieDao;
    }
    
    

    @Override
    public Categorie saveOrUpdateCategorie(Categorie categorie) throws ServiceException {
        try {
            if (categorie.getId() == null) {
                return categorieDao.create(categorie);
            } else {
                return categorieDao.update(categorie);
            }
        } catch (DataAccessException ex) {
            Logger.getLogger(CategorieServiceImpl.class.getName()).log(Level.SEVERE, null, ex);
            throw new ServiceException(ex.getMessage(), ex);
        }

    }

    @Override
    public Categorie findCategorieById(Long id) throws ServiceException {
        try {
            return categorieDao.findById(id);
        } catch (DataAccessException ex) {
            Logger.getLogger(CategorieServiceImpl.class.getName()).log(Level.SEVERE, null, ex);
            throw new ServiceException(ex.getMessage(), ex);
        }
    }

    @Override
    public void deleteCategorie(Long id) throws ServiceException {
        try {
            Categorie categorie = categorieDao.findById(id);
            if(categorie != null){
                categorieDao.delete(categorie);
            }
            else{
                throw new ServiceException("La categorie est introuvable");
            }
        } catch (DataAccessException ex) {
            Logger.getLogger(CategorieServiceImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public List<Categorie> findAllCategorie() throws ServiceException {
        try {
            return categorieDao.findAll();
        } catch (DataAccessException ex) {
            Logger.getLogger(CategorieServiceImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
         return Collections.EMPTY_LIST;
    }

}
