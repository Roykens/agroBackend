package com.royken.antic.agroprix.service;

import com.royken.antic.agroprix.entities.Marche;
import com.royken.antic.agroprix.entities.Ville;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author Kenfack Valmy-Roi <roykenvalmy@gmail.com>
 */
@Local
public interface IMarcheService {
    
    public Marche saveOrUpdateMarche(Marche marche) throws ServiceException;
    
    public Marche findMarcheById(Long id) throws ServiceException;
    
    public void deleteMarche(Long id) throws ServiceException;
    
    public List<Marche> findAllMarche() throws ServiceException;
    
    public List<Marche> findMarcheByVille(Long idVille) throws ServiceException;
}
