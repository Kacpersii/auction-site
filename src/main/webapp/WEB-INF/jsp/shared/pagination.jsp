

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<c:url var="firstPageUrl" value="${mainUrl}?page=0&size=${page.size}"/>
<c:url var="prevPageUrl" value="${mainUrl}?page=${page.number - 1}&size=${page.size}"/>
<c:url var="nextPageUrl" value="${mainUrl}?page=${page.number + 1}&size=${page.size}"/>
<c:url var="lastPageUrl" value="${mainUrl}?page=${page.totalPages - 1}&size=${page.size}"/>



<nav>
    <ul class="pagination pagination-sm" style="float: left">


        <li class="page-item${page.first?'disabled':''}">
            <a class="page-link" href="${page.first?'#':firstPageUrl}">
                <span>Pierwsza</span>
            </a>
        </li>


        <li class="page-item${page.first?' disabled':''}">
            <a class="page-link" href="${page.first?'#':prevPageUrl}">
                <span>&laquo;</span>
            </a>
        </li>

        <c:forEach var="pageIdx" begin="${0}" end="${page.totalPages-1}">
            <c:url var="pageUrl" value="${mainUrl}?page=${pageIdx}&size=${page.size}"/>
            <li class="page-item${pageIdx == page.number?' active':''}">
                <a class="page-link" href="${pageUrl}">${pageIdx+1}</a>
            </li>
        </c:forEach>

        <li class="page-item ${page.last?' disabled':''}">
            <a class="page-link" href="${page.last?'#':nextPageUrl}">
                <span>&raquo;</span>
            </a>
        </li>

        <li class="page-item${page.last?'disabled':''}">
            <a class="page-link" href="${page.last?'#':lastPageUrl}">
                <span>Ostatnia</span>
            </a>
        </li>

    </ul>

    <ul class="pagination pagination-sm" style="float: right">
        <c:set var="pageSizes" value="${[2, 4, 6, 10]}"/>
        <c:forEach var="size" items="${pageSizes}">
            <c:url var="pageUrl" value="${mainUrl}?page=0&size=${size}"/>
            <li class="page-item${page.size eq size?' active':''}">
                <a class="page-link" href="${pageUrl}"><span>${size}</span></a>
            </li>
        </c:forEach>
    </ul>
</nav>
