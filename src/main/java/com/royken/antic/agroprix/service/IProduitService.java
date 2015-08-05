package com.royken.antic.agroprix.service;

import com.royken.antic.agroprix.entities.Categorie;
import com.royken.antic.agroprix.entities.Produit;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author Kenfack Valmy-Roi <roykenvalmy@gmail.com>
 */
@Local
public interface IProduitService {
    
    public Produit saveOrUpdateProduit(Produit produit) throws ServiceException;
    
    public Produit findProduitById(Long id) throws ServiceException;
    
    public void deleteProduit(Long id) throws ServiceException;
    
    public List<Produit> findAllProduit() throws ServiceException;
    
    public List<Produit> findByCategorie(Long  idCategorie) throws ServiceException;
}
