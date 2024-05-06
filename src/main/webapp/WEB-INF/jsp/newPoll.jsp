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
                plugins: [ 'interaction', 'dayGrid', 'timeGrid' ],
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
                eventClick: function(info) {
                    info.event.remove();
                },
                select: function(info) {
                    const title = "Sélectionné de " + info.startStr + " à " + info.endStr;
                    calendar.addEvent({
                        title: title,
                        start: info.startStr,
                        end: info.endStr,
                        allDay: info.allDay,
                        color: '#ff9f89'
                    });

                    var events = calendar.getEvents();
                    events.forEach(function(event) {
                        var eventData = {
                            start: event.start.toISOString(),
                            end: event.end.toISOString(),
                            allDay: event.allDay
                        };

                        axios.post('/polls/edit', eventData, {
                            headers: {
                                'Content-Type': 'application/json'
                            }
                        })
                            .then(function(response) {
                                console.log('Success:', response.data);
                            })
                            .catch(function(error) {
                                console.error('Error:', error);
                            });
                    });

                    calendar.unselect(); // Nettoie la sélection visuelle actuelle
                },
                editable: true,
                eventLimit: true
            });

            calendar.render();
        });
    </script>

    <style>
        #calendar-container {
            max-width: 600px;
            margin: 0 auto;
            padding: 20px;
            box-shadow: 0 0 10px 0 rgba(0,0,0,0.3);
        }

    </style>
</head>
<h1>Création d'un nouveau sondage</h1>
<div class="card bg-light">
    <div class="card-body">
        <form:form method="POST" modelAttribute="poll" >

            <form:errors path="*" cssClass="alert alert-danger" element="div" />

            <div class="form-group my-1">
                <label for="title">Titre du sondage:</label>
                <form:input class="form-control" path="title" />
                <form:errors path="title" cssClass="alert alert-warning"
                             element="div" />
            </div>
            <div class="form-group my-1">
                <label for="description">Description:</label>
                <form:textarea class="form-control" path="description" rows="4" />
                <form:errors path="description" cssClass="alert alert-warning"
                             element="div" />
            </div>
            <div class="form-group my-1">
                <label for="location">Lieu:</label>

                <form:input class="form-control" path="location" />
                <form:errors path="location" cssClass="alert alert-warning"
                             element="div" />
            </div>
            <div class="form-group my-1">
                <label for="limitDate">Date limite:</label>
                <form:input class="form-control" path="limitDate" type="date" />
                <form:errors path="limitDate" cssClass="alert alert-warning"
                             element="div" />
            </div>
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