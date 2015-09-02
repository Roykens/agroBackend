package com.royken.antic.agroprix.resource.impl;

import com.royken.antic.agroprix.entities.EtatPrix;
import com.royken.antic.agroprix.entities.PrixProduitMarche;
import com.royken.antic.agroprix.entities.projection.PrixMarche;
import com.royken.antic.agroprix.resource.IPrixResource;
import com.royken.antic.agroprix.service.IPrixService;
import com.royken.antic.agroprix.service.ServiceException;
import com.royken.antic.agroprix.service.Util;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Collections;
import java.util.Date;
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
@Path("/prix")
public class PrixResource implements IPrixResource{
    
    @EJB
    private IPrixService prixService;

    public IPrixService getPrixService() {
        return prixService;
    }

    public void setPrixService(IPrixService prixService) {
        this.prixService = prixService;
    }
    
    

    @Override
    public PrixProduitMarche createPrix(String etat, PrixProduitMarche ppm) {
        try {
            ppm.setEtatPrix(Util.stringToEtatPrix(etat));
            return prixService.saveOrUpdatePrix(ppm);
        } catch (ServiceException ex) {
            Logger.getLogger(PrixResource.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    @Override
    public PrixProduitMarche getPrixProduitMarche(long id) {
        try {
            return prixService.findById(id);
        } catch (ServiceException ex) {
            Logger.getLogger(PrixResource.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    @Override
    public PrixProduitMarche updatePrix(long id, String etat, PrixProduitMarche ppm) {
        try {
            PrixProduitMarche ppmo = prixService.findById(id);
            if(ppmo != null){
                ppmo.setDatePrix(ppm.getDatePrix());
                ppmo.setEtatPrix(Util.stringToEtatPrix(etat));
                ppmo.setPrix(ppm.getPrix());
            }
        } catch (ServiceException ex) {
            Logger.getLogger(PrixResource.class.getName()).log(Level.SEVERE, null, ex);
        }
        throw new WebApplicationException(Response.Status.NOT_FOUND);
    }

    @Override
    public List<PrixProduitMarche> getAllPrix() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void deletePrix(long id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

   

    @Override
    public PrixProduitMarche getPrix(long idProduit, long idMarche) {
        try {
            return prixService.findByMarche(idMarche, idProduit);
        } catch (ServiceException ex) {
            Logger.getLogger(PrixResource.class.getName()).log(Level.SEVERE, null, ex);
        }
        throw new WebApplicationException(Response.Status.NOT_FOUND);
    }

    @Override
    public List<PrixProduitMarche> getPrixBetweenDates(long idProduit, long idMarche, String date1, String date2) {
        try {
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            Date datedeb = sdf.parse(date1);
            Date datefin = sdf.parse(date2);
            return prixService.findByProduitAndMarcheBetweenDate(idMarche, idProduit, datedeb, datefin);
        } catch (ParseException ex) {
            Logger.getLogger(PrixResource.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ServiceException ex) {
            Logger.getLogger(PrixResource.class.getName()).log(Level.SEVERE, null, ex);
        }
        return Collections.EMPTY_LIST;
    }

    @Override
    public List<PrixMarche> compare(long idProduit, long idVille) {
        try {
            return prixService.findByProduitAndVille(idProduit, idVille);
        } catch (ServiceException ex) {
            Logger.getLogger(PrixResource.class.getName()).log(Level.SEVERE, null, ex);
        }
        return Collections.EMPTY_LIST;
    }
    
}
