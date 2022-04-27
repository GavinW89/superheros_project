<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
   <!-- c:out ; c:forEach ; c:if -->
 <%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%> 
   <!-- Formatting (like dates) -->
 <%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
   <!-- form:form -->
 <%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>  
   <!-- for rendering errors on PUT routes -->
 <%@ page isErrorPage="true" %>   
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>New Super Hero</title>
  <!-- Bootstrap -->
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css" 
      rel="stylesheet" 
      integrity="sha384-EVSTQN3/azprG1Anm3QDgpJLIm9Nao0Yz1ztcQTwFspd3yD65VohhpuuCOmLASjC" 
      crossorigin="anonymous">
<link rel="stylesheet" type="text/css" href="/css/style.css">
</head>
<body>
    <div class="container-sm mt-4 p-4 text-center bg-light roundedCorners"> <!-- Beginning of Container -->
    
    <h1>Add a Super Hero</h1>
        
        <a href="/dashboard" class="btn btn-primary mt-3">Dashboard</a>
        
        
        
        <%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>    
<h3 class="m-4">New Super Hero</h3>
<form:form action="/updatingSuperHero/${superhero.id}" method="post" modelAttribute="superhero">
	<input type="hidden" value="${user.id}" name="user"/>
	<input type="hidden" value="put" name="_method"/>
    <p>
        <form:label path="name" class="font-weight-bold">Name:</form:label>
        <form:errors path="name" class="alert-danger"/>
        <br>
        <form:input path="name" class="form-control"/>
    </p>
    <p>
        <form:label path="superPower" class="font-weight-bold">Super Power:</form:label>
        <form:errors path="superPower" class="alert-danger"/>
        <form:input path="superPower" class="form-control"/>
    </p>
    <p>
        <form:label path="weakness" class="font-weight-bold">Weakness:</form:label>
        <form:errors path="weakness" class="alert-danger"/>
        <form:input path="weakness" class="form-control"/>
    </p>
    <p>
        <form:label path="origin" class="font-weight-bold">Origin:</form:label>
        <form:errors path="origin" class="alert-danger"/>
        <form:textarea path="origin" class="form-control"/>
    </p>
    <p>
        <form:label path="strength" class="font-weight-bold">Strength:</form:label>
        <form:errors path="strength" class="alert-danger"/>
        <form:input type="number" min="1" max="10" path="strength" class="form-control"/>
    </p>
    <p>
        <form:label path="imageLink" class="font-weight-bold">Image Link:</form:label>
        <form:errors path="imageLink" class="alert-danger"/>
        <form:input path="imageLink" class="form-control"/>
    </p>
    
    
    <input type="submit" value="Submit" class="btn btn-success m-2"/>
</form:form>    
<a href="/dashboard" class="btn btn-info">Cancel</a>
        
        
    </div> <!-- End of Container -->
</body>
</html>