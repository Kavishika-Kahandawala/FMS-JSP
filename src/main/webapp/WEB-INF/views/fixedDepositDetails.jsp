<%-- 
    Document   : fixedDepositDetails
    Created on : May 24, 2023, 4:25:54 PM
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
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
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
            .go_button:link,.go_button:visited{
                background-color: #f44336;
                color: white;
                padding: 14px 25px;
                text-align: center;
                text-decoration: none;
                display: inline-block;
            }
            .go_button:hover, .go_button:active{
                background-color: red;
            }
            body{
                color:white;
            }
        </style>
    </head>
    <jsp:include page="headerSign.jsp"></jsp:include>
        <body class="body bg-light">
            <div class="container  min-vh-100" style="background-color: #081624;">
                <form name="contactform" id="contactform" action="account/fdwithdraw" style="padding-bottom: 50px;" method="post">
                    <div class="row align-items-center mx-auto" style="padding: 40px 0px 50px;">

                        <h1>Fixed Deposit Details</h1>

                    </div>
                    <div class="form-row">
                        <div class="form-group col-md-6">
                            <p>Account Number : <%=request.getParameter("id")%> </p>
                        <input type="hidden" class="form-control" name="acc" id="acc" value=<%=request.getParameter("id")%> />
                    </div>
                </div>
                <div class="form-row">
                    <div class="form-group col-md-6">
                        <p>Maturity Date : <%=request.getParameter("date")%> </p>
                    </div>
                </div>
                <div class="form-row">
                    <div class="form-group col-md-6">
                        <p>Interest Earned : <%=request.getParameter("bal")%> </p>
                    </div>
                </div>

                <input type="submit" value="Withdraw" class="btn btn-outline-success my-2 my-sm-0" />
            </form>
        </div>
    </body>
</html>

