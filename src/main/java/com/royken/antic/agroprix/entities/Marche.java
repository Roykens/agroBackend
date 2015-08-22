package com.royken.antic.agroprix.entities;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import javax.annotation.Generated;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Version;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author Kenfack Valmy-Roi <roykenvalmy@gmail.com>
 */
@Entity
@XmlRootElement(name = "marche")
@XmlAccessorType(XmlAccessType.FIELD)
public class Marche implements Serializable{
    @OneToMany(mappedBy = "marche")
    private List<Agent> agents;
    
    
    @Version
    private int version;
    
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    
    @Basic
    private String nom;
    
    @Basic
    private Double longitude;
    
    @Basic
    private Double latitude;
    
    @Basic
    @Temporal(TemporalType.DATE)
    private Date dateCreation;
    
    @Basic
    private String description;
    
    @XmlTransient
    @ManyToMany(mappedBy = "marches")
    private List<Produit> produits;
    
    @ManyToOne
    private Ville ville;

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

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public Double getLongitude() {
        return longitude;
    }

    public void setLongitude(Double longitude) {
        this.longitude = longitude;
    }

    public Double getLatitude() {
        return latitude;
    }

    public void setLatitude(Double latitude) {
        this.latitude = latitude;
    }

    public Date getDateCreation() {
        return dateCreation;
    }

    public void setDateCreation(Date dateCreation) {
        this.dateCreation = dateCreation;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public List<Agent> getAgents() {
        return agents;
    }

    public void setAgents(List<Agent> agents) {
        this.agents = agents;
    }

    public List<Produit> getProduits() {
        return produits;
    }

    public void setProduits(List<Produit> produits) {
        this.produits = produits;
    }

    public Ville getVille() {
        return ville;
    }

    public void setVille(Ville ville) {
        this.ville = ville;
    }

    @Override
    public String toString() {
        return "Marche{" + "version=" + version + ", id=" + id + ", nom=" + nom + ", longitude=" + longitude + ", latitude=" + latitude + ", dateCreation=" + dateCreation + ", description=" + description + '}';
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 89 * hash + (this.agents != null ? this.agents.hashCode() : 0);
        hash = 89 * hash + (this.produits != null ? this.produits.hashCode() : 0);
        hash = 89 * hash + (this.ville != null ? this.ville.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Marche other = (Marche) obj;
        if (this.agents != other.agents && (this.agents == null || !this.agents.equals(other.agents))) {
            return false;
        }
        if (this.produits != other.produits && (this.produits == null || !this.produits.equals(other.produits))) {
            return false;
        }
        if (this.ville != other.ville && (this.ville == null || !this.ville.equals(other.ville))) {
            return false;
        }
        return true;
    }
    
    
}
