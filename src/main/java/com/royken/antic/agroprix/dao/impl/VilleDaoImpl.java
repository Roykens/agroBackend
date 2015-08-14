package com.royken.antic.agroprix.dao.impl;

import com.royken.antic.agroprix.entities.Ville;
import com.royken.antic.agroprix.dao.IVilleDao;
import com.royken.antic.agroprix.entities.Marche;
import com.royken.antic.agroprix.entities.Marche_;
import com.royken.antic.agroprix.entities.Produit;
import com.royken.antic.agroprix.entities.Produit_;
import com.royken.antic.agroprix.entities.Ville_;
import com.royken.generic.dao.DataAccessException;
import com.royken.generic.dao.impl.GenericDao;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

/**
 *
 * @author Kenfack Valmy-Roi <roykenvalmy@gmail.com>
 */
public class VilleDaoImpl extends GenericDao<Ville, Long> implements IVilleDao{

    @Override
    public List<Ville> findAllVilleByProduit(Produit produit) throws DataAccessException {
        CriteriaBuilder cb = getManager().getCriteriaBuilder();
        CriteriaQuery<Marche> cq = cb.createQuery(Marche.class);
        CriteriaQuery<Ville> cq2 = cb.createQuery(Ville.class);
        Root<Marche> aRoot = cq.from(Marche.class);
        Root<Ville> vRoot = cq.from(Ville.class);
        cq.where(cb.isMember(produit, aRoot.get(Marche_.produits)));
        cq.distinct(true);
        List<Marche> marches = getManager().createQuery(cq).getResultList();
        Set<Ville> result = new HashSet<Ville>();
        for (Marche march : marches) {
            result.add(march.getVille());
        }
        return new ArrayList<Ville>(result);
    }
//
//    @Override
//    public List<Ville> findAllVilleByProduit(Produit produit) throws DataAccessException {
//        
//    }
    
    
    
}
