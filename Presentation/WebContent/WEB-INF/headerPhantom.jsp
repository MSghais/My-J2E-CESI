<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<div id="wrapper">
	<title> Good POEI ! </title>
		<meta charset="utf-8" />
		<meta name="viewport" content="width=device-width, initial-scale=1, user-scalable=no" />
		<link rel="stylesheet" href="assetsPhantom/css/main.css" />

		
		<body>
				<!-- Header -->
				
						
						
						
					<header id="header">
						<div class="inner">

							<!-- Logo -->
								<a href="index.html" class="logo">
									<span class="symbol"><img src="images/logo.svg" alt="" /></span><span class="title"> GoodCESI</span>
								</a>

							<!-- Nav -->
								<nav>
									<ul>
										<li><a href="#menu">Menu</a></li>
									</ul>
								</nav>

						</div>
					</header>



<!--  Menu BAR UP  -->
	
                <nav >
                    <ul>
                   	 <li><a href="Accueil"> Accueil</a></li>
                      	

				 			<li><a href="Shopping"> Shopping </a></li>
							 			
							 			
							<li><a href="vueArticle.jsp"> Categories</a></li>
						
									
													<li> 
													<a href="vueArticle.jsp"> Explorer </a>
													<select name="acronymeThemeHeader" value="exploration" > 
														
														<c:forEach var="theme" items="${modelTheme.themes }">
														<option> <c:out value="${theme.theme_intitule}"/> </option>
														
														</c:forEach>
					
														</select>
														</li>
					
				
						<c:if test="${!empty sessionScope.userId}">
					
					
							  
							  
									<ul class="sous">
									
										<li> <a class="dropdown-item bg-dark  text-light" href="addArticleMVC"> Vendre un Article</a> </li> 
											
											
											
										<li> <a class="dropdown-item bg-dark  text-light" href="mesCommandes"> Commandes </a> </li> 
											
										<li> <a class="dropdown-item bg-dark  text-light" href="mesVentes"> Ventes en cours </a> </li> 
												
												
												
										<li> <a class="dropdown-item bg-dark  text-light" href="mesAchats"> Mes Achats</a> </li> 
																						
										<li> ${sessionScope.utilisateur.login} : <a href="deconnexion">Déconnexion</a> </li> 	
												
										<li> <a class="dropdown-item bg-dark  text-light" href="webSocket.jsp">Tchat Cesi</a> </li>
									
									
									
									</ul>
							
							</c:if>	
				
				
				<c:if test="${!empty sessionScope.userId}">
					
					
							  
							  
									<ul >
									
										<li> <a class="dropdown-item bg-dark  text-light" href="addArticleMVC"> Vendre un Article</a> </li> 
											
											
											
										<li> <a class="dropdown-item bg-dark  text-light" href="mesCommandes"> Commandes </a> </li> 
											
										<li> <a class="dropdown-item bg-dark  text-light" href="mesVentes"> Ventes en cours </a> </li> 
												
												
												
										<li> <a class="dropdown-item bg-dark  text-light" href="mesAchats"> Mes Achats</a> </li> 
																						
										<li> ${sessionScope.utilisateur.login} : <a href="deconnexion">Déconnexion</a> </li> 	
												
										<li> <a class="dropdown-item bg-dark  text-light" href="webSocket.jsp">Tchat Cesi</a> </li>
									
									
									
									</ul>
							
							</c:if>	
	 			
							 
							 		
							<c:if test="${empty sessionScope.userId}">
							   
							<ul>										
												 
							 <li > 
							 
							 <a class="dropdown-item bg-dark  text-light"href="Connexion">Connexion</a> </li>
							
								 
							 <li> <a class="dropdown-item bg-dark  text-light" href="Inscription">Incription MVC</a></li>
								
							</ul>
							</c:if>
							
						
				
							
							
								
														
                   	
                   	
                   	
                   	 </ul>
                    
                    
                    
                </nav>
				<!-- Menu -->
					<nav id="menu">
						<h2>Menu</h2>
						
					
						
						 <p> <li><a href="Accueil"> Accueil</a></li>    </p>
						 
						
							
							 <p> <li><a href="poserArticle"> Déposer Article</a></li>   </p>
							
							 <p> <li><a href="vueArticle.jsp"> Vue articlem</a></li>  </p><br>
							
							 <p>  <br><li><a href="vueArticle.jsp"> Vue articlem</a></li>   </p>
							 <p> <li><a href="vueConnection.jsp">Connexion</a></li>   </p><br> 
							 <p>   <li> <a href="signUser">Incription</a>   </li>  </p>   <br>
							 
							 <p>   <li> <a href="MVCInscription">Incription MVC</a>   </li>  </p>   <br>
							  <p>  <li><a href="signTeacher"> Devenir Teacher ? </a></li>   </p> <br>
							  
							 <p>  <li><a href="accueil.jsp"> A propos</a></li>   </p> <br>
					
						
					
										<c:if test="${!empty sessionScope.utilisateur}">
							   
							<ul>										
																		
																		<li> <a href="webSocket.jsp">Tchat Cesi</a> </li>
																			
												<li> ${sessionScope.utilisateur.login} : <a href="deconnexion">Déconnexion</a> </li> 
												
												<li><a href="addArticleMVC"> Déposer un Article</a></li>
												
											 
							 
								 
								<li>  ${sessionScope.utilisateur.login} : <a href="deconnexion">déconnexion</a> </li>
					
							</ul>
							</c:if>
							
							
							
					
									<c:if test="${empty sessionScope.utilisateur}">
								<li> <a href="Connexion">Connexion</a> </li>
							
								 <li> <a href="signUser">Incription</a></li> </li>
								 
								</c:if>
								
							
										
	
							
							</ul>											
							
				
							</nav>
					
			
					
					<!--   <span class="image main"><img src="images/commerce.png" alt="" /></span>
					
					-->
					<div>
					
					
				
                
                </div>
                
                
					
					
					</body>
					</html>
					<!--  	
							
						
								
	
							
							<c:if test="${!empty sessionScope.utilisateur}">
															<li class="nav-item dropdown">   <a href="DashBoard"> DashBoard </a>
							
								 <li class="nav-item dropdown" >
								
									<ul class="sous">
			
										<li ><a href="addArticleMVC"> Article</a>
													
											<li> ${sessionScope.utilisateur.login} : <a href="deconnexion">Déconnexion</a> </li> 	
													
											<li> <a href="webSocket.jsp">Tchat Cesi</a> 
											
																				  
											<li><a href="accueil.jsp"> A propos</a>
				
								
									</ul>											
				

							</c:if>	
						
							
							
							<c:if test="${!empty sessionScope.utilisateur}">
							
							
						
							 <nav id="dashboard" name="dashboard" >
												
							<ul>
							
								<li class="deroulant">   <a href="DashBoard"> DashBoard </a>
									
									<ul class="sous"> 
									
										<li><a href="addArticleMVC"> Article</a></li> 
																
										<li> ${sessionScope.utilisateur.login} : <a href="deconnexion">Déconnexion</a> </li> 	
												
										<li> <a href="webSocket.jsp">Tchat Cesi</a> </li>
										
																			  
										<li><a href="accueil.jsp"> A propos</a></li>
									
									
									</ul>
										
	
							
							</ul>											
							
				
							</nav>

							</c:if>
							
							
							
							 -->
							 
							 
							 
					<!-- 	<c:if test="${!empty sessionScope.utilisateur}">
							<li> <nav id="menu" name="test" >
							
							<select name="DashBorad" value="Dash" name="Board">
															<h2>TitleBOARD</h2>
															
										<li><a href="addArticleMVC"> Article</a></li> 
															
										<li> ${sessionScope.utilisateur.login} : <a href="deconnexion">Déconnexion</a> </li> 
															
											<li><a href="login">Connexion</a></li>
										<li><a href="signUser">Incription</a></li>
										 <li><a href="signTeacher"> Devenir Teacher ? </a></li> 
										 		
																		<li> <a href="webSocket.jsp">Tchat Cesi</a> </li>
													  
														</select>
														
														
										</nav>
										
						</c:if> -->
						
						
						<!--  
													<li> 
													<a href="vueArticle.jsp"> Explorer </a>
													<select name="acronymeTheme" value="exploration" name="truc de ouf"> 
														
														<c:forEach var="theme" items="${model.themes }">
														<option> <c:out value="${theme.nameRubrique}"/> </option>
														
														<select name="sousRubrique">
														
																		<c:forEach var="themeSub" items="${model.themes}">
																		
																		<option> <c:out value="${themeSub.sousRubriques}"/> </option>
																																				
																		</c:forEach>																													
														
														</select><option> <c:out value="${theme.description}"/> </option>
																													
																
														</c:forEach>
					
														</select></li>
														
														 -->
		