/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package atos.magiemagie.dao;

import atos.magiemagie.entity.Joueur;
import java.io.Serializable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

/**
 *
 * @author Administrateur
 */
public interface JoueurDaoCrud extends CrudRepository<Joueur, Long>{
    public Joueur findOneByPseudo(String pseudo);
    
    @Query("SELECT MAX(j.ordre)+1 FROM Joueur j JOIN j.partie p WHERE p.id=?1")
     public Long definirOrdre(long partieId); 
     
     
      @Query("SELECT j FROM Joueur j JOIN j.partie p WHERE p.id=?2 AND j.ordre=?1")
        public Joueur rechOrdre(long ordre ,long partieId);
}
