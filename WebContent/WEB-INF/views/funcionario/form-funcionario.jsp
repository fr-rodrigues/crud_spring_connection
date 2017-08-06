<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Formulario Funcionario</title>
</head>
<body>
	
	<a href="./principal">Inicio</a>

	<br />
	<h3>Formulario Funcionario</h3>
	<br />
	<a href="funcionario/listarFuncionario">Listar</a>
	<br /><br />
	
	<form:form method="post" action="./salvarListarFuncionario" commandName="funcionario">
		
		<form:hidden path="id" />
		
		Login:<br />
		<form:input path="login" /><br />
		Senha:<br />
		<form:password path="senha" /><br />
		
		<br />
		
		Nome:<br />
		<form:input path="nome" /><br />
		RG:<br />
		<form:input path="rg" /><br />
		Cpf:<br />
		<form:input path="cpf" /><br />
		<br />
		<br />
		<br />
		<br />
		<br />
		<input type="submit" value="Cadastrar" />
		
	</form:form>
</body>
</html>