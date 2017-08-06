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
	<h1>Lista de Alunos</h1>
	<br />
	<a href="../aluno">Formulario</a>
	<br /><br />
	
	<table border="2">
	
		<tr>
			<th>Id</th>
			<th>Nome</th>
			<th>Rg</th>
			<th>Cpf</th>
			<th>Editar</th>
		</tr>
		
		<c:forEach items="${alunos}" var="aluno">
			<tr>
				<td>${aluno.id}</td>
				<td>${aluno.nome}</td>
				<td>${aluno.rg}</td>
				<td>${aluno.cpf}</td>
				<td>
					<a href="${aluno.id}" >Editar</a>
				</td>
			</tr>
		</c:forEach>
	</table>
</body>
</html>