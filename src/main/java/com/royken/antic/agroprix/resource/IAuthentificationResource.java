package com.royken.antic.agroprix.resource;

import com.royken.antic.agroprix.entities.Categorie;
import com.royken.antic.agroprix.entities.projection.AgentMarche;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;

/**
 *
 * @author Kenfack Valmy-Roi <roykenvalmy@gmail.com>
 */
@Path("authentification")
public interface IAuthentificationResource {
    
    @GET
    @Path(value = "{login}/{password}")
    @Produces(value = "application/json")
    AgentMarche getCategorie(@PathParam(value = "login") String login,@PathParam(value = "password") String password);
}
