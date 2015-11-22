/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.antic.agroprix.web.beans;

import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;
import static org.apache.myfaces.view.facelets.util.Path.context;

/**
 *
 * @author dorimene
 */
@ManagedBean
@RequestScoped
public class AccueilBean {

    List<String> images = new ArrayList<String>();
    private final int nombreDePhoto = 13;

    public AccueilBean() {

    }

    @PostConstruct
    public void init() {
        int n = (int) (Math.random() * 75);
        n = n % (nombreDePhoto + 1);
        if (n == 0) {
            n = 1;
        }
        for (int i = n; i <= nombreDePhoto; i++) {
            images.add(i + ".jpg");
        }

    }

    public List<String> getImages() {
        return images;
    }

    public void setImages(List<String> images) {
        this.images = images;
    }

}
