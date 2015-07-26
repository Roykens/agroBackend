package com.royken.antic.agroprix.service;

import com.royken.antic.agroprix.entities.PrixProduitMarche;
import java.util.Date;
import javax.ejb.Local;

/**
 *
 * @author Kenfack Valmy-Roi <roykenvalmy@gmail.com>
 */
@Local
public interface IPrixService {
    
    public PrixProduitMarche saveOrUpdatePrix(int prix, Long idProduit, Long idMarche, Date date) throws ServiceException;
}
