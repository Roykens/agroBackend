package com.royken.antic.agroprix.resource;

import com.royken.antic.agroprix.entities.Avis;
import com.royken.antic.agroprix.entities.Avis;
import java.util.List;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;

/**
 *
 * @author  Kenfack Valmy-Roi <roykenvalmy@gmail.com>
 */
@Path("avis")
public interface IAvisResource {
    
    @POST
    @Produces(value = "application/json") 
    Avis createAvis(Avis avis);

    @GET
    @Produces(value = "application/json")
    List<Avis> getAllAvis();

    @GET
    @Path(value = "{id : \\d+}")
    @Produces(value = "application/json")
    Avis getAvis(@PathParam(value = "id")long id);

    
}
