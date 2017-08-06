package br.com.spring.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import br.com.spring.model.bean.Funcionario;
import br.com.spring.model.bean.Usuario;
import br.com.spring.model.dao.FuncionarioDao;
import br.com.spring.model.dao.UsuarioDao;
import br.com.spring.util.Criptografia;

/**
 * Classe controlador do funcionario
 * @author fernando
 *
 */
@Controller
public class FuncionarioController {
	
	private UsuarioDao usuarioDao;
	private FuncionarioDao funcionarioDao;
	
	@Autowired
	public FuncionarioController(UsuarioDao usuarioDao, FuncionarioDao funcionarioDao) {
		this.usuarioDao = usuarioDao;
		this.funcionarioDao = funcionarioDao;
	}
	
	/**
	 * Metodo que redireciona para a tela form do funcionario
	 * @return
	 */
	@RequestMapping("/funcionario")
	public ModelAndView form(){
		return new ModelAndView("funcionario/form-funcionario", "funcionario", new Funcionario());
	}

	/**
	 * Metodo para salvar e redirecionar para a tela listar
	 * @param funcionario
	 * @return
	 */
	@RequestMapping("funcionario/salvarListarFuncionario")
	public String saveList(Funcionario funcionario, BindingResult result){
		
		if(result.hasErrors()){
			return "funcionario/form-funcionario";
		}
		
		Usuario usuario = new Usuario();
		usuario.setId(funcionario.getId());
		usuario.setLogin(funcionario.getLogin());
		
		/**
		 * verifica se o id esta carregado
		 */
		if(funcionario.getId() == 0){
			
			usuario.setSenha(new Criptografia().encrypt(funcionario.getSenha()));
			
			/**
			 * salvando o usuario e recuperando o id gerado
			 * para salvar o funcionario
			 */
			funcionario.setId(this.usuarioDao.save(usuario));
			this.funcionarioDao.save(funcionario);
			
		}else{
			this.usuarioDao.update(usuario);
			this.funcionarioDao.update(funcionario);
		}
				
		return "redirect:listarFuncionario";
	}
	
	/**
	 * Metodo que lista os funcionarios
	 * @return
	 */
	@RequestMapping("funcionario/listarFuncionario")
	public ModelAndView listar(){
		
		List<Funcionario> funcionarios = this.funcionarioDao.findAll();
		ModelAndView view = new ModelAndView("funcionario/lista-funcionario");
		view.addObject("funcionarios", funcionarios);
		
		return view;
	}
	
	/**
	 * Metodo que carrega um funcionario para edicao
	 * @param id
	 * @return
	 */
	@RequestMapping("funcionario/{id}")
	public ModelAndView editar(@PathVariable Integer id){
		Funcionario funcionario = this.funcionarioDao.find(id);
		return new ModelAndView("/funcionario/form-funcionario-edi", "funcionario", funcionario);
	}
}
