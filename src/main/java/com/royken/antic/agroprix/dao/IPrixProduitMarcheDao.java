package com.royken.antic.agroprix.dao;

import com.royken.antic.agroprix.entities.Marche;
import com.royken.antic.agroprix.entities.PrixProduitMarche;
import com.royken.antic.agroprix.entities.projection.PrixProduit;
import com.royken.generic.dao.DataAccessException;
import com.royken.generic.dao.IGenericDao;
import java.util.Date;
import java.util.List;

/**
 *
 * @author Kenfack Valmy-Roi <roykenvalmy@gmail.com>
 */
public interface IPrixProduitMarcheDao extends IGenericDao<PrixProduitMarche, Long>{
    //public PrixProduitMarche findByMarche(Marche marche) throws DataAccessException;
    
    public PrixProduit findByMarche(Marche marche, Date date) throws DataAccessException;
    
    public List<PrixProduitMarche> findByMarche(Marche marche, Date debut, Date fin) throws DataAccessException;
    
    
}
