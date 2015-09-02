package com.royken.antic.agroprix.resource;

import com.royken.antic.agroprix.entities.Actualite;
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
@Path("actus")
public interface IActualiteResource {
    @POST
    @Produces(value = "application/json")
    Actualite createActualite(Actualite actualite);

    @GET
    @Path(value = "{id : \\d+}")
    @Produces(value = "application/json")
    Actualite getActualite(@PathParam(value = "id") long id);

    @PUT
    @Path(value = "{id : \\d+}")
    @Produces(value = "application/json")
    Actualite updateActualite(@PathParam(value = "id") long id, Actualite actualite);

    @GET
    @Produces(value = "application/json")
    List<Actualite> getAllActualite();
    
    @DELETE
    @Path(value = "{id : \\d+}")
    void deleteActualite(@PathParam(value = "id")long id);
    
}
