package br.com.spring.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Controller raiz - que recebe a primeira requisicao feita a aplicacao
 * @author fernando
 *
 */
@Controller
public class RootController {

	/**
	 * metodo que redireciona para a a pagina index da app
	 * @return
	 */
	@RequestMapping(value="/")
	public String index(){
		return "/usuario/login";
	}
}
