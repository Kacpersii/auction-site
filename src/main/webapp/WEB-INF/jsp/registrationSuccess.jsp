<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<c:import url="shared/header.jsp">
    <c:param name="pageName" value="registration"></c:param>
</c:import>

<html>
<head>
    <title>HOME</title>
</head>

<body class="card">
<div id="main">
    <div class="row" style="margin-top:20px">
        <div class="col-xs-12 ">
            <h1>Właśnie zostałeś zarejestrowany w systemie!</h1>
            Twoje konto nie jest jeszcze aktywne (flaga w bazie enabled=false),<br/>
            Po aktywacji konta możesz się  <a href="/login">zalogować</a>
        </div>
    </div>
</body>
</html>

<jsp:include page="shared/footer.jsp"/>