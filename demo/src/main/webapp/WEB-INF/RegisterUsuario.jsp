<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Registrarse</title>
</head>
<body>
	<h1>FRASES</h1>
	<ul>
		<li><a href="/">Login</a></li>
		<li><a href="/registro">Registro</a></li>
	</ul>
	<h2>Registrarse</h2>
	<form:form action="/register" method="POST" modelAttribute="usuario">
		
		<form:label path="nombre">Nombre:</form:label>
		<form:input path="nombre" type="text" />
		<form:errors path="nombre" />
		
		<form:label path="apellido">Apellido:</form:label>
		<form:input path="apellido" type="text" />
		<form:errors path="apellido" />
		
		<form:label path="email">Email:</form:label>
		<form:input path="email" type="text" />
		<form:errors path="email" />
		
		<form:label path="password">Contraseña:</form:label>
		<form:input path="password" type="text" />
		<form:errors path="password" />
		
		<form:label path="confirmarPassword">Confirmar Contraseña:</form:label>
		<form:input path="confirmarPassword" type="text" />
		<form:errors path="confirmarPassword" />
		
		<input type="submit" value="Registrase" />
	</form:form>
</body>
</html>