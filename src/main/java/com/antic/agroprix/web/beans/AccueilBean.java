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

/**
 *
 * @author dorimene
 */



@ManagedBean
@RequestScoped
public class AccueilBean {

    List<String> images = new ArrayList<String>();

    public AccueilBean() {

    }

    @PostConstruct
    public void init() {
        int n = (int)(Math.random()*75); 
        n=n%12;
        if(n==0){
           n=1; 
        }
        for (int i = n; i <= 11; i++) {            
            images.add(i+".jpg");  
            System.out.println("---- "+i);
        }
    }

    public List<String> getImages() {
        return images;
    }

    public void setImages(List<String> images) {
        this.images = images;
    }

}

