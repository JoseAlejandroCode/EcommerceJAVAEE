package com.ecommerce.dao;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.ecommerce.javabeans.Producto;
import com.ecommerce.javabeans.ProductoMoneda;

public class ProductoDAO {
	public static  boolean registrar(Producto p, ProductoMoneda cop, ProductoMoneda usd, ProductoMoneda pen){
		String sql = "{CALL sp_registrarproducto(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)}";
		Connection c = Connect.conectar();
		CallableStatement sentencia;
		try {
			sentencia = c.prepareCall(sql);
			sentencia.setString(1, p.getNombre());
			sentencia.setFloat(2, p.getPrecio());
			sentencia.setFloat(3, p.getPrecionuevo());
			sentencia.setInt(4, p.getStock());
			sentencia.setBoolean(5, p.isNuevo());
			sentencia.setBoolean(6, p.isRecomendado());
			sentencia.setString(7, p.getDescripcion());
			sentencia.setBoolean(8, p.isRecomendado());
			sentencia.setInt(9, p.getCodigo_marca());
			sentencia.setInt(10, p.getCodigo_categoria());
			sentencia.setString(11, p.getImg());
			
			sentencia.setString(12, cop.getMoneda());
			sentencia.setFloat(13, cop.getPrecio());
			sentencia.setFloat(14, cop.getPrecionuevo());
			
			sentencia.setString(15, usd.getMoneda());
			sentencia.setFloat(16, usd.getPrecio());
			sentencia.setFloat(17, usd.getPrecionuevo());
			
			sentencia.setString(18, pen.getMoneda());
			sentencia.setFloat(19, pen.getPrecio());
			sentencia.setFloat(20, pen.getPrecionuevo());
			
			return sentencia.executeUpdate() > 0;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			return false;
		} 
	}
	
	public static  ArrayList<Producto> listarProductosRecomendados(String moneda){
		String sql = "{CALL sp_listarRecomendados(?)}";
		Connection c = Connect.conectar();
		CallableStatement sentencia;
		try {
			sentencia = c.prepareCall(sql);
			sentencia.setString(1, moneda);
			ResultSet resultado = sentencia.executeQuery();
			ArrayList<Producto> lista = new ArrayList<>();
			while(resultado.next()) {
				Producto p = new Producto();
				p.setWebid(resultado.getInt("webid"));
				p.setNombre(resultado.getString("nombre"));
				p.setImg(resultado.getString("img"));
				p.setStock(resultado.getInt("stock"));
				p.setNuevo(resultado.getBoolean("nuevo"));
				if(!moneda.equalsIgnoreCase("MXN")) {
					p.setPrecio(resultado.getFloat("precio2"));
					p.setPrecionuevo(resultado.getFloat("precion2"));
				} else {
					p.setPrecio(resultado.getFloat("precio"));
					p.setPrecionuevo(resultado.getFloat("precio_nuevo"));
				}
				lista.add(p);
			}
			return lista;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			return null;
		} 
	}
	
	public static  ArrayList<Producto> listarProductosPorCategoria(String moneda, int cat){
		String sql = "{CALL sp_listarPorCategoria(?,?)}";
		Connection c = Connect.conectar();
		CallableStatement sentencia;
		try {
			sentencia = c.prepareCall(sql);
			sentencia.setString(1, moneda);
			sentencia.setInt(2, cat);
			ResultSet resultado = sentencia.executeQuery();
			ArrayList<Producto> lista = new ArrayList<>();
			while(resultado.next()) {
				Producto p = new Producto();
				p.setWebid(resultado.getInt("webid"));
				p.setNombre(resultado.getString("nombre"));
				p.setImg(resultado.getString("img"));
				p.setStock(resultado.getInt("stock"));
				p.setNuevo(resultado.getBoolean("nuevo"));
				if(!moneda.equalsIgnoreCase("MXN")) {
					p.setPrecio(resultado.getFloat("precio2"));
					p.setPrecionuevo(resultado.getFloat("precion2"));
				} else {
					p.setPrecio(resultado.getFloat("precio"));
					p.setPrecionuevo(resultado.getFloat("precio_nuevo"));
				}
				lista.add(p);
			}
			return lista;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			return null;
		} 
	}
	
	public static  ArrayList<Producto> listarProductosPorMarca(String moneda, int mar){
		String sql = "{CALL sp_listarPorMarca(?,?)}";
		Connection c = Connect.conectar();
		CallableStatement sentencia;
		try {
			sentencia = c.prepareCall(sql);
			sentencia.setString(1, moneda);
			sentencia.setInt(2, mar);
			ResultSet resultado = sentencia.executeQuery();
			ArrayList<Producto> lista = new ArrayList<>();
			while(resultado.next()) {
				Producto p = new Producto();
				p.setWebid(resultado.getInt("webid"));
				p.setNombre(resultado.getString("nombre"));
				p.setImg(resultado.getString("img"));
				p.setStock(resultado.getInt("stock"));
				p.setNuevo(resultado.getBoolean("nuevo"));
				if(!moneda.equalsIgnoreCase("MXN")) {
					p.setPrecio(resultado.getFloat("precio2"));
					p.setPrecionuevo(resultado.getFloat("precion2"));
				} else {
					p.setPrecio(resultado.getFloat("precio"));
					p.setPrecionuevo(resultado.getFloat("precio_nuevo"));
				}
				lista.add(p);
			}
			return lista;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			return null;
		} 
	}
	
	public static  Producto consultarProducto(String moneda, int webid){
		String sql = "{CALL sp_consultarProducto(?,?)}";
		Connection c = Connect.conectar();
		CallableStatement sentencia;
		try {
			sentencia = c.prepareCall(sql);
			sentencia.setString(1, moneda);
			sentencia.setInt(2, webid);
			
			ResultSet resultado = sentencia.executeQuery();
			Producto p = null;
			
			if(resultado.next())
			{
				p = new Producto();
				p.setWebid(resultado.getInt("webid"));
				p.setNombre(resultado.getString("nombre"));
				p.setImg(resultado.getString("img"));
				p.setStock(resultado.getInt("stock"));
				p.setNuevo(resultado.getBoolean("nuevo"));
				if(!moneda.equalsIgnoreCase("MXN")) {
					p.setPrecio(resultado.getFloat("precio2"));
					p.setPrecionuevo(resultado.getFloat("precion2"));
				} else {
					p.setPrecio(resultado.getFloat("precio"));
					p.setPrecionuevo(resultado.getFloat("precio_nuevo"));
				}
			}
			
			return p;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			return null;
		} 
	}
}
