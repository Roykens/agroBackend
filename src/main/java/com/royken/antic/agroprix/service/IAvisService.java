package com.royken.antic.agroprix.service;

import com.royken.antic.agroprix.entities.Avis;
import java.util.List;

/**
 *
 * @author Kenfack Valmy-Roi <roykenvalmy@gmail.com>
 */
public interface IAvisService {
    
    public Avis saveOrUpdateAvis(Avis avis) throws ServiceException;
    
    public Avis findAvisById(Long avisId) throws ServiceException;
    
    public List<Avis> findByRange(int debut, int fin) throws ServiceException;
    
    public List<Avis> findAll() throws ServiceException;
}
