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
    your name please
    <form:form method="POST" action="addName" modelAttribute="entry">
      <table><td><tr>
       <!-- <form:label path="name">Name</form:label> -->
       <form:input path="name" size="30" />
       <input type="submit" value="Submit"/>
      </table></tr></td>
    </form:form>
    Register
    <form:form method="POST" action="addUser" modelAttribute="user">
      <table><td>
        <form:label path="name">Name</form:label>
        <form:input path="name" size="15" />
        <form:label path="hashedPass">Password</form:label>
        <form:input type="password" path="hashedPass" size="15" />
        <input type="submit" value="Submit"/>
        </td></table>
    </form:form>
  </body>
</html>