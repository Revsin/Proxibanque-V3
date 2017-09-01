package fr.proxibanque.dao;

import java.sql.SQLException;
import java.util.List;

import fr.proxibanque.dao.exceptions.DaoException;
import fr.proxibanque.dao.exceptions.DoesNotExistDaoException;
import fr.proxibanque.dao.exceptions.DuplicatedDaoException;
import fr.proxibanque.dao.exceptions.VirementNotFoundDaoException;
import fr.proxibanque.metier.Virement;

public interface VirementDao {

	// Déclaration des méthodes de l'interface

	public Virement searchVirementByID(int VirementID) throws DaoException, DoesNotExistDaoException, SQLException;

	public List<Virement> getAllVirement() throws DaoException;

	public void createVirement(Virement virement) throws DaoException, DuplicatedDaoException;

	public void upDateVirement(Virement virement) throws DaoException, DoesNotExistDaoException;

	public void deleteVirement(Virement virement)
			throws DaoException, DoesNotExistDaoException, VirementNotFoundDaoException;

}
