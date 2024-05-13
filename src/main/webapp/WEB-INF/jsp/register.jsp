<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<!DOCTYPE html>
<html lang="fr">
<head>
    <meta charset="UTF-8">
    <title>Inscription</title>
</head>
<body>
<div class="container">
    <h2>Inscription</h2>
    <form:form modelAttribute="user" action="${pageContext.request.contextPath}/register" method="post">
        <div class="form-group">
            <label for="email">Email:</label>
            <form:input path="email" type="email" id="email" class="form-control" required="true"/>
        </div>
        <div class="form-group">
            <label for="firstName">Prénom:</label>
            <form:input path="firstName" type="text" id="firstName" class="form-control" required="true"/>
        </div>
        <div class="form-group">
            <label for="lastName">Nom:</label>
            <form:input path="lastName" type="text" id="lastName" class="form-control" required="true"/>
        </div>
        <div class="form-group">
            <label for="password">Mot de passe:</label>
            <form:password path="password" id="password" class="form-control" required="true"/>
        </div>
        <div class="form-group">
            <button type="submit" class="btn btn-primary">S'inscrire</button>
        </div>
    </form:form>
    <p>Déjà inscrit? <a href="login">Connectez-vous ici</a></p>
</div>
</body>
</html>
