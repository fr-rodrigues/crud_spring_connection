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

import br.com.spring.model.bean.Professor;

/**
 * Classe que implementa os metodos que interagem com a tabela professor
 * @author fernando
 *
 */
@Repository
public class ProfessorDao {
	
	private Connection connection;
	
	@Autowired
	public ProfessorDao(DataSource dataSource) {
		try {
			this.connection = dataSource.getConnection();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * metodo que salva um professor
	 * @param professor
	 */
	public void save(Professor professor) {
		String sql = "insert into professor(pro_nome, pro_rg, pro_cpf, pro_usu_id) values (?,?,?,?)";
		PreparedStatement stmt;
		
		try {
			stmt = this.connection.prepareStatement(sql);
			stmt.setString(1, professor.getNome());
			stmt.setString(2, professor.getRg());
			stmt.setInt(3, professor.getCpf());
			stmt.setInt(4, professor.getId());
			stmt.execute();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			this.endConnection();
		}
	}
	
	/**
	 * metodo que atualiza o professor
	 * @param professor
	 */
	public void update(Professor professor) {
		String sql = "update professor set pro_nome = ?, pro_rg = ?, pro_cpf = ? where pro_usu_id = ?";
		PreparedStatement stmt;
		
		try {
			stmt = this.connection.prepareStatement(sql);
			stmt.setString(1, professor.getNome());
			stmt.setString(2, professor.getRg());
			stmt.setInt(3, professor.getCpf());
			stmt.setInt(4, professor.getId());
			stmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			this.endConnection();
		}
	}
	
	/**
	 * metodo que busca um professor
	 * @param id
	 * @return Professor
	 */
	public Professor find(int id) {
		String sql = "select pro_nome, pro_rg, pro_cpf, usu_id, usu_log, usu_sen from professor "
				+ "inner join usuario on pro_usu_id = usu_id where pro_usu_id = ?";
		Professor professor = null;
		PreparedStatement stmt;
		ResultSet res;
		
		try {
			stmt = this.connection.prepareStatement(sql);
			stmt.setInt(1, id);
			res = stmt.executeQuery();
			
			while(res.next()){
				professor = new Professor();
				professor.setNome(res.getString(1));
				professor.setRg(res.getString(2));
				professor.setCpf(res.getInt(3));
				
				/**
				 * campos com os dados da tabela usuario
				 */
				professor.setId(res.getInt(4));
				professor.setLogin(res.getString(5));
				professor.setSenha(res.getString(6));
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			this.endConnection();
		}
		
		return professor;
	}
	
	/**
	 * metodo que lista os professores
	 * @return Lista Professor
	 */
	public List<Professor> findAll() {
		String sql = "select pro_nome, pro_rg, pro_cpf, usu_id, usu_log, usu_sen from professor "
				+ "inner join usuario on pro_usu_id = usu_id";
		List<Professor> professores = new ArrayList<Professor>();
		PreparedStatement stmt;
		ResultSet res;
		
		try {
			stmt = this.connection.prepareStatement(sql);
			res = stmt.executeQuery();
			
			while(res.next()){
				Professor professor = new Professor();
				professor.setNome(res.getString(1));
				professor.setRg(res.getString(2));
				professor.setCpf(res.getInt(3));
				
				/**
				 * campos com os dados da tabela usuario
				 */
				professor.setId(res.getInt(4));
				professor.setLogin(res.getString(5));
				professor.setSenha(res.getString(6));
				
				professores.add(professor);
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			this.endConnection();
		}
		
		return professores;
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