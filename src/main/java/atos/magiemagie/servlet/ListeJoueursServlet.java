/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package atos.magiemagie.servlet;

import atos.magiemagie.entity.Joueur;
import atos.magiemagie.entity.Joueur_;
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
@WebServlet(name = "DemarrerPartie", urlPatterns = {"/lister-joueurs"})
public class ListeJoueursServlet extends AutowireServlet {

     
     @Autowired
     private PartieService pService;
     private JoueurService jService = new JoueurService();
     
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        long idPartie =  Long.parseLong(req.getSession().getAttribute("idPartie").toString());
        pService.demarrerPartie(idPartie);
        resp.sendRedirect("demarrer-partie");
    }
    
   
    
    
     @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Long idPartie = Long.parseLong(req.getSession().getAttribute("idPartie").toString());
        List<Joueur> joueurs = jService.rechercheJoueursParID(idPartie);
         
       
       req.setAttribute("listeJoueurs", joueurs);
 
       req.getRequestDispatcher("lister-joueurs.jsp").forward(req, resp);
    }

    

}
