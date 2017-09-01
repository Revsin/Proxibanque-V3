<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ page import="fr.proxibanque.metier.BankExecutiveUser"%> 
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<html lang="fr">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Client Manager</title>
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.0/jquery.min.js"></script>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
<link rel="stylesheet" type="text/css" href="../css/proxibanque6.css">

<script src="../js/clientDisplayAJAX.js"></script> 

</head>
<body>

	<%	
		BankExecutiveUser beu = (BankExecutiveUser) session.getAttribute("bankExecUser");
	%>

<!-- 	<a href="LogOut">Log Out</a>	 -->

	<div class="container">
		<div class="row col-md-12 jumbotron">
			<h1>Client List</h1>
			<form method="POST" action="UpdateClientInfo">
				<div class="col-md-6">
					<br>
					<a href="html/MoneyTransferAJAX.html" class="btn btn-primary" role="button">Effectuer un virement</a>	  
		  			<input type="submit" class="btn btn-primary" value="Modifier">	
		  		</div>
			</form>
			
			<form method="POST" action="LogOut">
				<div class="col-md-1">
	  				<label for="exec">Executive: <c:out value="${login}"/></label>	  					  				
	  				<input type="submit" class="btn btn-primary" value="Log out">  					
				</div>
			</form>				
		</div>
	</div>
	
	
	<div class="table-responsive">
		<table id="divResultat"  class="alert alert-active table table-striped"></table>		
	</div>
		
</body>
</html>