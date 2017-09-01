package fr.proxibanque.dao.tests;

import static org.junit.Assert.*;

import java.sql.SQLException;
import java.util.List;

import org.junit.Test;

import fr.proxibanque.dao.BankExecutiveDao;
import fr.proxibanque.dao.exceptions.DaoException;
import fr.proxibanque.dao.exceptions.DoesNotExistDaoException;
import fr.proxibanque.dao.jdbc.BankExecutiveDaoImpl;
import fr.proxibanque.metier.BankExecutiveUser;

public class BankExecutiveDaoTestCase {

	@Test
	public final void testCreate() {

		BankExecutiveDao bankExecDao = new BankExecutiveDaoImpl();
		try {
			BankExecutiveUser bankExec = new BankExecutiveUser("thirdStone", "fromTheSun", "HENDRIX", "Jimi");

			bankExecDao.create(bankExec);

		} catch (Exception e) {
			e.printStackTrace();
			fail(e.getMessage());
		}
	}

	@Test
	public final void testRemove() {

		BankExecutiveDao bankExecDao = new BankExecutiveDaoImpl();
		try {
			bankExecDao.remove(15);

		} catch (Exception e) {
			e.printStackTrace();
			fail(e.getMessage());
		}
	}

	@Test(expected = DoesNotExistDaoException.class)
	public final void testRemoveWithException() throws DoesNotExistDaoException {

		BankExecutiveDao bankExecDao = new BankExecutiveDaoImpl();
		try {
			bankExecDao.remove(150);

		} catch (DaoException e) {
			e.printStackTrace();
			fail(e.getMessage());
		}
	}

	@Test
	public final void testSearchAll() {

		try {
			BankExecutiveDao bankExecDao = new BankExecutiveDaoImpl();
			List<BankExecutiveUser> liste = bankExecDao.searchAll();
			assertNotNull(liste);
			assertEquals(2, liste.size());
			for (BankExecutiveUser item : liste) {
				System.out.println(item);
			}

		} catch (DaoException e) {
			fail(e.getMessage());
		}

	}

	@Test
	public final void testsearchById() throws DoesNotExistDaoException, SQLException {

		BankExecutiveDao bankExecDao = new BankExecutiveDaoImpl();
		BankExecutiveUser bankExec = null;
		int Id = 13;
		try {

			bankExec = bankExecDao.searchById(Id);
			assertNotNull(bankExec);
			System.out.println("BankExecutiveUser avec ID# " + Id + " " + bankExec.toString());

		} catch (DaoException e) {
			e.printStackTrace();
			fail(e.getMessage());
		}
	}

	@Test
	public final void testUpdate() {

		BankExecutiveDao bankExecDao = new BankExecutiveDaoImpl();
		try {
			BankExecutiveUser bankExec = new BankExecutiveUser("areYou", "Experienced1967", "HENDRIX", "Jimi");

			bankExecDao.update(bankExec);

		} catch (Exception e) {
			e.printStackTrace();
			fail(e.getMessage());
		}
	}

}
