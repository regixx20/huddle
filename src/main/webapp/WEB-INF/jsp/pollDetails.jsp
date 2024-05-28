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
    <title>MeetEasy</title>
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/style.css" />
    <style>
        body {
            font-family: Arial, sans-serif;
            background-color: #f4f4f4;
            margin: 0;
            padding: 0;
        }
        .containerr {
            display: grid;
            grid-template-columns: repeat(auto-fill, minmax(200px, 1fr));
            gap: 20px;
            width: 80%;
            margin: 20px auto;
            overflow-y: auto;
            max-height: 600px;
            background-color: white;
            box-shadow: 0 0 10px rgba(0,0,0,0.1);
            padding: 20px;
        }
        .slot-entry {
            border: 1px solid #ccc;
            padding: 10px;
            background-color: #f9f9f9;
            box-shadow: 0 2px 4px rgba(0,0,0,0.1);
        }
        .slot-entry:hover {
            background-color: #f0f0f0; /* Changement de couleur au survol */
        }
        .slot-date, .time-duration {
            display: flex;
            justify-content: space-between;
            align-items: center;
            margin-bottom: 5px;
        }
        .time {
            font-weight: bold;
        }

        .day {
            font-size: 0.9em;
            color: #666;
        }
        .date {
            color: #333;
        }
        .slot-entry.selected {
            border: 2px solid #2fff00; /* Bordure bleue pour l'élément sélectionné */
            background-color: #f0f8ff; /* Légère couleur de fond pour l'élément sélectionné */
        }
    </style>
    <script src='https://cdnjs.cloudflare.com/ajax/libs/fullcalendar/4.2.0/core/locales/fr.js'></script>


    <script>
        function selectSlot(slotId) {
            // Désactiver la classe active pour tous les slots
            document.querySelectorAll('.slot-entry').forEach(slot => {
                slot.classList.remove('active');
            });

            // Activer la classe active pour le slot sélectionné
            const selectedSlot = document.getElementById('slot_' + slotId);
            selectedSlot.classList.add('active');

            // Mettre à jour la valeur de l'input caché
            document.getElementById('selectedSlotId').value = slotId;
        }
    </script>
</head>
<body>

<div class="container">
    <h2 style="text-align:center">${poll.title}</h2>
    <%-- Ajout d'une boucle pour simuler l'affichage des sondages --%>
    <c:url value="/polls/details" var="pollDetailsUrl">
        <c:param name="id" value="${poll.id}" />
    </c:url>
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
                    <form id="emailForm" action="${pageContext.request.contextPath}/send-mail" method="get">
                        <input type="hidden" name="senderEmail" value="${poll.creator.email}" />
                        <c:forEach var="email" items="${poll.paticipantMail()}">
                            <input type="hidden" name="recipientEmail" value="${email}" />
                        </c:forEach>
                        <input type="hidden" name="text" value="Le crénaux  du ${slot.start.dayOfMonth}/${slot.start.month}/${slot.start.year} à ${slot.start.hour}H${slot.start.minute} - ${slot.end.hour}H${slot.end.minute} a été choisis pour le songade ${poll.title}" />
                        <button type="submit">Sélectionner</button>
                    </form>

                </div>
            </c:forEach>
        </div>

        <div class="participants">
            <h3>Participants</h3>
            <c:forEach var="p" items="${poll.participants}">
                <div class="participant">
                    <span class="email">${p.fullName}</span><br>

                </div>
            </c:forEach><br><br>

            <!--- Participate to the poll <a href="/meeting/participate/${poll.id}/vote">Participate</a>--->
            <label>Lien à envoyer aux participants:</label>
            <div style="display: flex; align-items: center;">
                <input type="text" id="participationLink" value="http://localhost:8081/meeting/participate/${poll.id}/vote" readonly style="flex: 1; margin-right: 10px; height: 40px; box-sizing: border-box;" />
                <button class="copy-button" onclick="copyToClipboard()" style="height: 40px; display: flex; align-items: center; justify-content: center;">Copier</button>
            </div>
            <div id="copyNotification" class="notification">Lien copié dans le presse-papier</div>


        </div>

        <div class="containerr">
            <table class="table table-bordered">
                <thead>
                <tr>
                    <th>Participants</th>
                    <c:forEach var="slot" items="${poll.slots}">
                        <th>
                            <div class="slot-header">
                                <div class="date">${slot.start.dayOfMonth}/${slot.start.month}/${slot.start.year}</div>
                                <div class="time">${slot.start.hour}:${slot.start.minute < 10 ? '0' : ''}${slot.start.minute} - ${slot.end.hour}:${slot.end.minute < 10 ? '0' : ''}${slot.end.minute}</div>
                            </div>
                        </th>
                    </c:forEach>
                </tr>
                </thead>
                <tbody>
                <c:forEach var="participant" items="${poll.participants}">
                    <tr>
                        <td>${participant.fullName}</td>
                        <c:forEach var="slot" items="${poll.slots}">
                            <td class="slot-cell" onclick="selectSlot('${slot.id}', '${participant.id}')">
                                <c:choose>
                                    <c:when test="${slot.getVote(participant) == 'yes'}">
                                        <span>Oui</span>
                                    </c:when>
                                    <c:when test="${slot.getVote(participant) == 'no'}">
                                        <span>Non</span>
                                    </c:when>
                                    <c:when test="${slot.getVote(participant) == 'maybe'}">
                                        <span>Peut-être</span>
                                    </c:when>
                                    <c:otherwise>
                                        <span>Not Voted</span>
                                    </c:otherwise>
                                </c:choose>
                            </td>
                        </c:forEach>
                    </tr>
                </c:forEach>
                </tbody>
            </table>
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
