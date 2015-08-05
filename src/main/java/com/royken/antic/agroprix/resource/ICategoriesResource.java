package com.royken.antic.agroprix.resource;

import com.royken.antic.agroprix.entities.Categorie;
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
@Path("categories")
public interface ICategoriesResource {
    
    @POST
    @Produces(value = "application/json")
    Categorie createCategorie(Categorie categorie);

    @GET
    @Path(value = "{id : \\d+}")
    @Produces(value = "application/json")
    Categorie getCategorie(@PathParam(value = "id") long id);

    @PUT
    @Path(value = "{id : \\d+}")
    @Produces(value = "application/json")
    Categorie updateCategorie(@PathParam(value = "id") long id, Categorie categorie);

    @GET
    @Produces(value = "application/json")
    List<Categorie> getAllCategorie();
    
    @DELETE
    @Path(value = "{id : \\d+}")
    void deleteCategorie(@PathParam(value = "id")long id);
    
    @GET
    @Path(value = "{id : \\d+}/produits")
    @Produces(value = "application/json")
    List<Produit> getAllProduit(@PathParam(value = "id") long idCategorie);
    
}
