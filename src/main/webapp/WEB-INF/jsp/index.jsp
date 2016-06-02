<%@ page language="java" contentType="text/html; charset=ISO-8859-1" isELIgnored="false" 
    pageEncoding="ISO-8859-1"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
  <head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>JSP Page</title>
  </head>
  <body>
    <h1>your name:</h1>
  <form:form method="POST" action="addName">
    <table><td><tr>
     <form:label path="name">Name</form:label>
     <form:input path="name" size="60" />
     <input type="submit" value="Submit"/>
    </table></tr></td>
  </form:form>
  </body>
</html>
