<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
    <%@ page import="" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Edition du client</title>
</head>
<body>
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
<div class="well" style="background-color: rgb(89,247,223,)"> 
		<h1>Edition de votre client</h1>
</div>
		<hr></hr>
		<%
		client u = (client) session.getAttribute("utilisateur");
	%>
 <h2> Votre client <c:out value="${nom}"/> <c:out value="${prenom}"/> a bien été modifié </h2>
 
 <table class="alert alert-info table table-striped">
  <tr>
    <th>Nom</th><th>Prénom</th><th>Email</th>
    <th>Adresse</th><th>Code Postal</th><th>Ville</th>
  </tr>
  <tr>
    <td> <c:out value="${nom}"/> </td><td><c:out value="${prenom}"/> </td>
    <td> <c:out value="${email}"/> </td><td><c:out value="${adresse}"/> </td>
    <td> <c:out value="${codePostal}"/> </td><td><c:out value="${ville}"/> </td>
  </tr>
 
</table> 
 	
	</div>

<footer class="container-fluid text-center">
		<p>© 2017 Pentagone Team</p>
	</footer>
	
</body>
</html>