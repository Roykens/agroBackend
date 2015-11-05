package com.royken.antic.agroprix.resource.impl;

import com.royken.antic.agroprix.entities.Avis;
import com.royken.antic.agroprix.resource.IAvisResource;
import com.royken.antic.agroprix.service.IAvisService;
import com.royken.antic.agroprix.service.ServiceException;
import java.util.Collections;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.EJB;
import javax.ws.rs.Path;

/**
 *
 * @author Kenfack Valmy-Roi <roykenvalmy@gmail.com>
 */
@Path("/avis")
public class AvisResource implements IAvisResource{
    
    @EJB
    private IAvisService avisService;

    public IAvisService getAvisService() {
        return avisService;
    }

    public void setAvisService(IAvisService avisService) {
        this.avisService = avisService;
    }
    
    

    @Override
    public Avis createAvis(Avis avis) {
        try {
            return avisService.saveOrUpdateAvis(avis);
        } catch (ServiceException ex) {
            Logger.getLogger(AvisResource.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    @Override
    public List<Avis> getAllAvis() {
        try {
            return avisService.findAll();
        } catch (ServiceException ex) {
            Logger.getLogger(AvisResource.class.getName()).log(Level.SEVERE, null, ex);
        }
        return Collections.EMPTY_LIST;
    }

    @Override
    public Avis getAvis(long id) {
        try {
            return avisService.findAvisById(id);
        } catch (ServiceException ex) {
            Logger.getLogger(AvisResource.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
    
}
