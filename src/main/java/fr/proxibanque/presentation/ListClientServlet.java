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

import fr.proxibanque.metier.BankExecutiveUser;
import fr.proxibanque.metier.Client;
import fr.proxibanque.service.ServiceListClient;

/**
 * Servlet implementation class ListClientServlet Cette servlet liste les
 * clients du conseiller
 */
@WebServlet("/ListClientServlet")
public class ListClientServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public ListClientServlet() {
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

	protected void doProcess(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		// Etape 2 : Soumettre les paramètres de la requête à la couche service
		HttpSession maSession = request.getSession();
		// Param a checker : ("Conseiller")
		BankExecutiveUser be = (BankExecutiveUser) maSession.getAttribute("Conseiller");

		ServiceListClient service = new ServiceListClient();
		List<Client> clients = service.sendBackClientList(be.getExec_ID());

		// Etape 3 : Réponse à l'utilisateur

		RequestDispatcher dispatcher;

		maSession.setAttribute("Clients", clients);
		dispatcher = request.getRequestDispatcher("html/conseiller.html");

		dispatcher.forward(request, response);
	}

}
