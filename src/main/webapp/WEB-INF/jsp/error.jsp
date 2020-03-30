
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

    <jsp:include page="shared/header.jsp">
        <jsp:param name="pageName" value="home"/>
    </jsp:include>
<html>
    <head>
        <title>Błąd</title>
    </head>
    <body class="card">
        <div id="main">
            <h1>Domyślny widok błędu w Spring Boot</h1>
            <div>
                Wiadomość: <span>${message}</span></br>
                Wyjątek: <span>${exception}</span></br>
                <!--
        StackTrace: <span>${stackTrace}</span>
        -->
            </div>
        </div>
    </body>
</html>

<jsp:include page="shared/footer.jsp"/>