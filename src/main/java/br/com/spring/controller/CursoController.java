package br.com.spring.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import br.com.spring.model.bean.Curso;
import br.com.spring.model.bean.Professor;
import br.com.spring.model.dao.CursoDao;
import br.com.spring.model.dao.ProfessorDao;

/**
 * Classe controlador do Curso
 * @author fernando
 *
 */
@Controller
public class CursoController {
	
	private CursoDao cursoDao;
	private ProfessorDao professorDao;
	
	@Autowired
	public CursoController(CursoDao cursoDao, ProfessorDao professorDao) {
		this.cursoDao = cursoDao;
		this.professorDao = professorDao;
	}
	
	/**
	 * metodo que redireciona para o formulario do Curso
	 * @return
	 */
	@RequestMapping("/curso")
	public ModelAndView form(){
		ModelAndView view = new ModelAndView("curso/form-curso");
		view.addObject("curso", new Curso());
		view.addObject("professores", listaProfessores());
		
		return view;
	}
	
	/**
	 * Metodo que salva e atualiza o curso
	 * @param curso
	 * @param result
	 * @return
	 */
	@RequestMapping("curso/salvarCurso")
	public String salvar(Curso curso, BindingResult result){
		
		if(result.hasErrors()){
			return "redirect:/curso";
		}
		
		/**
		 * verifica se o id esta carregado
		 */
		if(curso.getId() == 0){
			this.cursoDao.save(curso);
		}else{
			this.cursoDao.update(curso);
		}
		
		return "redirect:/curso/listarCursos";
	}
	
	/**
	 * Metodo que carrega o formulario para edicao
	 * @param id
	 * @return
	 */
	@RequestMapping("curso/{id}")
	public ModelAndView editar(@PathVariable Integer id){

		ModelAndView view = new ModelAndView("curso/form-curso-edi");
		view.addObject("curso", this.cursoDao.find(id));
		view.addObject("professores", listaProfessores());
		
		return view;
	}
	
	/**
	 * Metodo que lista todos os cursos
	 * @return
	 */
	@RequestMapping("curso/listarCursos")
	public ModelAndView listaCurso(){
		return new ModelAndView("curso/lista-curso","cursos", this.cursoDao.findAll());
	}
	
	/**
	 * metodo que lista os professores
	 * @return
	 */
	private List<Professor> listaProfessores(){
		return professorDao.findAll();
	}

}
