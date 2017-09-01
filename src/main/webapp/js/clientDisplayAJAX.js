	$(document).ready(function () {	
		$.get("../json/bank_clients.json", displayClientList);
	});

	let ClientManager = function () {
	
		this.surname = "";
		this.firstname = "";
		this.address = "";
		this.zipcode = "";
		this.city = "";
		this.telephone = "";
		this.email = "";
		this.clientID = "";
	}

	//var clientManager;

	var clientListJSON, curClient;
	
	function updateClient() {
	
		//console.log(curClient);
	
		if(document.getElementById("nomDeFamille").value)
			curClient.surname = document.getElementById("nomDeFamille").value;
		if(document.getElementById("prenom").value)
			curClient.firstname = document.getElementById("prenom").value;
		if(document.getElementById("adresse").value)
			curClient.address = document.getElementById("adresse").value;
		if(document.getElementById("codePostal").value)
			curClient.zipcode = document.getElementById("codePostal").value;
		if(document.getElementById("ville").value)
			curClient.city = document.getElementById("ville").value;
		if(document.getElementById("tel").value)
			curClient.telephone = document.getElementById("tel").value;
		if(document.getElementById("email").value)
			curClient.email = document.getElementById("email").value;		
		
		//console.log(curClient);
		
		let clientTable = "<tr><th>Nom</th><th>Pr&eacutenom</th><th>Adresse</th><th>Code Postal</th>" +
		"<th>Ville</th><th>T&eacutel&eacutephone</th><th>Email</th><th>ID</th></tr>";
			
		clientTable += "<tr><td>" +				   
		curClient.surname + "</td><td>" +
		curClient.firstname + "</td><td>" +
		curClient.address + "</td><td>" +
		curClient.zipcode + "</td><td>" +
		curClient.city + "</td><td>" +
		curClient.telephone + "</td><td>" +
		curClient.email + "</td><td>" +
		curClient.clientID + "</td></tr>";							
		
		document.getElementById("divResultat").innerHTML = clientTable; 
		
		updateClientListDisplay(clientTable);
	}
	
	function updateClientListDisplay(clientDetails) {
			
		clientDetails += "<tr>" + 
		"<td><divclass=\"form-group\"><labelfor=\"nomDeFamille\"></label><input type=\"text\"class=\"form-control\"id=\"nomDeFamille\"></div></td>" + 
		"<td><divclass=\"form-group\"><labelfor=\"prenom\"></label><input type=\"text\"class=\"form-control\"id=\"prenom\"></div></td>" +
		"<td><divclass=\"form-group\"><labelfor=\"adresse\"></label><input type=\"text\"class=\"form-control\"id=\"adresse\"></div></td>" + 
		"<td><divclass=\"form-group\"><labelfor=\"codePostal\"></label><input type=\"text\"class=\"form-control\"id=\"codePostal\"></div></td>" + 
		"<td><divclass=\"form-group\"><labelfor=\"ville\"></label><input type=\"text\"class=\"form-control\"id=\"ville\"></div></td>" + 
		"<td><divclass=\"form-group\"><labelfor=\"tel\"></label><input type=\"text\"class=\"form-control\"id=\"tel\"></div></td>" + 
		"<td><divclass=\"form-group\"><labelfor=\"email\"></label><input type=\"text\"class=\"form-control\"id=\"email\"></div></td>" + 
		"<td><divclass=\"form-group\"><labelfor=\"id\"></label><input type=\"text\"class=\"form-control\"id=\"id\" disabled=\"disabled\" value=" + 
		curClient.clientID + 
		"></div></td></tr>";
		
		document.getElementById("divResultat").innerHTML = clientDetails; 
	}
	
	function displaySelectedClient() {
			
		let clientTable = "<tr><th>Nom</th><th>Pr&eacutenom</th><th>Adresse</th><th>Code Postal</th>" +
		"<th>Ville</th><th>T&eacutel&eacutephone</th><th>Email</th><th>ID</th></tr>";
		
		var radioBtnValue = $("input[type=radio]:checked").val();		
		console.log("radioBtnValue " + radioBtnValue);
		
		for (i = 0; i < clientListJSON.length; i++) { 
		
			let item = clientListJSON[i];
			
			console.log("item.clientID " + item.clientID);
			
			if(parseInt(item.clientID) === parseInt(radioBtnValue)) {
											
				clientTable += "<tr><td>" +				   
				item.surname + "</td><td>" +
				item.firstname + "</td><td>" +
				item.address + "</td><td>" +
				item.zipcode + "</td><td>" +
				item.city + "</td><td>" +
				item.telephone + "</td><td>" +
				item.email + "</td><td>" +
				item.clientID + "</td></tr>";	

				curClient = item;				
				
				updateClientListDisplay(clientTable); 
				
				return;
			}
		}		
	}
	
	let displayClientList = function (data, status){
				
	//	clientManager = new ClientManager();	
    	
		clientListJSON = data;
		
		let table="<tr><th>Nom</th><th>Pr&eacutenom</th><th>Adresse</th><th>Code Postal</th>" +
		"<th>Ville</th><th>T&eacutel&eacutephone</th><th>Email</th><th>ID</th><th>S&eacutelection</th></tr>";
		
		for (i = 0; i < data.length; i++) { 
			let item = data[i];
			
			table += "<tr><td>" +				   
			item.surname + "</td><td>" +
			item.firstname + "</td><td>" +
			item.address + "</td><td>" +
			item.zipcode + "</td><td>" +
			item.city + "</td><td>" +
			item.telephone + "</td><td>" +
			item.email + "</td><td>" +
			item.clientID + "</td><td>" +
			"<div class=\"radio\"><label><input type=\"radio\" name=\"optradio\" onclick=\"displaySelectedClient();\"value=" + 
			item.clientID + 
			"></label></div>" + 
			"</td><tr>";					
		}
		
		let elementDiv = document.getElementById("divResultat");
		elementDiv.innerHTML = table;			
	}
	
/*	
	function call(){		
		$.get("../json/bank_clients.json", displayClientList);
	}
*/
