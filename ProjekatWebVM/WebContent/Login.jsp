<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<jsp:useBean id="podaci" class="Klase.Podaci" scope="application"/>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<link rel="stylesheet" type="text/css" href="style.css">
<title>Insert title here</title>
</head>
<body>
	<div class="center">
	<div class="login">
	<form action=Glavna>
		<p>Username: </p><input type="text" name="email" />
		<p>Password: </p><input type="text" name="pass" /><br>
		<br>
		<input type="submit" value="log in" />
	</form>
	</div>
	</div>
</body>
</html>