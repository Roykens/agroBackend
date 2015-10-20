package com.royken.antic.agroprix.entities.projection;

import java.io.Serializable;
import java.util.Date;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Kenfack Valmy-Roi <roykenvalmy@gmail.com>
 */
@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
public class PrixProduit implements Serializable{
    
    private int nouveauPrix;
    
   private Long marcheId;
   
   private Long produitId;
   
   private String etatPrix;
   
   private Date datePrix;

    public int getNouveauPrix() {
        return nouveauPrix;
    }

    public void setNouveauPrix(int nouveauPrix) {
        this.nouveauPrix = nouveauPrix;
    }

    public Long getMarcheId() {
        return marcheId;
    }

    public void setMarcheId(Long marcheId) {
        this.marcheId = marcheId;
    }

    public Long getProduitId() {
        return produitId;
    }

    public void setProduitId(Long produitId) {
        this.produitId = produitId;
    }

    public String getEtatPrix() {
        return etatPrix;
    }

    public void setEtatPrix(String etatPrix) {
        this.etatPrix = etatPrix;
    }

    public Date getDatePrix() {
        return datePrix;
    }

    public void setDatePrix(Date datePrix) {
        this.datePrix = datePrix;
    }
   
   
    
}
