package br.com.spring.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import br.com.spring.model.bean.Aluno;
import br.com.spring.model.dao.AlunoDao;

/**
 * Classe Controlador do Aluno
 * @author fernando
 *
 */
@Controller
public class AlunoController {
	
	private AlunoDao alunoDao;
	
	@Autowired
	public AlunoController(AlunoDao alunoDao) {
		this.alunoDao = alunoDao;
	}
	
	/**
	 * Metodo que exibe a pagina do formulario
	 * @return
	 */
	@RequestMapping("/aluno")
	public ModelAndView form(){
		return new ModelAndView("aluno/form-aluno", "aluno", new Aluno());
	}

	/**
	 * Metodo que salva ou altera o aluno
	 * @param aluno
	 * @param result
	 * @return
	 */
	@RequestMapping("aluno/salvarAluno")
	public String cadastrar(Aluno aluno, BindingResult result){
		
		if(result.hasErrors()){
			return "aluno/form-aluno";
		}
		
		/**
		 * verifica se o id esta carregado
		 */
		if(aluno.getId() == 0){
			this.alunoDao.save(aluno);
		}else{
			this.alunoDao.update(aluno);
		}
		
		return "redirect:listarAlunos";
	}
	
	/**
	 * Metodo para listar todos os alunos
	 * @return
	 */
	@RequestMapping("aluno/listarAlunos")
	public ModelAndView listar(){
		
		List<Aluno> alunos = this.alunoDao.findAll();
		
		ModelAndView mv = new ModelAndView("aluno/lista-aluno");
		mv.addObject("alunos", alunos);
		
		return mv;
	}
	
	/**
	 * Metodo que carrega o formulario para alteração
	 * @param id
	 * @return
	 */
	@RequestMapping("aluno/{id}")
	public ModelAndView editar(@PathVariable Integer id){
		Aluno aluno = this.alunoDao.find(id);
		return new ModelAndView("/aluno/form-aluno-edi", "aluno", aluno);
	}

}
