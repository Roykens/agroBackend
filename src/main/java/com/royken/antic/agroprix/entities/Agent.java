package com.royken.antic.agroprix.entities;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
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
@XmlRootElement(name = "agent")
@XmlAccessorType(XmlAccessType.FIELD)
public class Agent implements Serializable{
    
    @Version
    private int version;
    
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    
    @Basic
    private String nom;
    
    @Basic
    @Column(nullable = false, unique = true)
    private String login;
    
    @Basic
    @Column(nullable = false)
    private String password;
    
    @Basic
    private String phone;
    
    @Basic
    private String mail;
    
    @Basic
    private String adresse;
    
    @ManyToOne
    @XmlTransient
    private Marche  marche;
    
    @Basic
    @Enumerated(EnumType.STRING)
    private RoleType roleType;

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

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Marche getMarche() {
        return marche;
    }

    public void setMarche(Marche marche) {
        this.marche = marche;
    }

    public RoleType getRoleType() {
        return roleType;
    }

    public void setRoleType(RoleType roleType) {
        this.roleType = roleType;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    public String getAdresse() {
        return adresse;
    }

    public void setAdresse(String adresse) {
        this.adresse = adresse;
    }

    @Override
    public String toString() {
        return "Agent{" + "version=" + version + ", id=" + id + ", nom=" + nom + ", login=" + login + ", password=" + password + ", phone=" + phone + ", mail=" + mail + ", adresse=" + adresse + ", marche=" + marche + ", roleType=" + roleType + '}';
    }
    
    
    
}
