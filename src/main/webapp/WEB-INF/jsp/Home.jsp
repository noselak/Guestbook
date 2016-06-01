<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>Guestbook</title>
</head>
<body>
<form:form method="GET" action="/Guestbook/addEntry">
<input type="submit" value="add test entry"/>
</form:form>

<form:form method="GET" action="/Guestbook/showBean">
<input type="submit" value="bean"/>
</form:form>

<c:forEach var="entry" items="${Entries}" varStatus="status">
                <tr>
                    <td>${entry.id}</td>
                    <td>${entry.name}</td>
                    <td>${entry.message}</td>                          
                </tr>
</c:forEach>   
</body>
</html>