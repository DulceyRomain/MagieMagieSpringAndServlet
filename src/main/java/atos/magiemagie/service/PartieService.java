/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package atos.magiemagie.service;


import atos.magiemagie.dao.CarteDAO;
import atos.magiemagie.dao.JoueurDAO;
import atos.magiemagie.dao.JoueurDaoCrud;
import atos.magiemagie.dao.PartieDAO;
import atos.magiemagie.dao.PartieDaoCrud;
import atos.magiemagie.entity.Carte;
import atos.magiemagie.entity.Joueur;
import atos.magiemagie.entity.Partie;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


/**
 *
 * @author Administrateur
 */
@Service
public class PartieService {
    
    @Autowired
    private PartieDaoCrud PdaoCrud;
    
    @Autowired
    private JoueurDaoCrud JdaoCrud;
    
     
    @Autowired
    private CarteService cs ;
    
    /**
     * 
     * Liste les parties dont aucun joueur n'est à l'etat à la main
     * ou GAGNE
     * 
     */
    public List<Partie> listerPartiesNonDemarrees(){
        return PdaoCrud.listerPartiesNonDemarrees();
    }
    
    @Transactional
    public Partie demarrerPartie(long idPartie){
        Partie p = PdaoCrud.findOne(idPartie); // rechercher Partie par id
        
         // Vérification nb de joueurs
         
        if(p.getJoueurs().size()< 2){
           throw new RuntimeException("Nombre de joueur insuffisant") ;
        }
        else{
          //MAJ Etat Joueur
          for ( Joueur j : p.getJoueurs()){
            if(j.getOrdre()== 1L){
                j.setEtatJoueur(Joueur.typeEtatJoueur.A_LA_MAIN);
                JdaoCrud.save(j);
            }
            for(int i=0;i<7;i++){
               //Distribution des cartes
                 cs.piocherCarte(j);
            }     
            } 
        }     
        return p;
    }
    
    @Transactional
    public Partie creerNouvellePartie(String nom){
        Partie p = new Partie();
        p.setNom(nom);
        PdaoCrud.save(p);
        return p;
    }
    
 public Joueur rechJoueurQuiALaMainId(long partieId){
        Joueur j = PdaoCrud.rechJoueurQuiALaMainId(partieId);
        return j;
     
    } 
    
    
}
