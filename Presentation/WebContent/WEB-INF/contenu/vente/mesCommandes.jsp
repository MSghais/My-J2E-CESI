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
	
		<table border="1">
			<th>Index </th><th>Statut Commande </th> <th> Article Id </th>
			  <c:forEach var="commandeAchat" items="${modelCommande.commandesListe}"> 
				<tr>
		
				
				
					<td><c:out value="${ commandeAchat.prix}"/></td>
					
						<td><c:out value="${ commandeAchat.article_id}"/></td>
					
						
				</tr> 
		      </c:forEach>
		      
		</table>
		      
	
		
		</div>
							


   <%@ include file="/WEB-INF/footerPhantom.jsp" %>
	</body>
</html>
