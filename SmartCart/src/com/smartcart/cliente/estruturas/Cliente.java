package com.smartcart.cliente.estruturas;

public class Cliente {
	private long id;
	private String primeiroNome;
	private String ultimoNome;
	private String contaNome;
	private String contaSenha;
	
	public Cliente() {}
	
	public Cliente(String primeiroNome, String ultimoNome,
			String contaNome, String contaSenha) {
		
		this.setPrimeiroNome(primeiroNome);
		this.setUltimoNome(ultimoNome);
		this.setContaNome(contaNome);
		this.setContaSenha(contaSenha);
	}
	
	public Cliente(long id, String primeiroNome, String ultimoNome,
			String contaNome, String contaSenha) {
		
		this.setId(id);
		this.setPrimeiroNome(primeiroNome);
		this.setUltimoNome(ultimoNome);
		this.setContaNome(contaNome);
		this.setContaSenha(contaSenha);
	}
	
	//getters
	public long getId() { return id; }
	public String getPrimeiroNome() { return this.primeiroNome; }
	public String getUltimoNome() { return this.ultimoNome; }
	public String getContaNome() { return this.contaNome; }
	public String getContaSenha() { return this.contaSenha; }
	
	//setters
	public void setId(long id) {
		this.id = id;
	}

	public void setPrimeiroNome(String primeiroNome) {
		this.primeiroNome = primeiroNome;
	}

	public void setUltimoNome(String ultimoNome) {
		this.ultimoNome = ultimoNome;
	}
	
	public void setContaNome(String contaNome) {
		this.contaNome = contaNome;
	}
	
	public void setContaSenha(String contaSenha) {
		this.contaSenha = contaSenha;
	}
}
