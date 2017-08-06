<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Formulario Aluno</title>
</head>
<body>

	<a href="../principal">Inicio</a>

	<br />
	<h3>Formulario Aluno</h3>
	<br />
	<a href="./listarAlunos">Listar</a>
	<br /><br />
	
	<form:form action="./salvarAluno" method="post" commandName="aluno">
	
		<form:hidden path="id"/>
	
		Nome:<br />
		<form:input path="nome"/><br />
		RG:<br />
		<form:input path="rg"/><br />
		CPF:<br />
		<form:input path="cpf"/><br />
		<br />
		<br />
		<input type="submit" value="cadastrar"/>
	
	</form:form>

</body>
</html>