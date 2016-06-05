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

    Log in
    <form:form method="POST" action="logIn" modelAttribute="user">
      <table><td>
        <form:label path="name">Name</form:label>
        <form:input path="name" size="15" />
        <form:label path="hashedPass">Password</form:label>
        <form:input type="password" path="hashedPass" size="15" />
        <input type="submit" value="Sign in"/>
      </td></table>
    </form:form>

    Register
    <form:form method="POST" action="addUser" modelAttribute="user">
      <table><td>
        <form:label path="name">Name</form:label>
        <form:input path="name" size="15" />
        <form:label path="hashedPass">Password</form:label>
        <form:input type="password" path="hashedPass" size="15" />
        <input type="submit" value="Register"/>
        </td></table>
    </form:form>
    
  </body>
</html>