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
    <title>MeatEasy</title>
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
            width: 70%; /* Ajustez la largeur de la container en pourcentage si nécessaire */
            margin: 0 auto; /* Centrer la container horizontalement */
        }

        .poll-box {
            width: 70vw;
            height: 70vw;
            max-width: 500px;
            max-height: 500px;
            margin: 20px auto;
            padding: 20px;
            border: 1px solid #ccc;
            box-sizing: border-box;
            background-color: white;
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
    <h1>MeatEasy</h1>
    <h2>${poll.title}</h2> Participate to the poll <a href="/meeting/participate/${poll.id}/vote">Participate</a>

</header>

<div class="container">

    <%-- Ajout d'une boucle pour simuler l'affichage des sondages --%>
    <c:url value="/polls/details" var="pollDetailsUrl">
        <c:param name="id" value="${poll.id}" />
    </c:url>
        <div class="container">
            <div class="poll-box" id="crenaux">
                <div class="header">
                    <h2>Détails du sondage</h2>
                </div>
                <div class="details">
                    <div class="detail">
                        <span class="label">Créé par :</span> <span class="value">${poll.creator} </span>
                    </div>
                    <div class="detail">
                        <span class="label">Description :</span> <span class="value">${poll.description}</span>
                    </div>
                    <div class="detail">
                        <span class="label">Date limite :</span> <span class="value">${poll.limitDate}</span>
                    </div>
                    <div class="detail">
                        <span class="label">Emplacement :</span> <span class="value">${poll.location}</span>
                    </div>
                </div>
                <div class="slots">
                    <h3>Créneaux</h3>
                    <c:forEach var="slot" items="${poll.slots}">
                        <div class="slot">
                            <span class="date">${slot.start.dayOfMonth}/${slot.start.month}/${slot.start.year}</span>
                            <span class="hour">${slot.start.hour}H${slot.start.minute} - ${slot.end.hour}H${slot.end.minute}</span>
                        </div>
                    </c:forEach>
                </div>

            </div>
        </div>



</div>

<footer>
    <p>Mon Sondage © 2024</p>
</footer>
</body>
</html>
