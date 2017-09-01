	let AuthenticationManager = function (paramLogin, paramPwd) {
	
		this.login = paramLogin;
		this.pwd = paramPwd;
		
		console.log("paramLogin " + this.login);
		console.log("paramPwd " + this.pwd);
	}

	var authMan;

	let authenticateBankExec = function (data, status){
		
		let param1 = document.getElementById("login").value;
		let param2 = document.getElementById("pwd").value;
		authMan = new AuthenticationManager(param1, param2);	
    	
		for (i = 0; i < data.length; i++) { 
			let item = data[i];
			if(item.login !== authMan.login)
				continue;
			else if(item.password === authMan.pwd) {
				console.log("Identification réussie: conseiller no. " + item.execID);
				window.open("html/ClientListDisplayAJAX.html", "_self"); 	
				return true;
			} 			
		}			
		
		console.log("Echec identification");
		window.open("html/Erreur Authentification.html", "_self"); 	
		return false;		
		
	}
	
	function call(){
	//	console.log("Appel AJAX");
		$.get("./json/bank_exec_creds.json", authenticateBankExec);
	}
