<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Delete Film</title>
</head>
<body>

<form method="POST" action="./DeleteController">
	<input type="hidden" name="id" value="${films.id}">
	<input type="submit" value="submit">
</form>

</body>
</html>