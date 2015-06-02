package com.smartcart.cliente.estruturas;

import com.smartcart.cliente.estruturas.ContaUsuario;

public class Cliente {
	private int id;
	private String primeiroNome;
	private String ultimoNome;
	//private ContaUsuario conta;
	private String contaNome;
	private String contaSenha;
	
//	public Cliente(int id, String primeiroNome, String ultimoNome,
//			ContaUsuario conta) {
//		
//		this.setId(id);
//		this.setPrimeiroNome(primeiroNome);
//		this.setUltimoNome(ultimoNome);
//		this.setConta(conta);
//	}
	
	public Cliente(int id, String primeiroNome, String ultimoNome,
			String contaNome, String contaSenha) {
		
		this.setId(id);
		this.setPrimeiroNome(primeiroNome);
		this.setUltimoNome(ultimoNome);
		this.setContaNome(contaNome);
		this.setContaSenha(contaSenha);
	}
	
	public int getId() { return id; }
	public String getPrimeiroNome() { return primeiroNome; }
	public String getUltimoNome() { return ultimoNome; }
	//public ContaUsuario getConta() { return conta; }
	
	public void setId(int id) {
		this.id = id;
	}

	public void setPrimeiroNome(String primeiroNome) {
		this.primeiroNome = primeiroNome;
	}

	public void setUltimoNome(String ultimoNome) {
		this.ultimoNome = ultimoNome;
	}

	//contaNome
	public String getContaNome() {
		return contaNome;
	}
	//contaNome
	public void setContaNome(String contaNome) {
		this.contaNome = contaNome;
	}

	//contaSenha
	public String getContaSenha() {
		return contaSenha;
	}
	//contaSenha
	public void setContaSenha(String contaSenha) {
		this.contaSenha = contaSenha;
	}

//	public void setConta(ContaUsuario conta) {
//		this.conta = conta;
//	}
}
