package fr.proxibanque.dao.exceptions;

public class DuplicatedDaoException extends Exception {
	
	private static final long serialVersionUID = 1L;

	public DuplicatedDaoException() {
		super("Erreur : probleme d'acces � la base de donnees");
		
	}

	public DuplicatedDaoException(String message) {
		super(message);
	}

	
}
