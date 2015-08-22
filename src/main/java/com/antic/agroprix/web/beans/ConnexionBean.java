/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.antic.agroprix.web.beans;

/**
 *
 * @author root
 */
import com.royken.antic.agroprix.entities.Agent;
import com.royken.antic.agroprix.entities.PrixProduitMarche;
import com.royken.antic.agroprix.entities.Produit;
import com.royken.antic.agroprix.entities.RoleType;
import com.royken.antic.agroprix.service.IAgentService;
import com.royken.antic.agroprix.service.IPrixService;
import com.royken.antic.agroprix.service.IProduitService;
import com.royken.antic.agroprix.service.ServiceException;
import java.util.List;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

/**
 *
 * @author root
 */
@ManagedBean
@SessionScoped
public class ConnexionBean {

    @EJB
    IAgentService agentService;
    @EJB
    IProduitService produitService;
    @EJB
    IPrixService prixService;
    String connecter;
    String menu;
    String message;
    String nom;
    String pwd;
    List<Produit> produits;
    List<PrixProduitMarche> prixProduitMarches;
    //String action;
    Agent agent;

    public ConnexionBean() {
        connecter = "connexion";
        menu = "/autres/menuSimple.xhtml";
        agent = new Agent();
    }

    public String connection() {
        message = new String();
        if (connecter.compareTo("déconnexion") == 0) {
            agent = new Agent();
            nom = new String();
            pwd = new String();
            connecter = "connexion";
            menu = "/autres/menuSimple.xhtml";
            return "accueil.xhtml";
        } else {
            return "connexion.xhtml";
        }
    }

    public String verifier() throws ServiceException {
        if (nom != null && pwd != null && !nom.isEmpty() && !pwd.isEmpty()) {
            agent = agentService.findByLoginaAndPassword(nom, pwd);
            pwd = "";
            if (agent != null && agent.getId() != null) {
                connecter = "déconnexion";
                if (agent.getRoleType().compareTo(RoleType.ADMIN) == 0) {
                    nom = new String();
                    pwd = new String();
                    menu = "/autres/menuAdmin.xhtml";
                    return "administrateur";
                } else if (agent.getRoleType().compareTo(RoleType.AGENT) == 0) {
                    nom = new String();
                    pwd = new String();
                    menu = "/autres/menuAgent.xhtml";
                    return "agent_marche";
                } else {
                    pwd = new String();
                    return init();
                }
            } else {
                return init();
            }
        } else {
            return init();
        }
    }

    private String init() {
        connecter = "connexion";
        menu = "/autres/menuSimple.xhtml";
        message = "nom d'utilisateur ou mot de passe incorrect";
        agent = new Agent();
        pwd = "";
        return "connexion";
    }

    public IAgentService getAgentService() {
        return agentService;
    }

    public void setAgentService(IAgentService agentService) {
        this.agentService = agentService;
    }

    public String getConnecter() {
        return connecter;
    }

    public void setConnecter(String connecter) {
        this.connecter = connecter;
    }

    public String getMenu() {
        return menu;
    }

    public void setMenu(String menu) {
        this.menu = menu;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getPwd() {
        return pwd;
    }

    public void setPwd(String pwd) {
        this.pwd = pwd;
    }

    public Agent getAgent() {
        return agent;
    }

    public void setAgent(Agent agent) {
        this.agent = agent;
    }

    public List<Produit> getProduits() throws ServiceException {
        if (agent != null && agent.getMarche() != null && agent.getMarche().getId() != null) {
            produits = produitService.findProduitByMarche(agent.getMarche().getId());
        }
        return produits;
    }

    public void setProduits(List<Produit> produits) {
        this.produits = produits;
    }

    public List<PrixProduitMarche> getPrixProduitMarches() throws ServiceException {
        if (agent != null && agent.getMarche() != null && agent.getMarche().getId() != null) {
            prixProduitMarches = prixService.findByMarche(agent.getMarche().getId());
        }
        return prixProduitMarches;
    }

    public void setPrixProduitMarches(List<PrixProduitMarche> prixProduitMarches) {
        this.prixProduitMarches = prixProduitMarches;
    }

}
