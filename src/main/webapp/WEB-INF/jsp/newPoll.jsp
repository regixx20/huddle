<%--
  Created by IntelliJ IDEA.
  User: meded
  Date: 23/04/2024
  Time: 19:36
  To change this template use File | Settings | File Templates.
--%>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="fr">
<head>
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/fullcalendar/3.10.2/fullcalendar.min.css" />
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
    <script>
        document.addEventListener('DOMContentLoaded', function() {
            var calendarEl = document.getElementById('calendar');
            var calendar = new FullCalendar.Calendar(calendarEl, {
                plugins: ['interaction', 'dayGrid', 'timeGrid'],
                header: {
                    left: 'prev,next today',
                    center: 'title',
                    right: 'dayGridMonth,timeGridWeek,timeGridDay'
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
                eventClick: function(info) {
                    info.event.remove();
                },

                select: function(info) {
                    calendar.addEvent({
                        title: "Sélectionné de " + moment(info.start).format('HH:mm') + " à " + moment(info.end).format('HH:mm'),
                        start: info.start,
                        end: info.end,
                        allDay: info.allDay,
                        color: '#ff9f89'
                    });

                    var slot = {
                        start: info.startStr,
                        end: info.endStr,
                        allDay: info.allDay
                    };
                    slots.push(slot);
                    // Délogage pour vérifier
                    //console.log('SlotQDSFFSF:', slots);
                    //console.log(JSON.stringify(slots));
                    updateSlotsInput();
                    console.log($('#slotsInput').val());


                },
                editable: true,
                eventLimit: true
            });
            calendar.render();
        });
        function updateSlotsInput() {
            $('#slotsInput').val(JSON.stringify(slots)); // Mise à jour du champ caché avec les slots en JSON
        }
        var slots = [];

    </script>
    <style>
        #calendar-container {
            max-width: 600px;
            margin: 0 auto;
            padding: 20px;
            box-shadow: 0 0 10px 0 rgba(0,0,0,0.3);
        }
        .card {
            max-width: 600px;
            margin: 0 auto; /* Centrer horizontalement */
        }

        .card-body {
            padding: 20px;
        }

        .form-group {
            margin-bottom: 20px;
        }

        .form-group label {
            font-weight: bold;
            margin-bottom: 5px;
        }
        .form-control {
            width: 100%;
            padding: 10px;
            font-size: 16px;
            border: 1px solid #ccc;
            border-radius: 4px;
        }



        .btn-info {
            width: 100%; /* Rendre le bouton "Submit" aussi large que son conteneur */
            padding: 10px 20px; /* Ajouter un remplissage pour le bouton */
            font-size: 16px; /* Taille de police pour le bouton */
            background-color: #007BFF; /* Couleur de fond du bouton */
            border: 1px solid #007BFF; /* Bordure du bouton */
            color: #fff; /* Couleur du texte du bouton */
            border-radius: 4px; /* Bordure arrondie */
            transition: all 0.3s ease; /* Ajouter une transition en douceur lors du survol */
            margin-top: 20px;
        }
        .btn-info:hover {
            background-color: #31b0d5; /* Couleur de fond du bouton au survol */
            border-color: #269abc; /* Bordure du bouton au survol */
        }
    </style>

</head>
<h1>Création d'un nouveau sondage</h1>
<div class="card bg-light" >
    <div class="card-body">
        <form:form method="POST" modelAttribute="poll" >
            <form:errors path="*" cssClass="alert alert-danger" element="div" />
            <c:if test="${empty sessionScope.isLoggedIn}">
            <div class="form-group my-1">
                <label  for="creator" >Email :</label>
                <input class="form-control" id="creator" name="creator" />
                <form:errors path="creator" cssClass="alert alert-warning"
                             element="div" />
            </c:if>
            <c:if test="${!empty sessionScope.isLoggedIn}">
                <input type="hidden" id="creator" name="creator" value="${email}" />
            </c:if>
            </div>
            <div class="form-group my-1">
                <label for="title">Titre du sondage :</label>
                <form:input class="form-control" path="title" />
                <form:errors path="title" cssClass="alert alert-warning"
                             element="div" />
            </div>
            <div class="form-group my-1">
                <label for="description">Description :</label>
                <form:textarea class="form-control" path="description" rows="4" />
                <form:errors path="description" cssClass="alert alert-warning"
                             element="div" />
            </div>
            <div class="form-group my-1">
                <label for="location">Lieu :</label>
                <form:input class="form-control" path="location" />
                <form:errors path="location" cssClass="alert alert-warning"
                             element="div" />
            </div>
            <div class="form-group my-1">
                <label for="limitDate">Date limite :</label>
                <form:input class="form-control" path="limitDate" type="date" />
                <form:errors path="limitDate" cssClass="alert alert-warning"
                             element="div" />
            </div>



            <input type="hidden" id="slotsInput" name="slots" value="" />

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