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

import br.com.spring.model.bean.Funcionario;


/**
 * classe que implementa metodos que interagem com a tabela funcionario
 * @author fernando
 *
 */
@Repository
public class FuncionarioDao {
	
private Connection connection;
	
	@Autowired
	public FuncionarioDao(DataSource dataSource) {
		try {
			this.connection = dataSource.getConnection();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * metodo que salva um funcionario
	 * @param Funcionario
	 */
	public void save(Funcionario funcionario) {
		String sql = "insert into funcionario(fun_nome, fun_rg, fun_cpf, fun_usu_id) values (?,?,?,?)";
		PreparedStatement stmt;
		
		try {
			stmt = this.connection.prepareStatement(sql);
			stmt.setString(1, funcionario.getNome());
			stmt.setString(2, funcionario.getRg());
			stmt.setInt(3, funcionario.getCpf());
			stmt.setInt(4, funcionario.getId());
			stmt.execute();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			this.endConnection();
		}
	}
	
	/**
	 * metodo que atualiza o funcionario
	 * @param Funcionario
	 */
	public void update(Funcionario funcionario) {
		String sql = "update funcionario set fun_nome = ?, fun_rg = ?, fun_cpf = ? where fun_usu_id = ?";
		PreparedStatement stmt;
		
		try {
			stmt = this.connection.prepareStatement(sql);
			stmt.setString(1, funcionario.getNome());
			stmt.setString(2, funcionario.getRg());
			stmt.setInt(3, funcionario.getCpf());
			stmt.setInt(4, funcionario.getId());
			stmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			this.endConnection();
		}
	}
	
	/**
	 * metodo que busca um funcionario
	 * @param id
	 * @return Funcionario
	 */
	public Funcionario find(int id) {
		String sql = "select fun_nome, fun_rg, fun_cpf, usu_id, usu_log, usu_sen from funcionario "
				+ "inner join usuario on fun_usu_id = usu_id where fun_usu_id = ?";
		Funcionario funcionario = null;
		PreparedStatement stmt;
		ResultSet res;
		
		try {
			stmt = this.connection.prepareStatement(sql);
			stmt.setInt(1, id);
			res = stmt.executeQuery();
			
			while(res.next()){
				funcionario = new Funcionario();
				funcionario.setNome(res.getString(1));
				funcionario.setRg(res.getString(2));
				funcionario.setCpf(res.getInt(3));
				
				/**
				 * campos com os dados da tabela usuario
				 */
				funcionario.setId(res.getInt(4));
				funcionario.setLogin(res.getString(5));
				funcionario.setSenha(res.getString(6));
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			this.endConnection();
		}
		
		return funcionario;
	}
	
	/**
	 * metodo que lista os funcionarios
	 * @return Lista Funcionarios
	 */
	public List<Funcionario> findAll() {
		String sql = "select fun_nome, fun_rg, fun_cpf, usu_id, usu_log, usu_sen from funcionario "
				+ "inner join usuario on fun_usu_id = usu_id";
		List<Funcionario> funcionarios = new ArrayList<Funcionario>();
		PreparedStatement stmt;
		ResultSet res;
		
		try {
			stmt = this.connection.prepareStatement(sql);
			res = stmt.executeQuery();
			
			while(res.next()){
				Funcionario funcionario = new Funcionario();
				funcionario.setNome(res.getString(1));
				funcionario.setRg(res.getString(2));
				funcionario.setCpf(res.getInt(3));
				
				/**
				 * campos com os dados da tabela usuario
				 */
				funcionario.setId(res.getInt(4));
				funcionario.setLogin(res.getString(5));
				funcionario.setSenha(res.getString(6));
				
				funcionarios.add(funcionario);
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			this.endConnection();
		}
		
		return funcionarios;
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