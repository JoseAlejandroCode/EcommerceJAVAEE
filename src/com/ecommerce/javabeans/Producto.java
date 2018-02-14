package com.ecommerce.javabeans;

public class Producto {
	private int webid;
	private String nombre;
	private float precio;
	private float precionuevo;
	private int stock;
	private boolean nuevo;
	private boolean recomendado;
	private boolean visible;
	private String descripcion;
	private int codigo_marca;
	private int codigo_categoria;
	private String img;
	
	public Producto() {
		super();
	}

	public int getWebid() {
		return webid;
	}

	public void setWebid(int webid) {
		this.webid = webid;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public float getPrecio() {
		return precio;
	}

	public void setPrecio(float precio) {
		this.precio = precio;
	}

	public float getPrecionuevo() {
		return precionuevo;
	}

	public void setPrecionuevo(float precionuevo) {
		this.precionuevo = precionuevo;
	}

	public int getStock() {
		return stock;
	}

	public void setStock(int stock) {
		this.stock = stock;
	}

	public boolean isNuevo() {
		return nuevo;
	}

	public void setNuevo(boolean nuevo) {
		this.nuevo = nuevo;
	}

	public boolean isRecomendado() {
		return recomendado;
	}

	public void setRecomendado(boolean recomendado) {
		this.recomendado = recomendado;
	}

	public boolean isVisible() {
		return visible;
	}

	public void setVisible(boolean visible) {
		this.visible = visible;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public int getCodigo_marca() {
		return codigo_marca;
	}

	public void setCodigo_marca(int codigo_marca) {
		this.codigo_marca = codigo_marca;
	}

	public int getCodigo_categoria() {
		return codigo_categoria;
	}

	public void setCodigo_categoria(int codigo_categoria) {
		this.codigo_categoria = codigo_categoria;
	}

	public String getImg() {
		return img;
	}

	public void setImg(String img) {
		this.img = img;
	}
	
	
}
