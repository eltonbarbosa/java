package com.model;

public class ContatoModel {
	private String name;
	private String email;
	private String telefone;

	public ContatoModel() {
	}

	public ContatoModel(String name, String email, String telefone) {
		this.name = name;
		this.email = email;
		this.telefone = telefone;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getTelefone() {
		return telefone;
	}

	public void setTelefone(String telefone) {
		this.telefone = telefone;
	}

}
