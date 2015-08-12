package com.royken.antic.agroprix.dao.impl;

import com.royken.antic.agroprix.entities.Marche;
import com.royken.antic.agroprix.dao.IMarcheDao;
import com.royken.antic.agroprix.entities.Marche_;
import com.royken.antic.agroprix.entities.Produit;
import com.royken.antic.agroprix.entities.Ville;
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
public class MarcheDaoImpl extends GenericDao<Marche, Long> implements IMarcheDao {

    @Override
    public List<Marche> findMarcheByVille(Ville ville) throws DataAccessException {
        CriteriaBuilder cb = getManager().getCriteriaBuilder();
        CriteriaQuery<Marche> cq = cb.createQuery(Marche.class);
        Root<Marche> niveauRoot = cq.from(Marche.class);
        cq.where(cb.equal(niveauRoot.get(Marche_.ville), ville));
        cq.orderBy(cb.asc(niveauRoot.get(Marche_.nom)));
        cq.distinct(true);
        return getManager().createQuery(cq).getResultList();
    }

    @Override
    public List<Marche> findByProduit(Produit produit) throws DataAccessException {
        CriteriaBuilder cb = getManager().getCriteriaBuilder();
        CriteriaQuery<Marche> cq = cb.createQuery(Marche.class);
        Root<Marche> marcheRoot = cq.from(Marche.class);
        cq.where(cb.isMember(produit, marcheRoot.get(Marche_.produits)));
        cq.distinct(true);
        return getManager().createQuery(cq).getResultList();
    }

}