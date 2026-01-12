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
            background-color: #28385E;
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
            background-color: #b95e5e;
        }
        .tab button.active {
            background-color: #0a3b9a;
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
        .poll-box-link {
    text-decoration: none; /* Enlève le soulignement des liens */
    color: inherit; /* Garde la couleur du texte héritée */
    display: block; /* Fait que le lien se comporte comme un div */
}

.poll-box {
    background-color: #f9f9f9;
    padding: 15px;
    margin: 10px 0;
    border: 1px solid #ddd;
    border-radius: 5px;
    transition: background-color 0.3s ease; /* Animation de fond au survol */
}

.poll-box:hover {
    background-color: #e9ecef; /* Change la couleur de fond au survol */
}

.poll-box div {
    padding: 5px 0; /* Espacement vertical pour le texte et le lien d'édition */
}

.poll-box small {
    font-size: smaller; /* Petite taille pour le texte 'Edit' */
    color: #007bff; /* Couleur pour le lien d'édition */
}
.poll-box:focus, .poll-box:hover {
    box-shadow: 0 0 5px rgba(0,123,255,0.5); /* Ajoute une ombre au survol ou au focus */
    cursor: pointer; /* Montre un curseur de pointeur pour indiquer un lien cliquable */
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
            <form action="/meeting/edit" method="get">
                <button type="submit">Créer un sondage</button>
            </form>
        <div class="tab">
            <button class="tablinks" onclick="openTab(event, 'MesSondages')" id="defaultOpen">Mes sondages</button>
            <button class="tablinks" onclick="openTab(event, 'MesParticipations')">Mes participations</button>
        </div>

        <div id="MesSondages" class="tabcontent">

            <c:if test="${not empty message}">
                <p>${message}</p>
            </c:if>
                    <c:forEach var="poll" items="${polls}">
                        <a href="/meeting/organize/${poll.id}" class="poll-box-link">
                                <div class="poll-box">
                                    <div>${poll.title}</div>
                                </div>
                                        <div><small> <a href="${pageContext.request.contextPath}/meeting/edit?id=${poll.id}" style="text-decoration: none;">Modifier</a></small></div>

</a>
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

</body>

</html>
