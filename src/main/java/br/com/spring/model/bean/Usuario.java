package br.com.spring.model.bean;

import java.io.Serializable;

/**
 * Classe em referencia a tabela usuario
 * @author fernando
 *
 */
public class Usuario implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	/**
	 * variaveis em referencia aos atributos
	 */
	
	private int id;
	private String login;
	private String senha;
	
	/**
	 * gets e sets
	 */
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getLogin() {
		return login;
	}
	public void setLogin(String login) {
		this.login = login;
	}
	public String getSenha() {
		return senha;
	}
	public void setSenha(String senha) {
		this.senha = senha;
	}	

}
