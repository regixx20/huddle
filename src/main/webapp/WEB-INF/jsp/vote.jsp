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

      .slot-entry {
         padding: 10px;
         margin-bottom: 5px;
         border: 1px solid #ccc;
         background-color: white;
      }

      .slot-time {
         display: flex;
         justify-content: space-between;
         align-items: center;
      }

      .star {
         cursor: pointer;
         color: gold; /* Change color if favorited */
      }

      .slot-details {
         display: flex;
         justify-content: space-between;
         margin-top: 5px;
      }

      .edit-link {
         cursor: pointer;
         color: blue;
         text-decoration: underline;
      }

      .participants .count {
         font-weight: bold;
      }

      .form-group {
         margin-top: 20px;
      }
      .options label {
         display: block;
         margin-top: 5px;
      }


   </style>
   <style>
      .yes { color: green; }
      .no { color: red; }
      .ifNecessary { color: orange; }
   </style>
   <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
   <script>
      function updateRadioValue(slotId) {
         var selectedValue = document.querySelector(`input[name='slot_${slotId}_response']:checked`).value;
         document.getElementById(`participantValue_${slotId}`).value = selectedValue;
         console.log('New state for slot', slotId, ':', selectedValue);
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
                  <div class="slot-entry">
                     <div class="slot-date">
                        <c:url value="/polls/details" var="pollDetailsUrl">
                           <c:param name="idSlot" value="${slot.id}"/>
                        </c:url>

                        <div class="day">${slot.dayOfWeek}</div>
                     </div>
                     <div class="time-duration">
                        <div class="time">
                           <label>
                              <span class="date">${slot.start.dayOfMonth}/${slot.start.month}/${slot.start.year}
                              ${slot.start.hour}H${slot.start.minute} - ${slot.end.hour}H${slot.end.minute}</span>

                              <!--<input type="checkbox"  onclick="toggleCheckbox(this)" data-id="${slot.id}" data-state="unchecked">-->
                              <div class="options">
                                 <label><input type="radio" name="slot_${slot.id}_response" value="yes" onchange="updateRadioValue('${slot.id}')"> Oui</label>
                                 <label><input type="radio" name="slot_${slot.id}_response" value="no" onchange="updateRadioValue('${slot.id}')"> Non</label>
                                 <label><input type="radio" name="slot_${slot.id}_response" value="maybe" onchange="updateRadioValue('${slot.id}')"> Peut-être</label>
                              </div>
                           </label>

                        </div>
                     </div>
                  </div>
               </c:forEach>

            <c:if test="${!empty sessionScope.isLoggedIn}">
               <input type="hidden" class="participant" name="participantEmail" value="${sessionScope.user.email}" />
               <input type="hidden" class="participant" name="participantlastName" value="${sessionScope.user.lastName}"/>
               <input type="hidden" class="participant" name="participantfirstName" value="${sessionScope.user.firstName}"/>
            </c:if>
            <div class="form-group">
               <button type="submit" class="btn btn-info">Soumettre</button>
            </div>
         </form:form>
      </div>
   </div>
</div>


</body>
</html>
