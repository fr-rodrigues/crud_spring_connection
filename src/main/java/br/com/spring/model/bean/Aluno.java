package br.com.spring.model.bean;

import java.io.Serializable;

/**
 * Classe em referencia a tabela aluno
 * @author fernando
 *
 */
public class Aluno implements Serializable{

	private static final long serialVersionUID = 1L;

	/**
	 * variaveis que referenciam os atributos
	 */
	
	private int id;
	private String nome;
	private String rg;
	private int cpf;
	
	/**
	 * gets e sets
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
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	
}
