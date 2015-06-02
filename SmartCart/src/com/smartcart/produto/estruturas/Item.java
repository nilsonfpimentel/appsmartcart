package com.smartcart.produto.estruturas;

public class Item {
	private String id;
	private String nome;
	private String fabricante;
	private String categoria;
	
	public Item(String id, String nome, String fabricante, String categoria) {
		this.setId(id);
		this.setNome(nome);
		this.setFabricante(fabricante);
		this.setCategoria(categoria);
	}
	
	public String getId() { return id; }
	public String getNome() { return nome; }
	public String getFabricante() { return fabricante; }
	public String getCategoria() { return categoria; }
	
	public void setId(String id) {
		this.id = id;
	}
	
	public void setNome(String nome) {
		this.nome = nome;
	}
	
	public void setFabricante(String fabricante) {
		this.fabricante = fabricante;
	}
	
	public void setCategoria(String categoria) {
		this.categoria = categoria;
	}
}
