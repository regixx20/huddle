<%-- Created by IntelliJ IDEA. User: meded Date: 23/04/2024 Time: 13:37 To change this template use File | Settings | File Templates. --%>
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

        .highlighted-slot {
            background-color: #ff0000;  // Rouge pour la visibilité
        transform: translateY(-10px);  // Décalage pour l'effet visuel
        box-shadow: 0 4px 8px rgba(0, 0, 0, 0.1);  // Ombre pour le relief
        transition: all 0.3s;  // Transition douce
        color: #ffffff;  // Texte en blanc pour le contraste
        }


        .poll-box {
            background-color: #fff;
            padding: 20px;
            box-shadow: 0 2px 10px rgba(0,0,0,0.1);
        }




        .slot-header {
            background-color: #f9f9f9;
            padding: 10px;
            border-radius: 8px;
            box-shadow: 0 2px 5px rgba(0,0,0,0.1);
            text-align: center;
            transition: transform 0.3s, box-shadow 0.3s;
        }

        .slot-header.active {
            background-color: #fffacd; /* light yellow background for active header */
            box-shadow: 0 4px 20px rgba(0,0,0,0.2);
        }
        .slot-cell {
            text-align: center;
            cursor: pointer;
        }

        th {
            padding: 8px 12px;
            background-color: #f8f9fa; /* Couleur de fond pour les entêtes */
            border-bottom: 2px solid #dee2e6; /* Bordure sous les entêtes pour une séparation claire */
            font-weight: bold; /* Texte en gras pour les entêtes */
            text-align: center; /* Centrer le texte */
        }

        .date-header {
            display: block; /* Afficher la date sur sa propre ligne */
            margin-bottom: 5px; /* Espace entre la date et l'heure */
            font-size: 0.9em; /* Taille de la police réduite pour la date */
            color: #495057; /* Couleur du texte pour la date */
        }

        .time-header {
            font-size: 1.1em; /* Taille de la police pour l'heure */
            color: #343a40; /* Couleur du texte pour l'heure */
        }
        .modal-content {
            background-color: #fefefe;
            margin: 15% auto; /* 15% du haut et centré horizontalement */
            padding: 20px;
            border: 1px solid #888;
            width: 80%; /* Vous pouvez ajuster la largeur à votre convenance */
        }

        /* Le bouton de fermeture */
        .close {
            color: #aaa;
            float: right;
            font-size: 28px;
            font-weight: bold;
        }

        .close:hover,
        .close:focus {
            color: black;
            text-decoration: none;
            cursor: pointer;
        }

        .disabled-overlay {
            position: relative;
            pointer-events: none;
        }

        .disabled-overlay::before {
            content: '';
            position: absolute;
            top: 0;
            left: 0;
            width: 100%;
            height: 100%;
            background: rgba(255, 255, 255, 0.7);
            z-index: 1;
            pointer-events: none;
        }

        .disabled-overlay .slot {
            opacity: 0.5;
            pointer-events: none;
        }
        .icon {
            margin-right: 5px;
            width: 20px;
            height: 20px;
        }



    </style>

    <script>
        var currentlySelected = null; // Garde une référence à l'index de la colonne actuellement sélectionnée


        function highlightColumn(columnIndex) {
            // Si une colonne est déjà sélectionnée et que c'est la même, désélectionner
            if (currentlySelected === columnIndex) {
                document.querySelectorAll('.column-' + currentlySelected).forEach(cell => {
                    cell.classList.remove('highlighted-slot');
                });
                currentlySelected = null;  // Réinitialiser la sélection actuelle
            } else {
                // Nettoyer les sélections précédentes
                if (currentlySelected !== null) {
                    document.querySelectorAll('.column-' + currentlySelected).forEach(cell => {
                        cell.classList.remove('highlighted-slot');
                    });
                }

                // Sélectionner la nouvelle colonne
                document.querySelectorAll('.column-' + columnIndex).forEach(cell => {
                    cell.classList.add('highlighted-slot');
                });
                currentlySelected = columnIndex;  // Mettre à jour l'index de la colonne actuellement sélectionnée
            }
        }





        // Cette fonction enlève le style de toutes les colonnes
        function clearAllHighlights() {
            document.querySelectorAll('.slot-header').forEach(header => {
                header.classList.remove('active', 'highlighted-slot');
            });
            document.querySelectorAll('.slot-cell').forEach(cell => {
                cell.classList.remove('highlighted-slot');
            });
        }



        function closeModal() {
            document.getElementById('myModal').style.display = 'none';
        }

        function submitDecideForm(slotId) {
            var hiddenInput = document.getElementById('decideSlotId');
            hiddenInput.value = slotId; // Mettre à jour la valeur du champ caché avec l'ID du créneau
            var form = document.getElementById('decideForm');
            form.submit(); // Soumettre le formulaire
        }

        function setReservationClicked() {
            document.getElementById('reservationClicked').value = "true";
            document.getElementById('confirmationContainer').style.display = 'block';
        }





    </script>
</head>
<body>

<div class="container">
    <h2 style="text-align:center">${poll.title}</h2>
    <c:url value="/polls/details" var="pollDetailsUrl">
        <c:param name="id" value="${poll.id}" />
    </c:url>

        <div class="header">
            <h2>Détails du sondage</h2>
        </div>
        <div class="details">
            <div class="detail">
                <span class="label">Créé par :</span> <span class="value">${poll.creator.fullName}</span>
            </div>
            <div class="detail">
                <span class="label"><img src="${pageContext.request.contextPath}/images/descriptionIcon.png" class="icon" alt="Description"> Description :</span> <span class="value">${poll.description}</span>
            </div>
            <div class="detail">
                <span class="label"><img src="${pageContext.request.contextPath}/images/calendarIcon.png" class="icon" alt="Date limite">Date limite :</span> <span class="value">${poll.formattedLimitDate} </span>
            </div>
            <div class="detail">
                <span class="label"><img src="${pageContext.request.contextPath}/images/localisationIcon.png" class="icon" alt="Emplacement">Emplacement :</span> <span class="value">${poll.location}</span>
            </div>
            <div class="detail">
                <span class="label">Nombre de participants :</span> <span class="value">${poll.participants.size()}</span>
            </div>
        </div>

        <div class="participants">
            <h3>Participants</h3>
            <c:forEach var="participant" items="${poll.participants}">
                <div class="participant">
                    <span class="email">${participant.fullName}</span>
                </div>
            </c:forEach>
            <label>Lien à envoyer aux participants:</label>
            <div style="display: flex; align-items: center;">
                <input type="text" id="participationLink" value="http://localhost:8081/meeting/participate/${poll.id}/vote" readonly style="flex: 1; margin-right: 10px; height: 40px; box-sizing: border-box;" />
                <button class="copy-button" onclick="copyToClipboard()" style="height: 40px; display: flex; align-items: center; justify-content:center;">Copier</button>
            </div>
            <div id="copyNotification" class="notification">Lien copié dans le presse-papier</div>
        </div>

        <div class="containerr">
            <table class="table table-bordered">
                <thead>
                <tr>
                    <th>Participants</th>
                    <c:forEach var="slot" items="${poll.slots}" varStatus="status">
                        <th onclick="highlightColumn(${status.index})" class="column-${status.index}slot-header">
                            <div class="slot-header">
                                <div class="date-header">${slot.dayOfWeek}  ${slot.start.dayOfMonth} ${slot.month} ${slot.start.year}</div>

                                <div class="time-header">${slot.start.hour}:${slot.start.minute < 10 ? '0' : ''}${slot.start.minute} - ${slot.end.hour}:${slot.end.minute < 10 ? '0' : ''}${slot.end.minute}</div>
                            </div>
                        </th>
                    </c:forEach>
                </tr>
                </thead>
                <tbody>
                <c:forEach var="participant" items="${poll.participants}">
                    <tr>
                        <td>${participant.fullName}</td>
                        <c:forEach var="slot" items="${poll.slots}" varStatus="status">
                            <td>
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
<c:if test="${!poll.decided && poll.creator.email == sessionScope.user.email}">
    <div class="slots">
        <h3>Créneaux</h3>
        <form:form id="decideForm" modelAttribute="poll"  method="POST">
                <input type="hidden" id="decideSlotId" name="isDecided" value="" />
        </form:form>
        <c:forEach var="slot" items="${poll.slots}">


            <div class="slot">
                <span class="date">${slot.start.dayOfMonth}/${slot.start.month}/${slot.start.year}</span>
                <span class="hour">${slot.start.hour}H${slot.start.minute} - ${slot.end.hour}H${slot.end.minute}</span>

                <button type="submit" onclick="submitDecideForm(${slot.id}); setReservationClicked();">Réserver </button>
            </div>

        </c:forEach>
    </div>
</c:if>

<c:if test="${poll.decided && poll.creator.email == sessionScope.user.email}">
<div id="displaySlotReserved"></div>
</c:if>
<c:if test="${poll.decided && poll.creator.email == sessionScope.user.email}">

    <c:forEach var="slot" items="${poll.slots}">
        <c:choose>
            <c:when test="${slot.chosen}">
            <p>Vous avez réservé le créneau du sondage intitulé ${poll.title} de ${slot.start.hour}:${slot.start.minute < 10 ? '0' : ''}${slot.start.minute} à ${slot.end.hour}:${slot.end.minute < 10 ? '0' : ''}${slot.end.minute} le
                    ${slot.dayOfWeek}  ${slot.start.dayOfMonth} ${slot.month} ${slot.start.year}
            </c:when>
        </c:choose>
    </c:forEach>
    <div class="slots disabled-overlay">
    <h3>Créneaux</h3>
    <c:forEach var="slot" items="${poll.slots}">
        <div class="slot">
            <span class="date">${slot.dayOfWeek}  ${slot.start.dayOfMonth} ${slot.month} ${slot.start.year}</span>
                <span class="hour">${slot.start.hour}:${slot.start.minute < 10 ? '0' : ''}${slot.start.minute} - ${slot.end.hour}:${slot.end.minute < 10 ? '0' : ''}${slot.end.minute}</span>

            <form id="emailForms" action="${pageContext.request.contextPath}/send-mail" method="get" onsubmit="return submitFormWithDelay(event);">
                <input type="hidden" name="senderEmail" value="${poll.creator.email}" />
                <c:forEach var="email" items="${poll.participantMail()}">
                    <input type="hidden" name="recipientEmail" value="${email}" />
                </c:forEach>
                <button type="submit" onclick=>Réserver</button>
            </form>
            <input type="hidden" id="reservationClicked" name="reservationClicked" value="false" />
        </div>
    </c:forEach>
    </div>


</c:if>
<c:forEach var="ptPoll" items="${user.participatedPolls}">
    <c:if test="${poll.id == ptPoll.id}">
    Vous avez déjà participé à ce sondage.
    </c:if>
</c:forEach>






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
