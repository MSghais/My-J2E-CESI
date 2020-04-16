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
		
		 <h4> Ajouter un article  </h4>
		<form action="addArticleMVC" method="post" >
		<fieldset>
		

						<c:if test="${ !empty msgErreurArticle }">
						 <span class="erreur">${form.erreurs}</span>
						<c:out value="${ msgErreurArticle }"></c:out>
						</c:if>  
	        		
				
                <label for="art_titre"> Titre <span class="requis">* </span> </label>
                <input type="text" id="art_titre" name=art_titre value="<c:out value="${article.titre}"/>" size="20" maxlength="60" />
                <span class="erreur">${form.erreurs['art_titre']}</span>
                <br />
                
		         <select name="acronymeTheme"> 
					
					<c:forEach var="theme" items="${modelTheme.themes }">
					<option> <c:out value="${theme.theme_intitule}"/> </option>
					
					
							
					</c:forEach>
					
					</select>
					
             
                 <label for="art_description"> Description <span class="requis">*</span> </label>
                <input type="text" id="art_description" name="art_description" value="<c:out value="${article.description}"/>" size="20" maxlength="20" />
                 <span class="erreur">${form.erreurs['art_description']}</span>
                <br />
   
                  <label for="art_contenu"> Contenu <span class="requis">*</span> </label>
                <div class="field">
					<textarea name="art_contenu" id="art_contenu" placeholder="Message">
					 
					</textarea>
					   <span class="erreur">${form.erreurs['art_contenu']}</span>
				</div>
                    
             
                
 			<label for="art_frais"> Frais <span class="requis">*</span> </label>
                <input type="number" id="art_frais" name="art_frais" value="<c:out value="${article.frais}"/>" size="20" maxlength="20" />
                 <span class="erreur">${form.erreurs['art_frais']}</span>
                <br />
				             
         
           
 			<label for="art_frais"> Prix <span class="requis">*</span> </label>
                <input type="number" id="art_prix" name="art_prix" value="<c:out value="${article.prix}"/>" size="20" maxlength="20" />
                 <span class="erreur">${form.erreurs['art_prix']}</span>
                <br />

   
	     	  <label for="art_url"> Image ou lien ? <span class="requis">*</span></label>
                <input type="url" id="art_url" name="art_url" value="<c:out value="${article.url}"/>" size="20" maxlength="60" />
                <span class="erreur">${form.erreurs['art_url']}</span>
                 <br />
          


                <input type="submit" value="Déposer"  name="creationArticle"  />
                <br />
                
                   
                            
                            
                 <p class="${empty form.erreurs ? 'succes' : 'erreur'}">${form.resultat}</p>
					
			</fieldset>
		</form>	
		<br/>
		
		<a href="vueConnection.jsp"> Se connecter </a>
		
					
            <form>
            
            
            <a href="vueConnection.jsp"> Se connecter </a>
            
	
					
        
      <a href="/WEB-INF/JSP/vueConnection.jsp"> Se connecter </a>
		
		
		
			<script src="assets/js/jquery.min.js"></script>
			<script src="assets/js/browser.min.js"></script>
			<script src="assets/js/breakpoints.min.js"></script>
			<script src="assets/js/util.js"></script>
			<script src="assets/js/main.js"></script>
					

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