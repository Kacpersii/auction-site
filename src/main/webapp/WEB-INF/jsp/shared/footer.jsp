<%--
  Created by IntelliJ IDEA.
  User: grzesiek
  Date: 20.10.2018
  Time: 11:04
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="security" uri="http://www.springframework.org/security/tags" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>

<body>
<div id="footer">
    <footer class="blockquote-footer">
        © 2019 Platformy programowania
        <security:authorize access="isAuthenticated()">
            <security:authentication property="authorities" var="roleList"></security:authentication>
            <div>
                Twoje role to:
                <c:forEach items="${roleList}" var="role">
                    ${role},
                </c:forEach>
            </div>
        </security:authorize>
    </footer>
</div>
</body>
</html>
