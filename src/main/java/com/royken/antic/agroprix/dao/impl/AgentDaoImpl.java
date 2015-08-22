package com.royken.antic.agroprix.dao.impl;

import com.royken.antic.agroprix.entities.Agent;
import com.royken.antic.agroprix.dao.IAgentDao;
import com.royken.antic.agroprix.entities.Agent_;
import com.royken.antic.agroprix.entities.projection.AgentMarche;
import com.royken.generic.dao.DataAccessException;
import com.royken.generic.dao.impl.GenericDao;
import java.io.Serializable;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

/**
 *
 * @author Kenfack Valmy-Roi <roykenvalmy@gmail.com>
 */
public class AgentDaoImpl extends GenericDao<Agent, Long> implements IAgentDao{

    @Override
    public Agent findAgentByLoginAndPassword(String login, String password) throws DataAccessException {
        CriteriaBuilder cb = getManager().getCriteriaBuilder();
        CriteriaQuery<Agent> cq = cb.createQuery(Agent.class);
        Root<Agent> aroot = cq.from(Agent.class);
       // cq.multiselect(aroot.get(Agent_.login),aroot.get(Agent_.password),aroot.get(Agent_.phone),aroot.get(Agent_.adresse));
        cq.where(cb.and(cb.like(aroot.get(Agent_.login), login), cb.like(aroot.get(Agent_.password), password)));
        try {
            return getManager().createQuery(cq).getSingleResult();
        } catch (Exception e) {
        }
        
        return null;
    }
    
}
