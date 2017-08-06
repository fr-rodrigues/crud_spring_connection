package br.com.spring.model.bean;

import java.io.Serializable;

/**
 * Classe em referencia a tabela curso
 * @author fernando
 *
 */
public class Curso implements Serializable{

	private static final long serialVersionUID = 1L;

	/**
	 * variaveis referentes aos atributos
	 */
	
	private int id;
	private String descricao;
	private Professor professor;
	
	/**
	 * gets e sets 
	 */
	
	public int getId() {
		return id;
	}
	
	public void setId(int id) {
		this.id = id;
	}

	public String getDescricao() {
		return descricao;
	}
	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public Professor getProfessor() {
		return professor;
	}

	public void setProfessor(Professor professor) {
		this.professor = professor;
	}
	
}
