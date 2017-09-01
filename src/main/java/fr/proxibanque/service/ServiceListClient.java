package fr.proxibanque.service;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import fr.proxibanque.dao.ClientDao;
import fr.proxibanque.dao.exceptions.DaoException;
import fr.proxibanque.dao.exceptions.DoesNotExistDaoException;
import fr.proxibanque.dao.jdbc.ClientDaoImpl;
import fr.proxibanque.metier.Client;

public class ServiceListClient {

	public List<Client> sendBackClientList(int BankExecutiveId) {
		List<Client> clients = new ArrayList<>();

		try {
			ClientDao clientDao = new ClientDaoImpl();
			clients = clientDao.searchByBankExecutiveId(BankExecutiveId);

			return clients;

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
