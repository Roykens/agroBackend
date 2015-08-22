/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.antic.agroprix.web.beans;

import com.royken.antic.agroprix.entities.Agent;
import com.royken.antic.agroprix.entities.Marche;
import com.royken.antic.agroprix.entities.RoleType;
import com.royken.antic.agroprix.service.IAgentService;
import com.royken.antic.agroprix.service.IMarcheService;
import com.royken.antic.agroprix.service.ServiceException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import org.primefaces.event.SelectEvent;

/**
 *
 * @author root
 */
@ManagedBean
@RequestScoped
public class AgentBean {

    @EJB
    IAgentService agentService;
    @EJB
    IMarcheService marcheService;
//    Marche marche = new Marche();    
    List<Marche> marches = new ArrayList<Marche>();
    public Agent agent = new Agent();
    public List<Agent> agents;
    public String pwd1 = new String();
    public String pwd2 = new String();   
    private Long marcheId;
    public List<RoleType> roleTypes = new ArrayList<RoleType>();

    public AgentBean() {       
        this.marcheId = 0L;
        roleTypes.add(RoleType.ADMIN);
        roleTypes.add(RoleType.AGENT);
    }

    public void ajouterajourAgent(){
        if (agent != null && pwd1 != null && pwd2 != null && pwd1.trim().compareTo(pwd2.trim()) == 0) {
            agent.setPassword(pwd1);
            if (marcheId != null) {
                try {
                    Marche marchelocal = marcheService.findMarcheById(marcheId);
                    System.out.println(marcheId + "===============================================" + marchelocal);
                    agent.setMarche(marchelocal);
                } catch (ServiceException ex) {
                    Logger.getLogger(AgentBean.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            if (agent.getId() == null && agent.getNom() != null && agent.getNom().length() != 0) {
                try {
                    agent = agentService.saveOrUpdateAgent(agent);
                    FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "opération reussie", agent.getNom() + " a été ajouté "));
                } catch (ServiceException ex) {
                    Logger.getLogger(AgentBean.class.getName()).log(Level.SEVERE, null, ex);
                }
            } else {
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Echec", " l'operation à échouer "));
            }
        }
        pwd1 = new String();
        pwd2 = new String();
        agent = new Agent();
    }

    public void mettreajourAgent(ActionEvent event) throws ServiceException {
        if (agent != null) {
            if (marcheId != null) {
                Marche marchelocal = marcheService.findMarcheById(marcheId);
                System.out.println(marcheId + "===============================================" + marchelocal);
                agent.setMarche(marchelocal);
            }
            if (agent.getId() == null && agent.getNom() != null && agent.getNom().length() != 0) {
                agent = agentService.saveOrUpdateAgent(agent);
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "opération reussie", agent.getNom() + " a été ajouté "));
            } else if (agent.getId() != null && agent.getNom() != null && agent.getNom().length() != 0) {
                agent = agentService.saveOrUpdateAgent(agent);
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "opération reussie", agent.getNom() + " a été mis à jour "));
            } else {
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Echec", " l'operation à échouer "));
            }
        }
        agent = new Agent();
    }

    public void supprimerAgent(ActionEvent event) throws ServiceException {
        if (agent != null && agent.getNom() != null && agent.getId() != null) {
            agentService.deleteAgent(agent.getId());
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "opération reussie", agent.getNom() + " a été supprimé "));
            agent = new Agent();
        }
    }

    public void handleRoleTypesChange() throws ServiceException {        
        if ((agent != null && agent.getRoleType() != null && agent.getRoleType().compareTo(RoleType.AGENT) == 0)) {            
            marches = marcheService.findAllMarche();
        } else {
            marches = null;
        }
    }

    public void annuler(ActionEvent event) throws ServiceException {
        agent = new Agent();
        pwd1 = new String();
        pwd2 = new String();
        marcheId = null;
    }

    public String marcheResult(Marche m) {

        String result = "";
        if (m != null) {
            result = m.getNom();
            if (m.getVille() != null && m.getVille().getNom() != null) {
                result = result + " - " + m.getVille().getNom();
            }
        }
        return result;
    }

    public IAgentService getAgentService() {
        return agentService;
    }

    public void setAgentService(IAgentService agentService) {
        this.agentService = agentService;
    }

    public Agent getAgent() {
        return agent;
    }

    public void setAgent(Agent agent) {
        this.agent = agent;
    }

    public List<Agent> getAgents() throws ServiceException {
        agents = agentService.findAllAgent();
        return agents;
    }
    
   public void setAgents(List<Agent> agents) {
        this.agents = agents;
    }

    public List<RoleType> getRoleTypes() {
        return roleTypes;
    }

    public void setRoleTypes(List<RoleType> roleTypes) {
        this.roleTypes = roleTypes;
    }

    public String getPwd1() {
        if (agent != null) {
            pwd1 = agent.getPassword();
        }
        return pwd1;
    }

    public void setPwd1(String pwd1) {

        this.pwd1 = pwd1;
    }

    public String getPwd2() {
        if (agent != null) {
            pwd1 = agent.getPassword();
        }
        return pwd2;
    }

    public void setPwd2(String pwd2) {
        this.pwd2 = pwd2;
    }

    public IMarcheService getMarcheService() {
        return marcheService;
    }

    public void setMarcheService(IMarcheService marcheService) {
        this.marcheService = marcheService;
    }

    public Long getMarcheId() {
        if (agent != null && agent.getMarche() != null) {
            marcheId = agent.getMarche().getId();
        }
        return marcheId;
    }

    public void setMarcheId(Long marcheId) {
        this.marcheId = marcheId;
    }

    public List<Marche> getMarches() throws ServiceException {
       marches =  marcheService.findAllMarche();
        return marches;
    }

    public void setMarches(List<Marche> marches) {
        this.marches = marches;
    }

}
