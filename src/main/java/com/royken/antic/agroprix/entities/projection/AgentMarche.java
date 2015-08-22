package com.royken.antic.agroprix.entities.projection;

import com.royken.antic.agroprix.entities.Agent;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Kenfack Valmy-Roi <roykenvalmy@gmail.com>
 */
@XmlRootElement(name = "adminMarche")
@XmlAccessorType(XmlAccessType.FIELD)
public class AgentMarche {
    
    private String reponse;
    
    private Agent agent;
    
    private Long idMarche;

    public String getReponse() {
        return reponse;
    }

    public void setReponse(String reponse) {
        this.reponse = reponse;
    }

    public Agent getAgent() {
        return agent;
    }

    public void setAgent(Agent agent) {
        this.agent = agent;
    }

    public Long getIdMarche() {
        return idMarche;
    }

    public void setIdMarche(Long idMarche) {
        this.idMarche = idMarche;
    }
    
    
    
    
}
