<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title></title>
</head>
<body>
	
	<form action="usuario/logof" method="post">
		<a href="aluno">Aluno</a>
		&nbsp;&nbsp;|&nbsp;&nbsp;
		<a href="funcionario">Funcionario</a>
		&nbsp;&nbsp;|&nbsp;&nbsp;
		<a href="professor">Professor</a>
		&nbsp;&nbsp;|&nbsp;&nbsp;
		<a href="curso">Curso</a>
		&nbsp;&nbsp;|&nbsp;&nbsp;
		<a href="matricula">Matricula</a>
		&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
		&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
		&nbsp;&nbsp;|&nbsp;&nbsp;
		<input type="submit" value="Sair"/>
		&nbsp;&nbsp;|&nbsp;&nbsp;
	</form>
	
</body>
</html>