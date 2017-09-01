package fr.proxibanque.dao.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.SQLIntegrityConstraintViolationException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import fr.proxibanque.dao.ClientDao;
import fr.proxibanque.dao.exceptions.DaoException;
import fr.proxibanque.dao.exceptions.DoesNotExistDaoException;
import fr.proxibanque.dao.exceptions.DuplicatedDaoException;
import fr.proxibanque.metier.Client;

/**
 * Implémentation de la classe DAO pour les clients
 * 
 * @author adminl
 *
 */

public class ClientDaoImpl implements ClientDao {

	@Override
	public Client searchById(int id) throws DaoException, DoesNotExistDaoException, SQLException {

		Connection connection = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		Client c = null;

		try {
			// 1 - Chargement de driver
			Class.forName("com.mysql.cj.jdbc.Driver");
			// 2 - Creation d'une connection
			// localhost = 127.0.0.1
			String url = "jdbc:mysql://localhost:3306/projet_pentagone?useSSL=true&useLegacyDatetimeCode=false&serverTimezone=Europe/Paris";
			String login = "root";
			String password = "root";
			connection = DriverManager.getConnection(url, login, password);
			// 3 - preparation de la requete
			String requete = "SELECT * FROM client WHERE client_id=?";
			stmt = connection.prepareStatement(requete);
			stmt.setInt(1, id);
			// 4 - Execution de la requete
			rs = stmt.executeQuery();

			// 5 - Traitement du resultat

			if (rs.next() == true) {

				c = new Client();

				c.setClientID(rs.getInt("client_id"));
				c.setSurName(rs.getString("surname"));
				c.setFirstName(rs.getString("firstname"));
				c.setAddress(rs.getString("address"));
				c.setZipCode(rs.getString("zipcode"));
				c.setCity(rs.getString("city"));
				c.setTelephone(rs.getString("telephone"));

			} else
				throw new DoesNotExistDaoException("Client inexistant");

			// 6 - Liberation des ressources
			stmt.close();
			connection.close();

		} catch (Exception e) {
			// e.printStackTrace();
			throw new DaoException("Erreur " + e.getMessage());

		} finally {

			if (stmt != null)
				try {
					stmt.close();
				} catch (SQLException e) {
					throw new DaoException("Erreur " + e.getMessage());
				}

			if (connection != null)
				try {
					connection.close();
				} catch (SQLException e) {
					throw new DaoException("Erreur " + e.getMessage());
				}
		}

		return c;
	}

	/**
	 * Méthode permettant d'extraire la luiste des clients d'un conseiller param id
	 * Numéro du conseiller
	 */
	@Override
	public List<Client> searchByBankExecutiveId(int id) throws DaoException, DoesNotExistDaoException, SQLException {

		Connection connection = null;
		PreparedStatement stmt = null;
		ResultSet resultat = null;
		List<Client> clients = new ArrayList<>();

		try {
			// 1 - Chargement de driver
			Class.forName("com.mysql.cj.jdbc.Driver");
			// 2 - Creation d'une connection
			// localhost = 127.0.0.1
			String url = "jdbc:mysql://localhost:3306/projet_pentagone?useSSL=true&useLegacyDatetimeCode=false&serverTimezone=Europe/Paris";
			String login = "root";
			String password = "root";
			connection = DriverManager.getConnection(url, login, password);
			// 3 - preparation de la requete
			String requete = "SELECT * FROM client WHERE exec_id = ?";
			stmt = connection.prepareStatement(requete);
			stmt.setInt(1, id);
			// 4 - Execution de la requete
			resultat = stmt.executeQuery();
			// 5 - Traitement du resultat

			if (resultat != null) {

				while (resultat.next()) {
					Client c = new Client();
					c.setClientID(resultat.getInt("client_id"));
					c.setAddress(resultat.getString("address"));
					c.setSurName(resultat.getString("surname"));
					c.setFirstName(resultat.getString("firstname"));

					clients.add(c);
				}
			} else
				throw new DoesNotExistDaoException("Le conseiller numero " + id + "ne possede pas de clients");
			// 6 - Liberation des ressources
			stmt.close();
			connection.close();

		} catch (Exception e) {
			// e.printStackTrace();
			throw new DaoException("Erreur " + e.getMessage());

		} finally {

			if (stmt != null)
				try {
					stmt.close();
				} catch (SQLException e) {
					throw new DaoException("Erreur " + e.getMessage());
				}

			if (connection != null)
				try {
					connection.close();
				} catch (SQLException e) {
					throw new DaoException("Erreur " + e.getMessage());
				}
		}

		return clients;

	}

	@Override
	public List<Client> searchAll() throws DaoException {
		try {
			// 1 - Chargement de driver
			Class.forName("com.mysql.cj.jdbc.Driver");
			// 2 - Creation d'une connection
			// localhost = 127.0.0.1
			String url = "jdbc:mysql://localhost:3306/projet_pentagone?useSSL=true&useLegacyDatetimeCode=false&serverTimezone=America/New_York";
			String login = "root";
			String password = "root";
			Connection connection = DriverManager.getConnection(url, login, password);
			// 3 - preparation de la requete
			String requete = "select client_id, surname, firstname, address, zipcode, city, telephone from client";
			Statement stmt = connection.createStatement();

			// 4 - Execution de la requete
			ResultSet resultat = stmt.executeQuery(requete);
			// 5 - Traitement du resultat
			List<Client> clients = new ArrayList<>();

			while (resultat.next()) {
				Client c = new Client();
				c.setClientID(resultat.getInt("client_id"));
				c.setAddress(resultat.getString("address"));
				c.setSurName(resultat.getString("surname"));
				c.setFirstName(resultat.getString("firstname"));

				clients.add(c);
			}
			// 6 - Liberation des ressources
			stmt.close();
			connection.close();
			return clients;

		} catch (Exception e) {
			throw new DaoException("Erreur " + e.getMessage());

		}
	}

	@Override
	public void create(Client client) throws DaoException, DuplicatedDaoException {

		Connection connection = null;
		PreparedStatement stmt = null;

		try {
			// 1 - Chargement de driver
			Class.forName("com.mysql.cj.jdbc.Driver");
			// 2 - Creation d'une connection
			// localhost = 127.0.0.1
			String url = "jdbc:mysql://localhost:3306/projet_pentagone?useSSL=true&useLegacyDatetimeCode=false&serverTimezone=Europe/Paris";
			String login = "root";
			String password = "root";
			connection = DriverManager.getConnection(url, login, password);
			// 3 - preparation de la requete
			String requete = "INSERT INTO client (client_id, surname, firstname, address, zipcode, city, telephone, exec_id) VALUES (?,?,?,?,?,?,?,?)";
			stmt = connection.prepareStatement(requete);
			stmt.setInt(1, client.getClientID());
			stmt.setString(2, client.getSurName());
			stmt.setString(3, client.getFirstName());
			stmt.setString(4, client.getAddress());
			stmt.setString(5, client.getZipCode());
			stmt.setString(6, client.getCity());
			stmt.setString(7, client.getTelephone());
			stmt.setInt(8, client.getExecId());
			// 4 - Execution de la requete
			int nombre = stmt.executeUpdate();
			System.out.println("Result of Execute Update: " + nombre);

			// 5 - Traitement du resultat
			// 6 - Liberation des ressources
			stmt.close();
			connection.close();

		} catch (SQLIntegrityConstraintViolationException e) {

			throw new DuplicatedDaoException("Erreur surname duplique -detail : " + e.getMessage());
		} catch (Exception e) {
			// e.printStackTrace();
			throw new DaoException("Erreur " + e.getMessage());

		} finally {

			if (stmt != null)
				try {
					stmt.close();
				} catch (SQLException e) {
					throw new DaoException("Erreur " + e.getMessage());
				}

			if (connection != null)
				try {
					connection.close();
				} catch (SQLException e) {
					throw new DaoException("Erreur " + e.getMessage());
				}
		}
	}

	@Override
	public void update(Client client) throws DaoException, DoesNotExistDaoException {

		Connection connection = null;
		PreparedStatement stmt = null;
		int nombre = -1;

		try {
			// 1 - Chargement de driver
			Class.forName("com.mysql.cj.jdbc.Driver");
			// 2 - Creation d'une connection
			// localhost = 127.0.0.1
			String url = "jdbc:mysql://localhost:3306/projet_pentagone?useSSL=true&useLegacyDatetimeCode=false&serverTimezone=Europe/Paris";
			String login = "root";
			String password = "root";
			connection = DriverManager.getConnection(url, login, password);
			// 3 - preparation de la requete
			String requete = "UPDATE client SET surname = ?, firstname = ?, address = ?, zipcode = ?, city = ?, telephone = ?"
					+ "WHERE client_id=?";
			stmt = connection.prepareStatement(requete);
			stmt.setString(1, client.getSurName());
			stmt.setString(2, client.getFirstName());
			stmt.setString(3, client.getAddress());
			stmt.setString(4, client.getZipCode());
			stmt.setString(5, client.getCity());
			stmt.setString(6, client.getTelephone());
			stmt.setInt(7, client.getClientID());
			// 4 - Execution de la requete
			nombre = stmt.executeUpdate();

			if (nombre == 0)
				throw new DoesNotExistDaoException("Client inexistant");

			// 5 - Traitement du resultat
			// 6 - Liberation des ressources
			stmt.close();
			connection.close();

		} catch (Exception e) {
			// e.printStackTrace();
			throw new DaoException("Erreur " + e.getMessage());

		} finally {

			if (stmt != null)
				try {
					stmt.close();
				} catch (SQLException e) {
					throw new DaoException("Erreur " + e.getMessage());
				}

			if (connection != null)
				try {
					connection.close();
				} catch (SQLException e) {
					throw new DaoException("Erreur " + e.getMessage());
				}
		}
	}

	@Override
	public void remove(int id) throws DaoException, DoesNotExistDaoException {

		Connection connection = null;
		PreparedStatement stmt = null;
		int nombre = -1;

		try {
			// 1 - Chargement de driver
			Class.forName("com.mysql.cj.jdbc.Driver");
			// 2 - Creation d'une connection
			// localhost = 127.0.0.1
			String url = "jdbc:mysql://localhost:3306/projet_pentagone?useSSL=true&useLegacyDatetimeCode=false&serverTimezone=Europe/Paris";
			String login = "root";
			String password = "root";
			connection = DriverManager.getConnection(url, login, password);
			// 3 - preparation de la requete
			String requete = "DELETE FROM client WHERE client_id=?";
			stmt = connection.prepareStatement(requete);
			stmt.setInt(1, id);
			// 4 - Execution de la requete
			nombre = stmt.executeUpdate();

			if (nombre == 0)
				throw new DoesNotExistDaoException("Client inexistant");

			// 5 - Traitement du resultat
			// 6 - Liberation des ressources
			stmt.close();
			connection.close();

		} catch (SQLException e) {
			// e.printStackTrace();
			throw new DaoException("Erreur " + e.getMessage());

		} catch (ClassNotFoundException e) {
			// e.printStackTrace();
			throw new DaoException("Erreur " + e.getMessage());

		}

		finally {

			if (stmt != null)
				try {
					stmt.close();
				} catch (SQLException e) {
					throw new DaoException("Erreur " + e.getMessage());
				}

			if (connection != null)
				try {
					connection.close();
				} catch (SQLException e) {
					throw new DaoException("Erreur " + e.getMessage());
				}
		}

	}

}
