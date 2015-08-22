package com.royken.antic.agroprix.resource.impl;

import com.royken.antic.agroprix.entities.Agent;
import com.royken.antic.agroprix.entities.Categorie;
import com.royken.antic.agroprix.entities.projection.AgentMarche;
import com.royken.antic.agroprix.resource.IAuthentificationResource;
import com.royken.antic.agroprix.service.IAgentService;
import com.royken.antic.agroprix.service.ServiceException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.EJB;
import javax.ws.rs.Path;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.Response;

/**
 *
 * @author Kenfack Valmy-Roi <roykenvalmy@gmail.com>
 */
@Path("/authentification")
public class AuthentificationResource implements IAuthentificationResource{
    
    @EJB
    private IAgentService agentService;

    public IAgentService getAgentService() {
        return agentService;
    }

    public void setAgentService(IAgentService agentService) {
        this.agentService = agentService;
    }
    
    

    @Override
    public AgentMarche getCategorie(String login, String password) {
        
        try {
            AgentMarche agentMarche = new AgentMarche();
            Agent agent = agentService.findByLoginaAndPassword(login, password);
            if(agent != null){
                agentMarche.setAgent(agent);
                System.out.println("Je suis ici resource");
                agentMarche.setReponse("succes");
                agentMarche.setIdMarche(agent.getMarche().getId());
                return agentMarche;
            }
            agentMarche.setReponse("error");
            return agentMarche;
        } catch (ServiceException ex) {
            Logger.getLogger(AuthentificationResource.class.getName()).log(Level.SEVERE, null, ex);
        }
        throw new WebApplicationException(Response.Status.NOT_FOUND);
    }
    
}
