package com.ecommerce.view;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet implementation class Inicio
 */
@WebServlet("/Home")
public class Home extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Home() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//response.getWriter().write("Hello, world!");
		response.setContentType("text/html;charset=UTF-8");
		HttpSession sesion = request.getSession();
		if(sesion.getAttribute("moneda") == null) {
			sesion.setAttribute("moneda", "MXN");
			sesion.setAttribute("nom_moneda", "$ Pesos Mexicanos");
		}
		if(request.getParameter("category")!=null) {
			sesion.setAttribute("category", Integer.parseInt(request.getParameter("category")));
		} else if(request.getParameter("brand")!=null) {
			sesion.setAttribute("brand", Integer.parseInt(request.getParameter("brand")));
		} else {
			sesion.setAttribute("category", 0);
			sesion.setAttribute("brand", 0);
		}
		request.getRequestDispatcher("WEB-INF/index.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
