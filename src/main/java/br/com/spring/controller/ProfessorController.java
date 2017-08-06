package br.com.spring.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import br.com.spring.model.bean.Professor;
import br.com.spring.model.bean.Usuario;
import br.com.spring.model.dao.ProfessorDao;
import br.com.spring.model.dao.UsuarioDao;
import br.com.spring.util.Criptografia;

/**
 * Classe controllador do Professor
 * @author fernando
 *
 */
@Controller
public class ProfessorController {

	private UsuarioDao usuarioDao;
	private ProfessorDao professorDao;
	
	@Autowired
	public ProfessorController(UsuarioDao usuarioDao, ProfessorDao professorDao) {
		this.usuarioDao = usuarioDao;
		this.professorDao = professorDao;
	}
	
	/**
	 * Exibe o formulario do professor
	 * @return
	 */
	@RequestMapping("/professor")
	public ModelAndView form(){
		return new ModelAndView("professor/form-professor", "professor", new Professor());
	}
	
	@RequestMapping("professor/salvarProfessor")
	public String salvar(Professor professor, BindingResult result){
		
		if(result.hasErrors()){
			return "professor/form-professor";
		}
		
		Usuario usuario = new Usuario();
		usuario.setId(professor.getId());
		usuario.setLogin(professor.getLogin());
		
		/**
		 * verifica se o id esta carregado
		 */
		if(professor.getId() == 0){
			
			/**
			 * salvando o usuario e recuperando o id gerado
			 * para salvar o professor
			 */
			usuario.setSenha(new Criptografia().encrypt(professor.getSenha()));
			professor.setId(this.usuarioDao.save(usuario));
			this.professorDao.save(professor);
			
		}else{
			this.usuarioDao.update(usuario);
			this.professorDao.update(professor);
		}
		
		return "redirect:listarProfessor";
	}
	
	/**
	 * Metodo que lista os alunos
	 * @return
	 */
	@RequestMapping("professor/listarProfessor")
	public ModelAndView listar(){
		
		List<Professor> professores = this.professorDao.findAll();
		ModelAndView mv = new ModelAndView("professor/listar-professor");
		mv.addObject("professores", professores);
		
		return mv;
	}
	
	/**
	 * Metodo que carrega o professor para edicao
	 * @param id
	 * @return
	 */
	@RequestMapping("professor/{id}")
	public ModelAndView editar(@PathVariable Integer id){
		Professor professor = this.professorDao.find(id);
		return new ModelAndView("professor/form-professor-edi", "professor", professor);
	}
}
