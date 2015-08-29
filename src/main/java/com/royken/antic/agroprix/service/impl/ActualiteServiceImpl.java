package com.royken.antic.agroprix.service.impl;

import com.royken.antic.agroprix.dao.IActualiteDao;
import com.royken.antic.agroprix.entities.Actualite;
import com.royken.antic.agroprix.service.IActualiteService;
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
public class ActualiteServiceImpl implements IActualiteService {

    @Inject
    private IActualiteDao actualiteDao;

    public IActualiteDao getActualiteDao() {
        return actualiteDao;
    }

    public void setActualiteDao(IActualiteDao actualiteDao) {
        this.actualiteDao = actualiteDao;
    }

    @Override
    public Actualite saveOrUpdateActuaite(Actualite actualite) throws ServiceException {

        try {
            if (actualite.getId() == null) {
                return actualiteDao.create(actualite);
            }
            else{
                return actualiteDao.update(actualite);
            }
        } catch (DataAccessException ex) {
            Logger.getLogger(ActualiteServiceImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
        throw new ServiceException("Service not found");
    }

    @Override
    public Actualite findById(Long id) throws ServiceException {
        try {
            return actualiteDao.findById(id);
        } catch (DataAccessException ex) {
            Logger.getLogger(ActualiteServiceImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
        throw new ServiceException("Service not found");
    }

    @Override
    public void deleteActualite(Long id) throws ServiceException {
        try {
            Actualite actualite = actualiteDao.findById(id);
            actualiteDao.delete(actualite);
        } catch (DataAccessException ex) {
            Logger.getLogger(ActualiteServiceImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public List<Actualite> findAllActivite() throws ServiceException {
        try {
            return actualiteDao.findAll();
        } catch (DataAccessException ex) {
            Logger.getLogger(ActualiteServiceImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
        return Collections.EMPTY_LIST;
    }

}
