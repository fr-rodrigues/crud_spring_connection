<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<script type="text/javascript" src="resources/js/jquery-1.11.3.min.js"></script>
<script type="text/javascript" src="resources/js/main.js"></script>
</head>
<body>

	<a href="./principal">Inicio</a>

	<br />
	<h3>Formulario Matricula</h3>
	<br />
	<a href="matricula/listarMatriculas">Listar</a>
	<br /><br />
	
	<form:form id="formMatricula" method="post" commandName="matricula">
		
		Aluno :<br />
		<form:select path="aluno.id">
			<form:options items="${alunos}" itemLabel="nome" itemValue="id"/>
		</form:select>
		<br />
		Curso :<br />
		<form:select path="curso.id">
			<form:options items="${cursos}" itemLabel="descricao" itemValue="id"/>
		</form:select>
		<br /><br />
		
		<input type="submit" value="Salvar">
	</form:form>
</body>
</html>