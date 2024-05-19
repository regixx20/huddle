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
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>

<c:set var="pageTitle" value="Mon espace" />

<!DOCTYPE html>
<html lang="fr">
<head>
    <meta charset="UTF-8">
    <title>Dashboard</title>
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/style.css" />
</head>
<body>
    <div class="container">
        <h2>Créer un nouveau sondage</h2>
        <form action="/meeting/edit" method="get">
            <button type="submit">Créer Sondage</button>
        </form>
    </div>
    <div  class="container">
        <h2>Mes sondages</h2>
        <%-- Ajout d'une boucle pour simuler l'affichage des sondages --%>
        <c:forEach var="poll" items="${polls}">
            <div class="poll-box">

                <button type="submit" class="btn btn-primary" onclick="window.location.href='/meeting/organize/${poll.id}'">${poll.title}</button>
                <br>
                <br>
                <a href="/meeting/edit?id=${poll.id}" style="text-decoration: none;">Edit</a>

            </div>
        </c:forEach>
    </div>
    <footer>
        <p>© 2024 MeetEasy - Tous droits réservés.</p>
    </footer>
</body>
</html>
