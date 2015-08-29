package com.royken.antic.agroprix.resource.impl;

import com.royken.antic.agroprix.entities.Actualite;
import com.royken.antic.agroprix.resource.IActualiteResource;
import com.royken.antic.agroprix.service.IActualiteService;
import com.royken.antic.agroprix.service.ServiceException;
import java.util.Collections;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.EJB;
import javax.ws.rs.Path;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.Response;

/**
 *
 * @author Kenfack Valmy-Roi <roykenvalmy@gmail.com>
 */
@Path("/actus")
public class ActualiteResource implements IActualiteResource{
    
    @EJB
    private IActualiteService actualiteService;

    public IActualiteService getActualiteService() {
        return actualiteService;
    }

    public void setActualiteService(IActualiteService actualiteService) {
        this.actualiteService = actualiteService;
    }
    
    

    @Override
    public Actualite createActualite(Actualite actualite) {
        try {
            return actualiteService.saveOrUpdateActuaite(actualite);
        } catch (ServiceException ex) {
            Logger.getLogger(ActualiteResource.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    @Override
    public Actualite getActualite(long id) {
        try {
            return actualiteService.findById(id);
        } catch (ServiceException ex) {
            Logger.getLogger(ActualiteResource.class.getName()).log(Level.SEVERE, null, ex);
        }
        throw new WebApplicationException(Response.Status.NOT_FOUND);
    }

    @Override
    public Actualite updateActualite(long id, Actualite actualite) {
        try {
            Actualite actualite1 = actualiteService.findById(id);
            if(actualite1 == null){
                throw new WebApplicationException(Response.Status.NOT_FOUND);
            }
            actualite1.setContact(actualite.getContact());
            actualite1.setDateInfo(actualite.getDateInfo());
            actualite1.setInfo(actualite.getInfo());
            actualite1.setPhoto(actualite.getPhoto());
            return actualiteService.saveOrUpdateActuaite(actualite1);
        } catch (ServiceException ex) {
            Logger.getLogger(ActualiteResource.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    @Override
    public List<Actualite> getAllActualite() {
        try {
            return actualiteService.findAllActivite();
        } catch (ServiceException ex) {
            Logger.getLogger(ActualiteResource.class.getName()).log(Level.SEVERE, null, ex);
        }
        return Collections.EMPTY_LIST;
    }

    @Override
    public void deleteActualite(long id) {
        try {
            actualiteService.deleteActualite(id);
        } catch (ServiceException ex) {
            Logger.getLogger(ActualiteResource.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
}
