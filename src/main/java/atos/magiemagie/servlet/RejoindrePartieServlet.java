/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package atos.magiemagie.servlet;

import atos.magiemagie.entity.Joueur;
import atos.magiemagie.entity.Partie;
import atos.magiemagie.service.JoueurService;
import atos.magiemagie.service.PartieService;
import atos.magiemagie.spring.AutowireServlet;
import java.io.IOException;
import java.io.PrintWriter;
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
@WebServlet(name = "RejoindrePartieServlet", urlPatterns = {"/rejoindre-partie"})
public class RejoindrePartieServlet extends AutowireServlet {
    @Autowired
    private PartieService pService;
    @Autowired
    private JoueurService jService;
    
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
       String pseudo = req.getParameter("pseudo");
       String avatar = req.getParameter("avatar");
       long idPartie = Long.parseLong(req.getSession().getAttribute("idPartie").toString());
       req.getSession().setAttribute("monJoueur", jService.rejoindrePartie(pseudo, avatar, idPartie).getId());
       resp.sendRedirect("lister-joueurs");
       
    }

    
    
   @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("rejoindre-partie.jsp").forward(req, resp);
        
    }

}
