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
@WebServlet(name = "listerPartieServlet", urlPatterns = {"/lister-parties"})
public class ListerPartieServlet extends AutowireServlet {
    @Autowired
    private PartieService pService;
    
     @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
       //recup liste des cat en db
        List<Partie> parties = pService.listerPartiesNonDemarrees();
     

       // Set attrib req avec liste cat
       req.setAttribute("listePartie", parties);

       //forward vers vue
       req.getRequestDispatcher("lister-parties.jsp").forward(req, resp);
    }

}
