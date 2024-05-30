<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<%@ taglib prefix="c" uri="jakarta.tags.core"%>
<%@ taglib prefix="fmt" uri="jakarta.tags.fmt"%>


<c:url var="css" value="/style.css" />
<c:url var="logoUrl" value="/images/logo_MeetEasy.png" />

<html>
<head>
    <meta charset="UTF-8">
    <title>MeetEasy</title>
    <link rel="preconnect" href="https://fonts.googleapis.com">
<link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
<link href="https://fonts.googleapis.com/css2?family=Lato:ital,wght@0,100;0,300;0,400;0,700;0,900;1,100;1,300;1,400;1,700;1,900&display=swap" rel="stylesheet">
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/style.css" />

</head>

<body>
    <header>
        <nav class="navbar">
            <div class="navbar-left">
                <img src="${pageContext.request.contextPath}/images/logo_MeetEasy.png" alt="logo" width="100" height="auto" onclick="window.location.href='/'" style="cursor: pointer;">
                <div class="nav-link">
                    <c:if test="${not empty sessionScope.user}">
                        <button type="button" class="btn btn-nav" onclick="window.location.href='/dashboard'">Mon espace</button>
                    </c:if>
                    <button type="button" class="btn btn-nav" onclick="window.location.href='/contact'">Contact</button>
                </div>
            </div>
            <div class="navbar-right">
                <c:choose>
                    <c:when test="${not empty sessionScope.user}">
                        <span>Compte: ${sessionScope.user.firstName} ${sessionScope.user.lastName}</span>
                        <button type="button" class="btn btn-logout" onclick="window.location.href='/logout'">Se déconnecter </button>
                    </c:when>
                    <c:otherwise>
                        <button type="button" class="btn btn-login" onclick="window.location.href='/login'">Se connecter</button>
                        <button type="button" class="btn btn-register" onclick="window.location.href='/register'">S'inscrire</button>
                    </c:otherwise>
                </c:choose>
            </div>
        </nav>
    </header>
</body>
</html>









