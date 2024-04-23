<%--
  Created by IntelliJ IDEA.
  User: meded
  Date: 23/04/2024
  Time: 19:36
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<html>
<h1>Création d'un nouveau sondage</h1>
<div class="card bg-light">
    <div class="card-body">
        <form:form method="POST" action="" modelAttribute="poll" >

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

            <div class="form-group my-1">
                <button type="submit" class="btn btn-info">Soumettre</button>
            </div>
        </form:form>
    </div>
</div>

<body>

</body>
</html>

