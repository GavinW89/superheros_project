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
<title>Dashboard</title>
  <!-- Bootstrap -->
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css" 
      rel="stylesheet" 
      integrity="sha384-EVSTQN3/azprG1Anm3QDgpJLIm9Nao0Yz1ztcQTwFspd3yD65VohhpuuCOmLASjC" 
      crossorigin="anonymous">
<link rel="stylesheet" type="text/css" href="/css/style.css">

</head>
<body>
    <div class="container-sm mt-4 p-4 text-center bg-light roundedCorners"> <!-- Beginning of Container -->
        <h1>Hello, ${user.userName}. Here are all the current Super Heros.</h1>
        <a href="/logout" class="btn btn-danger mt-3">Logout</a>
        <a href="/newSuperHero" class="btn btn-primary mt-3">Add A Super Hero</a>
        
          <div class="d-flex justify-content-center">
        
	        <Table class="table table-dark table-striped table-hover m-4">
	        
	        	<tr>
					 <th class="align-middle">Name</th>
					 <th class="align-middle">Super Power</th>
					 <th class="align-middle">Strength</th>
	        	</tr>  
	        	
	        	<c:forEach var="i" items="${allSuperHeros }">
		        	<tr>
		        	
			        		<td><a href="/oneSuperHero/${i.id }"><c:out value="${i.name }"></c:out></a></td>
			        		<td><c:out value="${i.superPower }"></c:out></td>
			        		<td><c:out value="${i.strength }"></c:out></td>
		        	</tr>  
			        	
		        	
	        	</c:forEach>
	        	   
	        </Table>
        </div>
        
    </div> <!-- End of Container -->
</body>
</html>