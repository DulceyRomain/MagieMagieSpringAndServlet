<%-- 
    Document   : creer-partie
    Created on : 13 juil. 2018, 11:50:20
    Author     : Administrateur
--%>

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


	<div class="affichageListe">
		<h2 id="listePartie">Création de partie</h2>
		<form method="POST">
			<label id = "nomPartie">Nom de la partie</label><br><br>
			<input id="inCrea" type="text" name="nom" placeholder="Witcher"><br><br>
			  <input type="submit"  id="buttonCreer"Créer la partie/>

		</form>
	</div>

</body>
</html>
