<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>


<!DOCTYPE html>
<html>
    <head>
        		<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
		<link rel="stylesheet" href="cssFormOOC.css" />
		<link rel="stylesheet" href="assets/css/main.css" >
        <title> Modification </title>
        	
				
    </head>
    <%@ include file="/WEB-INF/headerPhantom.jsp" %>
    <body>
    
    
		<section>   
		
		 <h4> Modifier votre Article </h4>
		<form action="modificationArticle" method="post" >
		<fieldset>
			
                <label for="art_titre"> Titre <span class="requis">* </span> </label>
                <input type="text" id="art_titre" name=art_titre value="<c:out value="${articleModif.titre}"/>" size="20" maxlength="60" />
                <span class="erreur">${form.erreurs['art_titre']}</span>          
          	   <c:out value="${form.erreurs['art_titre']}"></c:out>
          	   
                <br />
                
		                	<label for="theme_d"> Choissisez un theme <span class="requis">* </span> </label>
							
							 <select name="acronymeTheme"> 
							
							<c:forEach var="theme" items="${modelAll.themes }">
							<option> <c:out value="${theme.theme_intitule}"/> </option>
							
									
							</c:forEach>
							
							</select>
					
                
	     	  <label for="art_url"> Image ou lien ? <span class="requis">*</span></label>
                <input type="url" id="art_url" name="art_url" value="<c:out value="${articleModif.url}"/>" size="20" maxlength="60" />
                <span class="erreur">${form.erreurs['art_url']}</span>
                 <br />
						   <c:out value="${form.erreurs['art_url']}"></c:out>
				
                 <label for="art_description"> Description <span class="requis">*</span> </label>
                <input type="text" id="art_description" name="art_description" value="<c:out value="${articleModif.description}"/>" size="20" maxlength="20" />
                 <span class="erreur">${form.erreurs['art_description']}</span>
                <br />
                		   <c:out value="${form.erreurs['art_description']}"></c:out>
                
                  <label for="art_contenu"> Contenu <span class="requis">*</span> </label>
                <div class="field">
					<textarea name="art_contenu" id="art_contenu" placeholder="Message">
				<c:out value="${articleModif.contenu}"/>"
					</textarea>
					   <span class="erreur">${form.erreurs['art_contenu']}</span>
					   
					   <c:out value="${form.erreurs['art_contenu']}"></c:out>
				</div>
                                
             
                
 			<label for="art_frais"> Frais <span class="requis">*</span> </label>
                <input type="text" id="art_frais" name="art_frais" value="<c:out value="${articleModif.frais}"/>" size="20" maxlength="20" />
                 <span class="erreur">${form.erreurs['art_frais']}</span>
                <br />
                	   <c:out value="${form.erreurs['art_frais']}"></c:out>

           
 			<label for="art_frais"> Prix <span class="requis">*</span> </label>
                <input type="text" id="art_prix" name="art_prix" value="<c:out value="${articleModif.prix}"/>" size="20" maxlength="20" />
                 <span class="erreur">${form.erreurs['art_prix']}</span>
                <br />
					 <c:out value="${form.erreurs['art_prix']}"></c:out>
					



                <input type="submit" value="Déposer"  name="modifierArticle"  />
                <br />
                
                 <c:out value="${form.erreurs}"></c:out>
                 
                 
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

