package com.royken.antic.agroprix.service.impl;

import com.royken.antic.agroprix.entities.PrixProduitMarche;
import com.royken.antic.agroprix.service.IPrixService;
import com.royken.antic.agroprix.service.ServiceException;
import java.util.Date;
import javax.ejb.Stateless;
import javax.inject.Named;

/**
 *
 * @author Kenfack Valmy-Roi <roykenvalmy@gmail.com>
 */
@Stateless
@Named
public class PrixServiceImpl implements IPrixService{

    @Override
    public PrixProduitMarche saveOrUpdatePrix(int prix, Long idProduit, Long idMarche, Date date) throws ServiceException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
