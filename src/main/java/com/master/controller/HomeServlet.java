/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package com.master.controller;

import com.master.controller.model.FDAccModel;
import com.master.controller.model.LoanAccModel;
import com.master.controller.model.SavingAccModel;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import com.master.controller.model.UserModel;
import com.master.controller.model.dateTimeGen;
import jakarta.servlet.http.HttpSession;
import java.io.PrintWriter;
import java.security.SecureRandom;
import java.util.Random;

/**
 *
 * @author Kavishika
 */
public class HomeServlet extends HttpServlet {

//    private UsersDao UsersDao;
    UsersDao usersDao = new UsersDao();
    SavingAccDao savingAccDao = new SavingAccDao();
    FdAccDao fdAccDao = new FdAccDao();
    LoanAccDao loanAccDao = new LoanAccDao();
    dateTimeGen dateTime = new dateTimeGen();
    String sessionUsername = null;

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");

        String uri = request.getRequestURI().toLowerCase();

        String HomeUrl = "WEB-INF/views/index.jsp";

//        String username = "523";
        switch (uri) {

//                Login Page
            case "/fms/login":
                request.getRequestDispatcher("WEB-INF/views/login.jsp").forward(request, response);
                break;
//                Logout Page
            case "/fms/logout":
                request.getSession(false).invalidate();
                request.getRequestDispatcher("WEB-INF/views/index.jsp").forward(request, response);
                break;

//                Balance Inquiry Page
            case "/fms/balanceinquiry":

                request.getRequestDispatcher("WEB-INF/views/balanceInquiry.jsp").forward(request, response);
                break;

//            Registration Page
            case "/fms/registration":
                request.getRequestDispatcher("WEB-INF/views/registration.jsp").forward(request, response);
                break;

//            Create Account Page
            case "/fms/createaccount":
                request.getRequestDispatcher("WEB-INF/views/createAccount.jsp").forward(request, response);
                break;

//            Fixed Deposit Details Page
            case "/fms/fixeddepositdetails":
                request.getRequestDispatcher("WEB-INF/views/fixedDepositDetails.jsp").forward(request, response);
                break;

//            Loan Repayment Page
            case "/fms/loanrepayment":
                request.getRequestDispatcher("WEB-INF/views/loanRepayment.jsp").forward(request, response);
                break;

//            Transaction Operations Page
            case "/fms/transactionoperations":
                request.getRequestDispatcher("WEB-INF/views/transactionOperations.jsp").forward(request, response);
                break;

//            Redireted  Page
            case "/fms/redirect":
                request.getRequestDispatcher("WEB-INF/views/userBankAccountsCat.jsp").forward(request, response);
                break;

//            True  Page
            case "/fms/true":
                System.out.println("true");
                request.getRequestDispatcher("WEB-INF/views/userBankAccountsCat.jsp").forward(request, response);
                break;

//            False Operations Page
            case "/fms/false":
                System.out.println("false");
                request.getRequestDispatcher("WEB-INF/views/false.jsp").forward(request, response);
                break;

//            Edit User Page
            case "/fms/update":
                request.getRequestDispatcher("WEB-INF/views/edituser.jsp").forward(request, response);
                break;

//            Edit User Page
            case "/fms/user_profile":
                request.getRequestDispatcher("WEB-INF/views/userAccount.jsp").forward(request, response);
                break;

//            View User Page
            case "/fms/view":
                List<UserModel> userList = usersDao.viewUsers();
                System.out.println("list: " + userList.get(2).getPassword());
                request.setAttribute("userList", userList);
                request.getRequestDispatcher("WEB-INF/views/viewUsers.jsp").forward(request, response);
                break;
//            View Bank accounts categories
            case "/fms/accountscat":
                request.getRequestDispatcher("WEB-INF/views/userBankAccountsCat.jsp").forward(request, response);
                break;
//            View Saving accounts
            case "/fms/viewsavings":
                sessionUsername = (String) request.getSession(false).getAttribute("username");
                List<SavingAccModel> savinList = savingAccDao.viewAccounts(sessionUsername);
                System.out.println("list: " + savinList);
                request.setAttribute("savinList", savinList);
                request.getRequestDispatcher("WEB-INF/views/viewsavings.jsp").forward(request, response);
                break;
//            View Saving Account individual
            case "/fms/transsavingacc":
                request.getRequestDispatcher("WEB-INF/views/viewsavings.jsp").forward(request, response);
                break;
//            View Loan accounts
            case "/fms/viewloan":

                sessionUsername = (String) request.getSession(false).getAttribute("username");
                List<LoanAccModel> loanList = loanAccDao.viewLoanAcc(sessionUsername);
                System.out.println("listddd: " + loanList);
                request.setAttribute("loanList", loanList);
                request.getRequestDispatcher("WEB-INF/views/viewloan.jsp").forward(request, response);
                break;

//            View Fd accounts
            case "/fms/viewfd":
                sessionUsername = (String) request.getSession(false).getAttribute("username");
                List<FDAccModel> fdList = fdAccDao.viewFdAcc(sessionUsername);
                System.out.println("fdList: " + fdList);
                request.setAttribute("fdList", fdList);
                request.getRequestDispatcher("WEB-INF/views/viewfd.jsp").forward(request, response);
                break;

//            Set FD Time
            case "/fms/setfdtime":
//                request.setAttribute("userList", userList);
                request.getRequestDispatcher("WEB-INF/views/setFdTime.jsp").forward(request, response);
                break;
            case "/fms/setloantime":
//                request.setAttribute("userList", userList);
                request.getRequestDispatcher("WEB-INF/views/setLoanTime.jsp").forward(request, response);
                break;

//            Homepage
            case "/fms/home":
                request.getRequestDispatcher(HomeUrl).forward(request, response);
            case "/fms/":
                request.getRequestDispatcher(HomeUrl).forward(request, response);
                break;

//                404
            default:
//                request.getRequestDispatcher("WEB-INF/views/404.jsp").forward(request, response);
        }

    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
//        String action = request.getServletPath();

//       
    }

    private void insertUser(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, IOException {

        String username = request.getParameter("username");
        String fullName = request.getParameter("fullName");
        String password = request.getParameter("password");
        String repassword = request.getParameter("repassword");
        String email = request.getParameter("email");

        int validateStatus = usersDao.validateReg(username, password, repassword);
        String errorPass = "Sorry+passwords+does+not+match";
        String errorUsernameLen = "Sorry+username+should+be+within+25+characters";
        String errorUsernameExists = "Sorry+username+already+exists";
        switch (validateStatus) {
            case 0:
                UserModel newUser = new UserModel(username, fullName, password, email);
                usersDao.insertUser(newUser);
                response.sendRedirect("../redirect");
                break;
            case 1:
                response.sendRedirect("../registration?error=" + errorPass);
                break;
            case 2:
                response.sendRedirect("../registration?error=" + errorUsernameLen);
                break;
            case 3:
                response.sendRedirect("../registration?error=" + errorUsernameExists);
                break;
            default:
                response.sendRedirect("../false");
        }
    }

    private void loginUser(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, IOException, ServletException {

        String username = request.getParameter("username");
        String password = request.getParameter("password");
        
        String errorMsg= "Sorry+wrong+credentials";

        boolean ExistUser = usersDao.loginUser(username, password);
        System.out.println("status " + ExistUser);
        if (ExistUser) {

            System.out.println("User Exists");
            HttpSession session = request.getSession(true);
            session.setAttribute("username", username);
            response.sendRedirect("../true");
        } else {
            response.sendRedirect("../login?error=" + errorMsg);
        }

    }

    private void updateUser(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, IOException, ServletException {

        String username = request.getParameter("username");
        String fullName = request.getParameter("fullName");
        String password = request.getParameter("password");
        String email = request.getParameter("email");

        UserModel newUser = new UserModel(username, fullName, password, email);

        boolean UpdateStatus = usersDao.updateUser(newUser);
//        System.out.println("ffffffffffff");
        if (UpdateStatus) {
            response.sendRedirect("../true");

        } else {
            response.sendRedirect("../false");
        }

    }

    private void listUsers(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, IOException {
        request.setAttribute("welcome", "welcome duuuuude!");
    }

    //Account interest Rates
    float SavingIntsRate = (float) 1.5;

    private void createAcc(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, IOException, ServletException {

        String accountType = request.getParameter("accountType");
        int initDeposit = Integer.parseInt(request.getParameter("initDeposit"));
        System.out.println("Res: Account type is: " + accountType);
        System.out.println("Res: initDeposit is: " + initDeposit);
        String sessionUsername = (String) request.getSession(false).getAttribute("username");

//Account Number Creation
        Random rd = new SecureRandom();
        String AccNumber = Integer.toString(rd.nextInt(99999999));

        String timenow = dateTime.getTime();
        String dateNow = dateTime.getDate();

        switch (accountType) {

//                Savings Acc
            case "savings":
                SavingAccModel newSavAccModel = new SavingAccModel(SavingIntsRate, sessionUsername, AccNumber, accountType, dateNow, timenow, initDeposit);
                savingAccDao.insertSavingAcc(newSavAccModel);
                response.sendRedirect("../redirect");
                break;

//                FD Acc
            case "fd":
                FDAccModel newFdAccModel = new FDAccModel(0, 0, sessionUsername, AccNumber, accountType, dateNow, timenow, initDeposit);
                fdAccDao.insertFdAcc(newFdAccModel);
                response.sendRedirect("../setfdtime?id=" + AccNumber);
                break;

//                Loan Acc
            case "loan":
                LoanAccModel newLoanAccModel = new LoanAccModel(99, 99, sessionUsername, AccNumber, accountType, dateNow, timenow, initDeposit);
                loanAccDao.insertLoanAcc(newLoanAccModel);
                response.sendRedirect("../setloantime?id=" + AccNumber);
                break;

            default:
                System.out.println("Error om Acc type fetch");
        }

    }

    private void setFdTime(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, IOException {

        String sessionUsername = (String) request.getSession(false).getAttribute("username");

        //[TODO] - Delete this line
//        sessionUsername = "523";
        int depositTime = Integer.parseInt(request.getParameter("depositTime"));
        String accId = request.getParameter("acc");
        boolean status = fdAccDao.checkFdTimeAvail(sessionUsername, accId);
        if (status == false) {
            fdAccDao.setFdTime(sessionUsername, depositTime, accId);
            response.sendRedirect("../redirect");
        } else {
            response.sendRedirect("../false");
        }

    }

    private void setLoanTime(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, IOException {

        String sessionUsername = (String) request.getSession(false).getAttribute("username");

        int depositTime = Integer.parseInt(request.getParameter("depositTime"));
        String accId = request.getParameter("acc");
        boolean status = loanAccDao.checkLoanTimeAvail(sessionUsername, accId);

        if (status == false) {
            loanAccDao.setLoanTime(sessionUsername, depositTime, accId);
            response.sendRedirect("../redirect");
        } else {
            response.sendRedirect("../false");
        }
    }

    private void initTrasaction(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, IOException {

        String sessionUsername = (String) request.getSession(false).getAttribute("username");

        int transType = Integer.parseInt(request.getParameter("transactionType"));
        System.out.println("trans type " + transType);
        String accId = request.getParameter("acc");
        float amount = Float.parseFloat(request.getParameter("amount"));
        String typeTr = "withdraw";
        if (transType == 2) {
            amount = -amount;
            typeTr = "deposit";
        }
        boolean accStatus = savingAccDao.checkAccAvail(sessionUsername, accId);

        if (accStatus == true) {
            float amountStatus = savingAccDao.checkBalAvail(sessionUsername, accId, amount);
            if (amountStatus != 0) {
                boolean updateStatus = savingAccDao.updateBal(sessionUsername, accId, amount, amountStatus);

                System.out.println("updated");
                if (updateStatus == true) {
                    savingAccDao.insertTransaction(sessionUsername, accId, typeTr, amount);
                    //Money withdrawed
                    response.sendRedirect("../redirect");
                } else {
                    //something went wrong
                    System.out.println("s");
                    response.sendRedirect("../false");
                }
            } else {
                //not enough money
                System.out.println("4");
                response.sendRedirect("../false");
            }
        } else {
        }
    }

    private void loanRepay(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, IOException {

        String sessionUsername = (String) request.getSession(false).getAttribute("username");

        String accId = request.getParameter("acc");
        float amount = Float.parseFloat(request.getParameter("amount"));

        boolean accStatus = loanAccDao.checkAccAvail(sessionUsername, accId);

        if (accStatus == true) {
            loanAccDao.insertTransaction(sessionUsername, accId, amount);
            response.sendRedirect("../redirect");
        }
    }

    private void withdrawFd(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, IOException {

        String sessionUsername = (String) request.getSession(false).getAttribute("username");

        String accId = request.getParameter("acc");

        String accStatus = fdAccDao.checkAccAvail(sessionUsername, accId);

        if (!accStatus.equals("false")) {
            fdAccDao.setWithdraw(sessionUsername, accId);
            fdAccDao.insertTransaction(sessionUsername, accId, Float.parseFloat(accStatus));
            response.sendRedirect("../redirect");
        } else {
            response.sendRedirect("../false");
        }
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
//        doGet(request, response);
        try {
            String action = request.getServletPath();

            switch (action) {
                case "/user/register":
                    insertUser(request, response);
                    break;
                case "/user/login":

                    loginUser(request, response);
                    break;
                case "/user/edit":
                    updateUser(request, response);
                    break;
                case "/account/create":
                    createAcc(request, response);
                    break;
                case "/account/setfdtime":
                    setFdTime(request, response);
                    break;
                case "/account/setloantime":
                    setLoanTime(request, response);
                    break;
                case "/account/transactioninit":
                    initTrasaction(request, response);
                    break;
                case "/account/loanrepayment":
                    loanRepay(request, response);
                    break;
                case "/account/fdwithdraw":
                    withdrawFd(request, response);
                    break;
                default:

                    System.out.println("Default post");
            }

        } catch (SQLException ex) {
            throw new ServletException(ex);
        }
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
