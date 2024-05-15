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
        .poll-box {
            padding: 10px;
            background-color: #e0e0e0;
            margin-bottom: 10px;
            border-radius: 5px;
            box-shadow: 0 2px 5px rgba(0,0,0,0.2);
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
        input{
            padding: 10px;
            margin-top: 10px;
        }

        .btn-pill {
            border-radius: 20px;
            margin-bottom: 10px;
            margin-top: 10px;
            padding: 10px 20px;
            font-size: 16px;
        }

        .btn-pill-primary {
            background-color: #007BFF;
            color: #fff;
            border: none;
        }

        .btn-pill-secondary {
            background-color: #6c757d;
            color: #fff;
            border: none;
        }

        .btn-pill-danger {
            background-color: #dc3545;
            color: #fff;
            border: none;
        }


    </style>
</head>
<body>
<header>
    <h1>MeatEasy</h1>
    <div >
        <c:if test="${empty sessionScope.loggedInUser}">
        <!-- Afficher ces boutons si l'utilisateur n'est pas connecté -->
        <button type="button" class="btn btn-primary btn-pill btn-pill-primary" onclick="window.location.href='/login'">Login</button>
        <button type="button" class="btn btn-secondary btn-pill btn-pill-secondary" onclick="window.location.href='/register'">Register</button>
        </c:if>
        <c:if test="${not empty sessionScope.loggedInUser}">
        <!-- Afficher ce bouton si l'utilisateur est connecté -->
        <form action="/logout" method="post">
            <button type="submit" class="btn btn-danger btn-pill btn-pill-danger">Logout</button>
        </form>
        </c:if>

</header>


<div class="container">
    <a href="/homePage">Page d'accueil</a>
    <h2>Créer un nouveau sondage</h2>
    <form action="/meeting/edit" method="get">
        <button type="submit">Créer Sondage</button>
    </form>
</div>

<div class="container">
    <h2>Mes sondages</h2>
    <%-- Ajout d'une boucle pour simuler l'affichage des sondages --%>
    <c:forEach var="poll" items="${polls}">
        <div class="poll-box">

            <a href="/meeting/organize/${poll.id}" style="text-decoration: none;" >
                    ${poll.title}
            </a>
            <a href="/meeting/edit?id=${poll.id}" style="text-decoration: none;">Edit</a>


        </div>


    </c:forEach>
    <form action="/polls" method="get">
        <button type="submit">Voir mes sondages</button>
    </form>
</div>



</body>
</html>
