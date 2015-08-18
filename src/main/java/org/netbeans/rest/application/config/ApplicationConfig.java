/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.netbeans.rest.application.config;

import java.util.Set;
import javax.ws.rs.core.Application;

/**
 *
 * @author Kenfack Valmy-Roi <roykenvalmy@gmail.com>
 */
@javax.ws.rs.ApplicationPath("/api")
public class ApplicationConfig extends Application {

    @Override
    public Set<Class<?>> getClasses() {
        Set<Class<?>> resources = new java.util.HashSet<Class<?>>();
        addRestResourceClasses(resources);
        return resources;
    }

    /**
     * Do not modify addRestResourceClasses() method.
     * It is automatically populated with
     * all resources defined in the project.
     * If required, comment out calling this method in getClasses().
     */
    private void addRestResourceClasses(Set<Class<?>> resources) {
        resources.add(com.royken.antic.agroprix.resource.impl.CategoriesResource.class);
        resources.add(com.royken.antic.agroprix.resource.impl.MarcheResource.class);
        resources.add(com.royken.antic.agroprix.resource.impl.PrixResource.class);
        resources.add(com.royken.antic.agroprix.resource.impl.ProduitResource.class);
        resources.add(com.royken.antic.agroprix.resource.impl.VilleResource.class);
    }
    
}
