package com.royken.antic.agroprix.dao;

import com.royken.antic.agroprix.entities.Agent;
import com.royken.generic.dao.DataAccessException;
import com.royken.generic.dao.IGenericDao;
import java.io.Serializable;

/**
 *
 * @author Kenfack Valmy-Roi <roykenvalmy@gmail.com>
 */
public interface IAgentDao extends IGenericDao<Agent, Long>{
    
    public Agent findAgentByLoginAndPassword( String login, String password) throws DataAccessException;
}
