package com.ecommerce.view;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.ecommerce.javabeans.Producto;
import com.ecommerce.dao.ProductoDAO;
import com.ecommerce.javabeans.Item;

/**
 * Servlet implementation class Cart
 */
@WebServlet(name = "CartController", urlPatterns = { "/Cart" })
public class Cart extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Cart() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		if(request.getParameter("action") != null) {
			String a = request.getParameter("action");
			int webid = 0;
			Producto p;
			HttpSession session = request.getSession();
			if(a.equals("order")) {
				webid = Integer.parseInt(request.getParameter("id"));
				if(session.getAttribute("cart") == null) {
					ArrayList<Item> cart = new ArrayList<>();
					p = ProductoDAO.consultarProducto(session.getAttribute("moneda").toString(), webid);
					cart.add(new Item(p, 1));
					session.setAttribute("cart", cart);
				} else {
					ArrayList<Item> cart = (ArrayList<Item>)session.getAttribute("cart");
					int indice = yaExisteProducto(webid, cart);
					if(indice == -1) {
						p = ProductoDAO.consultarProducto(session.getAttribute("moneda").toString(), webid);
						cart.add(new Item(p, 1));
					} else {
						int cantidad = cart.get(indice).getCantidad() + 1;
						cart.get(indice).setCantidad(cantidad);
					}
					session.setAttribute("cart", cart);
				}
			} else if (a.equals("deleted")) {
				ArrayList<Item> cart = (ArrayList<Item>)session.getAttribute("cart");
				int indice = yaExisteProducto(webid, cart);
				cart.remove(indice);
				session.setAttribute("cart", cart);
			} else if (a.equals("finish")) {
				ArrayList<Item> cart = (ArrayList<Item>)session.getAttribute("cart");
				cart.clear();
				session.setAttribute("cart", cart);
				response.setContentType("text/html;charset=UTF-8");
				request.getRequestDispatcher("Home").forward(request, response);
			}
		}
		
		
		response.setContentType("text/html;charset=UTF-8");
		request.getRequestDispatcher("WEB-INF/cart.jsp").forward(request, response);
	}

	private int yaExisteProducto(int webid, ArrayList<Item> cart) {
		for(int i = 0; i < cart.size(); i++) {
			if(cart.get(i).getP().getWebid() == webid) {
				return i;
			}
		}
		return -1;
	}
	
	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
