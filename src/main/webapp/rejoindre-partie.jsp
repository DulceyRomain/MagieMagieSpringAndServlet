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
	<div id="Entete">
		<h1 id="titre">Magie-Magie</h1>
		
		
	</div>



	<div class="creaJoueur">
		<h2 id="listePartie">Création de joueur</h2>
		<div class="dPartie">
                    <form method="POST" action="rejoindre-partie">
			<span class="partie">Choisissez votre pseudo</span>
                        <input class="inPersoSaisie" type="text" name="pseudo" placeholder="Geralt"><br>
                        <span class="partie">Choisissez votre avatar</span><br>
                        <label for="ciri"><img class="witch2" src="images/ciri.png"></label>
                        <input id="ciri" class="inPerso" type="radio" value="ciri" name="avatar">
                        <label for="keira"><img class="witch3" src="images/keira.png"></label>
                        <input id="keira" class="inPerso" type="radio"  value="keira" name="avatar" >
                        <label for="triss"><img class="witch" src="images/triss.png"></label>
                        <input id="triss" class="inPerso" type="radio"  value="triss" name="avatar" >
                        <label for="yennefer"><img class="witch"  src="images/yennefer.png"></label>
                        <input id="yennefer" class="inPerso" type="radio" value="yennefer" name="avatar"><br>
                        <input id="buttonRejoindre" value="Crée le personnage et rejoindre la partie" type="submit">
                    </form>
		</div>
		
	</div>


</body>
</html>
