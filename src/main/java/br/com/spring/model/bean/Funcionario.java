package br.com.spring.model.bean;

import java.io.Serializable;

/**
 * Classe em referencia a tabela funcionario
 * herdando a classe usuario 
 * @author fernando
 * @see Usuario
 *
 */
public class Funcionario extends Usuario implements Serializable {

	private static final long serialVersionUID = 1L;
	
	/**
	 * variaveis em referencia aos atributos
	 */
	
	private String nome;
	private String rg;
	private int cpf;
	
	/**
	 *gests e sets 
	 */
	
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public String getRg() {
		return rg;
	}
	public void setRg(String rg) {
		this.rg = rg;
	}
	public int getCpf() {
		return cpf;
	}
	public void setCpf(int cpf) {
		this.cpf = cpf;
	}

}
