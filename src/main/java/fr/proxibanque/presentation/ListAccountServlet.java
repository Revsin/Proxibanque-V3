package fr.proxibanque.presentation;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import fr.proxibanque.metier.BasicAccount;
import fr.proxibanque.service.ServiceListAccount;

/**
 * Servlet implementation class ListAccountsServlet Cette Servlet liste les
 * comptes d'un client
 */
@WebServlet("/ListAccountsServlet")
public class ListAccountServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public ListAccountServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		doProcess(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		doProcess(request, response);
	}

	/**
	 * Méthode de traitement de la Servlet
	 * 
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 */
	protected void doProcess(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		// Etape 1: Récupération des paramètres de la requête
		String string_id = request.getParameter("Id");
		int id = Integer.parseInt(string_id);

		// Etape 2 : Soumettre les paramètres de la requête à la couche service

		ServiceListAccount service = new ServiceListAccount();
		List<BasicAccount> accounts = service.sendBackAccountList(id);

		// Etape 3 : Réponse à l'utilisateur

		RequestDispatcher dispatcher;

		HttpSession maSession = request.getSession();
		maSession.setAttribute("Comptes", accounts);
		dispatcher = request.getRequestDispatcher("html/conseiller.html");

		dispatcher.forward(request, response);

	}

}
