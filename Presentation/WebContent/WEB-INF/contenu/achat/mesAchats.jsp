<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>


<!DOCTYPE html>
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
		<link rel="stylesheet" href="css/siteStyle.css" />
		<link rel="stylesheet" href="assets/css/main.css" />
			<link rel="stylesheet" href="assetsPhantom/css/main.css" >
			
		<%@ include file="/WEB-INF/headerPhantom.jsp" %>
		<title>Ma vue</title>
	</head>
	<body>
	
	
	<h4> Mes Articles acheter </h4>
	
		<table >
			<thead>  <th>Titre  </th> <th> Contenu  </th> <th>Description </th>
			<th>Prix </th><th> Frais </th>  <th> Statut </th> </thead>
			  <c:forEach var="article" items="${modelCommande.achatArticles}"> 
				
				
					  	<tbody>
					  	
					  	<tr>
					  	
					  		<td><c:out value="${ article.titre}"/></td>
					
						<td><c:out value="${ article.contenu}"/></td>
					  	
			  			<td><c:out value="${ article.description}"/></td>
			  			
			  			
					  	<td><c:out value="${ article.prix}"/></td>
					
				
				
					 	<td><c:out value="${ article.frais}"/></td>
					 	
					 	
					<td><c:out value="${ article.status}"/></td>
					
					
				
				
						
						</tr> 
						
						   </tbody>
		      </c:forEach>
		      
		  
		      
		</table>
		
		
		
			
	
	<h4> Mes Achats en cours d'acheminement </h4>
	
		<table border="1">
		<thead>  <th>Titre  </th> <th> Contenu  </th>  <th> Vendeur  </th> 
			<th>Prix </th><th> Frais </th>  <th> Statut </th> </thead>
			<th>Date</th>
			
			  <c:forEach var="commande" items="${modelCommande.commandesListe}"> 
				
				
					  	<tbody>
					  	
					  	<tr>
					  	
					  		<td><c:out value="${ commande.article.titre}"/></td>
					
						<td><c:out value="${ commande.article.contenu}"/></td>
			
					<td><c:out value="${achat.article.vendeur.login}"/></td>
			  			
					
					  			<td><c:out value="${ commande.article.prix}"/></td>
					
							  	
			  			<td><c:out value="${ commande.article.frais}"/></td>
					
							  	
			  			<td><c:out value="${ commande.article.status}"/>
			  			
		  		
			  			</td>
					
				
				
	
			  			
			  							
				
			  			<td>
			  				<td><c:out value="${ commande.date_creation}"/>
			  					<c:out value="${ commande.date_envoi}"/>
			  				
			  				</td>
		
						
						</tr> 
						
						   </tbody>
		      </c:forEach>
		      
		  
		      
		</table>
		
	
	
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
			
	
		
		
		
		</div>
	</body>
			<%@ include file="/WEB-INF/footerPhantom.jsp" %>
	
</html>