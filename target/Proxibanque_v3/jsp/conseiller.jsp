<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ page import="fr.proxibanque.metier.BankExecutiveUser"%> 
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html  lang="fr">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">

<meta name="viewport" content="width=device-width, initial-scale=1">

<title>Espace Conseiller</title>

<link rel="stylesheet"	href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
<link rel="stylesheet" type="text/css" href="../css/style.css">

<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
<script
	src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>


</head>
<body>

	<%	
		BankExecutiveUser beu = (BankExecutiveUser) session.getAttribute("bankExecUser");
	%>

	<nav class="navbar navbar-inverse" style="background-color: rgb(15, 5, 107); border-bottom: none;">
		<div class="container-fluid">
			<div class="navbar-header">
				<button type="button" class="navbar-toggle" data-toggle="collapse"
					data-target="#myNavbar">
					<span class="icon-bar"></span> <span class="icon-bar"></span> <span
						class="icon-bar"></span>
				</button>
				<a class="navbar-brand" href="Images/logo.png"></a>
			</div>
			<div class="collapse navbar-collapse" id="myNavbar">
				<ul class="nav navbar-nav">
					<li class="active"><a href="index.html">Accueil</a></li>
					<li><a href="conseiller.html">Gérer un client</a></li>
					<li><a href="virexterne.html">Effectuer un virement externe</a></li>
				</ul>
				<ul class="nav navbar-nav navbar-right">
					
				</ul>
			</div>
		</div>
	</nav>

<div class="container">
<div class="well" style="background-color:  rgb(89,247,223)"> 
<div id="Globalconseiller">
		<div id="gaucheconseiller">
			<h1>Espace conseiller</h1>
			<p id="nomConseiller"></p>	
		</div>
		<div id="droiteconseiller">
	
		<% include file="../Images/martin.jpg"%>

		<!--  <img id="photoConseiller" src="" alt="agence" class="img-circle" style="width:50%;height: 50%;">-->
		</div>	
	</div>
</div>

<div>
	<h2>Liste de vos Clients</h2>
</div>
		<hr></hr>
		<table id="tableResultat" class="alert alert-info table table-striped"></table>
		<div class="form-group">
			<label for="sel1">Selection du client </label>
			<select class="form-control" id="sel1">
			</select>
		</div>
		<form method='POST' action='/EditerServlet'>
		<button name='button' type='submit' value="xxxxx"> Editer</button>
		</form>
		<form method='POST' action='/AfficherServlet'>
		<button name='button' type='submit' value="xxxxx"> Afficher les comptes</button>
		</form>
		<form method='POST' action='/VirementServlet'>
		<button name='button' type='submit' value="xxxxx"> Effectuer un virement interne</button>
		</form>
		</form>

		<br>
		<br>
		<form method="POST" action="LogOut">
				<div class="col-md-1">
	  				<label for="exec">Executive: <c:out value="${login}"/></label>	  					  				
	  				<input type="submit" class="btn btn-primary" value="Log out">  					
				</div>
			</form>		
		</div>
	
	<footer class="container-fluid text-center">
		<p>© 2017 Pentagone Team</p>
	</footer>

</body>
</html>