<%-- 
    Document   : liste-parties
    Created on : 13 juil. 2018, 11:46:58
    Author     : Administrateur
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta charset="utf-8">
        <title> Magie-Magie</title>
        <link href="css/style.css" rel="stylesheet" type="text/css"/>
    </head>
    <body>
        <div id="Entete">
            <h1 id="titre">Magie-Magie</h1>	
        </div>

        <div class="affichageListe">
            <h2 id="listePartie">Liste des Joueurs</h2>
            <form method="POST">
                <input id="buttonRejoindre" value="Démarrer la partie" type="submit">
            </form>    
            <c:forEach items="${listeJoueurs}" var="jouAct">
                <div class="dPartie">
                    <span class="partie"> ${jouAct.ordre} : ${jouAct.pseudo}   </span>  
                </div>
            </c:forEach>
        </div>
    </body>
</html>
