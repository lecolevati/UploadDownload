<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>DOWNLOAD</title>
</head>
<body>
	<div align="center">
		<jsp:include page="menu.jsp" />
		<c:if test="${not empty lista }">
		<table border="1">
			<thead>
				<tr>
					<th>Nome Arquivo</th>
					<th>Download</th>
				</tr>
			</thead>
			<tbody>
				<c:forEach items="${lista }" var="arquivo">
				<tr>
					<td><c:out value="${arquivo }" /></td>
					<td align="center"><a href="${pageContext.request.contextPath}/download?arquivo=${arquivo }">LINK</a></td>
				</tr>
				</c:forEach>
			</tbody>
		</table>
		</c:if>
	</div>
</body>
</html>