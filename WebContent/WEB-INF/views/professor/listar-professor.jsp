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
	<h3>Lista de Professores</h3>
	<br />
	<a href="../professor">Formulario</a>
	<br />
	<br />

	<table border="2">
		<tr>
			<th>Id</th>
			<th>Nome</th>
			<th>Rg</th>
			<th>Cpf</th>
			<th>Login</th>
		</tr>
		
		<c:forEach items="${professores}" var="professor">
			<tr>
				<td>${professor.id}</td>
				<td>${professor.nome}</td>
				<td>${professor.rg}</td>
				<td>${professor.cpf}</td>
				<td>${professor.login}</td>
				<td>
					<a href="${professor.id}">Editar</a>
				</td>
			</tr>
		</c:forEach>
	</table>
</body>
</html>