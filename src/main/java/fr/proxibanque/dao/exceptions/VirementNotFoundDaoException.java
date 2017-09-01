package fr.proxibanque.dao.exceptions;

public class VirementNotFoundDaoException extends Exception {

	// Exception Virement

	public VirementNotFoundDaoException() {
		super("Erreur : probleme d'acces à la base de donnees");
	}

	public VirementNotFoundDaoException(String message) {
		super(message);
	}

}
