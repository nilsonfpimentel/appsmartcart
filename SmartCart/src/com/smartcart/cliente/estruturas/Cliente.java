package com.smartcart.cliente.estruturas;

public class Cliente {
	/**
	 * Identificação única para o cadastro do cliente no banco de dados, automaticamente setado
	 * no momento do cadastro.
	 */
	private long id;
	private String primeiroNome;
	private String ultimoNome;
	private String email;
	private String senha;
	
	public Cliente() {}
	
	public Cliente(String primeiroNome, String ultimoNome,
			String email, String senha) {
		
		this.setPrimeiroNome(primeiroNome);
		this.setUltimoNome(ultimoNome);
		this.setEmail(email);
		this.setSenha(senha);
	}
	
	public Cliente(long id, String primeiroNome, String ultimoNome,
			String email, String senha) {
		
		this.setId(id);
		this.setPrimeiroNome(primeiroNome);
		this.setUltimoNome(ultimoNome);
		this.setEmail(email);
		this.setSenha(senha);
	}
	
	public long getId() { return id; }
	public String getPrimeiroNome() { return this.primeiroNome; }
	public String getUltimoNome() { return this.ultimoNome; }
	public String getEmail() { return this.email; }
	public String getSenha() { return this.senha; }
	
	public void setId(long id) {
		this.id = id;
	}

	public void setPrimeiroNome(String primeiroNome) {
		this.primeiroNome = primeiroNome;
	}

	public void setUltimoNome(String ultimoNome) {
		this.ultimoNome = ultimoNome;
	}
	
	public void setEmail(String email) {
		this.email = email;
	}
	
	public void setSenha(String senha) {
		this.senha = senha;
	}
}