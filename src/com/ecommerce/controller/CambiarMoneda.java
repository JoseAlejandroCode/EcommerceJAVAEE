package com.ecommerce.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet implementation class CambiarMoneda
 */
@WebServlet("/CambiarMoneda")
public class CambiarMoneda extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public CambiarMoneda() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.setContentType("text/html;charset=UTF-8");
		HttpSession sesion = request.getSession();
		if(request.getParameter("moneda") != null) {
			switch (request.getParameter("moneda")) {
			case "COP":
				sesion.setAttribute("moneda", request.getParameter("moneda"));
				sesion.setAttribute("nom_moneda", "$ Peso Colombiano");
				break;
			case "USD":
				sesion.setAttribute("moneda", request.getParameter("moneda"));
				sesion.setAttribute("nom_moneda", "$ Dolar EEUU     ");
				break;
			case "PEN":
				sesion.setAttribute("moneda", request.getParameter("moneda"));
				sesion.setAttribute("nom_moneda", "S/ Sol Peruano   ");
				break;
			default:
				sesion.setAttribute("moneda", "MXN");
				sesion.setAttribute("nom_moneda", "$ Peso Mexicano  ");
				break;
			}
		}
		
		response.sendRedirect("Home");
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
