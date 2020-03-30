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
        <h1>Wykryto atak CSRF</h1>
        <div>Próbujesz zafałszować formularz.
        </div>
    </div>
</div>
</body>
</html>

<jsp:include page="../shared/footer.jsp"/>