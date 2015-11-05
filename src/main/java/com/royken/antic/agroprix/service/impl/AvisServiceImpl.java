package com.royken.antic.agroprix.service.impl;

import com.royken.antic.agroprix.dao.IAvisDao;
import com.royken.antic.agroprix.entities.Avis;
import com.royken.antic.agroprix.service.IAvisService;
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
public class AvisServiceImpl implements IAvisService {

    @Inject
    private IAvisDao avisDao;

    public IAvisDao getAvisDao() {
        return avisDao;
    }

    public void setAvisDao(IAvisDao avisDao) {
        this.avisDao = avisDao;
    }

    @Override
    public Avis saveOrUpdateAvis(Avis avis) throws ServiceException {
        try {
            if (avis.getId() == null) {
                return avisDao.create(avis);
            } else {
                return avisDao.update(avis);
            }
        } catch (DataAccessException ex) {
            Logger.getLogger(AvisServiceImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    @Override
    public Avis findAvisById(Long avisId) throws ServiceException {
        try {
            return avisDao.findById(avisId);
        } catch (DataAccessException ex) {
            Logger.getLogger(AvisServiceImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    @Override
    public List<Avis> findByRange(int debut, int fin) throws ServiceException {
        return null;
    }

    @Override
    public List<Avis> findAll() throws ServiceException {
        try {
            return Collections.unmodifiableList(avisDao.findAll());
        } catch (DataAccessException ex) {
            Logger.getLogger(AvisServiceImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
        return Collections.EMPTY_LIST;
    }

}
