<%-- 
    Document   : creer-joueur
    Created on : 13 juil. 2018, 11:57:16
    Author     : Administrateur
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta charset="utf-8">
        <link rel="stylesheet"  href="css/style.css">
        <title> Magie-Magie</title>
    </head>
    <body>
       

        <div id=perso>
        </div>


        <div class="creaJoueur">
            <div class="dPartie">

                <div class="blockAdversaires">
                    <c:forEach items="${adversaires}" var="adv">  
                     <hr>
                            <c:if test="${adv.etatJoueur eq 'A_LA_MAIN'}"> 
                                <div class="adversaire">
                                    <h2 id="nbCartes">Nombre de cartes : ${adv.cartes.size()}</h2>                                
                                    <img id="aLaMain" class="witcher" src="images/${adv.avatar}.png">
                                    <h2 class="partie">${adv.pseudo}</h2>
                                </div>
                            </c:if>
                            <c:if test="${adv.etatJoueur eq 'PAS_LA_MAIN'}"> 
                                <div class="adversaire">
                                    <h2 id="nbCartes">Nombre de cartes : ${adv.cartes.size()}</h2> 
                                    <div id="pasLaMain">
                                    <img class="witcher" src="images/${adv.avatar}.png">
                                    </div>
                                    <h2 class="partie">${adv.pseudo}</h2>
                                </div>
                            </c:if>    
                         <hr>
                    </c:forEach><br>    
                </div>
                <hr>
                <c:if test="${monJoueur.etatJoueur eq 'A_LA_MAIN'}"> 
                    <div id=perso>
                        <h2 id="nbCartes">Nombre de carte : ${nbCarte}</h2>
                        <img id="aLaMainMonPerso" class="monJoueurAvatar" src="images/${monJoueur.avatar}.png">
                        <h2 id="nom">${monJoueur.pseudo}</h2>
                        <form>
                            <a id="lienCreer" href="<c:url value="/passer-tour"/>">Lancer un sort</a>
                            <a id="lienCreer" href="<c:url value="/passer-tour"/>">Passer son tour</a>
                        </form>
                    </div>
                </c:if>
                <c:if test="${monJoueur.etatJoueur eq 'PAS_LA_MAIN'}"> 
                    <div id=perso>
                        <h2 id="nbCartes">Nombre de carte : ${nbCarte}</h2>
                        <img id="pasLaMainPerso" class="monJoueurAvatar" src="images/${monJoueur.avatar}.png">
                        <h2 id="nom">${monJoueur.pseudo}</h2>
                         <a id="lienCreer" href="<c:url value="/passer-tour"/>">Passer son tour</a>
                    </div>
                </c:if>
                

               
                 <div id="afficheCarte">
                <c:forEach items="${listesCartes}" var="cartes">          
                         <img class="cartes" src="images/${cartes.ingredient}.png">  
                </c:forEach>
                 </div>
               
            </div>

        </div>


    </body>
</html>
