package com.ecommerce.javabeans;

public class Item {
	private Producto p;
	private int cantidad;
	
	public Item(Producto p, int cantidad) {
		super();
		this.p = p;
		this.cantidad = cantidad;
	}
	public Producto getP() {
		return p;
	}
	public void setP(Producto p) {
		this.p = p;
	}
	public int getCantidad() {
		return cantidad;
	}
	public void setCantidad(int cantidad) {
		this.cantidad = cantidad;
	}
	
	
}
