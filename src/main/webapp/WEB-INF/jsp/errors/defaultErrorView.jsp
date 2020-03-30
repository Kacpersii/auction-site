<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<jsp:include page="../shared/header.jsp">
    <jsp:param name="pageName" value="home"/>
</jsp:include>

<html>
<head>
    <title>HOME</title>
</head>

<body class="card">
<div id="main">
    URL żądania: ${url}<br/>
    Wyjątek: ${exception.message}
    <!--
                Wyjątek ten będzie widoczny w źródle strony, ale nie dla użytkownika
            <c:forEach items="${exception.stackTrace}" var="st">
                ${st}
            </c:forEach>
            -->
</div>
</body>
</html>

<jsp:include page="../shared/footer.jsp"/>
