<%--
  Created by IntelliJ IDEA.
  User: meded
  Date: 23/04/2024
  Time: 13:37
  To change this template use File | Settings | File Templates.
--%>
<%@ include file="/WEB-INF/jsp/header.jsp"%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>

<!DOCTYPE html>
<html lang="fr">
<head>
    <meta charset="UTF-8">
    <title>DOODLE 2.0</title>
    <style>
        body {
            font-family: Arial, sans-serif;
            background-color: #f4f4f4;
            margin: 0;
            padding: 0;
        }
        header {
            background-color: #007BFF;
            color: white;
            padding: 10px 20px;
            text-align: center;
        }
        .container {
            width: 80%;
            margin: 20px auto;
            padding: 20px;
            background-color: white;
            box-shadow: 0 0 10px 0 rgba(0,0,0,0.1);
        }
        footer {
            background-color: #333;
            color: white;
            text-align: center;
            padding: 10px 20px;
            position: fixed;
            bottom: 0;
            width: 100%;
        }
        input, button {
            padding: 10px;
            margin-top: 10px;
        }
    </style>
</head>
<body>
<header>
    <h1>Bienvenue sur DOODLE 2.0</h1>
</header>

<div class="container">
    <h2>Créer un nouveau sondage</h2>
    <form action="/newPoll" method="post">
        <button type="submit">Créer Sondage</button>
    </form>

</div>

<footer>
    <p>Mon Sondage © 2024</p>
</footer>
</body>
</html>

