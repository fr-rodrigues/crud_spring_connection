<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>

	<a href="../principal">Inicio</a>

	<br />
	<h1>Lista de Funcionario</h1>
	<br />
	<a href="../matricula">Formulario</a>
	<br /><br />
	
	<table border="2">
		<tr>
			<th>Aluno</th>
			<th>Curso</th>
			<th>Data de Matricula</th>
		</tr>
		
		<c:forEach items="${matriculas}" var="matricula">
			<tr>
				<td>${matricula.aluno.nome}</td>
				<td>${matricula.curso.descricao}</td>
				<td>${matricula.dtMatricula}</td>
				<td>
					<a href="${matricula.aluno.id}/${matricula.curso.id}">Editar</a>
				</td>
			</tr>
		</c:forEach>
	</table>
</body>
</html>