package com.royken.antic.agroprix.dao.impl;

import com.royken.antic.agroprix.entities.Categorie;
import com.royken.antic.agroprix.dao.ICategorieDao;
import com.royken.antic.agroprix.entities.Categorie_;
import com.royken.antic.agroprix.entities.Produit;
import com.royken.antic.agroprix.entities.Produit_;
import com.royken.generic.dao.DataAccessException;
import com.royken.generic.dao.impl.GenericDao;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Join;
import javax.persistence.criteria.Root;

/**
 *
 * @author Kenfack Valmy-Roi <roykenvalmy@gmail.com>
 */
public class CategoriDaoImpl extends GenericDao<Categorie, Long> implements ICategorieDao{

    @Override
    public Categorie getByProduit(Produit produit) throws DataAccessException {
        CriteriaBuilder cb = getManager().getCriteriaBuilder();
        CriteriaQuery<Categorie> cq = cb.createQuery(Categorie.class);
        Root<Categorie> cateRoot = cq.from(Categorie.class);
        //Join<Produit, Categorie> cate = cateRoot.join(Produit_.categorie);
        return null;
    }
    
}
