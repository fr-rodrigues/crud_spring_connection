package br.com.spring.controller;

import java.sql.Timestamp;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import br.com.spring.model.bean.Matricula;
import br.com.spring.model.dao.AlunoDao;
import br.com.spring.model.dao.CursoDao;
import br.com.spring.model.dao.MatriculaDao;

/**
 * Classe controlador da matricula
 * @author fernando
 *
 */
@Controller
public class MatriculaController {
	
	private AlunoDao alunoDao;
	private CursoDao cursoDao;
	private MatriculaDao matriculaDao;
	
	@Autowired
	public MatriculaController(AlunoDao alunoDao, CursoDao cursoDao, 
			MatriculaDao matriculaDao) {
		this.alunoDao = alunoDao;
		this.cursoDao = cursoDao;
		this.matriculaDao = matriculaDao;
	}
	
	/**
	 * Metodo que redireciona para o formulario da matricula
	 * @return
	 */
	@RequestMapping("matricula")
	public ModelAndView form(){
		ModelAndView view = new ModelAndView("matricula/form-matricula");
		view.addObject("matricula", new Matricula());
		view.addObject("alunos", this.alunoDao.findAll());
		view.addObject("cursos", this.cursoDao.findAll());
		
		return view;
	}
	
	/**
	 * metodo que salva ou altera a matricula
	 * @param matricula
	 * @param result
	 * @return
	 */
	@RequestMapping("matricula/salvarMatricula")
	public void salvar(Matricula matricula, BindingResult result){
		
		if(result.hasErrors()){
			return;
		}
		
		matricula.setAluno(this.alunoDao.find(matricula.getAluno().getId()));
		matricula.setCurso(this.cursoDao.find(matricula.getCurso().getId()));
		matricula.setDtMatricula(new Timestamp(new Date().getTime()));
		
		this.matriculaDao.save(matricula);
		matricula = new Matricula();

	}
	
	/**
	 * metodo para apagar a matricula
	 * @param matricula
	 * @return
	 */
	@RequestMapping("matricula/deletarMatricula")
	public String atualizar(Matricula matricula){
		
		this.matriculaDao.delete(matricula);
		
		return "redirect:listarMatriculas";
	}
	
	/**
	 *metodo que lista as matriculas 
	 * @return
	 */
	@RequestMapping("matricula/listarMatriculas")
	public ModelAndView lista(){
		return new ModelAndView("matricula/lista-matricula", "matriculas", this.matriculaDao.findAll());
	}
	
	/**
	 * metodo qeu carrega o formulario para exclusao
	 * @param aluid
	 * @param curid
	 * @return
	 */
	@RequestMapping("matricula/{aluid}/{curid}")
	public ModelAndView editar(@PathVariable Integer aluid, @PathVariable Integer curid){

		ModelAndView view = new ModelAndView("matricula/form-matricula-edi");
		view.addObject("matricula", this.matriculaDao.find(aluid, curid));
		view.addObject("alunos", this.alunoDao.findAll());
		view.addObject("cursos", this.cursoDao.findAll());
		
		return view;
	}

}
