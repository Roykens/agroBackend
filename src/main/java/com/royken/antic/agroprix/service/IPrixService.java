package com.royken.antic.agroprix.service;

import com.royken.antic.agroprix.entities.Marche;
import com.royken.antic.agroprix.entities.PrixProduitMarche;
import com.royken.antic.agroprix.entities.Produit;
import java.util.Date;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author Kenfack Valmy-Roi <roykenvalmy@gmail.com>
 */
@Local
public interface IPrixService {
    
    public PrixProduitMarche saveOrUpdatePrix(PrixProduitMarche prix) throws ServiceException;
    
    public PrixProduitMarche findById(Long id) throws ServiceException;
    
    public List<PrixProduitMarche> findByMarche(Long idMarche, Long idProduit) throws ServiceException;
    
    public List<PrixProduitMarche> findByProduitAndMarcheBetweenDate(Long idMarche, Long idProduit, Date date1, Date date2) throws ServiceException;
}
