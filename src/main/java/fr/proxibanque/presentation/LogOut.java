package fr.proxibanque.presentation;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import fr.proxibanque.metier.BankExecutiveUser;

/**
 * Servlet implementation class LogOut
 */
@WebServlet("/LogOut")
public class LogOut extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public LogOut() {
		super();

	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		doProcess(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		doProcess(request, response);
	}

	protected void doProcess(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		// Etape 1: Récupération des paramètres de la requête
		System.out.println("Logging out");

		// Etape 2: Traitement par la couche service
		String processURL = "";

		HttpSession session = request.getSession();
		session.setAttribute("pwd", "");
		session.setAttribute("login", "");
		session.setAttribute("bankExecUser", null);
		session.invalidate();
		processURL = "html/index.html";

		// Etape 3: Réponse à l'utilisateur
		RequestDispatcher dispacher = request.getRequestDispatcher(processURL);
		if (dispacher != null)
			dispacher.forward(request, response);

	}

}
