package com.smartcart.cliente.estruturas;

public class ContaUsuario {
	private String nomeDaConta;
	private String senha;
	
	public ContaUsuario(String nomeDaConta, String senha) {
		this.setNomeDaConta(nomeDaConta);
		this.setSenha(senha);
	}
	
	public String getNomeDaConta() { return nomeDaConta; }
	public String getSenha() { return senha; }
	
	public void setNomeDaConta(String nomeDaConta) {
		this.nomeDaConta = nomeDaConta;
	}
	
	public void setSenha(String senha) {
		this.senha = senha;
	}
}
