package com.royken.antic.agroprix.dao.impl;

import com.royken.antic.agroprix.entities.Produit;
import com.royken.antic.agroprix.dao.IProduitDao;
import com.royken.antic.agroprix.entities.Categorie;
import com.royken.antic.agroprix.entities.Marche;
import com.royken.antic.agroprix.entities.Produit_;
import com.royken.generic.dao.DataAccessException;
import com.royken.generic.dao.impl.GenericDao;
import java.util.List;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

/**
 *
 * @author Kenfack Valmy-Roi <roykenvalmy@gmail.com>
 */
public class ProduitDaoImpl extends GenericDao<Produit, Long> implements IProduitDao{

    @Override
    public List<Produit> findByCategorie(Categorie categorie) throws DataAccessException {
        CriteriaBuilder cb = getManager().getCriteriaBuilder();
        CriteriaQuery<Produit> cq = cb.createQuery(Produit.class);
        Root<Produit> niveauRoot = cq.from(Produit.class);
        cq.where(cb.equal(niveauRoot.get(Produit_.categorie), categorie));
        cq.orderBy(cb.asc(niveauRoot.get(Produit_.nom)));
        cq.distinct(true);
        return getManager().createQuery(cq).getResultList();
    }

    @Override
    public List<Produit> findByMarche(Marche marche) throws DataAccessException {
         CriteriaBuilder cb = getManager().getCriteriaBuilder();
        CriteriaQuery<Produit> cq = cb.createQuery(Produit.class);
        Root<Produit> aRoot = cq.from(Produit.class);
        cq.where(cb.isMember(marche, aRoot.get(Produit_.marches)));
        cq.distinct(true);
        return getManager().createQuery(cq).getResultList();
        
    }
    
}
