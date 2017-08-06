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
	<h3>Formulario Funcionario</h3>
	<br />
	<a href="curso/listarCursos">Listar</a>
	<br /><br />
	
	<form:form action="./salvarCurso" method="post" commandName="curso">
		<form:hidden path="id" /><br />
		
		Descricao :<form:input path="descricao" />
		<br />
		Professor :
		<form:select path="professor.id" items="${professores}" itemLabel="nome" itemValue="id"/>
				
		<input type="submit" value="Salvar"/>
	</form:form>
</body>
</html>