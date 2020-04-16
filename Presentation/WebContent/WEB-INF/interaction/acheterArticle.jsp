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
	
	<h4>Tableau des Articles </h4>
	<div class="table-wrapper">
										<table class="alt">
		
		<thead> <th> Titre </th><th> Description </th> <th> Contenu </th> <th> Frais </th>   <th> Prix </th>    <th> Achat </th> </thead>
			  
			  	<tbody>
			  	
			  	
				<tr>
				
					<td><c:out value="${ modelArticle.article.titre}"/></td>
					
					<td><c:out value="${modelArticle.article.description}"/></td>
					
					<td><c:out value="${modelArticle.article.contenu}"/></td>
					
					
					<td><c:out value="${modelArticle.article.frais}"/></td>
					
					<td><c:out value="${modelArticle.article.prix}"/></td>
				
				</tr> 
		     
		      
		      </tbody>
		      
		</table>
		
		</div>
	
	<form action="acheterArticle" method="post" > 
	<fieldset>
			<label for="codeBancaire">Insérer votre Code Banquaire <span class="requis">*</span> </label>
                <input type="number" id="codeBanquaire" name="codeBanquaire"  size="20" maxlength="10" />
                 <span class="erreur">${form.erreurs['art_frais']}</span>
                <br />

           <c:out value="${requestScope.erreurCb }"></c:out>                
           <c:out value="${ erreurCb }"></c:out>
           
 			<label for="chiffreSecret"> Les pictogrammes <span class="requis">*</span> </label>
                <input type="number" id="chiffreSecret" name="chiffreSecret" size="10" maxlength="3" />
                 <span class="erreur">${form.erreurs['art_prix']}</span>
                <br />


	       <c:out value="${requestScope.erreurPicto}"></c:out>                
           <c:out value="${ erreurPicto }"></c:out>
                 
                
                <input type="submit" value="Déposer"  name="acheterArticleInput" id="acheterArticleInput" /> 
                
                <br>
                
                    <button type="button" value="Déposer"  name="acheterArticleButton">  
                  </button> 
               
                  
                <!--   <button type="button" value="Déposer"  name="acheterArticleButton">  
                  </button> -->
                 <p class="${empty form.erreurs ? 'succes' : 'erreur'}">${form.resultat}</p>
					
	</fieldset>
		</form>	
	
		<br/>
	
	
	
	
		
		<h4> Mes Articles en Vente </h4>
	
		<table border="1">
			
			<thead> <th> Titre </th><th> Description <th> Theme </th></th><th> Description </th> <th> Contenu </th> <th> Frais </th>   <th> Prix </th> <th> Acheter </th> </thead>
			  <c:forEach var="article" items="${modelContenu.articles}"> 
				<tr>
				
				<td><c:out value="${ article.id}"/></td>
				
					<td><c:out value="${ article.titre}"/></td>
					
						<td><c:out value="${ article.theme}"/></td>
					<td><c:out value="${article.description}"/></td>
					
					<td><c:out value="${article.contenu}"/></td>
					
					<td><c:out value="${article.frais}"/></td>
					
					<td><c:out value="${article.prix}"/></td>
					
					
					<td> <button type="submit" name="acheter"> Acheter un article </button></td>
				</tr> 
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
	
		
		
		</div>
	</body>
			<%@ include file="/WEB-INF/footerPhantom.jsp" %>
	
</html>



