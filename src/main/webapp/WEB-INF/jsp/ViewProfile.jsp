<%@ page language="java" contentType="text/html; charset=ISO-8859-1" isELIgnored="false" 
    pageEncoding="ISO-8859-1"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
  <head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>Account details</title>
  </head>
  <body link="#004d00" alink="#00b300" vlink="#004d00">

    <form:form method="POST" action="updateDetails" modelAttribute="user">
      <table><td>
        <form:label path="location">Location</form:label>
        <form:input path="location" size="15" value="${user.location}" />
        <form:label path="email">e-mail</form:label>
        <form:input path="email" size="25" value="${user.email}" />
        <input type="submit" value="Update"/>
      </td></table>
    </form:form>
    <form:form method="POST" action="updatePassword" modelAttribute="user">
      <table><td>
        <form:label path="hashedPass">new password</form:label>
        <form:input path="hashedPass" size="15" value="" type="password" />
        <input type="submit" value="Update password"/>
      </td></table>
    </form:form>
    
    <a href="/Guestbook/Home">back</a>
    
  </body>
</html>
