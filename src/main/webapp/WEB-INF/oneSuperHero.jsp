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
<title>${superHero.name}</title>
  <!-- Bootstrap -->
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css" 
      rel="stylesheet" 
      integrity="sha384-EVSTQN3/azprG1Anm3QDgpJLIm9Nao0Yz1ztcQTwFspd3yD65VohhpuuCOmLASjC" 
      crossorigin="anonymous">
<link rel="stylesheet" type="text/css" href="/css/style.css">
</head>
<body>
    <div class="container-sm mt-4 p-4 text-center bg-light roundedCorners"> <!-- Beginning of Container -->
    
        
    <h1>${superHero.name}</h1>
    <p class="mt-1 greyedText ">(Added By ${superHero.user.userName})</p>
        <a href="/dashboard" class="btn btn-primary mt-3 mb-3">Dashboard</a>
        <a href="/newSuperHero" class="btn btn-primary mt-3 mb-3">New Super Hero</a>
        
        <div class="row p-5 m-4">
	        <div class="col-6">
		        <p>Name: <span class="boldText"><c:out value="${superHero.name}"/></span></p>
		        <br>
		        <p>Strength: <span class="boldText"><c:out value="${superHero.strength}"/></span></p>
		        <br>
		        <p>Super Power: <span class="boldText"><c:out value="${superHero.superPower}"/></span></p>
		        <br>
		        <p>Weakness: <span class="boldText"><c:out value="${superHero.weakness}"/></span></p>
		        <br>
		        <p>Origin: <span class="boldText"><c:out value="${superHero.origin}"/></span></p>
		        <br>
	        </div>
		    <div class="col">
		    	<img src="${superHero.imageLink}" class="rounded float-right img-fluid w-75"/>
		    </div>        
        </div>
        
		<c:choose>
			<c:when test="${(user.id == superHero.user.id)}">
        		<a href="/updateSuperHero/${superHero.id}" class="btn btn-success">Edit</a>
      		</c:when>
      	<c:otherwise>
      		<p>You cannot edit this name </p>
      	</c:otherwise>
      	</c:choose>
        
    </div> <!-- End of Container -->
</body>
</html>