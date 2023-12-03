<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Update Film</title>
</head>
<body>

<form method="POST" action="./UpdateController">
	<input type="hidden" name="id" value="${films.id}">
	<input type="text" name="title" placeholder="Enter Title" value="${films.title}">
	<input type="text" name="year" placeholder="Enter Year" value="${films.year}">
	<input type="text" name="director" placeholder="Enter Director" value="${films.director}">
	<input type="text" name="stars" placeholder="Enter Stars" value="${films.stars}">
	<input type="text" name="review" placeholder="Enter Review" value="${films.review}">
	<input type="submit" value="submit">
</form>

</body>
</html>