package com.royken.antic.agroprix.service.impl;

import com.royken.antic.agroprix.entities.Agent;
import com.royken.antic.agroprix.entities.dao.IAgentDao;
import com.royken.antic.agroprix.service.IAgentService;
import com.royken.antic.agroprix.service.ServiceException;
import com.royken.generic.dao.DataAccessException;
import java.util.Collections;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.inject.Named;

/**
 *
 * @author Kenfack Valmy-Roi <roykenvalmy@gmail.com>
 */
@Stateless
@Named
public class AgentServiceImpl implements IAgentService {

    @Inject
    private IAgentDao agentDao;

    public IAgentDao getAgentDao() {
        return agentDao;
    }

    public void setAgentDao(IAgentDao agentDao) {
        this.agentDao = agentDao;
    }
    
    

    @Override
    public Agent saveOrUpdateAgent(Agent agent) throws ServiceException {
        try {
            if (agent.getId() == null) {
                return agentDao.create(agent);
            }
            else{
                return agentDao.update(agent);
            }
        } catch (DataAccessException ex) {
            Logger.getLogger(AgentServiceImpl.class.getName()).log(Level.SEVERE, null, ex);
             throw new ServiceException(ex.getMessage(), ex);
        }

    }

    @Override
    public Agent findAgentById(Long id) throws ServiceException {
        try {
            return agentDao.findById(id);
        } catch (DataAccessException ex) {
            Logger.getLogger(AgentServiceImpl.class.getName()).log(Level.SEVERE, null, ex);
            throw new ServiceException(ex.getMessage(), ex);
        }
    }

    @Override
    public void deleteAgent(Long id) throws ServiceException {
        try {
            Agent agent = agentDao.findById(id);
            if(agent != null){
                agentDao.delete(agent);
            }
            else{
                throw new ServiceException("L'agent cherché est introuvable");
            }
        } catch (DataAccessException ex) {
            Logger.getLogger(AgentServiceImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public List<Agent> findAllAgent() throws ServiceException {
        try {
            return agentDao.findAll();
        } catch (DataAccessException ex) {
            Logger.getLogger(AgentServiceImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
        return Collections.EMPTY_LIST;
    }

}
