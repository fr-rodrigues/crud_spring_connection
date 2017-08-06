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

/**
 * Classe que define os metodos que interagem com a tabela aluno
 * @author fernando
 *
 */
@Repository
public class AlunoDao {

	private Connection connection;
	
	@Autowired
	public AlunoDao(DataSource dataSource) {
		try {
			this.connection = dataSource.getConnection();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	
	/**
	 * metodo que salva o aluno
	 * @param aluno
	 */
	public void save(Aluno aluno) {
		
		String sql = "insert into aluno(alu_nome, alu_rg, alu_cpf) values (?,?,?)";
		PreparedStatement stmt;
		
		try {
			stmt = this.connection.prepareStatement(sql);
			stmt.setString(1, aluno.getNome());
			stmt.setString(2, aluno.getRg());
			stmt.setInt(3, aluno.getCpf());
			stmt.execute();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			this.endConnection();
		}
	}
	
	/**
	 * metodo que atualiza o aluno
	 * @param aluno
	 */
	public void update(Aluno aluno) {
		
		String sql = "update aluno set alu_nome = ?, alu_rg = ?, alu_cpf = ? where alu_id = ?";
		PreparedStatement stmt;
		
		try {
			stmt = this.connection.prepareStatement(sql);
			stmt.setString(1, aluno.getNome());
			stmt.setString(2, aluno.getRg());
			stmt.setInt(3, aluno.getCpf());
			stmt.setInt(4, aluno.getId());
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
	 * @return Aluno
	 */
	public Aluno find(int id) {
		
		String sql = "select * from aluno where alu_id = ?";
		Aluno aluno = null;
		PreparedStatement stmt;
		ResultSet res;
		
		try {
			stmt = this.connection.prepareStatement(sql);
			stmt.setInt(1, id);
			res = stmt.executeQuery();
				
			while(res.next()){
					
				aluno = new Aluno();
				aluno.setId(res.getInt(1));
				aluno.setNome(res.getString(2));
				aluno.setRg(res.getString(3));
				aluno.setCpf(res.getInt(4));
					
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			this.endConnection();
		}
		
		return aluno;
	}
	
	/**
	 * metodo que lista os alunos
	 * @return Lista Aluno
	 */
	public List<Aluno> findAll() {
		
		String sql = "select * from aluno";
		List<Aluno> alunos = new ArrayList<Aluno>();
		PreparedStatement stmt;
		ResultSet res;
		
		try {
			stmt = this.connection.prepareStatement(sql);
			res = stmt.executeQuery();
			
			while(res.next()){
				Aluno aluno = new Aluno();
				aluno.setId(res.getInt(1));
				aluno.setNome(res.getString(2));
				aluno.setRg(res.getString(3));
				aluno.setCpf(res.getInt(4));
				
				alunos.add(aluno);
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			this.endConnection();
		}
		
		return alunos;
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