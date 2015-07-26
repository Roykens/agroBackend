package com.royken.antic.agroprix.service.impl;

import com.royken.antic.agroprix.entities.Marche;
import com.royken.antic.agroprix.dao.IMarcheDao;
import com.royken.antic.agroprix.dao.IVilleDao;
import com.royken.antic.agroprix.entities.Ville;
import com.royken.antic.agroprix.service.IMarcheService;
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
public class MarcheServiceImpl implements IMarcheService {

    @Inject
    private IMarcheDao marcheDao;
    
    @Inject
    private IVilleDao villeDao;

    public IMarcheDao getMarcheDao() {
        return marcheDao;
    }

    public void setMarcheDao(IMarcheDao marcheDao) {
        this.marcheDao = marcheDao;
    }

    @Override
    public Marche saveOrUpdateMarche(Marche marche) throws ServiceException {
        try {
            if (marche.getId() == null) {
                return marcheDao.create(marche);
            } else {
                return marcheDao.update(marche);
            }
        } catch (DataAccessException ex) {
            Logger.getLogger(MarcheServiceImpl.class.getName()).log(Level.SEVERE, null, ex);
            throw new ServiceException(ex.getMessage(), ex);
        }

    }

    @Override
    public Marche findMarcheById(Long id) throws ServiceException {
        try {
            return marcheDao.findById(id);
        } catch (DataAccessException ex) {
            Logger.getLogger(MarcheServiceImpl.class.getName()).log(Level.SEVERE, null, ex);
            throw new ServiceException(ex.getMessage(), ex);
        }
    }

    @Override
    public void deleteMarche(Long id) throws ServiceException {
        try {
            Marche marche = marcheDao.findById(id);
            if(marche != null){
                marcheDao.delete(marche);
            }
            else{
                throw new ServiceException("Le marché est introuvble");
            }
        } catch (DataAccessException ex) {
            Logger.getLogger(MarcheServiceImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public List<Marche> findAllMarche() throws ServiceException {
        try {
            return marcheDao.findAll();
        } catch (DataAccessException ex) {
            Logger.getLogger(MarcheServiceImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
        return Collections.EMPTY_LIST;
    }

    @Override
    public List<Marche> findMarcheByVille(Long idVille) throws ServiceException {
        try {
            Ville ville = villeDao.findById(idVille);
            if(ville != null){
                return marcheDao.findMarcheByVille(ville);
            }
            else{
                throw new ServiceException("La ville est introuvable");
            }
        } catch (DataAccessException ex) {
            Logger.getLogger(MarcheServiceImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
        return Collections.EMPTY_LIST;
    }

}
