<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>


<!DOCTYPE html>
<html>
    <head>
        		<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
		<link rel="stylesheet" href="cssFormOOC.css" />
		<link rel="stylesheet" href="assets/css/main.css" >
        <title> Formations </title>
        	
				
    </head>
    <%@ include file="/WEB-INF/headerPhantom.jsp" %>
    <body>
    
    
    <a href="WEB-INF/JSP/vueConnection.jsp"> Vous n'êtes pas connecté ?</a>
		
   
			
		<section>   
		
		 <h4> Ajouter article MVC </h4>
		<form action="addArticleMVC" method="post" >
		<fieldset>
		

	
	
									
				
                <label for="art_titre"> Titre <span class="requis">* </span> </label>
                <input type="text" id="art_titre" name=art_titre value="<c:out value="${article.art_titre}"/>" size="20" maxlength="60" />
                <span class="erreur">${form.erreurs['art_titre']}</span>
                <br />
                
		                	<label for="theme_d"> Choissisez un theme <span class="requis">* </span> </label>
							
							 <select name="acronymeTheme"> 
							
							<c:forEach var="theme" items="${modelAll.themes }">
							<option> <c:out value="${theme.theme_intitule}"/> </option>
							
							<option> <c:out value="${theme.theme_description}"/> </option>
					
									
									
							</c:forEach>
							
							</select>
					
                
	     	  <label for="art_url"> Image ou lien ? <span class="requis">*</span></label>
                <input type="url" id="art_url" name="art_url" value="<c:out value="${article.art_url}"/>" size="20" maxlength="60" />
                <span class="erreur">${form.erreurs['art_url']}</span>
                 <br />
                
                 <label for="art_description"> Description <span class="requis">*</span> </label>
                <input type="text" id="art_description" name="art_description" value="<c:out value="${article.art_description}"/>" size="20" maxlength="20" />
                 <span class="erreur">${form.erreurs['art_description']}</span>
                <br />
                
                <div class="field">
					<textarea name="message" id="message" placeholder="Message"></textarea>
				</div>
                                
                <label for="art_contenu">Contenu de l'article <span class="requis">*</span></label>
                <input type="text" id="art_contenu" name="art_contenu" value="" size="20" maxlength="20" />
                 <span class="erreur">${form.erreurs['art_contenu']}</span>
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
            
	
	 
        <div id="bloc_page">
            
            	<div id="main">
						<div class="inner">
							<h1>Generic Page</h1>
							<span class="image main"><img src="images/pic13.jpg" alt="" /></span>
							<p>Donec eget ex magna. Interdum et malesuada fames ac ante ipsum primis in faucibuset dolorm diam, vitae egestas enim auctor sit amet. Pellentesque leo mauris, consectetur id ipsum sit amet, fergiat. Pellentesque in mi eu massa lacinia malesuada et a elit. Donec urna ex, lacinia in purus ac, pretium pulvinar mauris. Curabitur sapien risus, commodo eget turpis at, elementum convallis elit. Pellentesque enim turpis, hendrerit tristique.</p>
							<p>Lorem ipsum dolor sit amet, consectetur adipiscing elit. Duis entum. Mauris risus lacus, blandit sit amet venenatis non, bibendum vitae dolor. Nunc lorem mauris, fringilla in aliquam at, euismod in lectus. Pellentesque habitant morbi tristique senectus et netus et malesuada fames ac turpis egestas. In non lorem sit amet elit placerat maximus. Pellentesque aliquam maximus risus, vel venenatis mauris vehicula hendrerit.</p>
							<p>Interdum et malesuada fames aue leo mauris, consectetur id ipsum sit amet, fersapien risus, commodo eget turpis at, elementum convallis elit. Pellentesque enim turpis, hendrerit tristique lorem ipsum dolor.</p>
						</div>
					</div>
					
					
					
					
			<div> 
			
		
		<h4>Tableau des Articles </h4>
	
		<table border="1">
			<th>NOM</th><th>PRENOM</th>
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