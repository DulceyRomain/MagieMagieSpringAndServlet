/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package atos.magiemagie.servlet;

import atos.magiemagie.dao.PartieDAO;
import atos.magiemagie.entity.Carte;
import atos.magiemagie.entity.Joueur;
import atos.magiemagie.service.CarteService;
import atos.magiemagie.service.JoueurService;
import atos.magiemagie.service.PartieService;
import atos.magiemagie.spring.AutowireServlet;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;

/**
 *
 * @author Administrateur
 */
@WebServlet(name = "DemarrerPartieServlet", urlPatterns = {"/demarrer-partie"})
public class DemarrerPartieServlet extends AutowireServlet {
    
    @Autowired
    private PartieService pService;
    @Autowired
    private CarteService cService ;
    @Autowired
    private JoueurService jService ;

    
    
    
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        long idPartie =  Long.parseLong(req.getSession().getAttribute("idPartie").toString());

        Long idMonJoueur = Long.parseLong(req.getSession().getAttribute("monJoueur").toString());
        req.setAttribute("monJoueur", jService.rechercheParID(idMonJoueur));
        List<Joueur> adversaires = cService.listeAutresJoueursParPartieId(idMonJoueur,idPartie);
        req.setAttribute("adversaires",adversaires);
        List<Carte> cartes = cService.rechercherCartesParId(idMonJoueur);
        req.setAttribute("listesCartes",cartes);
        int taille = cartes.size();
        req.setAttribute("nbCarte", taille);
        req.getRequestDispatcher("demarrer-partie.jsp").forward(req, resp);
    }
}
