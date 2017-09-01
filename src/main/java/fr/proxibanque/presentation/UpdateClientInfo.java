package fr.proxibanque.presentation;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class UpdateClientInfo
 */
@WebServlet("/UpdateClientInfo")
public class UpdateClientInfo extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public UpdateClientInfo() {
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
		/*
		 * String login = request.getParameter("login"); String pwd =
		 * request.getParameter("pwd"); System.out.println("login / pwd: " + login + " "
		 * + pwd);
		 */
		// Etape 2: Traitement par la couche service

		// Etape 3: Réponse à l'utilisateur
		RequestDispatcher dispacher = null;
		/*
		 * if(login == null || pwd == null) {
		 * 
		 * System.err.println("login / pwd null");
		 * 
		 * } else {
		 * 
		 * System.out.println("login / pwd: " + login + " " + pwd);
		 * 
		 * if(login.equalsIgnoreCase("moiGrandConseiller"))
		 * if(pwd.equalsIgnoreCase("123Soleil")) {
		 * 
		 * System.out.println("login / pwd OK"); dispacher =
		 * request.getRequestDispatcher("html/ClientListDisplayAJAX.html"); } else
		 * if(login.equalsIgnoreCase("moiGrandVizir"))
		 * if(pwd.equalsIgnoreCase("laMarelle123")) {
		 * 
		 * System.out.println("login / pwd OK"); dispacher =
		 * request.getRequestDispatcher("html/ClientListDisplayAJAX.html"); } else {
		 * 
		 * System.out.println("login / pwd Not OK"); dispacher =
		 * request.getRequestDispatcher("html/index.html"); }
		 * 
		 * }
		 */
		if (dispacher != null)
			dispacher.forward(request, response);

	}

}
