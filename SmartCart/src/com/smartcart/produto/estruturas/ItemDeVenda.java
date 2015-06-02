package com.smartcart.produto.estruturas;

public class ItemDeVenda {
	private int unidades;
	private double custo;
	private Item item;
	
	public ItemDeVenda(int unidades, double custo, Item item) {
		this.setUnidades(unidades);
		this.setCusto(custo);
		this.setItem(item);
	}
	
	public int getUnidades() { return unidades; }
	public double getCusto() { return custo; }
	public Item getItem() { return item; }
	
	public void setUnidades(int unidades) {
		this.unidades = unidades;
	}
	
	public void setCusto(double custo) {
		this.custo = custo;
	}
	
	public void setItem(Item item) {
		this.item = item;
	}
}
