<%--
  Created by IntelliJ IDEA.
  User: grzesiek
  Date: 10.11.2017
  Time: 18:35
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="security" uri="http://www.springframework.org/security/tags" %>
<html>
<jsp:include page="shared/header.jsp">
    <jsp:param name="pageName" value="logonForm"/>
</jsp:include>


<head>
    <title>Logowanie</title>
</head>
<body class="card">
<div id="main" class="container">
    <div class="row" style="margin-top:20px">
        <div class="col-xs-12 col-sm-8 col-md-6 col-sm-offset-2 col-md-offset-3">

            <form action="/login" method="post">
                <c:if test="${param.error != null}">
                    <p>
                        Nieprawidłowy login lub hasło.
                    </p>
                </c:if>
                <c:if test="${param.logout != null}">
                    <p>
                        Zostałeś wylogowany.
                    </p>
                </c:if>
                <div class="form-group">
                    <label for="username">Nazwa użytkownika</label>
                    <input type="text" id="username" name="username" class="form-control"/>
                </div>
                <div class="form-group">
                    <label for="password">Hasło</label>
                    <input type="password" id="password" name="password" class="form-control"/>
                </div>
                <div class="form-group">
                    <security:csrfInput/>
                    <button type="submit" class="btn btn-success">Zaloguj</button>
                </div>
            </form>
        </div>
    </div>
</div>
</body>
<jsp:include page="shared/footer.jsp"/>
</html>