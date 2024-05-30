<%--
  Created by IntelliJ IDEA.
  User: meded
  Date: 23/04/2024
  Time: 19:36
  To change this template use File | Settings | File Templates.
--%>
<%@ include file="/WEB-INF/jsp/header.jsp"%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<!DOCTYPE html>
<html lang="fr">
<head>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/moment.js/2.24.0/moment.min.js"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/fullcalendar/3.10.2/fullcalendar.min.js"></script>
    <link href='https://cdnjs.cloudflare.com/ajax/libs/fullcalendar/4.2.0/core/main.min.css' rel='stylesheet' />
    <link href='https://cdnjs.cloudflare.com/ajax/libs/fullcalendar/4.2.0/daygrid/main.min.css' rel='stylesheet' />
    <link href='https://cdnjs.cloudflare.com/ajax/libs/fullcalendar/4.2.0/timegrid/main.min.css' rel='stylesheet' />


    <script src='https://cdnjs.cloudflare.com/ajax/libs/fullcalendar/4.2.0/core/main.min.js'></script>
    <script src='https://cdnjs.cloudflare.com/ajax/libs/fullcalendar/4.2.0/interaction/main.min.js'></script>
    <script src='https://cdnjs.cloudflare.com/ajax/libs/fullcalendar/4.2.0/daygrid/main.min.js'></script>
    <script src='https://cdnjs.cloudflare.com/ajax/libs/fullcalendar/4.2.0/timegrid/main.min.js'></script>
    <script src="https://cdn.jsdelivr.net/npm/axios/dist/axios.min.js"></script>
    <script src='https://cdnjs.cloudflare.com/ajax/libs/fullcalendar/4.2.0/core/locales/fr.js'></script>
    <style>
        .fc-past {
            background-color: #d3d3d3 !important; /* Gris sombre pour les créneaux passés */
        }
        .fc .fc-today {
            background-color: transparent !important; /* Remplacez 'transparent' par la couleur de votre choix, si nécessaire */
        }

        body {
            font-family: "Montserrat", sans-serif;
            font-optical-sizing: auto;

            font-style: normal;

        }


    </style>
    <script>
        document.addEventListener('DOMContentLoaded', function() {
            var calendarEl = document.getElementById('calendar');
            var calendar = new FullCalendar.Calendar(calendarEl, {
                plugins: ['interaction', 'dayGrid', 'timeGrid'],
                locale:"fr",
                header: {
                    left: 'prev,next today',
                    center: 'title',
                    right: 'timeGridWeek,timeGridDay'
                },
                defaultView: 'timeGridWeek',
                slotDuration: '00:30:00',
                selectable: true,
                selectMirror: true,
                unselectAuto: false,
                selectOverlap: false,
                allDaySlot: false,
                scrollTime: '08:00:00',
                minTime: '08:00:00',
                maxTime: '20:00:00',
                height: 'auto',
                contentHeight: 'auto',
                editable: true,
                nowIndicator: true,
                validRange: {
                    start: moment().startOf('day') // Empêche la sélection des jours précédents aujourd'hui
                },
                eventClick: function (info) {
                    info.event.remove();
                },
                select: function (info) {
                    calendar.addEvent({
                        title: moment(info.start).format('HH:mm') + " à " + moment(info.end).format('HH:mm'),
                        start: info.start,
                        end: info.end,
                        allDay: info.allDay,
                        color: '#b95e5e'
                    });

                    var slot = {
                        start: info.startStr,
                        end: info.endStr,
                        allDay: info.allDay
                    };
                    slots.push(slot);
                    // Délogage pour vérifier
                    updateSlotsInput();
                    console.log($('#slotsInput').val());
                },
                eventDrop: function(info) {
                    // Empêcher le déplacement d'événements dans le passé
                    if (moment(info.event.start).isBefore(moment(), 'day')) {
                        info.revert();  // Revenir à la position originale si la nouvelle date est dans le passé
                    }
                    var eventDate = moment(info.event.start);
                    var now = moment();
                    if (eventDate.isBefore(now, 'minute') && eventDate.isSame(now, 'day')) {
                        info.revert();  // Revenir à la position originale si la nouvelle date est dans le passé
                    }
                },
                selectAllow: function (selectInfo) {
                    var start = moment(selectInfo.start);
                    var end = moment(selectInfo.end);

                    start.isSame(end, 'day');
                    start.isSame(end, 'day');
                    return moment().isSameOrBefore(selectInfo.start) && moment().isSameOrBefore(selectInfo.start, 'hour') && moment().isSameOrBefore(start, 'day') &&
                        start.isSame(end, 'day');
                },
            });
            calendar.render();
        });

        function updateSlotsInput() {
            $('#slotsInput').val(JSON.stringify(slots)); // Mise à jour du champ caché avec les slots en JSON
        }

        var slots = [];
    </script>
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/style.css"/>
</head>

<h1 style="color: white; text-align: center">Création d'un nouveau sondage</h1>

<div class="card bg-light">
    <div class="card-body">
        <form:form method="POST" modelAttribute="poll">
            <form:errors path="*" cssClass="alert alert-danger" element="div"/>
            <c:if test="${empty sessionScope.isLoggedIn}">
                <div class="form-group my-1">
                <label for="creator">Email :</label>
                <input class="form-control" id="creator" name="creator"/>
                <form:errors path="creator" cssClass="alert alert-warning"
                             element="div"/>
            </c:if>
            <c:if test="${!empty sessionScope.isLoggedIn}">
                <input type="hidden" id="creator" name="creator" value="${email}"/>
            </c:if>
            </div>
            <div class="form-group my-1">
                <label for="title">Titre du sondage :</label>
                <form:input class="form-control" path="title"/>
            </div>
            <div class="form-group my-1">
                <label for="description">Description :</label>
                <form:textarea class="form-control" path="description" rows="4"/>
                <form:errors path="description" cssClass="alert alert-warning"
                             element="div"/>
            </div>
            <div class="form-group my-1">
                <label for="location">Lieu :</label>
                <form:input class="form-control" path="location"/>
                <form:errors path="location" cssClass="alert alert-warning"
                             element="div"/>
            </div>
            <div class="form-group my-1">
                <label for="limitDate">Date limite :</label>
                <form:input class="form-control" path="limitDate" type="date"/>
                <form:errors path="limitDate" cssClass="alert alert-warning"
                             element="div"/>
            </div>


            <input type="hidden" id="slotsInput" name="slotsJson" value=""/>

            <div id='calendar-container'>
                <div id='calendar'></div>
            </div>
            <div class="form-group my-1">
                <button type="submit" class="btn btn-info">Submit</button>
            </div>
        </form:form>
    </div>
</div>
<body>
</body>
</html>