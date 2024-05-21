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
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/style.css" />
</head>
<body>

    <div class="container">
        <h2 style="text-align:center">${poll.title}</h2>
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
                            <span class="label">Créé par :</span> <span class="value">${poll.creator.email}  </span>
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
                        <div class="detail">
                            <span class="label">Nombre de participants  :</span> <span class="value">${poll.participants.size()}</span>
                        </div>
                    </div>
                    <div class="slots">
                        <h3>Créneaux</h3>
                        <c:forEach var="slot" items="${poll.slots}">
                            <div class="slot">
                                <span class="date">${slot.start.dayOfMonth}/${slot.start.month}/${slot.start.year}  </span>
                                <span class="hour">${slot.start.hour}H${slot.start.minute} - ${slot.end.hour}H${slot.end.minute}</span>

                            </div>
                        </c:forEach>
                    </div>

                    <div class="participants">
                        <h3>Participants</h3>
                        <c:forEach var="participant" items="${poll.participants}">
                            <div class="participant">
                                <span class="email">${participant.email}</span>

                            </div>
                        </c:forEach>

                   <!--- Participate to the poll <a href="/meeting/participate/${poll.id}/vote">Participate</a>--->
                        <label>Lien à envoyer aux participants:</label>
                        <div style="display: flex; align-items: center;">
                            <input type="text" id="participationLink" value="http://localhost:8081/meeting/participate/${poll.id}/vote" readonly style="flex: 1; margin-right: 10px; height: 40px; box-sizing: border-box;" />
                            <button class="copy-button" onclick="copyToClipboard()" style="height: 40px; display: flex; align-items: center; justify-content: center;">Copier</button>
                        </div>
                        <div id="copyNotification" class="notification">Lien copié dans le presse-papier</div>


                    </div>
            </div>



    </div>

    <footer>
        <p>Mon Sondage © 2024</p>
    </footer>

        <script>
            function copyToClipboard() {
                var copyText = document.getElementById("participationLink");
                copyText.select();
                copyText.setSelectionRange(0, 99999); /* For mobile devices */
                document.execCommand("copy");

                var notification = document.getElementById("copyNotification");
                notification.style.display = "block";
                setTimeout(function() {
                    notification.style.display = "none";
                }, 2000);
            }
        </script>
</body>
</html>
