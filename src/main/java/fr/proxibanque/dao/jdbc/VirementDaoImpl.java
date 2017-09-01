package fr.proxibanque.dao.jdbc;

import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import fr.proxibanque.dao.VirementDao;
import fr.proxibanque.dao.exceptions.DaoException;
import fr.proxibanque.dao.exceptions.DoesNotExistDaoException;
import fr.proxibanque.dao.exceptions.DuplicatedDaoException;
import fr.proxibanque.dao.exceptions.VirementNotFoundDaoException;
import fr.proxibanque.metier.Virement;

public class VirementDaoImpl implements VirementDao {

	// Création Virement

	@Override
	public void createVirement(Virement virement) throws DaoException, DuplicatedDaoException {

		// information à la base de données
		String url = "jdbc:mysql://localhost/formation";
		String loginD = "root";
		String passwd = "";
		Connection cn = null;
		PreparedStatement pstmt = null;
		String sql = null;

		try {
			// Etape 1 : Chargement du driver
			Class.forName("com.mysql.jdbc.Driver");

			// Etape 2: récupération de la connexion
			cn = DriverManager.getConnection(url, loginD, passwd);

			// Etape 3 : Creation de la requête
			pstmt = cn.prepareStatement(
					"INSERT INTO virement VALUES (virement_id, dateVirement, amount, compte_debiteur_id, compte_crediteur_id");

			// Etape 4 : renseignement des champs à insérer pr la requête
			pstmt.setString(1, virement.getVirementID());
			pstmt.setDate(2, (Date) virement.getDateVirement());
			pstmt.setFloat(3, virement.getAmount());
			pstmt.setInt(4, virement.getSourceAccount());
			pstmt.setInt(5, virement.getTargetAccount());

			// Etape 5 : exécution requête
			pstmt.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} finally {
			try {
				// Etape 6 : libérer ressources de la mémoire.
				cn.close();

			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		System.out.println("Un nouveau virement a été créée.");

	}

	// Chercher Virement par ID

	@Override
	public Virement searchVirementByID(int VirementID) throws DaoException, DoesNotExistDaoException, SQLException {
		// information à la base de données
		String url = "jdbc:mysql://localhost/formation";
		String loginD = "root";
		String passwd = "";
		Connection cn = null;
		Statement st = null;
		ResultSet rs = null;
		Virement virement = new Virement();

		try {
			// Etape 1 : Chargement du driver
			Class.forName("com.mysql.jdbc.Driver");

			// Etape 2: récupération de la connexion
			cn = DriverManager.getConnection(url, loginD, passwd);

			// Etape 3 : Creation d'un statement
			st = cn.createStatement();
			String sql = "SELECT virement_id, dateVirement, amount, compte_debiteur_id, compte_crediteur_id FROM virement where virement_id = ?";

			// Etape 4 : exécution requête
			rs = st.executeQuery(sql);

			// si récup données alors Etape 5 : ResultSet
			// il faut mettre à jour l'objet virement qui est en retour :
			// grâce à la méthode set qui prend en charge le résultat de la
			// requête sql
			if (rs.next()) {
				virement.setVirementID(rs.getString("virement_id"));
				virement.setDateVirement(rs.getDate("dateVirement"));
				virement.setAmount(rs.getFloat("amount"));
				virement.setSourceAccount(rs.getInt("compte_debiteur_id"));
				virement.setTargetAccount(rs.getInt("compte_crediteur_id"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} finally {
			try {
				// Etape 6 : libérer ressources de la mémoire.
				cn.close();
				st.close();
			} catch (SQLException e)

			{
				e.printStackTrace();
			}
		}
		System.out.println("Information virement");

		return virement;
	}

	// Mettre à jour Virement

	@Override
	public void upDateVirement(Virement virement) throws DaoException, DoesNotExistDaoException {
		PreparedStatement stmt = null;
		Connection connection = null;

		try {
			// 1 - Chargement de driver
			Class.forName("com.mysql.cj.jdbc.Driver");
			// 2 - Creation d'une connection
			// localhost = 127.0.0.1
			String url = "jdbc:mysql://localhost:3306/proxibanque?useSSL=true&useLegacyDatetimeCode=false&serverTimezone=America/New_York";
			String login = "root";
			String password = "root";
			connection = DriverManager.getConnection(url, login, password);
			// 3 - preparation de la requete
			String requete = "update virement set virement_id = ?, dateVirement = ?, amount = ?, compte_debiteur_id = ?, compte_crediteur_id = ? where virement_id = ?";
			stmt = connection.prepareStatement(requete);
			stmt.setString(1, virement.getVirementID());
			stmt.setDate(2, (Date) virement.getDateVirement());
			stmt.setFloat(3, virement.getAmount());
			stmt.setInt(4, virement.getSourceAccount());
			stmt.setInt(5, virement.getTargetAccount());
			// 4 - Execution de la requete
			int nombre = stmt.executeUpdate();
			if (nombre == 0) {
				throw new DoesNotExistDaoException(
						"Erreur : Le virement de numero " + virement.getVirementID() + " n existe pas dans la bdd");
			}
			// 5 - Traitement du resultat

		} catch (Exception e) {
			throw new DaoException("Erreur " + e.getMessage());

		} finally {
			// 6 - Liberation des ressources
			try {
				if (stmt != null)
					stmt.close();
				if (connection != null)
					connection.close();
			} catch (Exception e) {
				throw new DaoException("Erreur " + e.getMessage());
			}
		}

		System.out.println(
				"Des changements mise à jour sur le virement N°" + virement.getVirementID() + " ont été fait.");
	}

	@Override
	public void deleteVirement(Virement virement) throws VirementNotFoundDaoException, DaoException {
		PreparedStatement stmt = null;
		Connection connection = null;

		try {
			// 1 - Chargement de driver
			Class.forName("com.mysql.cj.jdbc.Driver");
			// 2 - Creation d'une connection
			// localhost = 127.0.0.1
			String url = "jdbc:mysql://localhost:3306/proxibanque?useSSL=true&useLegacyDatetimeCode=false&serverTimezone=America/New_York";
			String login = "root";
			String password = "root";
			connection = DriverManager.getConnection(url, login, password);
			// 3 - preparation de la requete
			String requete = "delete from virement where virement_id = ?";
			stmt = connection.prepareStatement(requete);
			stmt.setString(1, virement.getVirementID());
			// 4 - Execution de la requete
			int nombre = stmt.executeUpdate();
			if (nombre == 0) {
				throw new VirementNotFoundDaoException(
						"Erreur : Le virement numero " + virement.getVirementID() + " a été supprimé dans la bdd");
			}
			// 5 - Traitement du resultat

		} catch (SQLException e) {
			e.printStackTrace();
			throw new DaoException("Erreur " + e.getMessage());

		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			throw new DaoException("Erreur " + e.getMessage());
		} finally {
			// 6 - Liberation des ressources
			try {
				if (stmt != null)
					stmt.close();
				if (connection != null)
					connection.close();
			} catch (Exception e) {
				throw new DaoException("Erreur " + e.getMessage());
			}
		}

		System.out.println("Un virement a été supprimé.");

	}

	@Override
	public List<Virement> getAllVirement() throws DaoException {

		Statement stmt = null;
		Connection connection = null;

		try {
			// 1 - Chargement de driver
			Class.forName("com.mysql.cj.jdbc.Driver");
			// 2 - Creation d'une connection
			// localhost = 127.0.0.1
			String url = "jdbc:mysql://localhost:3306/proxibanque?useSSL=true&useLegacyDatetimeCode=false&serverTimezone=America/New_York";
			String login = "root";
			String password = "root";
			connection = DriverManager.getConnection(url, login, password);
			// 3 - preparation de la requete
			String requete = "select virement_id, dateVirement, amount, compte_debiteur_id, compte_crediteur_id FROM virement";
			stmt = connection.createStatement();

			// 4 - Execution de la requete
			ResultSet resultat = stmt.executeQuery(requete);
			// 5 - Traitement du resultat
			List<Virement> virements = new ArrayList<>();

			while (resultat.next()) {
				Virement v = new Virement();

				v.setVirementID(resultat.getString("virement_id"));
				v.setDateVirement(resultat.getDate("dateVirement"));
				v.setAmount(resultat.getFloat("amount"));
				v.setSourceAccount(resultat.getInt("compte_debiteur_id"));
				v.setTargetAccount(resultat.getInt("compte_crediteur_id"));

				virements.add(v);
			}

			return virements;

		} catch (Exception e) {
			throw new DaoException("Erreur " + e.getMessage());

		} finally {
			// 6 - Liberation des ressources
			try {
				if (stmt != null)
					stmt.close();
				if (connection != null)
					connection.close();
			} catch (Exception e) {
				throw new DaoException("Erreur " + e.getMessage());
			}
		}
	}

}
