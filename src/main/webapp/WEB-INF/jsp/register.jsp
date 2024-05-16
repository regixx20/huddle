<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<!DOCTYPE html>
<html lang="fr">
<head>
    <meta charset="UTF-8">
    <title>Inscription</title>
    <style>
        .container {
            width: 400px;
            margin: 0 auto;
            padding: 20px;
            border: 1px solid #ccc;
            border-radius: 5px;
            background-color: #fff;
            box-shadow: 0 2px 4px rgba(0, 0, 0, 0.1);
        }

        h2 {
            text-align: center;
            margin-bottom: 20px;
        }

        .form-group {
            margin-bottom: 20px;
        }

        label {
            font-weight: bold;
        }

        .form-control {
            width: 100%;
            padding: 8px;
            border: 1px solid #ccc;
            border-radius: 3px;
            box-sizing: border-box;
        }

        .btn-primary {
            display: block;
            width: 100%;
            padding: 10px;
            background-color: #007bff;
            color: #fff;
            border: none;
            border-radius: 3px;
            cursor: pointer;
            transition: background-color 0.3s ease;
        }

        .btn-primary:hover {
            background-color: #0056b3;
        }

        p {
            text-align: center;
            margin-top: 20px;
        }

        p a {
            color: #007bff;
            text-decoration: none;
        }

        p a:hover {
            text-decoration: underline;
        }

    </style>
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
