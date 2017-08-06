package br.com.spring.filter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

public class UsuarioInterceptor extends HandlerInterceptorAdapter{
	
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
	
		/**
		 * recuperando a requisicao
		 */
		String uri = request.getRequestURI();
		
		/**
		 * verifica as excecoes que  nao passarao ao interceptador...
		 * crud_spring_connection/ --> requisicao feita a 1 pagina
		 * usuario/logar" --> acao do formulario de login
		 * resources --> recursos da aplicacao
		 */
		if(uri.endsWith("crud_spring_connection/") || uri.endsWith("usuario/logar") || uri.contains("resources")){
			return true;
		}
		
		/**
		 * verifica se existe usuario na sessao
		 */
		if(request.getSession().getAttribute("usuario") != null){
			return true;
		} else {
			response.sendRedirect("/crud_spring_connection/");
			return false;
		}
		
	}

}
