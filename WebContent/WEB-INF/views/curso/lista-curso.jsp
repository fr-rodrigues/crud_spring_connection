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
	<a href="../curso">Formulario</a>
	<br /><br />
	
	<table border="2">
		<tr>
			<th>Id</th>
			<th>Descricao</th>
			<th>Professor</th>
		</tr>
		
		<c:forEach items="${cursos}" var="curso">
			<tr>
				<td>${curso.id}</td>
				<td>${curso.descricao}</td>
				<td>${curso.professor.nome}</td>
				<td>
					<a href="${curso.id}">Editar</a>
				</td>
			</tr>
		</c:forEach>
	</table>
</body>
</html>