
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

    <jsp:include page="shared/header.jsp">
        <jsp:param name="pageName" value="home"/>
    </jsp:include>

    <html>
    <head>
        <title>HOME</title>
    </head>

    <body class="card">
        <div id="main">
        To moja strona domowa
        <br/>
        ${message} (<= ta wiadomość jest wynikiem realizacji zadania 2)
        <br/>
        <a href="vehicleList.html">Lista komponentów JB wyświetlonych z użyciem JSTL (Zadanie 4)</a><br/>
        </div>
    </body>
</html>

<jsp:include page="shared/footer.jsp"/>