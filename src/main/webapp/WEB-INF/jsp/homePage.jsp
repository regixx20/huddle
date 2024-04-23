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
    <title>AmuBooking - Planifiez vos rendez-vous facilement</title>
    <style>
        body {
            font-family: Arial, sans-serif;
            margin: 0;
            padding: 0;
            background-color: #f7f7f7;
        }

        header {
            background-color: #007bff;
            color: white;
            padding: 20px;
            text-align: center;
        }

        main {
            padding: 20px;
        }

        section {
            margin-bottom: 30px;
        }

        h1, h2 {
            color: #007bff;
        }

        p {
            line-height: 1.6;
        }

        ul, ol {
            padding-left: 20px;
        }

        footer {
            background-color: #343a40;
            color: white;
            padding: 10px;
            text-align: center;
        }



    </style>
</head>
<body>
<header>
    <h1>Bienvenue sur AmuBooking</h1>
    <p>La plateforme de planification de rendez-vous conçue spécialement pour les étudiants de l'Université Aix-Marseille (AMU) et au-delà.</p>
</header>

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

