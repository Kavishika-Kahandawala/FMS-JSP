<%-- 
    Document   : registration
    Created on : May 24, 2023, 4:23:41 PM
    Author     : Kavishika
--%>
<%
    if (session.getAttribute("username") == null) {
%>
<jsp:include page="header.jsp"></jsp:include>
<%
} else {
    response.sendRedirect("true");
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
        <!-- Bootstrap -->
        <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.6.2/dist/css/bootstrap.min.css"
              integrity="sha384-xOolHFLEh07PJGoPkLv1IbcEPTNtaed2xpHsD9ESMhqIYd0nLMwNLD69Npy4HI+N" crossorigin="anonymous">
        <script src="https://cdn.jsdelivr.net/npm/jquery@3.5.1/dist/jquery.slim.min.js"
                integrity="sha384-DfXdz2htPH0lsSSs5nCTpuj/zy4C+OGpamoFVy38MVBnE+IbbVYUew+OrCXaRkfj"
        crossorigin="anonymous"></script>
        <script src="https://cdn.jsdelivr.net/npm/popper.js@1.16.1/dist/umd/popper.min.js"
                integrity="sha384-9/reFTGAW83EW2RDu2S0VKaIzap3H66lZH81PoYlFhbGU+6BZp6G7niu735Sk7lN"
        crossorigin="anonymous"></script>
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@4.6.2/dist/js/bootstrap.min.js"
                integrity="sha384-+sLIOodYLS7CIrQpBjl+C7nPvqq+FbNUBDunl/OZv93DB7Ln/533i8e/mZXLi/P+"
        crossorigin="anonymous"></script>
        <style>
            body{
                color:white;
            }
            .error-msg{
                color: #f94449;
            }
        </style>
    </head>
        <body class="body bg-light">
            <div class="container  min-vh-100" style="background-color: #081624;">
                <h1 style="padding:20px 0px">View Account Details</h1>


                <form name="contactform" id="contactform" action="user/register" style="padding-bottom: 10px 0px;" method="post">
                    <div class="row align-items-center mx-auto" style="padding: 30px 0px;">



                    </div>
                    <div class="fields">
                        <div class="form-row">
                            <div class="form-group col-md-6">
                                <label for="username">Username</label>
                                <input type="text" class="form-control" name="username" id="username" placeholder="Enter a username" />
                            </div>
                        </div>
                        <div class="form-row">
                            <div class="form-group col-md-6">
                                <label for="fullName">Full Name</label>
                                <input type="text" class="form-control" name="fullName" id="fullName" placeholder="Enter a username" />
                            </div>
                        </div>
                        <div class="form-row">
                            <div class="form-group col-md-6">
                                <label for="password">Password</label>
                                <input type="password" class="form-control" name="password" id="password" placeholder="Enter password" />
                            </div>
                        </div>
                        <div class="form-row">
                            <div class="form-group col-md-6">
                                <label for="repassword">Re-enter Password</label>
                                <input type="password" class="form-control" name="repassword" id="repassword" placeholder="Re-enter password" />
                            </div>
                        </div>
                        <div class="form-row">
                            <div class="form-group col-md-6">
                                <label for="email">Email address</label>
                                <input type="email" class="form-control" name="email" id="email"
                                       placeholder="name@example.com" />
                            </div>
                        </div>
                        <div class="error-msg">${param.error}</div> <br>

                    <input type="submit" class="btn btn-outline-success my-2 my-sm-0" value="Register"/>
                    <!--<input type="submit" value="Login" class="btn btn-outline-success my-2 my-sm-0" />-->
                    &nbsp;
            </form>
        </div>
    </body>
</html>
