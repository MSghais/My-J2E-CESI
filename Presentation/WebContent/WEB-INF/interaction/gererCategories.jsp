<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>


<!DOCTYPE html>
<html>
    <head>
        		<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
		<link rel="stylesheet" href="cssFormOOC.css" />
		<link rel="stylesheet" href="assets/css/main.css" >
        <title> Vends du rêve </title>
        	
				
    </head>
    <%@ include file="/WEB-INF/headerPhantom.jsp" %>
    <body>
    
    
    <a href="WEB-INF/JSP/vueConnection.jsp"> Vous n'êtes pas connecté ?</a>
		
   
			
		<section>   
		
		 <h4> Creer une categories</h4>
		<form action="gererCategories" method="post" >
		<fieldset>
		


                
	     	  <label for="categories"> Intitule <span class="requis">*</span></label>
                <input type="text" id="intitule" name="intitule" value="<c:out value="${theme.intitule}"/>" size="20" maxlength="60" />

                 <br />
                

                <input type="submit" value="Déposer"  name="creationCategorie"  />
                <br />
                
                 <p class="${empty form.erreurs ? 'succes' : 'erreur'}">${form.resultat}</p>
					
			</fieldset>
		</form>	
		<br/>
		
							
				 <h4> Liste des categories  </h4>
                
		      <table>
		      
		      
					<c:forEach var="theme" items="${modelTheme.themes }">
					
					<tr>
					<td> <c:out value="${theme.theme_intitule}"/>
					</td>
					</tr>
				
					
							
					</c:forEach>
		      
		      </table>
	
					
        
      <a href="/WEB-INF/JSP/vueConnection.jsp"> Se connecter </a>
		
		


		</section>
		
	</body>
			<%@ include file="/WEB-INF/footerPhantom.jsp" %>
	
	
</html>



<!-- 



		
		<td> Choissisez un Theme :</td>
					<td>
					
					 <select name="acronymeTheme"> 
					
					<c:forEach var="theme" items="${model.themes }">
					<option> <c:out value="${theme.nameRubrique}"/> </option>
					
					<option> <c:out value="${theme.description}"/> </option>
						
							<c:forEach var="themeSub" items="${model.themes}">
							
							<option> <c:out value="${themeSub.sousRubriques}"/> </option>
							
							
							</c:forEach>
							
							
					</c:forEach>
					
					</select>
				
				
			<h4>Tableau des Articles par theme </h4>
	
		<table border="1">
			<th>NOM</th> 
			<th>PRENOM</th>
			
				<tr>
			  <c:forEach var="article" items="${model.articles}"> 
				<tr>
				
				<td><c:out value="${ article.id}"/></td>
				
					<td><c:out value="${ article.nom}"/></td>
					
						<td><c:out value="${ article.themeString}"/></td>
					<td><c:out value="${article.description}"/></td>
					
					<td><c:out value="${article.contenu}"/></td>
				</tr> 
		      </c:forEach>
		      
		</table>





		                	<label for="theme_d"> Choissisez un theme <span class="requis">* </span> </label>
							
							 <select name="acronymeTheme"> 
							
							<c:forEach var="theme" items="${model.themes }">
							<option> <c:out value="${theme.nameRubrique}"/> </option>
							
							<option> <c:out value="${theme.description}"/> </option>
								
									<c:forEach var="themeSub" items="${model.themes}">
									
									<option> <c:out value="${themeSub.sousRubriques}"/> </option>
									
									
									</c:forEach>
									
									
							</c:forEach>
							
							</select> -->