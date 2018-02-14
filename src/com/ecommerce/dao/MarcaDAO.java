package com.ecommerce.dao;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.ecommerce.javabeans.Marca;

public class MarcaDAO {
	public static  ArrayList<Marca> listarTodoDeMarcas(){
		String sql = "{CALL sp_listartododeMarca()}";
		Connection c = Connect.conectar();
		CallableStatement sentencia;
		try {
			sentencia = c.prepareCall(sql);
			ResultSet resultado = sentencia.executeQuery();
			ArrayList<Marca> lista = new ArrayList<>();
			while(resultado.next()) {
				Marca mar = new Marca();
				mar.setCodigo(resultado.getInt("codigo"));
				mar.setNombre(resultado.getString("nombre"));
				lista.add(mar);
			}
			return lista;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			return null;
		}
	}

	public static int contarMarcas(int marca){
		String sql = "{CALL sp_contarProductoMarca(?)}";
		Connection c = Connect.conectar();
		CallableStatement sentencia;
		try {
			sentencia = c.prepareCall(sql);
			sentencia.setInt(1, marca);
			ResultSet resultado = sentencia.executeQuery();
			resultado.next();
			return resultado.getInt(1);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			return 0;
		} 
	}
}
