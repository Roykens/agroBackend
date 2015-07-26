package com.royken.antic.agroprix.service.impl;

import com.royken.antic.agroprix.entities.Ville;
import com.royken.antic.agroprix.entities.dao.IVilleDao;
import com.royken.antic.agroprix.service.IVilleServcie;
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
public class VilleServiceImpl implements IVilleServcie {

    @Inject
    private IVilleDao villeDao;

    public IVilleDao getVilleDao() {
        return villeDao;
    }

    public void setVilleDao(IVilleDao villeDao) {
        this.villeDao = villeDao;
    }
    
    

    @Override
    public Ville saveOrUpdateVille(Ville ville) throws ServiceException {
        try {
            if (ville.getId() == null) {
                return villeDao.create(ville);
            } else {
                return villeDao.update(ville);
            }
        } catch (DataAccessException ex) {
            Logger.getLogger(VilleServiceImpl.class.getName()).log(Level.SEVERE, null, ex);
            throw new ServiceException(ex.getMessage(), ex);
        }

    }

    @Override
    public Ville findVilleById(Long id) throws ServiceException {
        try {
            return villeDao.findById(id);
        } catch (DataAccessException ex) {
            Logger.getLogger(VilleServiceImpl.class.getName()).log(Level.SEVERE, null, ex);
            throw new ServiceException(ex.getMessage(), ex);
        }
    }

    @Override
    public void deleteVille(Long id) throws ServiceException {
        try {
            Ville ville = villeDao.findById(id);
            if (ville != null) {
                villeDao.delete(ville);
            } else {
                throw new ServiceException("La Ville est introuvable");
            }

        } catch (DataAccessException ex) {
            Logger.getLogger(VilleServiceImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public List<Ville> findAllVille() throws ServiceException {
        try {
            return villeDao.findAll();
        } catch (DataAccessException ex) {
            Logger.getLogger(VilleServiceImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
        return Collections.EMPTY_LIST;
    }

}
