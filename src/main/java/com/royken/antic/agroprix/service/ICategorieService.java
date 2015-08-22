package com.royken.antic.agroprix.service;

import com.royken.antic.agroprix.entities.Categorie;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author Kenfack Valmy-Roi <roykenvalmy@gmail.com>
 */
@Local
public interface ICategorieService {
    
    public Categorie saveOrUpdateCategorie(Categorie categorie) throws ServiceException;
    
    public Categorie findCategorieById(Long id) throws ServiceException;
    
    public void deleteCategorie(Long id) throws ServiceException;
    
    public List<Categorie> findAllCategorie() throws ServiceException;
    
}
