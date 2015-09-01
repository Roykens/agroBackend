package com.royken.antic.agroprix.entities;

import java.io.Serializable;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;
import javax.persistence.Version;
import javax.validation.constraints.Min;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author Kenfack Valmy-Roi <roykenvalmy@gmail.com>
 */
@Entity
@XmlRootElement(name = "prix")
@XmlAccessorType(XmlAccessType.FIELD)
public class PrixProduitMarche implements Serializable{

    public PrixProduitMarche() {
        df = new SimpleDateFormat("dd-MM-yyyy");
    }
 
    @Version
    private int version;
    
    @Transient
    @XmlTransient
    private DateFormat df;
    
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    
    @Basic
    @Min(0)
    private Integer prix;
    
    @Basic
    @Temporal(TemporalType.DATE)
    private Date datePrix;
    
    @XmlTransient
    @ManyToOne
    private Produit produit;
    
    @XmlTransient
    @ManyToOne
    private Marche marche;
    
    @Basic
    @Enumerated(EnumType.STRING)
    private EtatPrix etatPrix;

    public int getVersion() {
        return version;
    }

    public void setVersion(int version) {
        this.version = version;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getPrix() {
        return prix;
    }

    public void setPrix(Integer prix) {
        this.prix = prix;
    }

    public Date getDatePrix() {
        return datePrix;
    }

    public void setDatePrix(Date datePrix) {
        this.datePrix = datePrix;
    }

    public Produit getProduit() {
        return produit;
    }

    public void setProduit(Produit produit) {
        this.produit = produit;
    }

    public Marche getMarche() {
        return marche;
    }

    public void setMarche(Marche marche) {
        this.marche = marche;
    }

    public DateFormat getDf() {
        return df;
    }

    public void setDf(DateFormat df) {
        this.df = df;
    }

    public EtatPrix getEtatPrix() {
        return etatPrix;
    }

    public void setEtatPrix(EtatPrix etatPrix) {
        this.etatPrix = etatPrix;
    }

    @Override
    public String toString() {
        return "PrixProduitMarche{" + " id=" + id + ", prix=" + prix + ", datePrix=" + datePrix + ", produit=" + produit + ", marche=" + marche + ", etatPrix=" + etatPrix + '}';
    }
    
    
    
}
