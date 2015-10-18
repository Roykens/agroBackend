/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.antic.agroprix.web.beans;

import com.royken.antic.agroprix.entities.Categorie;
import com.royken.antic.agroprix.entities.Marche;
import com.royken.antic.agroprix.entities.PrixProduitMarche;
import com.royken.antic.agroprix.entities.Produit;
import com.royken.antic.agroprix.entities.Ville;
import com.royken.antic.agroprix.service.ICategorieService;
import com.royken.antic.agroprix.service.IMarcheService;
import com.royken.antic.agroprix.service.IPrixService;
import com.royken.antic.agroprix.service.IProduitService;
import com.royken.antic.agroprix.service.IVilleService;
import com.royken.antic.agroprix.service.ServiceException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.event.ActionEvent;
import org.primefaces.model.chart.Axis;
import org.primefaces.model.chart.AxisType;
import org.primefaces.model.chart.CategoryAxis;
import org.primefaces.model.chart.LineChartModel;
import org.primefaces.model.chart.LineChartSeries;

/**
 *
 * @author root
 */
@ManagedBean
@RequestScoped
public class PrixDesProduitBean {

    @EJB
    IVilleService villeService;
    @EJB
    IMarcheService marcheService;
    @EJB
    ICategorieService categorieService;
    @EJB
    IProduitService produitService;
    @EJB
    IPrixService prixService;
    Long villeId = null;
    Long marcheId = null;
    Long categorieId = null;
    Long produitId = null;
    List<Produit> produits = new ArrayList<Produit>();
    List<Marche> marches;
    List<Ville> villes = new ArrayList<Ville>();
    Date dateDebut = new Date();
    Date dateFin = new Date();
    private LineChartModel model ;
    List<PrixProduitMarche> prixProduitMarches;
    PrixProduitMarche prixProduitMarche;
    private  DateFormat df = new SimpleDateFormat("dd/MM/yyyy");
    private int debut = 0;
    
    private int fin = 20;

    public void handleVilleChange(ActionEvent event) throws ServiceException {
        if (villeId != null) {
            marches = marcheService.findMarcheByVille(villeId);
            //  produits = new ArrayList<Produit>();
            prixProduitMarches = new ArrayList<PrixProduitMarche>();
            System.out.println("" + marches);
            marches = marcheService.findByProduitAndVille(produitId, villeId);
        }
    }
    public PrixDesProduitBean() {
        model = new LineChartModel();
    }


    public void handleMarcheChange() throws ServiceException {

       // Marche marche = marcheService.findMarcheById(marcheId);
       // produits = produitService.findProduitByMarche(marche.getId());

    }

    public void handleProduitChange() throws ServiceException {
        if (produitId != null) {
            villes = villeService.findAllByProduit(produitId);
            prixProduitMarches = new ArrayList<PrixProduitMarche>();
            // marches = marcheService.findByProduitAndVille(produitId, villeId);
            marches = new ArrayList<Marche>();
//        if (marcheId != null) {
//            produits = produitService.findProduitByMarche(marcheId);
//        }
    }
    }

    public void handleCategorieChange() throws ServiceException {
        if (categorieId != null) {
            System.out.println("Je suis ici");
            System.out.println("L'id " + categorieId);
            produits = produitService.findByCategorie(categorieId);
            for (Produit produit : produits) {
                System.out.println(produit);
            }
        }
    }

    public void voirPrixProduit() throws ServiceException {
        if (marcheId != null && produitId != null) {
            prixProduitMarche = prixService.findByMarche(marcheId, produitId);
        }
        villeId = null;
        marcheId = null;
        categorieId = null;
        produitId = null;
        produits = null;
        marches = null;

    }

    public int getDebut() {
        return debut;
    }

    public void setDebut(int debut) {
        this.debut = debut;
    }

    public int getFin() {
        return fin;
    }

    public void setFin(int fin) {
        this.fin = fin;
    }
    
    
    
    public void plus(){
        fin += 20;
    }

    public String voirVariationPrixProduit() throws ServiceException {
        System.out.println("ttttttttttttttttttttttttttttttttt");
        System.out.println("");
        if (marcheId != null && produitId != null && dateFin.after(dateDebut)) {
          //  prixProduitMarches = prixService.findByProduitAndMarcheBetweenDate(marcheId, produitId, dateDebut, dateFin);
            prixProduitMarches = prixService.findByProduitAndMarcheBetweenRange(marcheId, produitId, debut, fin);
        }
        marcheId = null;
        produitId = null;
        return "accueil";
    }

    public String annuler() throws ServiceException {
        prixProduitMarche = new PrixProduitMarche();
        return "prix_des_produits";
    }

    public String afficherPrixProduit() throws ServiceException {
        voirPrixProduit();
        String result = "";
        if (prixProduitMarche != null && prixProduitMarche.getProduit() != null && prixProduitMarche.getProduit().getNom() != null && prixProduitMarche.getMarche() != null && prixProduitMarche.getMarche().getNom() != null) {
            result = "Le produit << " + prixProduitMarche.getProduit().getNom() + " >> au " + prixProduitMarche.getMarche().getNom();
            if (prixProduitMarche.getMarche().getVille() != null && prixProduitMarche.getMarche().getVille().getId() != null) {
                result += " de " + prixProduitMarche.getMarche().getVille().getNom();
            }
            result += " coûte " + prixProduitMarche.getPrix() + " FCFA";
        } else {
            result = "Aucun prix associé au produit";
        }
        return result;
    }

    public IVilleService getVilleService() {
        return villeService;
    }

    public void setVilleService(IVilleService villeService) {
        this.villeService = villeService;
    }

    public IMarcheService getMarcheService() {
        return marcheService;
    }

    public void setMarcheService(IMarcheService marcheService) {
        this.marcheService = marcheService;
    }

    public ICategorieService getCategorieService() {
        return categorieService;
    }

    public void setCategorieService(ICategorieService categorieService) {
        this.categorieService = categorieService;
    }

    public IProduitService getProduitService() {
        return produitService;
    }

    public void setProduitService(IProduitService produitService) {
        this.produitService = produitService;
    }

    public List<Produit> getProduits() throws ServiceException {

        if (marcheId != null) {
            produits = produitService.findProduitByMarche(marcheId);
            return produits;
        }
        if (categorieId != null) {
            produits = produitService.findByCategorie(categorieId);
            return produits;
        }
        produits = produitService.findAllProduit();
        return produits;
    }

    public void setProduits(List<Produit> produits) {
        this.produits = produits;
    }

    public List<Marche> getMarches() throws ServiceException {
        marches = marcheService.findAllMarche();
        if (villeId != null) {
            marches = marcheService.findMarcheByVille(villeId);
        }
        return marches;
    }

    public void setMarches(List<Marche> marches) {
        this.marches = marches;
    }

    public IPrixService getPrixService() {
        return prixService;
    }

    public void setPrixService(IPrixService prixService) {
        this.prixService = prixService;
    }

    public Long getVilleId() {
        return villeId;
    }

    public void setVilleId(Long villeId) {
        this.villeId = villeId;
    }

    public Long getMarcheId() {
        return marcheId;
    }

    public void setMarcheId(Long marcheId) {
        this.marcheId = marcheId;
    }

    public Long getCategorieId() {
        return categorieId;
    }

    public void setCategorieId(Long categorieId) {
        this.categorieId = categorieId;
    }

    public Long getProduitId() {
        return produitId;
    }

    public void setProduitId(Long produitId) {
        this.produitId = produitId;
    }

    public List<PrixProduitMarche> getPrixProduitMarches() {
        return prixProduitMarches;
    }

    public void setPrixProduitMarches(List<PrixProduitMarche> prixProduitMarches) {
        this.prixProduitMarches = prixProduitMarches;
    }

    public PrixProduitMarche getPrixProduitMarche() {
        return prixProduitMarche;
    }

    public void setPrixProduitMarche(PrixProduitMarche prixProduitMarche) {
        this.prixProduitMarche = prixProduitMarche;
    }

    public Date getDateDebut() {
        return dateDebut;
    }

    public void setDateDebut(Date dateDebut) {
        this.dateDebut = dateDebut;
    }

    public Date getDateFin() {
        return dateFin;
    }

    public void setDateFin(Date dateFin) {
        this.dateFin = dateFin;
    }

    public LineChartModel getModel() throws ServiceException {      
        int max = 0;
        if (prixProduitMarches != null && !prixProduitMarches.isEmpty()) {
            PrixProduitMarche p = prixProduitMarches.get(0);
            LineChartSeries series1 = new LineChartSeries();
            if (p.getProduit() != null) {
                series1.setLabel("" + p.getProduit().getNom());
                if (p.getMarche() != null && p.getMarche().getNom() != null && p.getMarche().getNom().length() != 0) {
                    series1.setLabel("" + p.getProduit().getNom() + " au " + p.getMarche().getNom());
                    if (p.getMarche().getVille() != null && p.getMarche().getVille().getNom() != null && p.getMarche().getVille().getNom().length() != 0) {
                        series1.setLabel("" + p.getProduit().getNom() + " au " + p.getMarche().getNom() + " de " + p.getMarche().getVille().getNom());
                    }
                }
            }            
            for (PrixProduitMarche ppm : prixProduitMarches) {
                series1.set("" + df.format(ppm.getDatePrix()), ppm.getPrix());
                if (max < ppm.getPrix()) {
                    max = ppm.getPrix();
                }
                System.out.println(""+ppm);
            }
            model.addSeries(series1);
            model.setTitle("VARIATION DES PRIX DU " + df.format(dateDebut) + " AU " + df.format(dateFin));
            model.setLegendPosition("n");
            Axis yAxis = model.getAxis(AxisType.Y);
            yAxis.setMin(0);
            yAxis.setMax(max + 100);
            yAxis.setLabel("Prix");
            model.setShowPointLabels(true);
            model.getAxes().put(AxisType.X, new CategoryAxis("Date"));
        }else{
        return new LineChartModel();
        }
        return model;
    }

    public void setModel(LineChartModel model) {
        this.model = model;
    }

    public DateFormat getDf() {
        return df;
    }

    public void setDf(DateFormat df) {
        this.df = df;
    }

 

}
