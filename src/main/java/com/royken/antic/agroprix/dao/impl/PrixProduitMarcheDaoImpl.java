package com.royken.antic.agroprix.dao.impl;

import com.royken.antic.agroprix.entities.PrixProduitMarche;
import com.royken.antic.agroprix.dao.IPrixProduitMarcheDao;
import com.royken.antic.agroprix.entities.Marche;
import com.royken.antic.agroprix.entities.PrixProduitMarche_;
import com.royken.antic.agroprix.entities.Produit;
import com.royken.antic.agroprix.entities.projection.PrixProduit;
import com.royken.generic.dao.DataAccessException;
import com.royken.generic.dao.impl.GenericDao;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

/**
 *
 * @author Kenfack Valmy-Roi <roykenvalmy@gmail.com>
 */
public class PrixProduitMarcheDaoImpl extends GenericDao<PrixProduitMarche, Long> implements IPrixProduitMarcheDao {

    @Override
    public PrixProduit findByMarche( Produit produit,Marche marche, Date date) throws DataAccessException {
//        CriteriaBuilder cb = getManager().getCriteriaBuilder();
//            CriteriaQuery<PrixProduit> cq = cb.createQuery(PrixProduit.class);
//            Root<PrixProduit> aRoot = cq.from(PrixProduit.class);
//            cq.where(cb.and(
//                    cb.equal(aRoot.get(PrixProduitMarche_.marche), marche),
//                    cb.equal(aRoot.get(PrixProduitMarche_.produit), pro)
//            ));
//            return getManager().createQuery(cq).getSingleResult();
        return null;
    }

    @Override
    public List<PrixProduitMarche> findByMarche(Marche marche, Produit produit, Date debut, Date fin) throws DataAccessException {
        CriteriaBuilder cb = getManager().getCriteriaBuilder();
        CriteriaQuery<PrixProduitMarche> cq = cb.createQuery(PrixProduitMarche.class);
        Root<PrixProduitMarche> pRoot = cq.from(PrixProduitMarche.class);
        cq.where(cb.and(cb.equal(pRoot.get(PrixProduitMarche_.marche), marche), cb.equal(pRoot.get(PrixProduitMarche_.produit), produit),cb.between(pRoot.get(PrixProduitMarche_.datePrix), debut, fin)));
        return getManager().createQuery(cq).getResultList();
    }

    @Override
    public PrixProduitMarche findPrixByMarcheAndProduit(Marche marche, Produit produit) throws DataAccessException {
        CriteriaBuilder cb = getManager().getCriteriaBuilder();
        CriteriaQuery<PrixProduitMarche> cq = cb.createQuery(PrixProduitMarche.class);
        Root<PrixProduitMarche> pRoot = cq.from(PrixProduitMarche.class);
        cq.where(cb.and(cb.equal(pRoot.get(PrixProduitMarche_.marche), marche), cb.equal(pRoot.get(PrixProduitMarche_.produit), produit))).orderBy(cb.desc(pRoot.get(PrixProduitMarche_.datePrix)));
//        List<PrixProduitMarche> prixI =  getManager().createQuery(cq).getResultList();
//        List<PrixProduitMarche> result = new ArrayList<PrixProduitMarche>();
//        result.add(prixI.get(0));
//        if(prixI.size() > 1){
//            result.add(prixI.get(1));
//        }
        return getManager().createQuery(cq).getResultList().get(0);
        ///return result;
    }

    @Override
    public List<PrixProduitMarche> findByMarche(Marche marche) throws DataAccessException {
        CriteriaBuilder cb = getManager().getCriteriaBuilder();
        CriteriaQuery<PrixProduitMarche> cq = cb.createQuery(PrixProduitMarche.class);
        Root<PrixProduitMarche> prixRoot = cq.from(PrixProduitMarche.class);
        cq.where(cb.equal(prixRoot.get(PrixProduitMarche_.marche), marche));
        return getManager().createQuery(cq).getResultList();
    }

    @Override
    public List<PrixProduitMarche> findAllPrixByMarcheAndProduit(Marche marche, Produit produit) throws DataAccessException {
        CriteriaBuilder cb = getManager().getCriteriaBuilder();
        CriteriaQuery<PrixProduitMarche> cq = cb.createQuery(PrixProduitMarche.class);
        Root<PrixProduitMarche> pRoot = cq.from(PrixProduitMarche.class);
        cq.where(cb.and(cb.equal(pRoot.get(PrixProduitMarche_.marche), marche), cb.equal(pRoot.get(PrixProduitMarche_.produit), produit))).orderBy(cb.desc(pRoot.get(PrixProduitMarche_.datePrix)));
//        List<PrixProduitMarche> prixI =  getManager().createQuery(cq).getResultList();
//        List<PrixProduitMarche> result = new ArrayList<PrixProduitMarche>();
//        result.add(prixI.get(0));
//        if(prixI.size() > 1){
//            result.add(prixI.get(1));
//        }
        return getManager().createQuery(cq).getResultList();
    }
    
}
