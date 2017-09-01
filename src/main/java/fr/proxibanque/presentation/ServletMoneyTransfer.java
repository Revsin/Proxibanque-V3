package fr.proxibanque.presentation;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import fr.proxibanque.service.VirementService;

/**
 * Servlet implementation class MoneyTransfer
 */
@WebServlet("/MoneyTransfer")
public class ServletMoneyTransfer extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public ServletMoneyTransfer() {
		super();
		// TODO Auto-generated constructor stub
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

		String sourceAccount = request.getParameter("sourceAccount");
		String targetAccount = request.getParameter("targetAccount");
		String amount = request.getParameter("amount");

		int sourceAccount2 = Integer.parseInt(sourceAccount);
		int targetAccount2 = Integer.parseInt(targetAccount);
		float amount2 = Float.parseFloat(sourceAccount);

		// Etape 2: Traitement par la couche service
		VirementService virementserv = new VirementService();

		// Etape 3 : Mise en session

		HttpSession maSession = request.getSession();
		maSession.setAttribute("virement", virementserv);

		// Etape 4: Réponse à l'utilisateur

		RequestDispatcher dispatcher = null;

		if (sourceAccount == null || targetAccount == null) {
			dispatcher = request.getRequestDispatcher("ErreurAccountsNotFound.html");
		} else {
			System.out.println("Débiteur / créditeur: " + sourceAccount2 + " " + targetAccount2);
		}

		if (virementserv.virementmeth(sourceAccount2, targetAccount2, amount2) == true) {
			dispatcher = request.getRequestDispatcher("MoneyTransferAJAX.html");
		} else {
			dispatcher = request.getRequestDispatcher("ErreurInsufficientBalance.html");
		}

		if (dispatcher != null)
			dispatcher.forward(request, response);

	}

}
