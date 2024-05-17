<%--
 Created by IntelliJ IDEA.
 User: meded
 Date: 15/05/2024
 Time: 14:14
 To change this template use File | Settings | File Templates.
--%>
<%@ include file="/WEB-INF/jsp/header.jsp"%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<!DOCTYPE html>
<html lang="fr">
<head>
   <meta charset="UTF-8">
   <title>MeatEasy</title>
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
         width: 70%; /* Ajustez la largeur de la container en pourcentage si nécessaire */
         margin: 0 auto; /* Centrer la container horizontalement */
      }


      .poll-box {
         width: 70vw;
         height: 70vw;
         max-width: 500px;
         max-height: 500px;
         margin: 20px auto;
         padding: 20px;
         border: 1px solid #ccc;
         box-sizing: border-box;
         background-color: white;
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
      input, button {
         padding: 10px;
         margin-top: 10px;
      }
   </style>
</head>
<body>
<header>


</header>
<div class="container">


   <%-- Ajout d'une boucle pour simuler l'affichage des sondages --%>

   <div class="container">
      <div class="poll-box" id="crenaux">
         <div class="slots">
            <h3>Créneaux</h3>
            <form method="get">
               <c:forEach var="slot" items="${poll.slots}">
                  <div>
                     <input type="checkbox" name="slotId" value="${slot.id}"/>
                     <label>${slot.start} - ${slot.end}</label>
                  </div>
               </c:forEach>
               <input type="submit" value="Voter"/>
            </form>

         </div>


      </div>
   </div>








</body>
</html>
