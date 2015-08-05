package com.royken.antic.agroprix.service;

import com.royken.antic.agroprix.entities.Agent;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author Kenfack Valmy-Roi <roykenvalmy@gmail.com>
 */
@Local
public interface IAgentService {
    
    public Agent saveOrUpdateAgent(Agent agent) throws ServiceException;
    
    public Agent findAgentById(Long id) throws ServiceException;
    
    public Agent findByLoginaAndPassword(String login, String password) throws ServiceException;
    
    public void deleteAgent(Long id) throws ServiceException;
    
    public List<Agent> findAllAgent() throws ServiceException;
    
}
