package com.royken.antic.agroprix.service;

import com.royken.antic.agroprix.entities.EtatPrix;

/**
 *
 * @author Kenfack Valmy-Roi <roykenvalmy@gmail.com>
 */
public class Util {
    
    public static EtatPrix stringToEtatPrix(String etat){
        if("Hausse".equals(etat)){
            return EtatPrix.Hausse;
        }
        if("Baisse".equals(etat)){
            return EtatPrix.Baisse;
        }
        if("Stable".equals(etat)){
            return EtatPrix.Stable;
        }
        return null;
    }
    
}
