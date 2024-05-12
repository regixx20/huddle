<%--
  Created by IntelliJ IDEA.
  User: meded
  Date: 07/05/2024
  Time: 00:49
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
    sefztr"ztt
</head>
<body>
<c:forEach var="slot" items="${slots}">
    <div class="poll-box">
        <a href="/polls/details?id=${slot.id}" style="text-decoration: none;" >
                ${slot.id} - ${slot.start} - ${slot.end} - ${slot.poll.title}
        </a>
        <a href="/polls/edit?id=${slot.id}" style="text-decoration: none;">Edit</a>


    </div>


</c:forEach>
</body>
</html>
