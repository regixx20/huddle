<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <title>Login</title>
</head>
<body>
<h2>Login</h2>
<form action="<c:url value='/login'/>" method="post">
    <label for="email">Email:</label>
    <input type="email" id="email" name="email" required/><br/><br/>

    <label for="password">Password:</label>
    <input type="password" id="password" name="password" required/><br/><br/>

    <input type="submit" value="Login"/>
</form>
<c:if test="${param.error != null}">
    <p style="color: red;">Invalid email or password.</p>
</c:if>
</body>
</html>
