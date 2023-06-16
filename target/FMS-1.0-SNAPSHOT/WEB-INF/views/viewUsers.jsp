<%-- 
    Document   : viewUsers
    Created on : May 26, 2023, 11:14:22 PM
    Author     : Kavishika
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <h1>All users </h1>
        
        <div align="center">
        <table border="1" cellpadding="5">
            <caption><h2>List of Books</h2></caption>
            <tr>
                <th>username</th>
                <th>full Name</th>
                <th>password</th>
                <th>email</th>
            </tr>
            <c:forEach var="users" items="${userList}">
                <tr>
                    <td><c:out value="${users.username}" /></td>
                    <td><c:out value="${users.fullName}" /></td>
                    <td><c:out value="${users.password}" /></td>
                    <td><c:out value="${users.email}" /></td>
                </tr>
            </c:forEach>
            
        </table>
    </div>
    </body>
</html>
