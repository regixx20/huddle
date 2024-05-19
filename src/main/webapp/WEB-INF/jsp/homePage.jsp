<%--
  Created by IntelliJ IDEA.
  User: meded
  Date: 23/04/2024
  Time: 20:37
  To change this template use File | Settings | File Templates.
--%>
<%@ include file="/WEB-INF/jsp/header.jsp"%>
<%@ page language="java" contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Accueil</title>
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/style.css" />
</head>
<body>

<div class="main-container">
    <div class="container">
        <h2>Créer un nouveau sondage</h2>
        <form action="/meeting/edit" method="get">
            <button type="submit">Créer Sondage</button>
        </form>
    </div>
    <c:if test="${not empty sessionScope.isLoggedIn}">
        <div class="container">
            <h2>Accéder à mon espace</h2>
            <form action="/dashboard" method="get">
                <button type="submit">Mon espace</button>
            </form>
        </div>
    </c:if>
</div>
<main>
    <section id="purpose">
        <h2>Pourquoi utiliser MeetEasy ?</h2>
        <p>MeetEasy simplifie la planification de vos rendez-vous et réunions. Voici comment :</p>
        <ul>
            <li>Organisez facilement des sondages pour trouver le meilleur créneau horaire pour vos réunions de groupe, vos projets professionnels ou vos rencontres informelles.</li>
            <li>Éliminez les échanges de courriels fastidieux en permettant à chaque participant de choisir ses disponibilités sur un même calendrier.</li>
            <li>Accédez rapidement aux disponibilités de chacun, facilitant ainsi la coordination des horaires entre collègues, amis et partenaires.</li>
            <li>Gagnez du temps et évitez les conflits d'horaires grâce à une interface intuitive et conviviale.</li>
        </ul>
    </section>

    <section id="how-to-use">
        <h2>Comment utiliser MeetEasy ?</h2>
        <p>C'est simple ! Suivez ces étapes pour commencer :</p>
        <ol>
            <li>Inscrivez-vous gratuitement sur MeetEasy en créant un compte.</li>
            <li>Créez un nouveau sondage en définissant le titre, la description et les options de date et d'heure.</li>
            <li>Invitez les participants en partageant le lien du sondage ou en les ajoutant directement par leur adresse e-mail.</li>
            <li>Consultez les réponses en temps réel et confirmez le meilleur créneau horaire pour votre rendez-vous.</li>
            <li>Profitez d'une organisation efficace et sans tracas de vos rencontres !</li>
        </ol>
    </section>

    <section id="benefits">
        <h2>Les avantages de MeetEasy</h2>
        <ul>
            <li>Facile d'utilisation</li>
            <li>Compatible avec tous les appareils, que ce soit un ordinateur, une tablette ou un smartphone.</li>
            <li>Confidentialité garantie : vos données personnelles et vos plannings restent privés et sécurisés.</li>
        </ul>
    </section>
</main>

<footer>
    <p>&copy; 2024 MeetEasy - Tous droits réservés.</p>
</footer>
</body>
</html>
