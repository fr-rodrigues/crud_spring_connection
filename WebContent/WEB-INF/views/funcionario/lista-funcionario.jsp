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
	<a href="../funcionario">Formulario</a>
	<br /><br />
	
	<table border="2">
		<tr>
			<th>Id</th>
			<th>Nome</th>
			<th>Rg</th>
			<th>Cpf</th>
			<th>Login</th>
		</tr>
		
		<c:forEach items="${funcionarios}" var="funcionario">
			<tr>
				<td>${funcionario.id}</td>
				<td>${funcionario.nome}</td>
				<td>${funcionario.rg}</td>
				<td>${funcionario.cpf}</td>
				<td>${funcionario.login}</td>
				
				<td>
					<a href="${funcionario.id}">Editar</a>
				</td>
			</tr>
		</c:forEach>
	</table>
</body>
</html>