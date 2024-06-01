<%@ include file="/WEB-INF/jsp/header.jsp"%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>

<c:set var="pageTitle" value="Mon espace" />

<!DOCTYPE html>
<html lang="fr">
<head>
    <meta charset="UTF-8">
    <title>Dashboard</title>
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/style.css" />
    <style>
        /* Styles des onglets */
        .tab {
            overflow: hidden;
            border-bottom: 1px solid #ccc;
            background-color: #f1f1f1;
            display: flex;
            justify-content: center;
        }
        .tab button {
            background-color: inherit;
            border: none;
            outline: none;
            cursor: pointer;
            padding: 14px 16px;
            transition: 0.3s;
            font-size: 17px;
            margin: 0 10px;
        }
        .tab button:hover {
            background-color: #ddd;
        }
        .tab button.active {
            background-color: #ccc;
            border-bottom: 3px solid #007bff;
        }
        .tabcontent {
            display: none;
            padding: 20px;
            border: 1px solid #ccc;
            border-top: none;
        }
        .tabcontent.active {
            display: block;
        }
        .container {
            max-width: 800px;
            margin: 0 auto;
            padding: 20px;
        }
        .poll-box {
            background-color: #f9f9f9;
            padding: 15px;
            margin: 10px 0;
            border: 1px solid #ddd;
            border-radius: 5px;
        }
        .poll-box button {
            background-color: #007bff;
            color: white;
            border: none;
            padding: 10px 15px;
            cursor: pointer;
            border-radius: 4px;
        }
        .poll-box button:hover {
            background-color: #0056b3;
        }
        .poll-box a {
            color: #007bff;
            text-decoration: none;
            margin-top: 10px;
            display: inline-block;
        }
        .poll-box a:hover {
            text-decoration: underline;
        }
        footer {
            text-align: center;
            padding: 20px;
            background-color: #f1f1f1;
            border-top: 1px solid #ccc;
            margin-top: 20px;
        }
    </style>
    <script>
        function openTab(evt, tabName) {
            var i, tabcontent, tablinks;
            tabcontent = document.getElementsByClassName("tabcontent");
            for (i = 0; i < tabcontent.length; i++) {
                tabcontent[i].style.display = "none";
                tabcontent[i].classList.remove("active");
            }
            tablinks = document.getElementsByClassName("tablinks");
            for (i = 0; i < tablinks.length; i++) {
                tablinks[i].className = tablinks[i].className.replace(" active", "");
            }
            document.getElementById(tabName).style.display = "block";
            document.getElementById(tabName).classList.add("active");
            evt.currentTarget.className += " active";
        }

        document.addEventListener("DOMContentLoaded", function() {
            document.getElementById("defaultOpen").click();
        });
    </script>
</head>
<body>
    <div class="container">
        <div class="tab">
            <button class="tablinks" onclick="openTab(event, 'MesSondages')" id="defaultOpen">Mes sondages</button>
            <button class="tablinks" onclick="openTab(event, 'MesParticipations')">Mes participations</button>
        </div>

        <div id="MesSondages" class="tabcontent">
            <h2>Créer un nouveau sondage</h2>
            <form action="/meeting/edit" method="get">
                <button type="submit">Créer Sondage</button>
            </form>
            <c:if test="${not empty message}">
                <p>${message}</p>
            </c:if>
            <h2>Mes sondages</h2>
                    <c:forEach var="poll" items="${polls}">
                        <div class="poll-box">
                            <button type="submit" onclick="window.location.href='/meeting/organize/${poll.id}'">${poll.title}</button>
                            <br><br>
                            <a href="${pageContext.request.contextPath}/meeting/edit?id=${poll.id}" style="text-decoration: none;">Edit</a>
                        </div>
                    </c:forEach>
        </div>

        <div id="MesParticipations" class="tabcontent">
            <h2>Mes participations</h2>

                    <c:forEach var="ptPolls" items="${user.participatedPolls}">
                        <div class="poll-box">
                            <button type="submit" onclick="window.location.href='/meeting/organize/${ptPolls.id}'">${ptPolls.title}</button>
                        </div>
                    </c:forEach>
        </div>
    </div>
    <footer>
        <p>© 2024 MeetEasy - Tous droits réservés.</p>
    </footer>
</body>
</html>
