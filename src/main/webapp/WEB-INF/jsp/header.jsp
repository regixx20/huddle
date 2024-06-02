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
<style>
.navbar {
            display: flex;
            justify-content: space-between;
            align-items: center;
            padding: 10px 20px;
            background-color: #f8f9fa;
            border-bottom: 1px solid #ddd;
        }
        .navbar-left {
            display: flex;
            align-items: center;
        }
        .navbar-left img {
            height: 50px; /* Réduction de la taille du logo */
            margin-right: 20px;
        }
        .nav-link {
            display: flex;
            align-items: center;
        }
        .nav-link button {
            margin-left: 10px;
        }
        .navbar-right {
            display: flex;
            align-items: center;
        }
        .navbar-right span {
            margin-right: 10px;
        }

        .navbar-right .user-info {
            display: flex;
            align-items: center;
            margin-right: 20px;
        }
        .navbar-right .user-info img {
            height: 30px; /* Réduction de la taille de l'icône de profil */
            width: 30px; /* Largeur de l'icône de profil */
            border-radius: 50%;
            margin-right: 10px;
        }
        .navbar-right .user-info span {
            font-size: 16px;
            color: #333;
        }

</style>
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
                    <div class="user-info">
                    <img src="${pageContext.request.contextPath}/images/user-interface.png" alt="Profile Picture">
                        <span>${sessionScope.user.firstName} ${sessionScope.user.lastName}</span>
                    </div>
                        <button type="button" class="btn btn-logout" onclick="window.location.href='/logout'">Se déconnecter </button>

                    </c:when>
                    <c:otherwise>
                        <button type="button" class="btn btn-login" onclick="window.location.href='/signin'">Se connecter</button>
                        <button type="button" class="btn btn-register" onclick="window.location.href='/register'">S'inscrire</button>
                    </c:otherwise>
                </c:choose>
            </div>
        </nav>
    </header>
</body>
</html>









