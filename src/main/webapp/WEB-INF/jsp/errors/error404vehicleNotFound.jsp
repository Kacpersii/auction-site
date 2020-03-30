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

    <div>
        <h1>404 - Nie znaleziono pojazdu</h1>
        <div>
            URL żądania: ${url}</br>
            Wyjątek: ${exception.message}
        </div>
    </div>
</div>
</body>
</html>

<jsp:include page="../shared/footer.jsp"/>