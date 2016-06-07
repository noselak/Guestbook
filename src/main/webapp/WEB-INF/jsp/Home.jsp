<%@ page language="java" contentType="text/html; charset=ISO-8859-1" isELIgnored="false" 
    pageEncoding="ISO-8859-1"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
  <title>Guestbook</title>
  <style type="text/css"><!-- A{text-decoration:none} --></style>
</head>
<body link="#004d00" alink="#00b300" vlink="#004d00">
  
  <p align="right"><table>
    logged as <font color="green">${nameSession} </font> 
    <form:form method="POST" action="logout" modelAttribute="user">
    <input type="submit" value="Logout"/> 
    </form:form>
    <form:form method="GET" action="ViewProfile" modelAttribute="user">
    <input type="submit" value="Profile"/> 
    </form:form>
  </table></p>
  
  <table width="100%"><tr><p align="center">
    <form:form method="POST" action="/Guestbook/addEntry">
      <form:label path="message"></form:label>
      <form:input path="message" size="120" />
      <!--<input type="submit" value="Submit"/>-->
    </form:form>
  </p></tr></table>

<table width="100%">
<tr>
<td width="95%">   
  <table>
    <c:forEach var="entry" items="${Entries}" varStatus="status">
      <tr>
        <td><p align="center">
          <font color="green" size="4">${entry.name}</font>
          <font size="1">
          <br>${entry.location}
          <br>${entry.email}
          </font>
        </p></td>
        <td>${entry.message}</td>                          
      </tr>
    </c:forEach>
  </table>
</td>
<td style="width: 5%; vertical-align: top;">
<p align="center">registered users</p>
	<c:forEach var="user" items="${Users}">
		${user.name}<br>
	</c:forEach>
</td>
</tr>
</table>
	  

</body>
</html>
