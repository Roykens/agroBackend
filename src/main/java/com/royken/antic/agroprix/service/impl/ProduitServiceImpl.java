package com.royken.antic.agroprix.service.impl;

import com.royken.antic.agroprix.dao.ICategorieDao;
import com.royken.antic.agroprix.entities.Produit;
import com.royken.antic.agroprix.dao.IProduitDao;
import com.royken.antic.agroprix.entities.Categorie;
import com.royken.antic.agroprix.entities.Marche;
import com.royken.antic.agroprix.service.IProduitService;
import com.royken.antic.agroprix.service.ServiceException;
import com.royken.generic.dao.DataAccessException;
import java.util.Collections;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.Stateless;
import javax.inject.Inject;

/**
 *
 * @author Kenfack Valmy-Roi <roykenvalmy@gmail.com>
 */
@Stateless
public class ProduitServiceImpl implements IProduitService {

    @Inject
    private IProduitDao produitDao;
    
    @Inject
    private ICategorieDao categorieDao;

    public IProduitDao getProduitDao() {
        return produitDao;
    }

    public void setProduitDao(IProduitDao produitDao) {
        this.produitDao = produitDao;
    }

    public ICategorieDao getCategorieDao() {
        return categorieDao;
    }

    public void setCategorieDao(ICategorieDao categorieDao) {
        this.categorieDao = categorieDao;
    }
    
    

    @Override
    public Produit saveOrUpdateProduit(Produit produit) throws ServiceException {
        try {
            if (produit.getId() == null) {
                return produitDao.create(produit);
            }
            else{
                return produitDao.update(produit);
            }
        } catch (DataAccessException ex) {
            Logger.getLogger(ProduitServiceImpl.class.getName()).log(Level.SEVERE, null, ex);
         throw new ServiceException(ex.getMessage(), ex);
        }

    }

    @Override
    public Produit findProduitById(Long id) throws ServiceException {
        try {
            return produitDao.findById(id);
        } catch (DataAccessException ex) {
            Logger.getLogger(ProduitServiceImpl.class.getName()).log(Level.SEVERE, null, ex);
            throw new ServiceException(ex.getMessage(), ex);
        }
    }

    @Override
    public void deleteProduit(Long id) throws ServiceException {
        try {
            Produit produit = produitDao.findById(id);
            if(produit != null){
                produitDao.delete(produit);
            }
            else{
                throw new ServiceException("Le produit est introuvable");
            }
        } catch (DataAccessException ex) {
            Logger.getLogger(ProduitServiceImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public List<Produit> findAllProduit() throws ServiceException {
        try {
            return produitDao.findAll();
        } catch (DataAccessException ex) {
            Logger.getLogger(ProduitServiceImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
        return Collections.EMPTY_LIST;
    }

    @Override
    public List<Produit> findByCategorie(Long idCategorie) throws ServiceException {
        try {
            Categorie categorie = categorieDao.findById(idCategorie);
            return produitDao.findByCategorie(categorie);
        } catch (DataAccessException ex) {
            Logger.getLogger(ProduitServiceImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
        return Collections.EMPTY_LIST;
    }

    @Override
    public List<Produit> findProduitByMarche(Marche marche) throws ServiceException {
        try {
            return produitDao.findByMarche(marche);
        } catch (DataAccessException ex) {
            Logger.getLogger(ProduitServiceImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
        return Collections.EMPTY_LIST;
    }

}
