<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE html>
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
		
		<link rel="stylesheet" href="assetsPhantom/css/main.css" />
		<link rel="stylesheet" href="cssFormOOC.css" />
	
	
		<title> Inscription </title>
	</head>
	<body>
		<%@ page import="utilisateurs.entite.Role" %>
		<%@ include file="/WEB-INF/headerPhantom.jsp" %>
		
		
	<div>
	
	<section>
	
	
		<table>
		
	
		<tr>
		<td> <input type="button"> <a href="connectionUser"> Se connecter </a> </td>
		</tr>
		
		</table>
		
		<c:set var="roleVALUES" value="<%=Role.values()%>"/>
		
		                 <p class="${empty inscriptionForm.erreurs ? 'succes' : 'erreur'}">${inscriptionForm.resultat}</p>
		                 
		                 	<c:out value="${erreurs}"/><br>
		
		<h4> Inscription </h4>
		<form action="Inscription" method="post" >
		<fieldset>
		
		
		
		            <c:out value="${erreurs }"></c:out>
		            
		            
		            <c:out value="${erreursAllForm }"></c:out>
		            
		
					<label for="rubrique_name"> Role <span class="requis">* </span> </label>
							
								 <select name="role"> 
							 
							 
									<c:forEach var="roleVal" items="${roleStatus}">
									
									<option> <c:out value="${roleVal}"/> </option>
									
									
									</c:forEach>
						</select>
						
				
                <label for="username">Nom d'utilisateur <span class="requis">* </span> </label>
                <input type="text" id="username" name="username" value="<c:out value="${utilisateur.username}"/>" size="20" maxlength="60" />
                <span class="erreur">${inscriptionForm.erreurs['username']}</span>
                <br />
                 <c:out value="${erreurs ['username']}"></c:out>
                 <label for="login"> Login de connexion <span class="requis">*</span> </label>
                <input type="text" id="login" name="login" value="<c:out value="${utilisateur.login}"/>" size="20" maxlength="20" />
                 <span class="erreur">${inscriptionForm.erreurs['login']}</span>
                 
               
                <br />
                             <c:out value="${erreurs ['login']}"></c:out>
                
                
				       <label for="email">Adresse email <span class="requis">*</span></label>
                <input type="email" id="email" name="email" value="<c:out value="${utilisateur.email}"/>" size="20" maxlength="60" />
                <span class="erreur">${inscriptionForm.erreurs['email']}</span>
                                     <c:out value="${erreurs ['email']}"></c:out>
                
                
                <label for="motdepasse">Mot de passe <span class="requis">*</span></label>
                <input type="password" id="password" name="password" value="" size="20" maxlength="20" />
                 <span class="erreur">${inscriptionForm.erreurs['password']}</span>
                       <c:out value="${erreurs ['password']}"></c:out>
                <br />

                <label for="confirmation">Confirmation du mot de passe <span class="requis">*</span></label>
                <input type="password" id="confirmation" name="confirmation" value="" size="20" maxlength="20" />
                <span class="erreur">${inscriptionForm.erreurs['confirmation']}</span>
                 <br />
                 <c:out value="${erreurs ['confirmation']}"></c:out>


                <input type="submit" value="S'incrire"  name="inscriptionUserMVC"  />
                <br />
                
                 <p class="${empty inscriptionForm.erreurs ? 'succes' : 'erreur'}">${inscriptionForm.resultat}</p>
					
					<c:out value="${erreurs}"/><br>
					
			</fieldset>
		</form>	
		<br/>
		
			
	
					

			<%@ include file="/WEB-INF/footerPhantom.jsp" %>
		
		</section>
		
		
		</div>
	</body>
	
</html>