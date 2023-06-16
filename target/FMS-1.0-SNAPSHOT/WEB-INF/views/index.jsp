<%-- 
    Document   : index
    Created on : May 24, 2023, 4:13:36 PM
    Author     : Kavishika
--%>
<%
    if (session.getAttribute("username") == null) {
%>
<jsp:include page="header.jsp"></jsp:include>
<%
} else {
%>
<jsp:include page="headerSign.jsp"></jsp:include>
<%
    }
%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Homepage</title>
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
        </style>
    </head>
    <body class="body bg-light">
        <div class="container-fluid  min-vh-100" style="background-color: #081624;">
            <h1>Welcome To FMS</h1>
            <p>Your Personal Finance Management Solution
            </p>
            <section id="about-me">
                <div class="jumbotron jumbotron-fluid p-3 p-md-5 text-white bg-dark" style="margin-bottom:0;">
                    <div class="col-md-12">
                        <h1 class="display-4 font-italic">A Topic<br>
                            <span class="text-muted">It'll blow your mind.</span>
                        </h1>
                        <p class="lead my-3">Multiple lines of text that form the lede, informing new readers quickly and efficiently
                            about what's most interesting in this post's contents. Multiple lines of text that form the lede, informing
                            new
                            readers quickly and efficiently
                            about what's most interesting in this post's contents. Multiple lines of text that form the lede, informing
                            new
                            readers quickly and efficiently
                            about what's most interesting in this post's contents. Multiple lines of text that form the lede, informing
                            new
                            readers quickly and efficiently
                            about what's most interesting in this post's contents.</p>
                        <p class="lead mb-0"><a href="#" class="text-white font-weight-bold">Continue reading...</a></p>
                    </div>

                </div>
            </section>
    </body>
</html>
