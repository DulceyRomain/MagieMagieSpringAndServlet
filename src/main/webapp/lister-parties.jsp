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
            <h2 id="listePartie">Liste des parties</h2>
            <a id="lienbtn" href="<c:url value="/creer-partie"/>"> Créer une partie</a>  
            <c:forEach items="${listePartie}" var="partAct">
                <div class="dPartie">
                    <span class="partie"> ${partAct.id}.</span>
                    <span class="partie">${partAct.nom} : </span> 
                    <span class="partie">${partAct.joueurs.size()} Joueurs </span>
                    <a id="lienCreer" href="<c:url value="/rejoindre-partie"/>?idPartie=${partAct.id}"> Rejoindre la partie</a><br>
                </div>
            </c:forEach>
        </div>
        <div id="creerPartie">

        </div>

    </body>
</html>
