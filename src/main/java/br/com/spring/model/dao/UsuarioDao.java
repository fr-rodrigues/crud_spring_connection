package br.com.spring.model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import br.com.spring.model.bean.Usuario;

/**
 * Classe que implementa os metodos que interagem com a tabela usuario
 * @author fernando
 *
 */
@Repository
public class UsuarioDao {
	
	private Connection connection;
	
	@Autowired
	public UsuarioDao(DataSource dataSource) {
		try {
			this.connection = dataSource.getConnection();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * metodo que salva o usuario
	 * @param usuario
	 * @return PK da insercao
	 */
	public int save(Usuario usuario) {
		String sql = "insert into usuario(usu_log, usu_sen) values (?,?)";
		PreparedStatement stmt;
		ResultSet res;
		int cod = 0;
		
		try {
			stmt = this.connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
			stmt.setString(1, usuario.getLogin());
			stmt.setString(2, usuario.getSenha());
			stmt.execute();
			
			res = stmt.getGeneratedKeys();
			
			while(res.next()){
				cod = res.getInt(1);
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			this.endConnection();
		}
		
		return cod;
	}
	
	/**
	 * classe que atualiza o usuario
	 * @param usuario
	 */
	public void update(Usuario usuario) {
		String sql = "update usuario set usu_log = ? where usu_id = ?";
		PreparedStatement stmt;
		
		try {
			stmt = this.connection.prepareStatement(sql);
			stmt.setString(1, usuario.getLogin());
			stmt.setInt(2, usuario.getId());
			stmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			this.endConnection();
		}
	}
	
	/**
	 * metodo que busca o aluno
	 * @param id
	 * @return Usuario
	 */
	public Usuario find(int id) {
		String sql = "select * from usuario where usu_id = ?";
		PreparedStatement stmt;
		ResultSet res;
		Usuario usuario = null;
		
		try {
			stmt = this.connection.prepareStatement(sql);
			stmt.setInt(1, id);
			res = stmt.executeQuery();
			
			while(res.next()){
				usuario = new Usuario();
				usuario.setId(res.getInt(1));
				usuario.setLogin(res.getString(2));
				usuario.setSenha(res.getString(3));
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			this.endConnection();
		}
		
		return usuario;
	}
	
	/**
	 * metodo que lista os usuarios
	 * @return Lista Usuario
	 */
	public List<Usuario> findAll() {
		String sql = "select * from usuario";
		List<Usuario> usuarios = new ArrayList<Usuario>();
		Usuario usuario;
		PreparedStatement stmt;
		ResultSet res;
		
		try {
			stmt = this.connection.prepareStatement(sql);
			res = stmt.executeQuery();
			while(res.next()){
				usuario = new Usuario();
				usuario.setId(res.getInt(1));
				usuario.setLogin(res.getString(2));
				usuario.setSenha(res.getString(3));
				
				usuarios.add(usuario);
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			this.endConnection();
		}
		
		return usuarios;
	}
	
	/**
	 * metodo para validar o usuario
	 * @param login
	 * @param password
	 * @return
	 */
	public boolean isUsuario(Usuario usuario){
		
		String sql = "select * from usuario where usu_log = ? and usu_sen = ?";
		PreparedStatement stmt;
		
		try {
			stmt = this.connection.prepareStatement(sql);
			stmt.setString(1, usuario.getLogin());
			stmt.setString(2, usuario.getSenha());
			ResultSet resultSet = stmt.executeQuery();
			
			if(resultSet.next()){
				return true;
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			this.endConnection();
		}
		
		return false;
		
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
