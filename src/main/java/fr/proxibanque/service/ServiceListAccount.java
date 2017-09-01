package fr.proxibanque.service;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import fr.proxibanque.dao.BasicAccountDao;
import fr.proxibanque.dao.exceptions.DaoException;
import fr.proxibanque.dao.exceptions.DoesNotExistDaoException;
import fr.proxibanque.dao.jdbc.BasicAccountDaoImpl;
import fr.proxibanque.metier.BasicAccount;

/**
 * Classe de traitement de la Servlet renvoyant la liste des comptes d'un client
 * 
 * @author adminl
 *
 */

public class ServiceListAccount {

	public List<BasicAccount> sendBackAccountList(int id) {
		List<BasicAccount> accounts = new ArrayList<BasicAccount>();

		try {
			BasicAccountDao accountDao = new BasicAccountDaoImpl();
			accounts = accountDao.searchByClientId(id);

			return accounts;

		} catch (DoesNotExistDaoException e) {
			System.out.println(e.getMessage());
			return null;

		} catch (DaoException e) {
			System.out.println(e.getMessage());
			return null;

		} catch (SQLException e) {
			System.out.println(e.getMessage());
			return null;
		}
	}

}
