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
public class PrixMarche implements Serializable{
    
    private String nomMarche;
    
    private int prix;
    
    private Date datePrix;

    public String getNomMarche() {
        return nomMarche;
    }

    public void setNomMarche(String nomMarche) {
        this.nomMarche = nomMarche;
    }

    public int getPrix() {
        return prix;
    }

    public void setPrix(int prix) {
        this.prix = prix;
    }

    public Date getDatePrix() {
        return datePrix;
    }

    public void setDatePrix(Date datePrix) {
        this.datePrix = datePrix;
    }
    
    
}
