<%-- Created by IntelliJ IDEA. User: meded Date: 15/05/2024 Time: 14:14 To change this template use File | Settings | File Templates. --%>
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
   <style>
      .yes { color: green; }
      .no { color: red; }
      .ifNecessary { color: orange; }
   </style>
   <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
   <script>
      function toggleCheckbox(checkbox) {
         console.log('Checkbox clicked, current state:', checkbox.dataset.state);
         const currentState = checkbox.dataset.state || 'unchecked';
         switch (currentState) {
            case 'unchecked':
               checkbox.dataset.state = 'yes';
               checkbox.checked = true;
               break;
            case 'yes':
               checkbox.dataset.state = 'no';
               checkbox.checked = true;
               break;
            case 'no':
               checkbox.dataset.state = 'ifNecessary';
               checkbox.checked = true;
               break;
            case 'ifNecessary':
               checkbox.dataset.state = 'unchecked';
               checkbox.checked = false;
               break;
         }
         const hiddenInput = document.getElementById('participantValue_' + checkbox.dataset.id);
         hiddenInput.value = checkbox.dataset.state;
         console.log('New state:', checkbox.dataset.state);
         applyStyles(checkbox);
      }

      function applyStyles(checkbox) {
         switch (checkbox.dataset.state) {
            case 'yes':
               checkbox.style.color = 'green';
               break;
            case 'no':
               checkbox.style.color = 'red';
               break;
            case 'ifNecessary':
               checkbox.style.color = 'orange';
               break;
            default:
               checkbox.style.color = '';
               break;
         }
      }

   </script>
</head>
<body>
<div class="container">
   <div class="poll-box" id="creneaux">
      <div class="slots">
         <h3>Créneaux</h3>
         <form:form method="POST" modelAttribute="slot">
            <form:errors path="*" cssClass="alert alert-danger" element="div"/>
            <c:forEach var="slot" items="${poll.slots}">
               <div>
                  <c:url value="/polls/details" var="pollDetailsUrl">
                     <c:param name="idSlot" value="${slot.id}"/>
                  </c:url>
                  <label>${slot.start} - ${slot.end}
                     <input type="checkbox"  onclick="toggleCheckbox(this)" data-id="${slot.id}" data-state="unchecked">
                  </label>
                  <input type="hidden" id="participantValue_${slot.id}" name="participantValue_${slot.id}" value="unchecked"/>



                  </div>
            </c:forEach>
            <c:if test="${!empty sessionScope.isLoggedIn}">
            <input type="hidden" id="participant" name="participant" value="${sessionScope.user}" />
            </c:if>

            <div class="form-group my-1">
               <button type="submit" class="btn btn-info">Soumettre</button>
            </div>
         </form:form>
      </div>
   </div>
</div>
</body>
</html>
