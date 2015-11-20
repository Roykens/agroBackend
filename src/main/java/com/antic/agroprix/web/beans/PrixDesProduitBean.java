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
//    Date dateDebut = new Date();
//    Date dateFin = new Date();
    private LineChartModel model;
    List<PrixProduitMarche> prixProduitMarches;
    PrixProduitMarche prixProduitMarche;
    private DateFormat df = new SimpleDateFormat("dd-MM-yyyy");
    private int debut = 0;

    private int fin = 20;

    public PrixDesProduitBean() {
        model = new LineChartModel();
    }

    public void handleVilleChange() throws ServiceException {
        if (villeId != null) {
            marches = marcheService.findMarcheByVille(villeId);
            if (produitId != null) {
                prixProduitMarches = new ArrayList<PrixProduitMarche>();
                marches = marcheService.findByProduitAndVille(produitId, villeId);
            }
        }
    }

    public void handleProduitChange() throws ServiceException {
        if (produitId != null) {

            villes = villeService.findAllByProduit(produitId);
            System.out.println("" + villes);
            prixProduitMarches = new ArrayList<PrixProduitMarche>();

        }
    }

    public void handleCategorieChange() throws ServiceException {
        if (categorieId != null) {
            produits = produitService.findByCategorie(categorieId);

        }
    }

//    public void voirPrixProduit() throws ServiceException {
//        if (marcheId != null && produitId != null) {
//            prixProduitMarche = prixService.findByMarche(marcheId, produitId);
//        }
//    }
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

    public void plus() {
        fin += 20;
    }

//    public String voirVariationPrixProduit() throws ServiceException {
//        if (marcheId != null && produitId != null) {
//            //  prixProduitMarches = prixService.findByProduitAndMarcheBetweenDate(marcheId, produitId, dateDebut, dateFin);
//            prixProduitMarches = prixService.findByProduitAndMarcheBetweenRange(marcheId, produitId, debut, fin);
//        }
//
//        return "accueil";
//    }
    public String voirEvolution() throws ServiceException {
        if (marcheId != null && produitId != null) {
            prixProduitMarches = prixService.findByProduitAndMarcheBetweenRange(marcheId, produitId, debut, fin);
        }
        return annuler();
    }

    public String annuler() throws ServiceException {
        prixProduitMarche = new PrixProduitMarche();
        villeId = null;
        marcheId = null;
        produitId = null;
        categorieId = null;
        return "prix_des_produits";
    }

    public String afficherPrixProduit() throws ServiceException {
        String result = "";
        if (marcheId != null && produitId != null) {
            prixProduitMarche = prixService.findByMarche(marcheId, produitId);
        }        
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

//        if (marcheId != null) {
//            produits = produitService.findProduitByMarche(marcheId);
//            return produits;
//        }
//        if (categorieId != null) {
//            produits = produitService.findByCategorie(categorieId);
//            return produits;
//        }
        if (categorieId == null || categorieId == 0) {
            return produitService.findAllProduit();
        }
        return produits;
    }

    public List<Ville> getVilles() throws ServiceException {
        if (produitId == null) {
            return villeService.findAllVille();
        }
        return villes;
    }

    public void setVilles(List<Ville> villes) {
        this.villes = villes;
    }

    public void setProduits(List<Produit> produits) {
        this.produits = produits;
    }

    public List<Marche> getMarches() throws ServiceException {

//        if (villeId != null) {
//            marches = marcheService.findMarcheByVille(villeId);
//        }       
        if (villeId == null) {
            return marcheService.findAllMarche();
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
            }
            model.addSeries(series1);
            model.setTitle("GRAPHE DE VARITION DES PRIX");
            model.setLegendPosition("n");
            Axis yAxis = model.getAxis(AxisType.Y);
            yAxis.setMin(0);
            yAxis.setMax(max + 100);
            yAxis.setLabel("Prix");
            model.setShowPointLabels(true);
            model.getAxes().put(AxisType.X, new CategoryAxis("Date"));
            return model;
        } else {
            return new LineChartModel();
        }

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
