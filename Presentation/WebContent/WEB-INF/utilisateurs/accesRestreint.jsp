<%@ page pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
    <head>
        <meta charset="utf-8" />
        <title>Accès restreint</title>
    </head>
    <body>
        <p>Vous êtes connecté(e) avec le login ${sessionScope.utilisateur.login} et le mot de passe ${sessionScope.utilisateur.motDePasse}, vous avez bien accès à l'espace restreint.</p>
    </body>
</html>