package com.royken.antic.agroprix.resource.impl;

import com.royken.antic.agroprix.entities.Marche;
import com.royken.antic.agroprix.resource.IMarcheResource;
import com.royken.antic.agroprix.service.IMarcheService;
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
@Path("/marches")
public class MarcheResource implements IMarcheResource{
    
    @EJB
    private IMarcheService marcheService;

    public IMarcheService getMarcheService() {
        return marcheService;
    }

    public void setMarcheService(IMarcheService marcheService) {
        this.marcheService = marcheService;
    }
    
    

    @Override
    public Marche createMarche(Marche marche) {
        try {
            return marcheService.saveOrUpdateMarche(marche);
        } catch (ServiceException ex) {
            Logger.getLogger(MarcheResource.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
    }

    @Override
    public List<Marche> getAllMarches() {
        try {
            return marcheService.findAllMarche();
        } catch (ServiceException ex) {
            Logger.getLogger(MarcheResource.class.getName()).log(Level.SEVERE, null, ex);
        }
        return Collections.EMPTY_LIST;
    }

    @Override
    public Marche getMarche(long id) {
        try {
            Marche marche = marcheService.findMarcheById(id);
            if(marche == null){
                throw new WebApplicationException(Response.Status.NOT_FOUND);
            }
            return marche;
        } catch (ServiceException ex) {
            Logger.getLogger(MarcheResource.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
    }

    @Override
    public Marche updateMarche(long id, Marche marche) {
        try {
            Marche marche1 = marcheService.findMarcheById(id);
            if(marche1 != null){
                marche1.setDateCreation(marche.getDateCreation());
                marche1.setLatitude(marche.getLatitude());
                marche1.setLongitude(marche.getLongitude());
                marche1.setNom(marche.getNom());
                marche1.setDescription(marche.getDescription());
                return marcheService.saveOrUpdateMarche(marche1);

            }
            else{
                throw new WebApplicationException(Response.Status.NOT_FOUND);
            }
        } catch (ServiceException ex) {
            Logger.getLogger(MarcheResource.class.getName()).log(Level.SEVERE, null, ex);
        return null;
        }
        
    }

    @Override
    public void deleteMarche(long id) {
        try {
            marcheService.deleteMarche(id);
        } catch (ServiceException ex) {
            Logger.getLogger(MarcheResource.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    
}
