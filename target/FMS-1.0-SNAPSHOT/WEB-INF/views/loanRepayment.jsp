<%-- 
    Document   : loanRepayment
    Created on : May 24, 2023, 4:25:35 PM
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
<!DOCTYPE html>
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
            
            body{
                color:white;
            }
        </style>
    </head>
    <jsp:include page="headerSign.jsp"></jsp:include>
        <body class="body bg-light">
            <div class="container  min-vh-100" style="background-color: #081624;">


                <form name="contactform" id="contactform" action="account/loanrepayment" style="padding-bottom: 50px;" method="post">
                    <div class="row align-items-center mx-auto" style="padding: 40px 0px 50px;">

                        <h1 style="padding:30px 0px">Loan Payment</h1>

                    </div>
                    <div class="fields">
                        <div class="form-row">
                            <div class="form-group col-md-6">
                                <p>Account Number : <%=request.getParameter("id")%> </p>
                            <input type="hidden" class="form-control" name="acc" id="acc" value=<%=request.getParameter("id")%> />
                        </div>
                    </div>
                        <div class="form-row">
                    <div class="form-group col-md-6">
                        <p>Monthly Amount : <%=request.getParameter("bal")%> </p>
                        <input type="hidden" class="form-control" name="acc" id="acc" value=<%=request.getParameter("id")%> />
                    </div>
                        </div>
                    <div class="form-row">
                    <div class="form-group col-md-6">
                        <label for="amount">Amount</label>
                        <input type="text" class="form-control" name="amount" id="amount" placeholder="Enter a Value" />
                    </div>
                    </div>


                    <input type="submit" value="submit" class="btn btn-outline-success my-2 my-sm-0" />
                    &nbsp;
            </form>
        </div>
    </body>
</html>