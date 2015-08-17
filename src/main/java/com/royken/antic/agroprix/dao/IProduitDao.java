package com.royken.antic.agroprix.dao;

import com.royken.antic.agroprix.entities.Categorie;
import com.royken.antic.agroprix.entities.Marche;
import com.royken.antic.agroprix.entities.Produit;
import com.royken.generic.dao.DataAccessException;
import com.royken.generic.dao.IGenericDao;
import java.io.Serializable;
import java.util.List;

/**
 *
 * @author Kenfack Valmy-Roi <roykenvalmy@gmail.com>
 */
public interface IProduitDao extends IGenericDao<Produit, Long>{
    
    public List<Produit> findByCategorie(Categorie categorie) throws DataAccessException;
    
    public List<Produit> findByMarche(Marche marche) throws DataAccessException;
    
}
