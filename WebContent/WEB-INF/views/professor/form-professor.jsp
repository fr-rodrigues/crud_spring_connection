<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>

	<a href="./principal">Inicio</a>

	<br />
	<h3>Formulario Professor</h3>
	<br />
	<a href="professor/listarProfessor">Listar</a>
	<br />
	<br />

	<form:form action="./professor/salvarProfessor" method="post" commandName="professor">
		<form:hidden path="id" />
		
		Nome :<form:input path="nome" />
		<br />
		Rg :<form:input path="rg" />
		<br />
		Cpf :<form:input path="cpf" />
		<br />
		Login :<form:input path="login" />
		<br />
		Senha :<form:password path="senha" />
		<br />
		<br />
		<br />
		<br />
		
		<input type="submit" value="Salvar">
	</form:form>
</body>
</html>