package com.royken.antic.agroprix.service.impl;

import com.royken.antic.agroprix.dao.IMarcheDao;
import com.royken.antic.agroprix.dao.IPrixProduitMarcheDao;
import com.royken.antic.agroprix.dao.IProduitDao;
import com.royken.antic.agroprix.entities.Marche;
import com.royken.antic.agroprix.entities.PrixProduitMarche;
import com.royken.antic.agroprix.entities.Produit;
import com.royken.antic.agroprix.service.IPrixService;
import com.royken.antic.agroprix.service.ServiceException;
import com.royken.generic.dao.DataAccessException;
import java.util.Collections;
import java.util.Date;
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
public class PrixServiceImpl implements IPrixService{

    @Inject
    private IPrixProduitMarcheDao prixProduitMarcheDao;
    
    @Inject
    private IMarcheDao marcheDao;
    
    @Inject
    private IProduitDao produitDao;

    public IPrixProduitMarcheDao getPrixProduitMarcheDao() {
        return prixProduitMarcheDao;
    }

    public void setPrixProduitMarcheDao(IPrixProduitMarcheDao prixProduitMarcheDao) {
        this.prixProduitMarcheDao = prixProduitMarcheDao;
    }

    public IMarcheDao getMarcheDao() {
        return marcheDao;
    }

    public void setMarcheDao(IMarcheDao marcheDao) {
        this.marcheDao = marcheDao;
    }

    public IProduitDao getProduitDao() {
        return produitDao;
    }

    public void setProduitDao(IProduitDao produitDao) {
        this.produitDao = produitDao;
    }
    
    
    
    
    
//    @Override
//    public PrixProduitMarche saveOrUpdatePrix(int prix, Long idProduit, Long idMarche, Date date) throws ServiceException {
//        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
//    }

    @Override
    public PrixProduitMarche saveOrUpdatePrix(PrixProduitMarche prix) throws ServiceException {
         try {
        if(prix.getId() == null){
           
                return prixProduitMarcheDao.create(prix);
                 }
        else{
            return prixProduitMarcheDao.update(prix);
        }
            } catch (DataAccessException ex) {
                Logger.getLogger(PrixServiceImpl.class.getName()).log(Level.SEVERE, null, ex);
            }
         return null;
       
    }

    @Override
    public PrixProduitMarche findByMarche(Long idMarche, Long idProduit) throws ServiceException {
        try {
            Marche marche = marcheDao.findById(idMarche);
            if(marche == null){
                throw new ServiceException("Service not found");
            }
            Produit produit = produitDao.findById(idProduit);
            if(produit == null){
                throw new ServiceException("Service not found");
            }
            return prixProduitMarcheDao.findPrixByMarcheAndProduit(marche, produit);
        } catch (DataAccessException ex) {
            Logger.getLogger(PrixServiceImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    @Override
    public List<PrixProduitMarche> findByProduitAndMarcheBetweenDate(Long idMarche, Long idProduit, Date date1, Date date2) throws ServiceException {
        try {
            Marche marche = marcheDao.findById(idMarche);
            if(marche == null){
                throw new ServiceException("Resource not found");
            }
            Produit produit = produitDao.findById(idProduit);
            if(produit == null){
                throw new ServiceException("Service not found");
            }
            return prixProduitMarcheDao.findByMarche(marche, produit, date1, date2);
        } catch (DataAccessException ex) {
            Logger.getLogger(PrixServiceImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    @Override
    public PrixProduitMarche findById(Long id) throws ServiceException {
        try {
            return prixProduitMarcheDao.findById(id);
        } catch (DataAccessException ex) {
            Logger.getLogger(PrixServiceImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    @Override
    public List<PrixProduitMarche> findByMarche(Long idMarche) throws ServiceException {
        try {
            Marche marche = marcheDao.findById(idMarche);
            if(marche == null){
                throw new ServiceException("Service not found");
            }
            return prixProduitMarcheDao.findByMarche(marche);
        } catch (DataAccessException ex) {
            Logger.getLogger(PrixServiceImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
        return Collections.EMPTY_LIST;
    }

    @Override
    public List<PrixProduitMarche> findAll() throws ServiceException {
        try {
            return prixProduitMarcheDao.findAll();
        } catch (DataAccessException ex) {
            Logger.getLogger(PrixServiceImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
        return Collections.EMPTY_LIST;
    }
    
}
