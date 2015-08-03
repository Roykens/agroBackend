package com.royken.antic.agroprix.dao;

import com.royken.antic.agroprix.entities.Marche;
import com.royken.antic.agroprix.entities.Produit;
import com.royken.antic.agroprix.entities.Ville;
import com.royken.generic.dao.DataAccessException;
import com.royken.generic.dao.IGenericDao;
import java.io.Serializable;
import java.util.List;

/**
 *
 * @author Kenfack Valmy-Roi <roykenvalmy@gmail.com>
 */
public interface IMarcheDao extends IGenericDao<Marche, Long>{
    
    public List<Marche> findMarcheByVille(Ville ville) throws DataAccessException;
    
    public List<Marche> findByProduit(Produit produit) throws DataAccessException;
    
}
