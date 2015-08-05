package com.royken.antic.agroprix.resource;

import com.royken.antic.agroprix.entities.Marche;
import com.royken.antic.agroprix.entities.Produit;
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
@Path("produits")
public interface IProduitResource {
    
    @POST
    @Produces(value = "application/json") 
    Produit createProduit(Produit produit);

    @GET
    @Produces(value = "application/json")
    List<Produit> getAllProduits();

    @GET
    @Path(value = "{id : \\d+}")
    @Produces(value = "application/json")
    Produit getProduit(@PathParam(value = "id")long id);

    @PUT
    @Path(value = "{id : \\d+}")
    @Produces(value = "application/json")
    Produit updateProduit(@PathParam(value = "id")long id, Produit produit);

    @DELETE
    @Path(value = "{id : \\d+}")
    void deleteProduit(@PathParam(value = "id")long id);
    
}
