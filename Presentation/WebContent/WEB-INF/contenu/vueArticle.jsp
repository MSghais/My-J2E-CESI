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
	
		<h4>Ajouter une promotion</h4>
		<form action="createArticle" method="post" name=formInscription>
			<table>
				<tr>
					<td> Nom </td>
					<td><input type="text" name="username"></td>
				</tr>
				<tr>
					<td> Theme </td>
					<td><input type="text" name="login"></td>
				</tr>
				
				<tr>
					<td> Email :</td>
					<td><input type="text" name="email"></td>
				</tr>
				
				<tr>
					<td>  Mot de Passe:</td>
					<td><input type="text" name="password1"></td>
				</tr>
				
				<tr>
					<td>  Same MDP:</td>
					<td><input type="text" name="password2" value="Retapez votre mot de passe"></td>
				</tr>
				
				
				<tr>
					<td></td>
					<td><input type="submit" value="S'inscrire" name="creationUser"></td>
				</tr>
				
				
				
			</table>
		</form>	
		<br/>
		
		
		<div> 
		<section>   
		
		 <h4> Article </h4>
		<form action="creationArticle" method="post" >
		<fieldset>

				
                <label for="username">Nom d'utilisateur <span class="requis">* </span> </label>
                <input type="text" id="username" name="username" value="<c:out value="${utilisateur.username}"/>" size="20" maxlength="60" />
                <span class="erreur">${form.erreurs['username']}</span>
                <br />
                
                 <label for="login"> Login de connexion <span class="requis">*</span> </label>
                <input type="text" id="login" name="login" value="<c:out value="${utilisateur.login}"/>" size="20" maxlength="20" />
                 <span class="erreur">${form.erreurs['login']}</span>
                <br />
                
                
				       <label for="email">Adresse email <span class="requis">*</span></label>
                <input type="email" id="email" name="email" value="<c:out value="${utilisateur.email}"/>" size="20" maxlength="60" />
                <span class="erreur">${form.erreurs['email']}</span>
                
                
                
                <label for="motdepasse">Mot de passe <span class="requis">*</span></label>
                <input type="password" id="password" name="password" value="" size="20" maxlength="20" />
                 <span class="erreur">${form.erreurs['password']}</span>
                <br />

                <label for="confirmation">Confirmation du mot de passe <span class="requis">*</span></label>
                <input type="password" id="confirmation" name="confirmation" value="" size="20" maxlength="20" />
                <span class="erreur">${form.erreurs['confirmation']}</span>
                <br />


                <input type="submit" value="S'incrire"  name="inscriptionUserMVC"  />
                <br />
                
                 <p class="${empty form.erreurs ? 'succes' : 'erreur'}">${form.resultat}</p>
					
			</fieldset>
		</form>	
		<br/>
		
		</section>
		
		</div>
		<a href="/WEB-INF/JSP/vueConnection.jsp"> Se connecter </a>
		
		

				<tr>
					<td>Choisir la formation : </td>
					<td><select name="promotion">
		    			<c:forEach var="promotion" items="${model.promotions}"> 
				    		<option value="${promotion.acronyme}"><c:out value="${promotion.acronyme}"/></option>
					    </c:forEach>
				    </select></td>
				<tr>
					<td>Nom : </td>
					<td><input type="text" name="nom"></td>
				</tr>
				<tr>
					<td>Prénom : </td>
					<td><input type="text" name="prenom"></td>
				</tr>
				<tr>
					<td></td>
					<td><input type="submit" value="Ajouter" name="creerEtudiant"></td>
				</tr>
			</table>
		</form>	
		<br/>
		<h4>Tableau des Etudiants</h4>
		<table border="1">
			<th> ID </th>  <th>NOM </th> <th>PRENOM</th> <th> Retard </th> <th>Absence </th>
			  <c:forEach var="etudiant" items="${model.etudiants}"> 
				<tr>
					<td><c:out value="${etudiant.id}"/></td>   
					<td><c:out value="${etudiant.nom}"/></td>
					<td><c:out value="${etudiant.prenom}"/></td>
					<td><c:out value="${etudiant.retard}"/></td>
					<td><c:out value="${etudiant.absence}"/></td> 
					
					<td> <a href="etudiants?retard=${etudiant.id}" > Retard </a> </td> 
					<td> <a href="etudiants?absent=${etudiant.id}" > Absent </a> </td> 
					
				</tr> 
		      </c:forEach>
		   
		</table>

		
		</div>
	</body>
			<%@ include file="/WEB-INF/footerPhantom.jsp" %>
	
</html>