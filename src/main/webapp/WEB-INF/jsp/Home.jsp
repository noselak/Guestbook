<%@ page language="java" contentType="text/html; charset=ISO-8859-1" isELIgnored="false" 
    pageEncoding="ISO-8859-1"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
  <title>Guestbook</title>
</head>
<body>
  
  <form:form method="POST" action="logout" modelAttribute="user">
  <table>
    <td>You are logged as ${nameSession} <input type="submit" value="Logout"/> </td>
  </table>
  </form:form>
  
  <form:form method="POST" action="/Guestbook/addEntry">
  <table>
    <!--
   <tr>
       <td><form:label path="name">Name</form:label></td>
       <td><form:input path="name" /></td>
   </tr>
    -->
   <tr>
       <!--<td><form:label path="message">Message</form:label></td>-->
       <td><form:input path="message" size="50" /></td>
       <td><input type="submit" value="Submit"/></td>
   </tr>
   <!--<tr>
       <td colspan="2">
           <input type="submit" value="Submit"/>
       </td>
   </tr>-->
  </table>  
</form:form>
  
<table>
  <c:forEach var="entry" items="${Entries}" varStatus="status">
    <tr>
      <td><font color="green">${entry.name}:</font></td>
      <td>${entry.message}</td>                          
    </tr>
  </c:forEach>
</table>
  
</body>
</html>