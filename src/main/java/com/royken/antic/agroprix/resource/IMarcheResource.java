package com.royken.antic.agroprix.resource;

import com.royken.antic.agroprix.entities.Marche;
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
@Path("marches")
public interface IMarcheResource {
    @POST
    @Produces(value = "application/json") 
    Marche createMarche(Marche marche);

    @GET
    @Produces(value = "application/json")
    List<Marche> getAllMarches();

    @GET
    @Path(value = "{id : \\d+}")
    @Produces(value = "application/json")
    Marche getMarche(@PathParam(value = "id")long id);

    @PUT
    @Path(value = "{id : \\d+}")
    @Produces(value = "application/json")
    Marche updateMarche(@PathParam(value = "id")long id, Marche marche);

    @DELETE
    @Path(value = "{id : \\d+}")
    void deleteMarche(@PathParam(value = "id")long id);
    
   
    
    
}
