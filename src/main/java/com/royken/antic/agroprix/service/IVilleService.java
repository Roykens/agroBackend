package com.royken.antic.agroprix.service;

import com.royken.antic.agroprix.entities.Marche;
import com.royken.antic.agroprix.entities.Produit;
import com.royken.antic.agroprix.entities.Ville;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author Kenfack Valmy-Roi <roykenvalmy@gmail.com>
 */
@Local
public interface IVilleService {
    
    public Ville saveOrUpdateVille(Ville ville) throws ServiceException;
    
    public Ville findVilleById(Long id) throws ServiceException;
    
    public void deleteVille(Long id) throws ServiceException;
    
    public List<Ville> findAllVille() throws ServiceException;
    
    public List<Marche> findAllMarcheByVille(Long idVille) throws ServiceException;
    
    public List<Ville> findAllByProduit(Long idProduit) throws ServiceException;
}
