
            function addExemple() {
                let ajout = document.getElementById("skills");
                ajout.insertAdjacentHTML ('afterend', 
                    `<div class="form-row  justify-content-between">
                        <div class="form-group col-md-4">
                            <input class="form-control" type="text" name="ing_nom" placeholder="ingredient">
                        </div>
                        <div class="form-group col-md-3">
                            <input class="form-control" type="number" name="quantite-ingredient" placeholder="quantite de l'ingrédient">
                        </div>
                        <div class="form-group col-md-3">
                            <input class="form-control" type="text" name="qua_mesure" placeholder="mesure de l'ingrédient">
                        </div>                    
                        <span class="col-md-1 align-self-center" > <span class="text-danger">
                    </div>`);
            }
            
            
            function addSkills() {
                let ajout = document.getElementById("skills_intitule");
                ajout.insertAdjacentHTML ('afterend', 
                    `<div class="form-row  justify-content-between">
                   
                  <label for="skills_intitule"> Skills  <span class="requis">* </span> </label>
                <input type="text" id="skills_intitule" name=skills_intitule value="<c:out value="${skills.skills_intitule}"/>" size="20" maxlength="60" />
                <span class="erreur">${form.erreurs['skills_intitule']}</span>
                
                
                </div>	   
                       `);
            }
            
            
            function addTheme() {
                let ajout = document.getElementById("theme_intitule");
                ajout.insertAdjacentHTML ('afterend', 
                 `   
                  
                <div class= "form-group col-md-3">
                      		
	                <label for="theme_intitule"> Créer un Hashtag  <span class="requis">* </span> </label>
	                <input type="text" id="theme_intitule" name=theme_intitule value="<c:out value="${article.theme_id}"/>" size="20" maxlength="60" />
	                <span class="erreur">${form.erreurs['theme']}</span>
	             
                
                </div>                   `	
                		       		
                		);

            }