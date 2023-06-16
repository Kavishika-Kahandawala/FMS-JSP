<%-- 
    Document   : registration
    Created on : May 24, 2023, 4:23:41 PM
    Author     : Kavishika
--%>
<%
    if (session.getAttribute("username") == null) {
        response.sendRedirect("login");
%>
<%
} else {
%>
<%
    }
%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%--<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>--%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>FMS | Registration</title>
    </head>
    <body>
        <form name="contactform" id="contactform" action="user/edit" style="padding-bottom: 50px;" method="post">
          <div class="row align-items-center mx-auto" style="padding: 40px 0px 50px;">

            <h1>Register</h1>

          </div>
          <div class="form-row"></div>
          <div class="fields">
            <div class="form-row">
              <div class="form-group col-md-6">
                  <label for="username">Username</label>
                <input type="text" class="form-control" name="username" id="username" placeholder="Enter a username" />
              </div>
              <div class="form-group col-md-6">
                  <label for="fullName">Full Name</label>
                <input type="text" class="form-control" name="fullName" id="fullName" placeholder="Enter a username" />
              </div>
              <div class="form-group col-md-6">
                  <label for="password">Password</label>
                <input type="password" class="form-control" name="password" id="password" placeholder="Enter password" />
              </div>
              <div class="form-group col-md-6">
                  <label for="re-password">Re-enter Password</label>
                <input type="password" class="form-control" name="re-password" id="password" placeholder="Re-enter password" />
              </div>
              <div class="form-group col-md-6">
                <label for="email">Email address</label>
                <input type="email" class="form-control" name="email" id="email"
                  placeholder="name@example.com" />
              </div>
            </div>
              
          <input type="submit" value="Register" class="primary" />
          &nbsp;
        </form>
    </body>
</html>
