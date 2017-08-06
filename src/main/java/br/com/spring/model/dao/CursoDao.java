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

import br.com.spring.model.bean.Curso;
import br.com.spring.model.bean.Professor;

/**
 * Classe que implemeta metodos que interagem a tabela curso
 * @author fernando
 *
 */
@Repository
public class CursoDao {

	private Connection connection;
	
	@Autowired
	public CursoDao(DataSource dataSource) {
		try {
			this.connection = dataSource.getConnection();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * metodo que salva o curso
	 * @param curso
	 */
	public void save(Curso curso) {
		String sql = "insert into curso(cur_desc, cur_pro_id) values (?,?)";
		PreparedStatement stmt;
		
		try {
			stmt = this.connection.prepareStatement(sql);
			stmt.setString(1, curso.getDescricao());
			stmt.setInt(2, curso.getProfessor().getId());
			stmt.execute();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			this.endConnection();
		}
	}
	
	/**
	 * metodo que altera um curso
	 * @param curso
	 */
	public void update(Curso curso) {
		String sql = "update curso set cur_desc = ?, cur_pro_id = ? where cur_id = ?";
		PreparedStatement stmt;
		
		try {
			stmt = this.connection.prepareStatement(sql);
			stmt.setString(1, curso.getDescricao());
			stmt.setInt(2, curso.getProfessor().getId());
			stmt.setInt(3, curso.getId());
			stmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			this.endConnection();
		}
	}
	
	/**
	 * metodo que busca um aluno
	 * @param id
	 * @return Curso
	 */
	public Curso find(int id) {
		String sql = "select * from curso inner join professor on cur_pro_id = pro_usu_id where cur_id = ?";
		Curso curso = null;
		PreparedStatement stmt;
		ResultSet res;
		
		try {
			stmt = this.connection.prepareStatement(sql);
			stmt.setInt(1, id);
			res = stmt.executeQuery();
			
			while(res.next()){
				curso = new Curso();
				curso.setId(res.getInt(1));
				curso.setDescricao(res.getString(2));
				
				Professor professor = new Professor();
				professor.setId(res.getInt(3));
				professor.setNome(res.getString(4));
				professor.setRg(res.getString(5));
				professor.setCpf(res.getInt(6));
				
				curso.setProfessor(professor);
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			this.endConnection();
		}
		
		return curso;
	}
	
	/**
	 * metodo que lista os cursos
	 * @return Lista Curso
	 */
	public List<Curso> findAll() {
		String sql = "select * from curso inner join professor on cur_pro_id = pro_usu_id";
		List<Curso> cursos = new ArrayList<Curso>();
		PreparedStatement stmt;
		ResultSet res;
		
		try {
			stmt = this.connection.prepareStatement(sql);
			res = stmt.executeQuery();
			
			while(res.next()){
				Curso curso = new Curso();
				curso.setId(res.getInt(1));
				curso.setDescricao(res.getString(2));
				
				Professor professor = new Professor();
				
				professor.setId(res.getInt(3));
				professor.setNome(res.getString(4));
				professor.setRg(res.getString(5));
				professor.setCpf(res.getInt(6));
				curso.setProfessor(professor);
				
				cursos.add(curso);
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			this.endConnection();
		}
		
		return cursos;
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