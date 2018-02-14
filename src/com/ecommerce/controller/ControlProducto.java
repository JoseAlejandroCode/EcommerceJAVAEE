package com.ecommerce.controller;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Random;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileItemFactory;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

import com.ecommerce.dao.ProductoDAO;
import com.ecommerce.javabeans.Producto;
import com.ecommerce.javabeans.ProductoMoneda;


/**
 * Servlet implementation class ControlProducto
 */
@WebServlet("/ControlProducto")
public class ControlProducto extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ControlProducto() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		recibirDatos(request);
		String url = request.getAttribute("imagen").toString();
		
		String nombre = request.getAttribute("nombre").toString();
		float precio = Float.parseFloat(request.getAttribute("precio").toString());
		float precion = Float.parseFloat(request.getAttribute("precionuevo").toString());
		
		float preciocop = Float.parseFloat(request.getAttribute("preciocop").toString());
		float precioncop = Float.parseFloat(request.getAttribute("precionuevocop").toString());
		
		float preciousd = Float.parseFloat(request.getAttribute("preciousd").toString());
		float precionusd = Float.parseFloat(request.getAttribute("precionuevousd").toString());
		
		float preciopen = Float.parseFloat(request.getAttribute("preciopen").toString());
		float precionpen = Float.parseFloat(request.getAttribute("precionuevopen").toString());
		
		int cantidad = Integer.parseInt(request.getAttribute("cantidad").toString());
		
		int marca = Integer.parseInt(request.getAttribute("marca").toString());
		int categoria = Integer.parseInt(request.getAttribute("categoria").toString());
		
		String descripcion = request.getAttribute("descripcion").toString();
		
		boolean nuevo;
		boolean recomendado;
		boolean visible;
		
		try {
			nuevo = request.getAttribute("nuevo").toString().equalsIgnoreCase("on");
		} catch (Exception e){
			nuevo = false;
		}
		
		try {
			recomendado = request.getAttribute("recomendado").toString().equalsIgnoreCase("on");
		} catch (Exception e){
			recomendado = false;
		}
		
		try {
			visible = request.getAttribute("visible").toString().equalsIgnoreCase("on");
		} catch (Exception e){
			visible = false;
		}
		
		String accion = request.getAttribute("accion").toString();
		
		Producto p = new Producto();
		p.setNombre(nombre);
		p.setPrecio(precio);
		p.setPrecionuevo(precion);
		p.setCodigo_marca(marca);
		p.setCodigo_categoria(categoria);
		p.setDescripcion(descripcion);
		p.setImg(url);
		p.setNuevo(nuevo);
		p.setRecomendado(recomendado);
		p.setStock(cantidad);
		p.setVisible(visible);
		
		ProductoMoneda cop = new ProductoMoneda();
		cop.setMoneda("COP");
		cop.setPrecio(preciocop);
		cop.setPrecionuevo(precioncop);
		
		ProductoMoneda usd = new ProductoMoneda();
		usd.setMoneda("USD");
		usd.setPrecio(preciousd);
		usd.setPrecionuevo(precionusd);
		
		ProductoMoneda pen = new ProductoMoneda();
		pen.setMoneda("PEN");
		pen.setPrecio(preciopen);
		pen.setPrecionuevo(precionpen);
		
		if(accion.equalsIgnoreCase("Registrar")) {
			if(ProductoDAO.registrar(p, cop, usd, pen)) {
				request.setAttribute("mensaje", "<p style='color:green'>Producto registrado</p>");			
			} else {
				request.setAttribute("mensaje", "<p style='color:red'>Producto no registrado</p>");
			}
		} else {
			request.setAttribute("mensaje", "<p style='color:red'>Acción desconocida</p>");
		}
		request.getRequestDispatcher("admin").forward(request, response);
		//doGet(request, response);
	}

	private void recibirDatos(HttpServletRequest request) {	
		try {
			FileItemFactory fileFactory = new DiskFileItemFactory();
			
			ServletFileUpload servletUpload = new ServletFileUpload(fileFactory);
			
			String nombre  = "";
			List items = servletUpload.parseRequest(request);
			for(int i = 0; i < items.size(); i++) {
				FileItem item = (FileItem) items.get(i);
				if(!item.isFormField()) {
					String ruta = request.getServletContext().getRealPath("/")+"foto/";
					SimpleDateFormat sdf = new SimpleDateFormat("ddMyyyyhhmmss");
					String fecha = sdf.format(new Date());
					nombre = fecha + new Random().nextLong() + item.getName();
					String nuevonombre = ruta+nombre;
					File folder = new File(ruta);
					if(!folder.exists()) {
						folder.mkdirs();
					}
					File imagen = new File(nuevonombre);
					if(item.getContentType().contains("image")) {
						item.write(imagen);
						request.setAttribute(item.getFieldName(), nombre);
					}
				} else {
					request.setAttribute(item.getFieldName(), item.getString());
				}
			}
		} catch (FileUploadException e) {
			// TODO Auto-generated catch block
			request.setAttribute("subida", false);
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			request.setAttribute("subida", false);
			
		}
	}
	
}
