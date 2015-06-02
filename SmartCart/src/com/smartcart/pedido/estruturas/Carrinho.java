package com.smartcart.pedido.estruturas;

import java.util.ArrayList;

import com.smartcart.cliente.estruturas.Cliente;
import com.smartcart.produto.estruturas.ItemDeVenda;

public class Carrinho {
	private Cliente cliente;
	private ArrayList<ItemDeVenda> produtos;
	private double valorTotal;
	
	public Carrinho(Cliente cliente) {
		this.setCliente(cliente);
		this.produtos = new ArrayList<ItemDeVenda>();
	}
	
	public Cliente getCliente() { return cliente; }
	public ArrayList<ItemDeVenda> getProdutos() { return produtos; }
	public double getValorTotal() { return valorTotal; }
	
	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}
	
	public void adicionar(ItemDeVenda produto) {
		this.produtos.add(produto);
	}
	
	public void remover() {
		
	}

	public void setValorTotal(double valorTotal) {
		this.valorTotal = valorTotal;
	}
	
}
