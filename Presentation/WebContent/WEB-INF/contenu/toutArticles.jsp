<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE HTML>
<!--
	Phantom by HTML5 UP
	html5up.net | @ajlkn
	Free for personal and commercial use under the CCA 3.0 license (html5up.net/license)
-->
<html>
	<head>
		<title> Good POEI </title>
		<meta charset="utf-8" />
		<meta name="viewport" content="width=device-width, initial-scale=1, user-scalable=no" />
	
		  
		
	</head>
	
	   
		    <%@ include file="/WEB-INF/headerPhantom.jsp" %>
		    
		    
									<h1>Cherchez votre bonheur</h1> <br>	
									
	<body class="is-preload">
		<!-- Wrapper -->
		

				<!-- Main -->
	<div id="main">
	
	
		<section class="tiles">
							
						<div class="inner">
				
							
							
								<h4>Tableau des Articles </h4>
								
								<c:if test="${empty sessionScope.utilisateur }">
								
								<a href="Connexion" > </a>
								</c:if>
	<div class="table-wrapper">
					
					
	<form action="toutArticles" method="post">
	<table class="alt">
		
			<thead> <th> Titre </th><th> Description </th> <th> Contenu </th> <th> Frais </th>   <th> Prix </th>    <th> Achat </th> </thead>
			  
			  	<tbody>
			  	
			  	
			  	<c:forEach var="article" items="${modelContenu.articles}"> 
				<tr>
				
					<td><c:out value="${ article.titre}"/></td>
					
					<td><c:out value="${article.description}"/></td>
					
					<td><c:out value="${article.contenu}"/></td>
					
					
					<td><c:out value="${article.frais}"/></td>
					
					<td><c:out value="${article.prix}"/></td>
					
								
					<td> <a  id="lienAcheter" href="acheterArticle?acheter=${article.id}" > Acheter Lien Scred</a> </td> 
							
					<td> <a  id="lienAcheterIn" href="?acheter=${article.id}" > Acheter Lien Scred Tout </a> </td> 
					
					
					
						<td> <a  href="acheterArticle?acheterA=${article.id}" > Acheter A </a> </td> 
					 
 
					<c:if test="${ requestScope.userId == article.user_vendeur}">
					<td> <a  href="mesVentes"> Modifier </a> ></td> 
					  </c:if>
			
					

					
					
					<td> <a  href="acheterArticle?acheterA=${article.id}" > Acheter </a> </td>

					
				<c:url var="urlAcheter" value="acheterArticle">  
  					<c:param name="article_id" value= "${ article.id} "/>  
				</c:url> 
		
				</tr> 
		      </c:forEach>
		      
		    
		      </tbody>
		      
		</table>
	
	</form>
										<table class="alt">
		
			<thead> <th> Titre </th><th> Description </th> <th> Contenu </th> <th> Frais </th>   <th> Prix </th>    <th> Achat </th> </thead>
			  
			  	<tbody>
			  	
			  	<c:forEach var="article" items="${modelContenu.articles}"> 
				<tr>
				
					<td><c:out value="${ article.titre}"/></td>
					
					<td><c:out value="${article.description}"/></td>
					
					<td><c:out value="${article.contenu}"/></td>
					
					
					<td><c:out value="${article.frais}"/></td>
					
					<td><c:out value="${article.prix}"/></td>
					
					
								
					<td> <a  id="lienAcheter" href="acheterArticle?acheter_id=${article.id}" > Acheter Lien Scred</a> </td> 
							
					<td> <a  id="lienAcheterIn" href="?acheter_id=${article.id}" > Acheter Lien Scred Tout </a> </td> 
					
					
						<td> <a  href="acheterArticle?acheterA=${article.id}" > Acheter A </a> </td> 
					 
 
					
			
					
					<td> <a  href="acheterArticle"> Acheter </a> ></td> 
					
					<td> <a  href="acheterArticle?acheterA=${article.id}" > Acheter </a> </td>

					
				<c:url var="urlAcheter" value="acheterArticle">  
  					<c:param name="article_id" value= "${ article.id} "/>  
				</c:url> 
		
				</tr> 
		      </c:forEach>
		      
		      </tbody>
		      
		</table>
		
		</div>
							
					
					
					
					</div>
							
							<c:forEach var="articleCarre" items="${modelContenu.articles}">
						
							<article>
							
									<span class="image">
										<img src="images/pic01.jpg" alt="" />
									</span>
								
								<a href="generic.html">
												
								<h2>  		<option> <c:out value="${articleCarre.titre}"/> </option>        </h2>
								
								
										<div class="content">
										
										 <c:out value="${articleCarre.description}"/>
										
											 <c:out value="${articleCarre.contenu}"/>
										</div>
									</a>
								</article>
							
							
							</c:forEach>
								
								
								
								
			
								<article class="style5">
									<span class="image">
										<img src="images/pic10.jpg" alt="" />
									</span>
									<a href="generic.html">
										<h2>Ultricies</h2>
										<div class="content">
											<p>Sed nisl arcu euismod sit amet nisi lorem etiam dolor veroeros et feugiat.</p>
										</div>
									</a>
								</article>
								
								<article class="style4">
									<span class="image">
										<img src="images/pic12.jpg" alt="" />
									</span>
									<a href="generic.html">
										<h2>Pretium</h2>
										<div class="content">
											<p>Sed nisl arcu euismod sit amet nisi lorem etiam dolor veroeros et feugiat.</p>
										</div>
									</a>
								</article>
							</section>
						</div>
		


				

		<!-- Scripts -->
			

   <%@ include file="/WEB-INF/footerPhantom.jsp" %>
	</body>
</html>


