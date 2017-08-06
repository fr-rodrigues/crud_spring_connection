package br.com.spring.model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import br.com.spring.model.bean.Aluno;
import br.com.spring.model.bean.Curso;
import br.com.spring.model.bean.Matricula;

/**
 * Classe que implementa metodos que interagem com a tabela matricula
 * @author fernando
 *
 */
@Repository
public class MatriculaDao {
	
	private Connection connection;

	@Autowired
	public MatriculaDao(DataSource dataSource) {
		try {
			this.connection = dataSource.getConnection();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	/**
	 * metodo que salva uma matricula
	 * @param matricula
	 */
	public void save(Matricula matricula) {
		String sql = "insert into matricula(mat_data, mat_alu_id, mat_cur_id) values (?,?,?)";
		PreparedStatement stmt;
		
		try {
			stmt = this.connection.prepareStatement(sql);
			stmt.setTimestamp(1, matricula.getDtMatricula());
			stmt.setInt(2, matricula.getAluno().getId());
			stmt.setInt(3, matricula.getCurso().getId());
			stmt.execute();
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			this.endConnection();
		}
	}
	
	/**
	 * metodo que busca a matricula
	 * @param idAluno
	 * @param idCurso
	 * @return
	 */
	public Matricula find(int idAluno, int idCurso){
		String sql = "select mat_cur_id, mat_alu_id, mat_data, cur_desc, alu_nome, alu_rg, alu_cpf from matricula "
				+ "inner join curso on mat_cur_id = cur_id "
				+ "inner join aluno on mat_alu_id = alu_id "
				+ "where mat_alu_id = ? and mat_cur_id = ?";
		PreparedStatement stmt;
		ResultSet res;
		Matricula matricula = null;
		
		try {
			stmt = this.connection.prepareStatement(sql);
			stmt.setInt(1, idAluno);
			stmt.setInt(2, idCurso);
			res = stmt.executeQuery();
			while(res.next()){				
				Aluno aluno = new Aluno();
				Curso curso = new Curso();
				
				curso.setId(res.getInt(1));
				aluno.setId(res.getInt(2));
				
				curso.setDescricao(res.getString(4));
				
				aluno.setNome(res.getString(5));
				aluno.setRg(res.getString(6));
				aluno.setCpf(res.getInt(7));
				
				matricula = new Matricula();
				
				matricula.setAluno(aluno);
				matricula.setCurso(curso);
				matricula.setDtMatricula(res.getTimestamp(3));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			this.endConnection();
		}
		
		return matricula;
	}
	
	/**
	 * metodo que lista as matriculas
	 * @return
	 */
	public List<Matricula> findAll(){
		String sql = "select mat_cur_id, mat_alu_id, mat_data, cur_desc, alu_nome, alu_rg, alu_cpf from matricula "
				+ "inner join curso on mat_cur_id = cur_id "
				+ "inner join aluno on mat_alu_id = alu_id ";
		PreparedStatement stmt;
		ResultSet res;
		List<Matricula> matriculas = new ArrayList<Matricula>();
		
		try {
			stmt = this.connection.prepareStatement(sql);
			res = stmt.executeQuery();
			while(res.next()){
				Aluno aluno = new Aluno();
				Curso curso = new Curso();
				
				curso.setId(res.getInt(1));
				aluno.setId(res.getInt(2));
				
				curso.setDescricao(res.getString(4));
				
				aluno.setNome(res.getString(5));
				aluno.setRg(res.getString(6));
				aluno.setCpf(res.getInt(7));
				
				Matricula matricula = new Matricula();
				
				matricula.setAluno(aluno);
				matricula.setCurso(curso);
				matricula.setDtMatricula(res.getTimestamp(3));
				
				matriculas.add(matricula);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			this.endConnection();
		}
		
		return matriculas;
	}
	
	/**
	 * metodo que atualiza a matricula
	 * @param matricula
	 */
	public Matricula delete(Matricula matricula){
		String del = "delete from matricula where mat_alu_id = ? and mat_cur_id = ?";
		PreparedStatement stmt;
		
		try {
			stmt = this.connection.prepareStatement(del);
			stmt.setInt(1, matricula.getAluno().getId());
			stmt.setInt(2, matricula.getCurso().getId());
			
			stmt.execute();
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			this.endConnection();
		}
		
		return matricula;
	}
	
	/**
	 * metodo que encerra a conexao
	 */
	private void endConnection(){
		if(this.connection != null){
			try {
				this.connection.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}
}
