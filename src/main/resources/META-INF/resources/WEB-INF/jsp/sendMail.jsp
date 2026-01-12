<%--
  Created by IntelliJ IDEA.
  User: meded
  Date: 03/06/2024
  Time: 00:38
  To change this template use File | Settings | File Templates.
--%>
<%@ include file="/WEB-INF/jsp/header.jsp"%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="fr">
<head>
    <meta charset="UTF-8">
    <title>Confirmation de Réservation</title>
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/style.css" />
    <style>

        .confirmation-container .btn-home {
            display: inline-block;
            padding: 10px 20px;
            color: white;
            background-color: #007bff;
            border: none;
            border-radius: 4px;
            text-decoration: none;
            font-size: 16px;
        }
        .confirmation-container .btn-home:hover {
            background-color: #0056b3;
        }
    </style>
</head>
<body>
    <div class="confirmation-container">
        <h2>Réservation Confirmée</h2>
        <c:forEach var="slot" items="${poll.slots}">
           <c:choose>
               <c:when test="${slot.chosen}">
               <p>Vous avez réservé le créneau du sondage intitulé ${poll.title} de ${slot.start} à ${slot.end}.</p>
                  </c:when>
            </c:choose>
        </c:forEach>

        <a href="${pageContext.request.contextPath}/" class="btn-home">Retour à l'accueil</a>
    </div>
</body>
</html>
