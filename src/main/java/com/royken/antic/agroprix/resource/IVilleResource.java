package com.royken.antic.agroprix.resource;

import com.royken.antic.agroprix.entities.Ville;
import java.util.List;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;

/**
 *
 * @author Kenfack Valmy-Roi <roykenvalmy@gmail.com>
 */
@Path("/villes")
public interface IVilleResource {
    @POST
    @Produces(value = "application/json") 
    Ville createVille(Ville ville);

    @GET
    @Produces(value = "application/json")
    List<Ville> getAllVilles();

    @GET
    @Path(value = "{id : \\d+}")
    @Produces(value = "application/json")
    Ville getVille(@PathParam(value = "id")long id);

    @PUT
    @Path(value = "{id : \\d+}")
    @Produces(value = "application/json")
    Ville updateVille(@PathParam(value = "id")long id, Ville ville);

    @DELETE
    @Path(value = "{id : \\d+}")
    void deleteVille(@PathParam(value = "id")long id);
    
}
