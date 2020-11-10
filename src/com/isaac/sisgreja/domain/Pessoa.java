package com.isaac.sisgreja.domain;

import java.util.Date;

public abstract class Pessoa {
	
	private int cpf;
	private String nome;
	private Date dataNascimento;

	public int getCpf() {
		return cpf;
	}

	public void setCpf(int cpf) {
		this.cpf = cpf;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public Date getDataNacimento() {
		return dataNascimento;
	}

	public void setDataNacimento(Date dataNacimento) {
		this.dataNascimento = dataNacimento;
	}

}
