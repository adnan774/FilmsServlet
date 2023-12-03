<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
	<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Films</title>
<link href="css/site.css" rel="stylesheet">
</head>
<body>

<div>
	<fieldset>
		<form method="GET" action="./search">
			<label>Search Films:</label>
			<input type="search" name="search">
    		<input type="submit" value="Search">
    		<br/>	
    	</form>
	</fieldset>	
</div>

<div>
	<fieldset>
		<form method="POST" action="./films">
			<label>Insert Film:</label>
			<input type="hidden" name="id">
			<input type="text" name="title" placeholder="Enter Title"">
			<input type="text" name="year" placeholder="Enter Year"">
			<input type="text" name="director" placeholder="Enter Director">
			<input type="text" name="stars" placeholder="Enter Stars">
			<input type="text" name="review" placeholder="Enter Review">
			<input type="submit" value="submit">
		</form> 
	</fieldset>
</div>

	<table id="films">
		<tr>
			<th>ID</th>
			<th>Title</th>
			<th>Year</th>
			<th>Director</th>
			<th>Stars</th>
			<th>Review</th>
		</tr>
		<c:forEach items="${films}" var="f">
		<tr>
			<td>${f.id}</td> 
			<td>${f.title}</td>
			<td>${f.year}</td>
			<td>${f.director}</td>
			<td>${f.stars}</td>
			<td>${f.review}</td>
			<td><a href="./UpdateController?id=${f.id}">Update</a></td>
			<td><a href="./DeleteController?id=${f.id}">Delete</a></td>
		</tr>
		</c:forEach>
	</table>
	<br>
	
	
</body>
</html>