	$(document).ready(function () {	
		$.get("../json/account_list.json", displayAccountList);
	});

	let ClientManager = function () {
	
		this.surname = "";
		this.firstname = "";
		this.address = "";
		this.zipcode = "";
		this.city = "";
		this.telephone = "";
		this.email = "";
		this.clienntID = "";
	}

	//var clientManager;
	
	var accountListJSON;
	var sourceAccount, targetAccount;
	let transferAmount;
	let success;
	let moneyTransferErrorType;

	let displayAccountList = function (data, status){
				
	//	clientManager = new ClientManager();	
    	
		accountListJSON = data;
		
		let table="<tr><th>Num&eacutero de Compte</th><th>Solde</th><th>Date de Cr&eacuteation</th><th>Type de Compte</th></tr>";
		
		for (i = 0; i < data.length; i++) { 
			let item = data[i];
			
			table += "<tr><td>" +				   
			item.accountNumber + "</td><td>" +
			item.accountBalance + "</td><td>" +
			item.creationDate + "</td><td>" +
			item.accountType + "</td></tr>";						
		}
		
		let elementDiv = document.getElementById("divResultat");
		elementDiv.innerHTML = table;			
	}
	
	function moneyTransfer() {
	
		if(success === false) {
			
			moneyTransferErrorType = 1;
			console.log("Comptes non trouvés! Erreur no. " + moneyTransferErrorType);
			window.open("ErreurAccountsNotFound.html", "_self"); 
			return;
		}
			
		if(parseFloat(transferAmount) < 0) {

			moneyTransferErrorType = 2;
			console.log("Montant négatif! Erreur no. " + moneyTransferErrorType);
			window.open("ErreurNegativeAmount.html", "_self"); 
			return;
		}
			
		if(sourceAccount.accountNumber === targetAccount.accountNumber) {
			
			moneyTransferErrorType = 3;
			console.log("Comptes identiques! Erreur no. " + moneyTransferErrorType);
			window.open("ErreurIdenticalAccounts.html", "_self"); 
			return;			
		}
			
		if(parseFloat(sourceAccount.accountBalance) < parseFloat(transferAmount)) {
			
			moneyTransferErrorType = 4;
			console.log("Solde insuffisant! Erreur no. " + moneyTransferErrorType);
			window.open("ErreurInsufficientBalance.html", "_self"); 
			return;			
		}
		
		targetAccount.accountBalance = parseFloat(targetAccount.accountBalance) + parseFloat(transferAmount);
		sourceAccount.accountBalance = parseFloat(sourceAccount.accountBalance) - parseFloat(transferAmount);	
		
		moneyTransferErrorType = 0;
		console.log("Virement réussi! ");
		
		console.log(
					sourceAccount.accountNumber + " " +
					sourceAccount.accountBalance + " " +
					sourceAccount.creationDate + " " +
					sourceAccount.accountType);
					
		console.log(
					targetAccount.accountNumber + " " +
					targetAccount.accountBalance + " " +
					targetAccount.creationDate + " " +
					targetAccount.accountType);	
	}
	
	function displayMoneyTransferResult() {
	
		if(success === false)
			return;
	
		let table="<tr><th>Num&eacutero de Compte</th><th>Solde</th><th>Date de Cr&eacuteation</th><th>Type de Compte</th></tr>";

		table += "<tr><td>" +				   
		sourceAccount.accountNumber + "</td><td>" +
		sourceAccount.accountBalance + "</td><td>" +
		sourceAccount.creationDate + "</td><td>" +
		sourceAccount.accountType + "</td></tr>";						

		table += "<tr><td>" +				   
		targetAccount.accountNumber + "</td><td>" +
		targetAccount.accountBalance + "</td><td>" +
		targetAccount.creationDate + "</td><td>" +
		targetAccount.accountType + "</td></tr>";						

		let elementDiv = document.getElementById("divResultat");
		elementDiv.innerHTML = table;		
	
	}
	
	function retrieveAccounts() {
	
		let sourceAccountNumber = document.getElementById("sourceAccount").value;
		let targetAccountNumber = document.getElementById("targetAccount").value;
		transferAmount = document.getElementById("amount").value;
		
		let success1 = false, success2 = false;	
		console.log("Compte à débiter: " + sourceAccountNumber + " Compte à créditer: " + targetAccountNumber + " Montant: " + transferAmount);
		
		for (i = 0; i < accountListJSON.length; i++) { 
			let item = accountListJSON[i];
	
			if(item.accountNumber === sourceAccountNumber) {
				sourceAccount = item;
				
				console.log(
					sourceAccount.accountNumber + " " +
					sourceAccount.accountBalance + " " +
					sourceAccount.creationDate + " " +
					sourceAccount.accountType);
					
				success1 = true;
				
			//	console.log("Success 1 ? " + success1);
			}
	
			if(item.accountNumber === targetAccountNumber) {
				targetAccount = item;

				console.log(
					targetAccount.accountNumber + " " +
					targetAccount.accountBalance + " " +
					targetAccount.creationDate + " " +
					targetAccount.accountType);	

				success2 = true;
				
			//	console.log("Success 2 ? " + success2);
			}
/*			
			console.log(
			item.accountNumber + " " +
			item.accountBalance + " " +
			item.creationDate + " " +
			item.accountType)
*/			
			
		}
		
		success = success1 && success2;
//		console.log("Success ? " + success);
		
		moneyTransfer();
		if(moneyTransferErrorType == 0)
			displayMoneyTransferResult();
		
		return success;
	}
	
/*	
	function call(){		
		$.get("../json/account_list.json", displayAccountList);
	}
*/
