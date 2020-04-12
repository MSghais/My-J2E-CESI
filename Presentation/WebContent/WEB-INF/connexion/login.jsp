<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE html> 
<html> 
	<head> 
		<meta http-equiv= "Content-Type" content="text/html; charset=ISO-8859-1"> 
		<link rel="stylesheet" href="cssFormOOC.css" />
		<link rel="stylesheet" href="assetsPhantom/css/main.css" />

		<title>Login</title> 
	</head> 	
	<body> 
				
					 
		 <%@ include file="/WEB-INF/headerPhantom.jsp" %>
		 
		 
		<c:if test="${!empty sessionScope.utilisateur}">
    		${sessionScope.utilisateur.login} : <a href="deconnexion">Déconnexion</a>
        </c:if>
        <a href="accesPublic.jsp">acces public</a>
        <a href="accesrestreint">acces privé</a>
		<h4>Login</h4>
		
			
		
					

		
		<form action="Connexion" method="post">
			<table>
				<tr>
					<td>Login : </td>
					<td><input type="text" name="login"></td>
				</tr>
				<tr>
					<td>Mot de passe : </td>
					<td><input type="password" name="password"></td>
				</tr>
				<tr>
					<td></td>
					<td><input type="submit" value="Se connecter" name="connecter"></td>
				</tr>
			</table>
		</form>	
		<br>
		
		<c:out value="${requestScope.erreurs}"/><br>
						<c:out value="${requestScope.erreurs}"/><br>
				<c:out value="${requestScope.msgErreur}"/><br>
			<c:out value="${msgErreur}"/><br>
		
		
			<c:out value="${requestScope.erreursMap}"/><br>
			
						<c:out value="${requestScope.erreurs}"/><br>
						<c:out value="${requestScope.msgErreur}"/><br>
						<c:out value="${msgErreur}"/><br>
		

         <%@ include file="/WEB-INF/footerPhantom.jsp" %>
         
	</body>
	
	
	
</html>

<!--  -->
