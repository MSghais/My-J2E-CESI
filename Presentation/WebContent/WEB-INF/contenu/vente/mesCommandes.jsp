<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE HTML>

<html>
	<head>
		<title> Mes commandes</title>
		<meta charset="utf-8" />
		<meta name="viewport" content="width=device-width, initial-scale=1, user-scalable=no" />
	
		  
		
	</head>
	
	   
		    <%@ include file="/WEB-INF/headerPhantom.jsp" %>
		    
		    
									<h1>Cherchez votre bonheur</h1> <br>	
									
	<body class="is-preload">
		<!-- Wrapper -->
		

				<!-- Main -->
	<div id="main">
	
	
		<section >
							
						<div class="inner">
				
							
		
		<h4> Mes Articles en Vente </h4>
	
		<form action="mesCommandes" method="post">
		<table >
			
			<thead>
			 <th> Titre </th> <th> Theme </th><th> Description </th>
			<th> Contenu </th> <th> Frais </th>   <th> Prix </th>  <th> Statut </th>   <th> Date </th>
			 <th> Gérer Article </th>   <th>  Gérer sa Commande</th>  
			 </thead>
			 
			 <tbody>
			  <c:forEach var="article" items="${modelCommande.ventesArticles}"> 
				<tr>
				
				
					<td><c:out value="${ article.titre}"/></td>
					
					<td><c:out value="${ article.theme.theme_intitule}"/></td>
					<td><c:out value="${article.description}"/></td>
					
					<td><c:out value="${article.contenu}"/></td>
					
					<td><c:out value="${article.frais}"/></td>
					
					<td><c:out value="${article.prix}"/></td>
				
					<td><c:out value="${article.status}"/></td>
					
						<td><c:out value="${article.date}"/></td>
						
					
					
					
					<td> <a  id="modifier" href="modificationArticle?modifier=${article.id}" > 1.  Modifier </a>  <br>
					
					 <a  id="supprimer" href="?supprimer=${article.id}" > 2.  Supprimer </a>  <br>
					
					</td> 
					
		
				
					<td> <a  id="valider" href="?validerCommande=${article.id}" > 1.  Validez Commande </a> <br>
					
					<a  id="envoyer" href="?envoyerCommande=${article.id}" > 2.  Envoyez </a>  <br>
					
					<a  id="supprimer" href="?supprimerCommande=${article.id}" > 3.  Supprimer Commande </a>  <br>
					
					 </td> 
					
					<td>  </td> 
					
					<td>  </td> 
							
	
					</tr> 
		      </c:forEach>
			 
			 </tbody>
			 
					
					
					
				
		      
		</table>
	</form>
		
		</div>
		</section>
		
		</div>
							


   <%@ include file="/WEB-INF/footerPhantom.jsp" %>
	</body>
</html>

