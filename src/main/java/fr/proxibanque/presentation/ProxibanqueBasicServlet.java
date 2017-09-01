package fr.proxibanque.presentation;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import fr.proxibanque.json.rest.JerseyClientPost;
import fr.proxibanque.metier.BankExecutiveUser;

/**
 * Servlet implementation class ProxibanqueBasicServlet
 */
@WebServlet("/ProxibanqueAuthentication")
public class ProxibanqueBasicServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public ProxibanqueBasicServlet() {
		super();

	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		doProcess(request, response);

		// System.out.println("La banque de vos rêves...");
		// response.getWriter().append("Served at: ").append(request.getContextPath());

		/*
		 * response.setContentType("text/html"); PrintWriter out = response.getWriter();
		 * out.println("<HTML>");
		 * out.print("<HEAD><TITLE>Système d'Information de Proxibanque</TITLE></HEAD>"
		 * ); out.print("<BODY>"); out.
		 * println("<CENTER>Accès Conseiller - Choix de langue: Anglais</CENTER><BR><BR>"
		 * ); out.
		 * println("<CENTER>Annotation ligne 15 @WebServlet(\"/ProxibanqueBasicServlet\") commentée et configuration au travers de web.xml pour cette version...</CENTER>"
		 * ); out.println("</BODY"); out.println("</HTML");
		 */
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
		String login = request.getParameter("login");
		String pwd = request.getParameter("pwd");

		// Etape 2: Traitement par la couche service
		String processURL = "html/ErreurAuthentification.html";

		HttpSession session = request.getSession();
		session.invalidate();

		if (login == null || pwd == null) {

			System.err.println("login / pwd null");

		} else {

			System.out.println("login / pwd: " + login + " " + pwd);

			BankExecutiveUser user = new BankExecutiveUser();
			user.setLogin(login);
			user.setPwd(pwd);
			session = request.getSession();
			session.setAttribute("bankExecUser", user);
			session.setAttribute("login", login);
			session.setAttribute("pwd", pwd);
			// if(login.equals("moiGrandConseiller") && pwd.equals("123Soleil") ||
			// login.equals("moiGrandVizir") && pwd.equals("laMarelle123"))

			JerseyClientPost.perform(null, user.toString());

			processURL = "html/conseiller.html";
			// processURL = "jsp/processLogin.jsp";

			/*
			 * if(login.equals("moiGrandConseiller") && pwd.equals("123Soleil") ||
			 * login.equals("moiGrandVizir") && pwd.equals("laMarelle123")) {
			 * 
			 * processURL = "html/ClientListDisplayAJAX.html";
			 * System.out.println("login / pwd OK");
			 * 
			 * } else {
			 * 
			 * processURL = "html/Erreur Authentification.html";
			 * System.out.println("login / pwd Not OK"); }
			 */

		}

		// Etape 3: Réponse à l'utilisateur
		RequestDispatcher dispacher = request.getRequestDispatcher(processURL);
		if (dispacher != null)
			dispacher.forward(request, response);

	}

}
