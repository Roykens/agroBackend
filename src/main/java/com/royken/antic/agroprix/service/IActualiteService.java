package com.royken.antic.agroprix.service;

import com.royken.antic.agroprix.entities.Actualite;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author Kenfack Valmy-Roi <roykenvalmy@gmail.com>
 */
@Local
public interface IActualiteService {
    
    public Actualite saveOrUpdateActuaite(Actualite actualite) throws ServiceException;
    
    public Actualite findById(Long id) throws ServiceException;
    
    public void deleteActualite(Long id) throws ServiceException;
    
    public List<Actualite> findAllActivite() throws ServiceException;
    
}
