/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package atos.magiemagie.service;

import atos.magiemagie.dao.CarteDAO;
import atos.magiemagie.dao.CarteDaoCrud;
import atos.magiemagie.dao.JoueurDAO;
import atos.magiemagie.dao.JoueurDaoCrud;
import atos.magiemagie.dao.PartieDAO;
import atos.magiemagie.dao.PartieDaoCrud;
import atos.magiemagie.entity.Carte;
import atos.magiemagie.entity.Joueur;
import atos.magiemagie.entity.Partie;
import java.util.List;
import java.util.Random;
import java.util.Scanner;
import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author Administrateur
 */
@Service
public class CarteService {

     @Autowired
    private PartieDaoCrud PdaoCrud;
    
    @Autowired
    private JoueurDaoCrud JdaoCrud;
    
    @Autowired
    private CarteDaoCrud CdaoCrud;
    
    private CarteDAO cDao = new CarteDAO();
   
    
     

    public List<Joueur> listeAutresJoueursParPartieId(long idJoueurAExclure, long idPartie) {

        List<Joueur> joueurs = cDao.listeAutresJoueursParPartieId(idJoueurAExclure, idPartie);
        
        return joueurs;
    
    }
    @Transactional
    public Carte donnerUneCarte (long idPartie){
        
        Joueur joueurALaMain = PdaoCrud.rechJoueurQuiALaMainId(idPartie);
        JoueurService jService = new JoueurService();
        Joueur j = jService.ciblerUnJoueur();
        long  idJoueur = PdaoCrud.rechJoueurQuiALaMainId(idPartie).getId();
        
        Scanner s = new Scanner(System.in);
        System.out.print("Sélectionner la carte à cibler : ");
        long carteADonner = s.nextLong();
        Carte carte = cDao.rechercherUneCarte(carteADonner, idJoueur);
        joueurALaMain.getCartes().remove(carte);
        j.getCartes().add(carte);
        carte.setJoueurProprietaire(j);
        CdaoCrud.save(carte);
        
        
        return carte;
    }
    
    @Transactional
    public void sortSommeil() {
        JoueurService jService = new JoueurService();
        Joueur j = jService.ciblerUnJoueur();
        j.setEtatJoueur(Joueur.typeEtatJoueur.SOMMEIL_PROFOND);
        JdaoCrud.save(j);
    }

    
     public List<Carte> rechercherCartesParId (long idJoueur){
       
        List<Carte> cartes = cDao.rechercherCartesParId(idJoueur);
        return cartes;
    }
    
    @Transactional
    public void sortinvisibilite(long idPartie, long idJoueurAExclure ) {
        Partie p = new Partie();
        List<Joueur> joueurs = cDao.listeAutresJoueursParPartieId(idJoueurAExclure, idPartie);
        for (Joueur joueur : joueurs) {

            List<Carte> cartes = joueur.getCartes();
            int taille = cartes.size();
            Random r = new Random();
            int alea = r.nextInt(taille);
            Carte carteAPrendre = cartes.get(alea);
            joueur.getCartes().remove(alea);
            Joueur joueurALaMain = PdaoCrud.rechJoueurQuiALaMainId(idPartie);
            joueurALaMain.getCartes().add(carteAPrendre);
            carteAPrendre.setJoueurProprietaire(joueurALaMain);
            CdaoCrud.save(carteAPrendre);
        }
    }
    @Transactional
    public void sortPhiltreAmour(long idPartie) {
        JoueurService jService = new JoueurService();
        Joueur j = jService.ciblerUnJoueur();
        List<Carte> cartes = j.getCartes();
        int taille = (cartes.size())/2;

        if(taille == 1){
            cartes.clear();
            j.setEtatJoueur(Joueur.typeEtatJoueur.PERDU);
        }
        
        for (int i = 0; i < taille ; i++) {
            Random r = new Random();
            int alea = r.nextInt(taille);
            Carte carteAPrendre =  cartes.get(alea);
            j.getCartes().remove(alea);
            Joueur joueurALaMain = PdaoCrud.rechJoueurQuiALaMainId(idPartie);
            joueurALaMain.getCartes().add(carteAPrendre);
            carteAPrendre.setJoueurProprietaire(joueurALaMain);
             CdaoCrud.save(carteAPrendre);
        }

    }

    
    @Transactional
    public void sortHypnose(long idPartie) {
        JoueurService jService = new JoueurService();
        Joueur j = jService.ciblerUnJoueur();
        Joueur joueurALaMain = PdaoCrud.rechJoueurQuiALaMainId(idPartie);
        List<Carte> cartes = j.getCartes();
        int taille = cartes.size();
        
        this.donnerUneCarte(idPartie);
        
        for (int i = 0; i < 3 ; i++) {
            Random r = new Random();
            int alea = r.nextInt(taille);
            Carte carteAPrendre =  cartes.get(alea);
            j.getCartes().remove(alea);
            joueurALaMain.getCartes().add(carteAPrendre);
            carteAPrendre.setJoueurProprietaire(joueurALaMain);
            CdaoCrud.save(carteAPrendre);
        }
    }

    public List<Joueur> sortDivination(long idJoueurAExclure, long idPartie) {

        List<Joueur> joueurs = cDao.listeAutresJoueursParPartieId(idJoueurAExclure, idPartie);

        return joueurs;

    }

    
  
    public Carte piocherCarte(Joueur joueur) {

        Carte carte = new Carte();
        Carte.typeCarte[] tabTypesCartes = Carte.typeCarte.values();
        Random r = new Random();
        int n = r.nextInt(tabTypesCartes.length);
        carte.setIngredient(tabTypesCartes[n]);
        carte.setJoueurProprietaire(joueur);

        CdaoCrud.save(carte);
        return carte;
    }

    public void lancerSort(long idJoueurLanceur, long idPartie, long idCarte1, long idCarte2) {

        Joueur joueur = new Joueur();
        // Détermine le sort
        Carte carte1 = cDao.rechercherUneCarte(idCarte1, idJoueurLanceur);
        Carte carte2 = cDao.rechercherUneCarte(idCarte2, idJoueurLanceur);

        String compareCarte1 = carte1.getIngredient().toString();
        String compareCarte2 = carte2.getIngredient().toString();

        if (compareCarte1 == "BAVE_DE_CRAPAUD" && compareCarte2 == "CORNE_DE_LICORNE" || compareCarte1 == "CORNE_DE_LICORNE" && compareCarte2 == "BAVE_DE_CRAPAUD") {
            //Sort Invisibilité
            this.sortinvisibilite(idPartie,idJoueurLanceur);
        }
        if (compareCarte1 == "MANDRAGORE" && compareCarte2 == "CORNE_DE_LICORNE" || compareCarte1 == "CORNE_DE_LICORNE" && compareCarte2 == "MANDRAGORE") {
            this.sortPhiltreAmour(idPartie);
        }
        if (compareCarte1 == "BAVE_DE_CRAPAUD" && compareCarte2 == "LAPIS_LAZULI" || compareCarte1 == "LAPIS_LAZULI" && compareCarte2 == "BAVE_DE_CRAPAUD") {
            this.sortHypnose(idPartie);
        }
        if (compareCarte1 == "AILE_DE_CHAUVE_SOURIS" && compareCarte2 == "LAPIS_LAZULI" || compareCarte1 == "LAPIS_LAZULI" && compareCarte2 == "AILE_DE_CHAUVE_SOURIS") {
            this.sortDivination(idJoueurLanceur, idPartie);
        }
        if (compareCarte1 == "AILE_DE_CHAUVE_SOURIS" && compareCarte2 == "MANDRAGORE" || compareCarte1 == "MANDRAGORE" && compareCarte2 == "AILE_DE_CHAUVE_SOURIS") {
            this.sortSommeil();

        }

        // Supprime les 2 cartes de l'émetteur
        cDao.SupprCarte(carte1);
        cDao.SupprCarte(carte2);

     /*   if (joueur.getCartes().isEmpty()) {
            joueur.setEtatJoueur(Joueur.typeEtatJoueur.PERDU);
//          jDao.modifier(joueur);
            System.out.println("LOSER");
        }*/
        JoueurService jService = new JoueurService();
        jService.passerAuJoueurSuivant(idPartie);
       

    }

}
