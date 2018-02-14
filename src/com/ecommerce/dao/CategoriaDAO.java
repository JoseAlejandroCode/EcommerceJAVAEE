package com.ecommerce.dao;

import com.ecommerce.javabeans.Categoria;


import java.sql.Connection;
import java.sql.CallableStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class CategoriaDAO {
	public static  ArrayList<Categoria> listar(){
		String sql = "{CALL sp_listarCategoriaSuperior()}";
		Connection c = Connect.conectar();
		CallableStatement sentencia;
		try {
			sentencia = c.prepareCall(sql);
			ResultSet resultado = sentencia.executeQuery();
			ArrayList<Categoria> lista = new ArrayList<>();
			while(resultado.next()) {
				Categoria cat = new Categoria();
				cat.setCodigo(resultado.getInt("codigo"));
				cat.setNombre(resultado.getString("nombre"));
				lista.add(cat);
			}
			return lista;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			return null;
		} 
	}
	
	public static  ArrayList<Categoria> listarSub(int codigo){
		String sql = "{CALL sp_listarSubCateogorias("+codigo+")}";
		Connection c = Connect.conectar();
		CallableStatement sentencia;
		try {
			sentencia = c.prepareCall(sql);
			ResultSet resultado = sentencia.executeQuery();
			ArrayList<Categoria> lista = new ArrayList<>();
			while(resultado.next()) {
				Categoria cat = new Categoria();
				cat.setCodigo(resultado.getInt("codigo"));
				cat.setNombre(resultado.getString("nombre"));
				lista.add(cat);
			}
			return lista;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			return null;
		} 
	}
	
	public static boolean esSuperior(int codigo){
		String sql = "{CALL sp_contarSubCateogorias("+codigo+")}";
		Connection c = Connect.conectar();
		CallableStatement sentencia;
		try {
			sentencia = c.prepareCall(sql);
			ResultSet resultado = sentencia.executeQuery();
			resultado.next();
			return resultado.getInt("cantidad") > 0;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			return false;
		} 
	}
	
	public static  ArrayList<Categoria> listarTodoDeCategoria(){
		String sql = "{CALL sp_listartododeCategoria()}";
		Connection c = Connect.conectar();
		CallableStatement sentencia;
		try {
			sentencia = c.prepareCall(sql);
			ResultSet resultado = sentencia.executeQuery();
			ArrayList<Categoria> lista = new ArrayList<>();
			while(resultado.next()) {
				Categoria cat = new Categoria();
				cat.setCodigo(resultado.getInt("codigo"));
				cat.setNombre(resultado.getString("nombre"));
				lista.add(cat);
			}
			return lista;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			return null;
		} 
	}
}
