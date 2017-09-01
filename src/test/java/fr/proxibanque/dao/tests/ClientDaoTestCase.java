package fr.proxibanque.dao.tests;

import static org.junit.Assert.*;

import java.sql.SQLException;
import java.util.List;

import org.junit.Test;

import fr.proxibanque.dao.ClientDao;
import fr.proxibanque.dao.exceptions.DaoException;
import fr.proxibanque.dao.exceptions.DoesNotExistDaoException;
import fr.proxibanque.dao.jdbc.ClientDaoImpl;
import fr.proxibanque.metier.Client;

public class ClientDaoTestCase {

	@Test
	public final void testSearchAll() {

		try {
			ClientDao clientDao = new ClientDaoImpl();
			List<Client> liste = clientDao.searchAll();
			assertNotNull(liste);
			assertEquals(10, liste.size());
			for (Client item : liste) {
				System.out.println(item);
			}

		} catch (DaoException e) {
			fail(e.getMessage());
		}

	}

	@Test
	public final void testCreate() {

		ClientDao clientDao = new ClientDaoImpl();
		try {
			Client client = new Client(11, "OUAHIDI", "Hafid", "17 rue droite", "06300", "Nice", "0619643172", 1);

			clientDao.create(client);

		} catch (Exception e) {
			e.printStackTrace();
			fail(e.getMessage());
		}
	}

	@Test
	public final void testUpdate() {

		ClientDao clientDao = new ClientDaoImpl();
		try {
			Client client = new Client(11, "HUGO", "Victor", "75000", "1 Place de la République", "Paris", "0612345678",
					3);

			clientDao.update(client);

		} catch (Exception e) {
			e.printStackTrace();
			fail(e.getMessage());
		}
	}

	@Test
	public final void testRemove() {

		ClientDao clientDao = new ClientDaoImpl();
		try {
			clientDao.remove(11);

		} catch (Exception e) {
			e.printStackTrace();
			fail(e.getMessage());
		}
	}

	@Test(expected = DoesNotExistDaoException.class)
	public final void testRemoveWithException() throws DoesNotExistDaoException {

		ClientDao clientDao = new ClientDaoImpl();
		try {
			clientDao.remove(150);

		} catch (DaoException e) {
			e.printStackTrace();
			fail(e.getMessage());
		}
	}

	@Test
	public final void testSearchById() throws DoesNotExistDaoException, SQLException {

		ClientDao clientDao = new ClientDaoImpl();
		Client client = null;
		int Id = 8;
		try {

			client = clientDao.searchById(Id);
			assertNotNull(client);
			System.out.println("Client avec ID# " + Id + " " + client.toString());

		} catch (DaoException e) {
			e.printStackTrace();
			fail(e.getMessage());
		}
	}

	@Test
	public final void testSearchByBankExecutiveId() throws DaoException, DoesNotExistDaoException, SQLException {
		ClientDao clientDao = new ClientDaoImpl();
		int id = 2;
		try {
			List<Client> liste = clientDao.searchByBankExecutiveId(id);
			assertNotNull(liste);
			assertEquals(4, liste.size());
			for (Client item : liste) {
				System.out.println(item);
			}

		} catch (DaoException e) {
			e.printStackTrace();
			fail(e.getMessage());
		} catch (DoesNotExistDaoException e) {
			e.printStackTrace();
			fail(e.getMessage());
		} catch (SQLException e) {
			e.printStackTrace();
			fail(e.getMessage());
		}

	}

}
