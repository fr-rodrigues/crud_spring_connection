package br.com.spring.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;

import br.com.spring.model.bean.Usuario;
import br.com.spring.model.dao.UsuarioDao;
import br.com.spring.util.Criptografia;

/**
 * Classe responsavel por logar o usuario
 * @author fernando
 *
 */
@Controller
public class UsuarioController {
	
	private UsuarioDao usuarioDao;
	
	@Autowired
	public UsuarioController(UsuarioDao usuarioDao) {
		this.usuarioDao = usuarioDao;
	}
	
	/**
	 * metodo para logar o usuario
	 * @param usuario
	 * @param result
	 * @param session
	 * @return
	 */
	@RequestMapping("usuario/logar")
	public String logar(Usuario usuario, BindingResult result, HttpSession session){
				
		if(result.hasErrors()){
			return "usuario/login";
		}
		
		usuario.setSenha(new Criptografia().encrypt(usuario.getSenha()));
		
		/**
		 * verifica se o usuario existe
		 */
		if(this.usuarioDao.isUsuario(usuario)){
			session.setAttribute("usuario", usuario);
			return "redirect:/principal";
		}
		
		return "redirect:/";
		
	}

	/**
	 * metodo que faz o logof do usuario
	 * @param session
	 * @return
	 */
	@RequestMapping("usuario/logof")
	public String logof(HttpSession session){
		session.removeAttribute("usuario");
		return "redirect:/";
	}
	
	/**
	 * metodo responsavel para redirecionar para a tela index
	 * @return
	 */
	@RequestMapping("principal")
	public String indexRedirect(){
		return "/index";
	}

}
