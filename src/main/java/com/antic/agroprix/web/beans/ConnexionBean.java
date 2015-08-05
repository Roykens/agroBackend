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
import com.royken.antic.agroprix.service.IAgentService;
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
    String connecter;
    String menu;
    String message;
    String nom;
    String pwd;
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

    public String verifier() {
        // nom=null;
        if (nom != null && pwd != null) {
            //agent = agentService.findAgentByNomAndPwd(nom, pwd);          
            agent.setId(1L);
            agent.setLogin("admin");
            agent.setPassword("admin");
            pwd = "";
            if (agent != null && agent.getId() != null) {
                connecter = "déconnexion";
                nom = new String();
                pwd = new String();
                menu = "/autres/menuAgent.xhtml";
                return "administrateur";
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

}
