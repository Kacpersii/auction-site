<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<c:import url="shared/header.jsp">
	<c:param name="pageName" value="vehicleForm"></c:param>
</c:import>

<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body class="card">
<div id="main" class="container">

		<form:form modelAttribute="vehicle">

			<div class="form-group">
				<label for="name" class="bmd-label-floating">Nazwa:</label>
				<form:input path="name" cssClass="form-control" cssErrorClass="form-control is-invalid"/>
				<form:errors path="name" cssClass="error text-danger" element="div"/>
			</div>
			<div class="form-group">
				<label for="model" class="bmd-label-floating">Model:</label>
				<form:input path="model" cssClass="form-control" cssErrorClass="form-control is-invalid"/>
				<form:errors path="model" cssClass="error text-danger" element="div"/>
			</div>
			<div class="form-group">
				<label for="price" class="bmd-label-floating">Cena:</label>
				<form:input path="price" cssClass="form-control" cssErrorClass="form-control is-invalid"/>
				<form:errors path="price" cssClass="error text-danger" element="div"/>
			</div>
			<div class="form-group">
				<label for="productionDate" class="bmd-label-floating">Data Produkcji:</label>
				<form:input path="productionDate" cssClass="form-control" type="date" cssErrorClass="form-control is-invalid"/>
				<form:errors path="productionDate" cssClass="error text-danger" element="div"/>
			</div>

			<div class="form-group">
				<label class="bmd-label-floating">
					<form:checkbox path="exclusive" /> Ekskluzywny?
				</label>
			</div>

			<div class="form-group">
				<label for="vehicleType.id" class="bmd-label-floating">Typ Pojazdu:</label>
				<form:select path="vehicleType.id" cssClass="form-control" cssErrorClass="form-control is-invalid">
					<form:option value="-1">--wybierz typ pojazdu--</form:option>
					<form:options items="${vehicleTypes}" itemLabel="name" itemValue="id"/>
				</form:select>
				<form:errors path="vehicleType.id" cssClass="error text-danger" element="div"/>
			</div>

			<div class="form-group">
				<label class="bmd-label-floating">Wyposażenie pojazdu:</label>
				<form:checkboxes path="accessories" element="div class='checkbox' style='left:25px;'" items="${accessoryList}" itemLabel="name" itemValue="id"/>
				<form:errors path="accessories" cssClass="error text-danger" element="div"></form:errors>
			</div>

			<button type="submit" class="btn btn-success" >Wyślij</button>
		</form:form>


</body>
</html>

</div>

<jsp:include page="shared/footer.jsp"/>
