package fr.proxibanque.dao;

import java.sql.SQLException;
import java.util.List;

import fr.proxibanque.dao.exceptions.DaoException;
import fr.proxibanque.dao.exceptions.DoesNotExistDaoException;
import fr.proxibanque.dao.exceptions.DuplicatedDaoException;
import fr.proxibanque.metier.BasicAccount;

/**
 * @author SEBASTIENM Interface DAO pour les comptes
 */
public interface BasicAccountDao {

	public void create(BasicAccount account) throws DaoException, DuplicatedDaoException;

	public void remove(int id) throws DaoException, DoesNotExistDaoException;

	public List<BasicAccount> searchAll() throws DaoException;

	public BasicAccount searchById(int id) throws DaoException, DoesNotExistDaoException, SQLException;

	public void update(BasicAccount account) throws DaoException, DoesNotExistDaoException;

	public List<BasicAccount> searchByClientId(int id) throws DaoException, DoesNotExistDaoException, SQLException;
}
