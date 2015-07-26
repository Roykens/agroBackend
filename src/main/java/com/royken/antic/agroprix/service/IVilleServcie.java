package com.royken.antic.agroprix.service;

import com.royken.antic.agroprix.entities.Ville;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author Kenfack Valmy-Roi <roykenvalmy@gmail.com>
 */
@Local
public interface IVilleServcie {
    
    public Ville saveOrUpdateVille(Ville ville) throws ServiceException;
    
    public Ville findVilleById(Long id) throws ServiceException;
    
    public void deleteVille(Long id) throws ServiceException;
    
    public List<Ville> findAllVille() throws ServiceException;
}
