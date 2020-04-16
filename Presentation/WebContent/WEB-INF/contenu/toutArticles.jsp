<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE HTML>

<html>
	<head>
		<title> Good POEI </title>
		<meta charset="utf-8" />
		<meta name="viewport" content="width=device-width, initial-scale=1, user-scalable=no" />
	
		  
		
	</head>
	
	   		<%@ page import="contenu.enume.StatutArticle" %>
		    <%@ include file="/WEB-INF/headerPhantom.jsp" %>
		    
		    	 
		    
									<h1>Cherchez votre bonheur</h1> <br>	
									
	<body class="is-preload">
		<!-- Wrapper -->
		

				<!-- Main -->
	<div id="main">
	
	
		<section >
							
						<div class="inner">
				
							
							
								<h4>Tableau des Articles </h4>
								
								<c:if test="${empty sessionScope.utilisateur }">
								
								<a href="Connexion" > </a>
								
								</c:if>
	<div >
									
	<form action="toutArticles" method="post">
	<table >
		
			<thead> <th> Titre </th> <th> Theme </th> <th> Description </th> <th> Contenu </th>
			 <th> Frais </th>   <th> Prix </th> <th> Date </th>      <th> Achat </th> </thead>
			  
			  	<tbody>
			  	
			  	
			  	<c:forEach var="article" items="${modelContenu.articles}"> 
			  	
			  	<c:if test="${ article.status == StatutArticle.DISPONIBLE}">
			  	
			  		<tr>	
			  	
						  	<td><c:out value="${ article.titre}"/></td>
								
								<td><c:out value="${article.theme.theme_intitule}"/></td>
								<td><c:out value="${article.description}"/></td>
								
								<td><c:out value="${article.contenu}"/></td>
								
								
								<td><c:out value="${article.frais}"/></td>
								
								<td><c:out value="${article.prix}"/></td>
								
								
								<td>
								<fmt:formatDate value="${article.date}" pattern="dd-MM-yyyy"/>
								</td>
					
				
					
							<td> <a  href="acheterArticle?acheter=${article.id}" > Acheter </a> </td> 
					 
 
							
		
				</tr> 
			  	
			  	
			  	
			  	</c:if>
				<tr>
				
					
		      </c:forEach>
		      
		    
		      </tbody>
		      
		</table>
	
	</form>
										
										
										
		
		<form action="Shopping" method="post">
		
		<label>  Choissisez un Theme : </label>
					
					
					 <select name="acronymeTheme"> 
					
					<c:forEach var="theme" items="${modelTheme.themes }">
					<option> <c:out value="${theme.theme_intitule}"/> </option>
					
					
							
					</c:forEach>
					
					</select>
				
		 <input type="submit" value="Selectionner"  name="choixTheme"  /> 
						

			
<table>
						
				<thead> 
				<th> Titre </th> <th> Theme </th> <th> Description </th> <th> Contenu </th> 
				<th> Frais </th>   <th> Prix </th>   <th> Achat </th> </thead>
				 
			  
			  	<tbody>
			  	
			  	
			  	<c:forEach var="article" items="${modelContenuSelect.articles}"> 
			  	
			  	<c:if test="${ article.status == StatutArticle.DISPONIBLE}">
			  	
	  		  	
			  		<tr>	
			  	
						  	<td><c:out value="${ article.titre}"/></td>
								
								<td><c:out value="${article.theme_id}"/></td>
								<td><c:out value="${article.description}"/></td>
								
								<td><c:out value="${article.contenu}"/></td>
								
								
								<td><c:out value="${article.frais}"/></td>
								
								<td><c:out value="${article.prix}"/></td>
					
				
					
							<td> <a  href="acheterArticle?acheter=${article.id}" > Acheter </a> </td> 
					 
 		
						</tr> 
			  	
			  
			  	
			  	</c:if>
				
				
					
		      </c:forEach>
		</tbody>
		</table>
		
		</form>
	
							
										
			
							
					
					
					
					</div>
							
							<c:forEach var="articleCarre" items="${modelContenu.articles}">
						
						<label> Titre : </label><h2> </h2><c:out value="${articleCarre.titre}"/>
							<article>
							
							
							
									<span class="image">
										<img src="images/pic01.jpg" alt="" />
									
							<label> Description : </label>		<c:out value="${articleCarre.description}"/>
										
								<label> Contenu : </label>			<br> <c:out value="${articleCarre.contenu}"/>
									
							<label> Frais : </label>			<br> <c:out value="${articleCarre.frais}"/>
												
								<label> Prix : </label>			<br> <c:out value="${articleCarre.prix}"/>
										 
									
									</span>
								
										<div class="content">
										
										 
								<a  href="acheterArticle?acheter=${article.id}" > Acheter </a>
										</div>
								
								</article>
							
							
							</c:forEach>
								
								
								
					


				

		<!-- Scripts -->
			

   <%@ include file="/WEB-INF/footerPhantom.jsp" %>
	</body>
</html>


