package fr.proxibanque.service;

import fr.proxibanque.metier.BasicAccount;
import fr.proxibanque.metier.Virement;

import java.sql.SQLException;

import fr.proxibanque.dao.BasicAccountDao;
import fr.proxibanque.dao.VirementDao;
import fr.proxibanque.dao.exceptions.DaoException;
import fr.proxibanque.dao.exceptions.DoesNotExistDaoException;
import fr.proxibanque.dao.exceptions.DuplicatedDaoException;
import fr.proxibanque.dao.jdbc.BasicAccountDaoImpl;
import fr.proxibanque.dao.jdbc.VirementDaoImpl;

public class VirementService {

	public VirementService() {
		super();
	}

	public boolean virementmeth(int sourceAccount, int targetAccount, float amount) {

		try {
			// Cas negatif
			if (amount < 0) {
				System.out.println("ERREUR Transfert négatif !");
				return false;
			}

			// Cas positif
			else if (amount > 0) {

				// 1. Recherche des Comtpes associés au numéro de Compte débiteur et créditeur
				BasicAccountDao accountDao1 = new BasicAccountDaoImpl();
				BasicAccount account1 = accountDao1.searchById(sourceAccount);
				BasicAccount account2 = accountDao1.searchById(targetAccount);

				// 2. Modification des soldes des comptes débiteurs et créditeurs

				// 3.Gestion des découverts
				if (account1.getAccountBalance() - amount < -300) {
					System.out.println("ERREUR DECOUVERT NON AUTHORISE LIMITE DE 300 EUROS");
				} else {

					float bal1 = account1.getAccountBalance();
					bal1 -= amount;
					account1.setAccountBalance(bal1);

					float bal2 = account2.getAccountBalance();
					bal2 -= amount;
					account2.setAccountBalance(bal2);

					accountDao1.update(account1);
					accountDao1.update(account2);

					// Créer Virement

					Virement vir = new Virement();
					VirementDao virdao = new VirementDaoImpl();
					virdao.createVirement(vir);

					return true;
				}
			}
		} catch (DaoException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (DoesNotExistDaoException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (DuplicatedDaoException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return false;
	}
}
