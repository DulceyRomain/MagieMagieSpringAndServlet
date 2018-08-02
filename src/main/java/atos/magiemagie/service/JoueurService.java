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
import atos.magiemagie.entity.Joueur;
import static atos.magiemagie.entity.Joueur_.pseudo;
import atos.magiemagie.entity.Partie;
import java.util.List;
import java.util.Scanner;
import javax.persistence.EntityManager;
import javax.persistence.Persistence;
import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author Administrateur
 */
@Service
public class JoueurService {
    
    @Autowired
    private PartieDaoCrud PdaoCrud;
    
    @Autowired
    private JoueurDaoCrud JdaoCrud;
    
    @Autowired
    private CarteDaoCrud CdaoCrud;
    
    private JoueurDAO jDao = new JoueurDAO();
   
   
   
    
    @Transactional
    public Joueur rejoindrePartie(String pseudo,String avatar, long idPartie){
       
        // Recherche si le joueur existe déjà
        
        Joueur joueur = JdaoCrud.findOneByPseudo(pseudo);
        if(joueur==null){
          //le joueur n'existe pas encire
          joueur = new Joueur();
          joueur.setPseudo(pseudo);
        }
        joueur.setAvatar(avatar);
        joueur.setEtatJoueur(Joueur.typeEtatJoueur.PAS_LA_MAIN);
  
        if(JdaoCrud.definirOrdre(idPartie)  == null){
             joueur.setOrdre(1L);
        }else{
            joueur.setOrdre(JdaoCrud.definirOrdre(idPartie));
        }
       
        
        //Associe le joueur à la partie et vice-versa (JPA relations bidirectionellements)
        Partie p = PdaoCrud.findOne(idPartie)  ;
        
        joueur.setpartie(p);
        List<Joueur> listeJoueurs= p.getJoueurs();
        listeJoueurs.add(joueur);
        
        if(joueur.getId()==null){
            JdaoCrud.save(joueur);
         }
        else{
            JdaoCrud.save(joueur);
        }
        return joueur;   
    }
    
    
    public Joueur ciblerUnJoueur (){
       Scanner s = new Scanner(System.in);
        System.out.print("Sélectionner le joueur à cibler : ");
        String pseudo = s.nextLine();
        Joueur joueur =  JdaoCrud.findOneByPseudo(pseudo);
        
        return joueur;
        
    }
    
    public void passerSonTour(String pseudo, long idPartie){
        //appel du joueur 
        Partie p = PdaoCrud.findOne(idPartie);
        Joueur j =  JdaoCrud.findOneByPseudo(pseudo);
        
       // pioche d'une carte
        CarteService cService = new CarteService();
        cService.piocherCarte(j);
        this.passerAuJoueurSuivant(idPartie);  
}
    public Joueur rechercheParID(long idJoueur) {
        Joueur j = JdaoCrud.findOne(idJoueur);
        return j;
    }
    
    public List<Joueur> rechercheJoueursParID(long idPartie) {
       List<Joueur> joueurs = jDao.rechercheJoueursParID(idPartie);
       return joueurs;
    }
     @Transactional
    public void passerAuJoueurSuivant(long idPartie){
        
        Joueur joueurALaMain = PdaoCrud.rechJoueurQuiALaMainId(idPartie);
        Partie p = PdaoCrud.findOne(idPartie);
        
        //Determine si les autres joueurs ont perdu
        //et passe le joueur a l'état gagné
        // puis quitte la fonction
        if(jDao.determinerSiResteUnJoueur(idPartie) ){
            joueurALaMain.setEtatJoueur(Joueur.typeEtatJoueur.GAGNE);
            JdaoCrud.save(joueurALaMain);
            System.out.println("YOU WIN, FATALITY");
            return ;
        }
        
        joueurALaMain.setEtatJoueur(Joueur.typeEtatJoueur.PAS_LA_MAIN);
         JdaoCrud.save(joueurALaMain);
        Joueur jSuivant = new Joueur();
        long ordre = joueurALaMain.getOrdre()+ 1L; // Passer au  joueur suivant
        long max = p.getJoueurs().size();
        //Reviens à 0 quand le dernier joueur passe au joueur suivant
        if(ordre >max){
            jSuivant = JdaoCrud.rechOrdre(1L, idPartie);
            if( jSuivant == null){
            
         }   
        }else{
            jSuivant = JdaoCrud.rechOrdre(ordre, idPartie);
        }
        //Si l'état du joueur est différent de PAS_LA_MAIN
      
        
      
        while(jSuivant.getEtatJoueur() != Joueur.typeEtatJoueur.PAS_LA_MAIN){
                if(jSuivant.getEtatJoueur() == Joueur.typeEtatJoueur.SOMMEIL_PROFOND){
                    jSuivant.setEtatJoueur(Joueur.typeEtatJoueur.PAS_LA_MAIN);
                     JdaoCrud.save(jSuivant);
                    ordre++;
                    if(ordre >max){
                    jSuivant = jDao.rechOrdre(1L, idPartie);
                    }else{
                    jSuivant = jDao.rechOrdre(ordre, idPartie);
                 }
                }else if(jSuivant.getEtatJoueur() == Joueur.typeEtatJoueur.PERDU){
                    ordre++;
                    if(ordre >max){
                    jSuivant = jDao.rechOrdre(1L, idPartie);
                    }else{
                    jSuivant = jDao.rechOrdre(ordre, idPartie);              
                    }
                 
                    
        }
}
       jSuivant.setEtatJoueur(Joueur.typeEtatJoueur.A_LA_MAIN);
       JdaoCrud.save(jSuivant); 
}
}
