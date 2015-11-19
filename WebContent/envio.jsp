<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>UPLOAD</title>
</head>
<body>
	<div align="center">
		<jsp:useBean id="now" class="java.util.Date" />
		<jsp:include page="menu.jsp" />
		<br />
		<form action="upload" method="post" enctype="multipart/form-data">
			<table>
				<tr>
					<td>Data:</td>
					<td><input type="text" name="data" size="8"
						readonly="readonly"
						value="<fmt:formatDate type="date" value="${now}" />" /></td>
				</tr>
			</table>
			<input type="file" id="file" name="file1" multiple="muliple" required />
			<input type="submit" /> <br> <br>
			<c:if test="${not empty erro }">
				<c:forEach items="${erro }" var="e">
					<c:out value="${e }" />
					<br />
				</c:forEach>
			</c:if>
			<c:if test="${not empty message }">
				<c:out value="Arquivos Carregados : " />
				<br />
				<c:forEach items="${message }" var="msg">
					<c:out value="${msg }" />
					<br />
				</c:forEach>
			</c:if>
		</form>
	</div>
</body>
</html>