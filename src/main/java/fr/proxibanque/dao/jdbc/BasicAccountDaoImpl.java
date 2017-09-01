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

import fr.proxibanque.dao.exceptions.DaoException;
import fr.proxibanque.dao.exceptions.DoesNotExistDaoException;
import fr.proxibanque.dao.exceptions.DuplicatedDaoException;
import fr.proxibanque.metier.BasicAccount;
import fr.proxibanque.dao.BasicAccountDao;

/**
 * @author SEBASTIENM Implémentation de la classe DAO pour les comptes
 */
public class BasicAccountDaoImpl implements BasicAccountDao {
	
	@Override
	public void create(BasicAccount account) throws DaoException, DuplicatedDaoException{
		
		Connection connection = null;
		PreparedStatement stmt = null;
		
		try {
			// 1 - Chargement de driver
			Class.forName("com.mysql.cj.jdbc.Driver");
			// 2 - Creation d'une connection
			// localhost = 127.0.0.1
			String url = "jdbc:mysql://localhost:3306/projet_pentagone?useSSL=true&useLegacyDatetimeCode=false&serverTimezone=Europe/Paris";
			String login = "root";
			String password ="root";
			connection = DriverManager.getConnection(url, login, password);
			// 3 - preparation de la requete
			String requete = "INSERT INTO account (account_balance, account_id, account_type, creation_date, client_id) VALUES (?,?,?,?,?)";
			stmt = connection.prepareStatement(requete);
			stmt.setFloat(1, account.getAccountBalance());
			stmt.setInt(2, Integer.parseInt(account.getAccountNumber()));
			stmt.setString(3, account.getAccountType());
			stmt.setString(4, account.getCreationDate());
			stmt.setInt(5, account.getClient_id());
			
			
			// 4 - Execution de la requete
			int nombre = stmt.executeUpdate();
			System.out.println("Result of Execute Update: " + nombre);
			
			// 5 - Traitement du resultat
			// 6 - Liberation des ressources
			stmt.close();
			connection.close();
		
		} catch(SQLIntegrityConstraintViolationException e) {
			
			throw new DuplicatedDaoException( "Erreur compte duplique -detail : " + e.getMessage() ) ;
		} catch (Exception e) {
			//e.printStackTrace();
			throw new DaoException( "Erreur " + e.getMessage() ) ;
			
		} finally {
			
			if(stmt != null)			
			try {
				stmt.close();
			} catch (SQLException e) {
				throw new DaoException( "Erreur " + e.getMessage() ) ;
			}
			
			if(connection != null)
			try {
				connection.close();
			} catch (SQLException e) {
				throw new DaoException( "Erreur " + e.getMessage() ) ;
			}
		}
	}

	@Override
	public void remove(int id) throws DaoException, DoesNotExistDaoException {
		
		Connection connection = null;
		PreparedStatement stmt = null;
		int nombre =-1;
		
		try {
			// 1 - Chargement de driver
			Class.forName("com.mysql.cj.jdbc.Driver");
			// 2 - Creation d'une connection
			// localhost = 127.0.0.1
			String url = "jdbc:mysql://localhost:3306/projet_pentagone?useSSL=true&useLegacyDatetimeCode=false&serverTimezone=Europe/Paris";
			String login = "root";
			String password ="root";
			connection = DriverManager.getConnection(url, login, password);
			// 3 - preparation de la requete			
			String requete = "DELETE FROM account WHERE account_id=?";
			stmt = connection.prepareStatement(requete);
			stmt.setInt(1, id);
			// 4 - Execution de la requete
			nombre = stmt.executeUpdate();
			
			if(nombre == 0)
				throw new DoesNotExistDaoException( "BasicAccount inexistant");
			
			// 5 - Traitement du resultat
			// 6 - Liberation des ressources
			stmt.close();
			connection.close();
		
		} 
		catch (SQLException e) {
			//e.printStackTrace();
			throw new DaoException( "Erreur " + e.getMessage() ) ;
			
		} 
		catch (ClassNotFoundException e) {
			//e.printStackTrace();
			throw new DaoException( "Erreur " + e.getMessage() ) ;
			
		}
		
		finally {
			
			if(stmt != null)			
			try {
				stmt.close();
			} catch (SQLException e) {
				throw new DaoException( "Erreur " + e.getMessage() ) ;
			}
			
			if(connection != null)
			try {
				connection.close();
			} catch (SQLException e) {
				throw new DaoException( "Erreur " + e.getMessage() ) ;
			}
		}		

	}

	@Override
	public List<BasicAccount> searchAll() throws DaoException {
		try {
			// 1 - Chargement de driver
			Class.forName("com.mysql.cj.jdbc.Driver");
			// 2 - Creation d'une connection
			// localhost = 127.0.0.1
			String url = "jdbc:mysql://localhost:3306/projet_pentagone?useSSL=true&useLegacyDatetimeCode=false&serverTimezone=America/New_York";
			String login = "root";
			String password ="root";
			Connection connection = DriverManager.getConnection(url, login, password);
			// 3 - preparation de la requete
			String requete = "select * from account";
			Statement stmt = connection.createStatement();
		
			// 4 - Execution de la requete
			ResultSet resultat = stmt.executeQuery(requete);
			// 5 - Traitement du resultat
			List<BasicAccount> bankAccounts = new ArrayList<>();
			
			while(resultat.next()) {
				BasicAccount account = new BasicAccount();
				account.setAccountBalance(resultat.getFloat("account_balance"));
				account.setAccountNumber(resultat.getString("account_id"));
				account.setAccountType(resultat.getString("account_type"));
				account.setClient_id(resultat.getInt("client_id"));
				account.setCreationDate(resultat.getString("creation_date"));
				
				bankAccounts.add(account);
			}
			// 6 - Liberation des ressources
			stmt.close();
			connection.close();
			return bankAccounts;
		
		}catch (Exception e) {		
			throw new DaoException( "Erreur " + e.getMessage() ) ;
			
		}
	}
	
	@Override
	public List<BasicAccount> searchByClientId(int id)  throws DaoException, DoesNotExistDaoException, SQLException{
		Connection connection = null;
		PreparedStatement stmt = null;
		ResultSet resultat = null;
		List<BasicAccount> bankAccounts = new ArrayList<BasicAccount>();
		
		try {
			// 1 - Chargement de driver
			Class.forName("com.mysql.cj.jdbc.Driver");
			// 2 - Creation d'une connection
			// localhost = 127.0.0.1
			String url = "jdbc:mysql://localhost:3306/projet_pentagone?useSSL=true&useLegacyDatetimeCode=false&serverTimezone=America/New_York";
			String login = "root";
			String password ="root";
			connection = DriverManager.getConnection(url, login, password);
			// 3 - preparation de la requete
			String requete = "select * from account where client_id = ?";
			stmt = connection.prepareStatement(requete);
			stmt.setInt(1, id);
			// 4 - Execution de la requete
			resultat = stmt.executeQuery();

			// 5 - Traitement du resultat
			
			if (resultat != null) {
			
				while(resultat.next()) {
					BasicAccount account = new BasicAccount();
					account.setAccountBalance(resultat.getFloat("account_balance"));
					account.setAccountNumber(resultat.getString("account_id"));
					account.setAccountType(resultat.getString("account_type"));
					account.setClient_id(resultat.getInt("client_id"));
					account.setCreationDate(resultat.getString("creation_date"));
					
					bankAccounts.add(account);
				}
			}else 
				throw new DoesNotExistDaoException("Le client numero " + id + "ne possede pas de comptes");
			// 6 - Liberation des ressources
			stmt.close();
			connection.close();
		
		}catch (Exception e) {		
			throw new DaoException( "Erreur " + e.getMessage() ) ;
			
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
		
		return bankAccounts;
	}
	
	@Override
	public BasicAccount searchById(int id) throws DaoException, DoesNotExistDaoException, SQLException {
		
		Connection connection = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		BasicAccount account = null;
		
		try {
			// 1 - Chargement de driver
			Class.forName("com.mysql.cj.jdbc.Driver");
			// 2 - Creation d'une connection
			// localhost = 127.0.0.1
			String url = "jdbc:mysql://localhost:3306/projet_pentagone?useSSL=true&useLegacyDatetimeCode=false&serverTimezone=Europe/Paris";
			String login = "root";
			String password ="root";
			connection = DriverManager.getConnection(url, login, password);
			// 3 - preparation de la requete			
			String requete = "SELECT * FROM account WHERE account_id=?";
			stmt = connection.prepareStatement(requete);
			stmt.setInt(1, id);
			// 4 - Execution de la requete
			rs = stmt.executeQuery();
			
			// 5 - Traitement du resultat
			
		
			
			if(rs.next() == true) {
				
				account = new BasicAccount();
															
				account.setAccountBalance(rs.getFloat("account_balance"));
				account.setAccountNumber(rs.getString("account_id"));
				account.setAccountType(rs.getString("account_type"));
				account.setClient_id(rs.getInt("client_id"));
				account.setCreationDate(rs.getString("creation_date"));
				 				
			} else
				throw new DoesNotExistDaoException( "BasicAccount inexistant");
				
			
			// 6 - Liberation des ressources
			stmt.close();
			connection.close();
		
		} 
		catch (Exception e) {
			//e.printStackTrace();
			throw new DaoException( "Erreur " + e.getMessage() ) ;
			
		} finally {
			
			if(stmt != null)			
			try {
				stmt.close();
			} catch (SQLException e) {
				throw new DaoException( "Erreur " + e.getMessage() ) ;
			}
			
			if(connection != null)
			try {
				connection.close();
			} catch (SQLException e) {
				throw new DaoException( "Erreur " + e.getMessage() ) ;
			}
		}		
			
		return account;
	}

	@Override
	public void update(BasicAccount account) throws DaoException, DoesNotExistDaoException {
		
		Connection connection = null;
		PreparedStatement stmt = null;
		int nombre =-1;
		
		try {
			// 1 - Chargement de driver
			Class.forName("com.mysql.cj.jdbc.Driver");
			// 2 - Creation d'une connection
			// localhost = 127.0.0.1
			String url = "jdbc:mysql://localhost:3306/projet_pentagone?useSSL=true&useLegacyDatetimeCode=false&serverTimezone=Europe/Paris";
			String login = "root";
			String password ="root";
			connection = DriverManager.getConnection(url, login, password);
			// 3 - preparation de la requete			
			String requete = "UPDATE account SET account_balance = ?, creation_date = ?, account_type = ?, client_id = ?"
					+ " WHERE account_id = ?";
			stmt = connection.prepareStatement(requete);
			stmt.setFloat(1, account.getAccountBalance());
			stmt.setString(2, account.getCreationDate());
			stmt.setString(3, account.getAccountType());
			stmt.setInt(4, account.getClient_id());
			stmt.setInt(5, Integer.parseInt(account.getAccountNumber()));
			
			// 4 - Execution de la requete
			nombre = stmt.executeUpdate();
			
			if(nombre == 0)
				throw new DoesNotExistDaoException( "BasicAccount inexistant");
			
			// 5 - Traitement du resultat
			// 6 - Liberation des ressources
			stmt.close();
			connection.close();
		
		} 
		catch (Exception e) {
			//e.printStackTrace();
			throw new DaoException( "Erreur " + e.getMessage() ) ;
			
		} finally {
			
			if(stmt != null)			
			try {
				stmt.close();
			} catch (SQLException e) {
				throw new DaoException( "Erreur " + e.getMessage() ) ;
			}
			
			if(connection != null)
			try {
				connection.close();
			} catch (SQLException e) {
				throw new DaoException( "Erreur " + e.getMessage() ) ;
			}
		}
	}

}
