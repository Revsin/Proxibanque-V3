package fr.proxibanque.dao.tests;

import static org.junit.Assert.*;

import java.sql.SQLException;
import java.util.List;

import org.junit.Test;

import fr.proxibanque.dao.BankExecutiveDao;
import fr.proxibanque.dao.BasicAccountDao;
import fr.proxibanque.dao.exceptions.DaoException;
import fr.proxibanque.dao.exceptions.DoesNotExistDaoException;
import fr.proxibanque.dao.jdbc.BankExecutiveDaoImpl;
import fr.proxibanque.dao.jdbc.BasicAccountDaoImpl;
import fr.proxibanque.metier.BankExecutiveUser;
import fr.proxibanque.metier.BasicAccount;

public class BasicAccountDaoTestCase {

	// @Test
	public void testCreate() {
		BasicAccountDao bankAccountDao = new BasicAccountDaoImpl();
		try {
			BasicAccount bankAccount = new BasicAccount("22", "2012-05-08", "current", 2000, 1);

			bankAccountDao.create(bankAccount);

		} catch (Exception e) {
			e.printStackTrace();
			fail(e.getMessage());
		}
	}

	@Test
	public void testRemove() {
		BasicAccountDao bankAccountDao = new BasicAccountDaoImpl();
		try {
			bankAccountDao.remove(21);

		} catch (Exception e) {
			e.printStackTrace();
			fail(e.getMessage());
		}
	}

	@Test
	public void testSearchAll() {
		try {
			BasicAccountDao bankAccountDao = new BasicAccountDaoImpl();
			List<BasicAccount> liste = bankAccountDao.searchAll();
			assertNotNull(liste);
			assertEquals(20, liste.size());
			for (BasicAccount item : liste) {
				System.out.println(item);
			}

		} catch (DaoException e) {
			fail(e.getMessage());
		}
	}

	@Test
	public void testSearchByClientId() {
		try {
			BasicAccountDao bankAccountDao = new BasicAccountDaoImpl();
			List<BasicAccount> liste = bankAccountDao.searchByClientId(2);
			assertNotNull(liste);
			assertEquals(3, liste.size());
			for (BasicAccount item : liste) {
				System.out.println(item);
			}

		} catch (DaoException e) {
			e.printStackTrace();
			fail(e.getMessage());
		} catch (DoesNotExistDaoException e) {
			e.printStackTrace();
			fail(e.getMessage());
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			fail(e.getMessage());
		}
	}

	@Test
	public void testSearchById() {
		BasicAccountDao bankAccountDao = new BasicAccountDaoImpl();
		BasicAccount bankAccount = null;
		int Id = 13;
		try {

			bankAccount = bankAccountDao.searchById(Id);
			assertNotNull(bankAccount);
			System.out.println("BankExecutiveUser avec ID# " + Id + " " + bankAccount.toString());

		} catch (DaoException e) {
			e.printStackTrace();
			fail(e.getMessage());
		} catch (DoesNotExistDaoException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			fail(e.getMessage());
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			fail(e.getMessage());
		}
	}

	@Test
	public void testUpdate() {
		BasicAccountDao bankAccountDao = new BasicAccountDaoImpl();
		try {
			BasicAccount bankAccount = new BasicAccount("10", "2012-05-08", "current", 2000, 1);

			bankAccountDao.update(bankAccount);

		} catch (Exception e) {
			e.printStackTrace();
			fail(e.getMessage());
		}
	}

}
