package com.royken.antic.agroprix.dao;

import com.royken.antic.agroprix.entities.Agent;
import com.royken.antic.agroprix.entities.Categorie;
import com.royken.antic.agroprix.entities.Produit;
import com.royken.generic.dao.DataAccessException;
import com.royken.generic.dao.IGenericDao;
import java.util.List;
import javax.resource.spi.AdministeredObject;

/**
 *
 * @author Kenfack Valmy-Roi <roykenvalmy@gmail.com>
 */
public interface ICategorieDao extends IGenericDao<Categorie,Long>{
    
    public Categorie getByProduit(Produit produit) throws DataAccessException;
    
    public List<Categorie> findByAgent(Agent agent) throws DataAccessException;
    
}
