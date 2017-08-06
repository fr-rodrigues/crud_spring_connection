package br.com.spring.model.bean;

import java.io.Serializable;
import java.sql.Timestamp;

/**
 * Classe que referencia a tabela matricula
 * @author fernando
 *
 */
public class Matricula implements Serializable {

	private static final long serialVersionUID = 1L;
	
	/**
	 * variaveis em referencias aos atributos
	 */
	
	private Timestamp dtMatricula;
	private Aluno aluno;
	private Curso curso;
	
	/**
	 * gets e sets 
	 */
	
	public Timestamp getDtMatricula() {
		return dtMatricula;
	}
	public void setDtMatricula(Timestamp dtMatricula) {
		this.dtMatricula = dtMatricula;
	}
	public Aluno getAluno() {
		return aluno;
	}
	public void setAluno(Aluno aluno) {
		this.aluno = aluno;
	}
	public Curso getCurso() {
		return curso;
	}
	public void setCurso(Curso curso) {
		this.curso = curso;
	}
	
}
