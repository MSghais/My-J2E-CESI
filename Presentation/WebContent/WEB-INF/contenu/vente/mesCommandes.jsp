<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE HTML>

<html>
	<head>
		<title>Phantom by HTML5 UP</title>
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
				
							
		
		<h4> Mes Articles en Vente </h4>
	
		<form action="mesCommandes" method="post">
		<table border="1">
			
			<thead>
			 <th> Titre </th> <th> Theme </th><th> Description </th>
			<th> Contenu </th> <th> Frais </th>   <th> Prix </th>  <th> Statut </th> 
			 <th> Modifier </th>   <th> Supprimer </th>   <th> Envoyer </th> 
			 
			 
			 </thead>
			  <c:forEach var="article" items="${modelCommande.ventesArticles}"> 
				<tr>
				
				
					<td><c:out value="${ article.titre}"/></td>
					
						<td><c:out value="${ article.theme}"/></td>
					<td><c:out value="${article.description}"/></td>
					
					<td><c:out value="${article.contenu}"/></td>
					
					<td><c:out value="${article.frais}"/></td>
					
					<td><c:out value="${article.prix}"/></td>
					
					
						
					<td><c:out value="${article.status}"/></td>
					
					<td> <a  id="modifier" href="?modifier=${article.id}" > Modifier </a> </td> 
					
					<td> <a  id="supprimer" href="?supprimer=${article.id}" > Modifier </a> </td> 
					
					
					<td> <a  id="envoyer" href="?envoyer=${article.id}" > Envoyez </a> </td> 
					
					
					
					
				</tr> 
		      </c:forEach>
		      
		</table>
	</form>
		
		</div>
							


   <%@ include file="/WEB-INF/footerPhantom.jsp" %>
	</body>
</html>
