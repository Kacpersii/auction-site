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

    <h1>403 - Odmowa dostępu</h1>
    <div>Witaj '<security:authentication property="principal.username"/>',
        nie masz odpowiednich praw dostępu do żądanej strony.
    </div>
</div>
</body>
</html>

<jsp:include page="../shared/footer.jsp"/>
