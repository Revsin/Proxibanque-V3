package fr.proxibanque.dao;

import java.sql.SQLException;
import java.util.List;

import fr.proxibanque.dao.exceptions.DaoException;
import fr.proxibanque.dao.exceptions.DoesNotExistDaoException;
import fr.proxibanque.dao.exceptions.DuplicatedDaoException;
import fr.proxibanque.metier.Client;

public interface ClientDao {

	public Client searchById(int id) throws DaoException, DoesNotExistDaoException, SQLException;

	public List<Client> searchByBankExecutiveId(int id) throws DaoException, DoesNotExistDaoException, SQLException;;

	public List<Client> searchAll() throws DaoException;

	public void create(Client client) throws DaoException, DuplicatedDaoException;

	public void update(Client client) throws DaoException, DoesNotExistDaoException;

	public void remove(int id) throws DaoException, DoesNotExistDaoException;

}
