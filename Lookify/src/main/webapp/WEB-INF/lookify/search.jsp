<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<link href="//netdna.bootstrapcdn.com/bootstrap/3.0.0/css/bootstrap.min.css" rel="stylesheet" id="bootstrap-css">
<script src="//netdna.bootstrapcdn.com/bootstrap/3.0.0/js/bootstrap.min.js"></script>
<script src="//code.jquery.com/jquery-1.11.1.min.js"></script>

<link rel="stylesheet" type="text/css" href="/css/search.css" />
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Search Results</title>
</head>
<body>
	<a class="dashboard" href="/dashboard">Dashboard</a>
	<div class="search-bar">
		<h3>Songs by artist: <c:out value="${artist}"></c:out></h3>
		<form action="/search" method="get">
			<input class="search" type="text" name="artist">
			<input class="submit" type="submit" value="New Search">
		</form>
	</div>
	<div class="container">
    	<div class="row col-md-6 col-md-offset-2 custyle">
    		<table class="table table-striped custab">
    			<thead>
        			<tr>
            			<th>ID</th>
            			<th>Song Name</th>
            			<th>Rating</th>
            			<th class="text-center">Action</th>
        			</tr>
    			</thead>
    			<tbody>
					<c:forEach items="${songs}" var="song">
					<tr>
						<td><c:out value="${song.id}"/></td>
						<td><a href="/songs/${song.id}"><c:out value="${song.title}"/></a></td>
						<td><c:out value="${song.rating}"/> <span class="glyphicon glyphicon-star"></span></td>
						<td class = "text-center"><a class='btn btn-danger btn-xs' href="/songs/delete/${song.id}"><span class="glyphicon glyphicon-remove"></span>Delete</a></td>
					</tr>
					</c:forEach>
				</tbody>
    		</table>
    	</div>
	</div>
</body>
</html>