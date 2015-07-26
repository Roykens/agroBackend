package com.royken.antic.agroprix.resource.impl;

import com.royken.antic.agroprix.entities.Ville;
import com.royken.antic.agroprix.resource.IVilleResource;
import com.royken.antic.agroprix.service.IVilleService;
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
@Path("villes")
public class VilleResource implements IVilleResource {

    @EJB
    private IVilleService villeService;

    public IVilleService getVilleService() {
        return villeService;
    }

    public void setVilleService(IVilleService villeService) {
        this.villeService = villeService;
    }

    @Override
    public Ville createVille(Ville ville) {
        try {
            return villeService.saveOrUpdateVille(ville);
        } catch (ServiceException ex) {
            Logger.getLogger(VilleResource.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    @Override
    public List<Ville> getAllVilles() {
        try {
            return villeService.findAllVille();
        } catch (ServiceException ex) {
            Logger.getLogger(VilleResource.class.getName()).log(Level.SEVERE, null, ex);
        }
        return Collections.EMPTY_LIST;
    }

    @Override
    public Ville getVille(long id) {
        try {
            Ville ville = villeService.findVilleById(id);
            if (ville == null) {
                throw new WebApplicationException(Response.Status.NOT_FOUND);
            } else {
                return ville;
            }
        } catch (ServiceException ex) {
            Logger.getLogger(VilleResource.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    @Override
    public Ville updateVille(long id, Ville ville) {
        try {
            Ville ville1 = villeService.findVilleById(id);
            if(ville1!= null){
                ville1.setNom(ville.getNom());
                return villeService.saveOrUpdateVille(ville);
            }
        } catch (ServiceException ex) {
            Logger.getLogger(VilleResource.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    @Override
    public void deleteVille(long id) {
        try {
            villeService.deleteVille(id);
        } catch (ServiceException ex) {
            Logger.getLogger(VilleResource.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}
