<%--
  Created by IntelliJ IDEA.
  User: meded
  Date: 23/04/2024
  Time: 20:37
  To change this template use File | Settings | File Templates.
--%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>MeatEasy - Planifiez vos rendez-vous facilement</title>
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
            width: 80%;
            margin: 20px auto;
            padding: 20px;
            background-color: white;
            box-shadow: 0 0 10px 0 rgba(0,0,0,0.1);
        }
        .poll-box {
            padding: 10px;
            background-color: #e0e0e0;
            margin-bottom: 10px;
            border-radius: 5px;
            box-shadow: 0 2px 5px rgba(0,0,0,0.2);
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
        input{
            padding: 10px;
            margin-top: 10px;
        }

        .btn-pill {
            border-radius: 20px;
            margin-bottom: 10px;
            margin-top: 10px;
            padding: 10px 20px;
            font-size: 16px;
        }

        .btn-pill-primary {
            background-color: #3CE6A5;
            color: #fff;
            border: none;
        }

        .btn-pill-secondary {
            background-color: #E6B13C;
            color: #fff;
            border: none;
        }

        .btn-pill-danger {
            background-color: #dc3545;
            color: #fff;
            border: none;
        }



    </style>
</head>
<body>
<header>
    <h1>MeatEasy</h1>
    <div >
        <c:if test="${empty sessionScope.isLoggedIn}">
        <!-- Afficher ces boutons si l'utilisateur n'est pas connecté -->
        <button type="button" class="btn btn-primary btn-pill btn-pill-primary" onclick="window.location.href='/login'">Login</button>
        <button type="button" class="btn btn-secondary btn-pill btn-pill-secondary" onclick="window.location.href='/register'">Register</button>
        </c:if>
        <c:if test="${not empty sessionScope.isLoggedIn}">
        <!-- Afficher ce bouton si l'utilisateur est connecté -->

        <button type="button" class="btn btn-primary btn-pill btn-pill-danger" onclick="window.location.href='/logout'">Logout</button>
        </c:if>

</header>

<div class="container">
    <h2>Créer un nouveau sondage</h2>
    <form action="/meeting/edit" method="get">
        <button type="submit">Créer Sondage</button>
    </form>
</div>

<div class="container">
    <c:if test="${not empty sessionScope.isLoggedIn}">
        <h2>Accéder à mon espace</h2>
        <form action="/dashboard" method="get">
            <button type="submit">Mon espace</button>
        </form>
    </c:if>
</div>

<main>
    <section id="purpose">
        <h2>Pourquoi utiliser AmuBooking ?</h2>
        <p>AmuBooking simplifie la planification de vos rendez-vous et réunions, que vous soyez étudiant à l'AMU ou non. Voici comment :</p>
        <ul>
            <li>Organisez facilement des sondages pour trouver le meilleur créneau horaire pour vos réunions de groupe, vos projets de cours, ou vos rencontres informelles.</li>
            <li>Éliminez les échanges de courriels fastidieux en permettant à chaque participant de choisir ses disponibilités sur un même calendrier.</li>
            <li>Accédez rapidement aux disponibilités de chacun, facilitant ainsi la coordination des horaires entre étudiants, professeurs et intervenants externes.</li>
            <li>Gagnez du temps et évitez les conflits d'horaires grâce à une interface intuitive et conviviale.</li>
        </ul>
    </section>

    <section id="how-to-use">
        <h2>Comment utiliser AmuBooking ?</h2>
        <p>C'est simple ! Suivez ces étapes pour commencer :</p>
        <ol>
            <li>Inscrivez-vous gratuitement sur AmuBooking en créant un compte étudiant ou en vous connectant avec vos identifiants AMU.</li>
            <li>Créez un nouveau sondage en définissant le titre, la description et les options de date et d'heure.</li>
            <li>Invitez les participants en partageant le lien du sondage ou en les ajoutant directement par leur adresse e-mail.</li>
            <li>Consultez les réponses en temps réel et confirmez le meilleur créneau horaire pour votre rendez-vous.</li>
            <li>Profitez d'une organisation efficace et sans tracas de vos rencontres !</li>
        </ol>
    </section>

    <section id="benefits">
        <h2>Les avantages d'AmuBooking</h2>
        <ul>
            <li>Facile d'utilisation</li>
            <li>Compatible avec tous les appareils, que ce soit un ordinateur, une tablette ou un smartphone.</li>
            <li>Confidentialité garantie : vos données personnelles et vos plannings restent privés et sécurisés.</li>
        </ul>
    </section>
</main>

<footer>
    <p>&copy; 2024 AmuBooking - Tous droits réservés.</p>
</footer>
</body>
</html>

