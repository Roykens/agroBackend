package com.royken.antic.agroprix.dao;

import com.royken.antic.agroprix.entities.Produit;
import com.royken.antic.agroprix.entities.Ville;
import com.royken.generic.dao.DataAccessException;
import com.royken.generic.dao.IGenericDao;
import java.util.List;

/**
 *
 * @author Kenfack Valmy-Roi <roykenvalmy@gmail.com>
 */
public interface IVilleDao extends IGenericDao<Ville, Long>{
 
  //  public List<Ville> findAllVilleByProduit(Produit produit) throws DataAccessException;
}
