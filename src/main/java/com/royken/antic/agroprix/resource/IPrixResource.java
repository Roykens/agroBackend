package com.royken.antic.agroprix.resource;

import com.royken.antic.agroprix.entities.PrixProduitMarche;
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
@Path("prix")
public interface IPrixResource {
    @POST
    @Path(value = "{etatPrix}")
    @Produces(value = "application/json")
    PrixProduitMarche createPrix(@PathParam(value = "etatPrix") String etat,PrixProduitMarche ppmZ);

    @GET
    @Path(value = "{id : \\d+}")
    @Produces(value = "application/json")
    PrixProduitMarche getPrixProduitMarche(@PathParam(value = "id") long id);

    @PUT
    @Path(value = "{id : \\d+}/{etatPrix}")
    @Produces(value = "application/json")
    PrixProduitMarche updatePrix(@PathParam(value = "id") long id,@PathParam(value = "etatPrix") String etat ,PrixProduitMarche ppm);

    @GET
    @Produces(value = "application/json")
    List<PrixProduitMarche> getAllPrix();
    
    @DELETE
    @Path(value = "{id : \\d+}")
    void deletePrix(@PathParam(value = "id")long id);
    
    @GET
    @Path(value = "{idProduit : \\d+}/{idMarche : \\d+}")
    @Produces(value = "application/json")
    PrixProduitMarche getPrix(@PathParam(value = "idProduit") long idProduit, @PathParam(value = "idMarche") long idMarche);
    
    @GET
    @Path(value = "{idProduit : \\d+}/{idMarche : \\d+}/{date1}/{date2}")
    @Produces(value = "application/json")
    List<PrixProduitMarche> getPrixBetweenDates(@PathParam(value = "idProduit") long idProduit, @PathParam(value = "idMarche") long idMarche, @PathParam(value = "date1") String date1, @PathParam(value = "date2") String date2);
    
    
    
}
