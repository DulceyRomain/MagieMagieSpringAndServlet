/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package atos.magiemagie.servlet;

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
@WebServlet(name = "PasseSonTourServlet", urlPatterns = {"/passer-tour"})
public class PasseSonTourServlet extends AutowireServlet {
    
    @Autowired
    private JoueurService jService ;
    @Autowired
    private PartieService pService ;
    
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
       long idPartie =  Long.parseLong(req.getSession().getAttribute("idPartie").toString());  
       String joueurActif = pService.rechJoueurQuiALaMainId(idPartie).getPseudo();
       jService.passerSonTour(joueurActif, idPartie);
       resp.sendRedirect("demarrer-partie");
    }

    
}
