/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.master.controller;

import com.master.controller.model.LoanAccModel;
import com.master.controller.model.dateTimeGen;
import com.master.databaseLayer.ConnectionPool;
import com.master.databaseLayer.DbUtilities;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.time.YearMonth;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;
import org.springframework.security.crypto.bcrypt.BCrypt;

/**
 *
 * @author Kavishika
 */
public class LoanAccDao {

    dateTimeGen dateTime = new dateTimeGen();

    public int insertLoanAcc(LoanAccModel newLoanAccModel) {
        ConnectionPool pool = ConnectionPool.getInstance();
        Connection connection = pool.getConnection();
        PreparedStatement ps = null;
        int generatedkey = 0;

        System.out.println("Res:        Came to insert Saving Acc -> SavingDao");
        String query
                = "INSERT INTO loan_acc (username, account_id, value, created_date, created_time, loan_time,loan_interest_rate, status ) "
                + "VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
        System.out.println("Res:        Insert Query done -> UsersDao");
        try {

            connection.setAutoCommit(false); //transaction block start
            ps = connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
            ps.setString(1, newLoanAccModel.getUsername());
            ps.setString(2, newLoanAccModel.getAccount_id());
            ps.setFloat(3, newLoanAccModel.getValue());
            ps.setString(4, newLoanAccModel.getCreatedDate());
            ps.setString(5, newLoanAccModel.getCreatedTime());
            ps.setInt(6, newLoanAccModel.getLoanTime());
            ps.setDouble(7, newLoanAccModel.getLoanInterstRate());
            ps.setInt(8, 0);

            ps.executeUpdate();
            ResultSet rs = ps.getGeneratedKeys();
            System.out.println("Res:        Resultset Done -> UsersDao");
            if (rs.next()) {
                generatedkey = rs.getInt(1);
            }

            if (generatedkey > 0) {

            } else {
                connection.rollback();
                return 0;
            }
            connection.commit(); //transaction block end

            System.out.println("Res:        Guess Done SQLInsert -> SavingDao");
            return generatedkey;

        } catch (SQLException e) {
            System.out.println(e);
            return 0;
        } finally {
            DbUtilities.closePreparedStatement(ps);
            pool.freeConnection(connection);
            System.out.println("Res:        Freed Connection -> UsersDao");
        }
    }

    public String getInterestRate(int time) {
        ConnectionPool pool = ConnectionPool.getInstance();
        Connection connection = pool.getConnection();
        PreparedStatement ps = null;
        String rsRate = null;

        String query
                = "SELECT rate FROM rates WHERE account_type = ? AND time= ?";
        System.out.println("select sql");
        try {
            ps = connection.prepareStatement(query);
            ps.setString(1, "loan");
            ps.setInt(2, time);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                rsRate = rs.getString("rate");
            }
        } catch (SQLException e) {
            System.out.println(e);
        } finally {
            DbUtilities.closePreparedStatement(ps);
            pool.freeConnection(connection);
        }
        return rsRate;
    }

    public boolean setLoanTime(String username, int fdTime, String accId) {

        ConnectionPool pool = ConnectionPool.getInstance();
        Connection connection = pool.getConnection();
        PreparedStatement ps = null;

        String rate = getInterestRate(fdTime);

        String query
                = "UPDATE loan_acc SET loan_time = ?, loan_interest_rate = ?, status = ? WHERE status = '0' AND username = ? AND account_id = ? ";
        try {
            connection.setAutoCommit(false); //transaction block start
            ps = connection.prepareStatement(query);
            ps.setInt(1, fdTime);
            ps.setString(2, rate);
            ps.setInt(3, 1);
            ps.setString(4, username);
            ps.setString(5, accId);

            System.out.println(accId);
            System.out.println("usernmae is " + username);

            System.out.println("updated loan_acc with months");
            ps.executeUpdate();
            connection.commit();// transaction block ends here
            return true;

        } catch (SQLException e) {
            System.out.println(e);
            return false;
        } finally {
            DbUtilities.closePreparedStatement(ps);
            pool.freeConnection(connection);

        }

    }

    public boolean checkLoanTimeAvail(String username, String accId) {
        ConnectionPool pool = ConnectionPool.getInstance();
        Connection connection = pool.getConnection();
        PreparedStatement ps = null;
        boolean status = false;

        String query
                = "SELECT id FROM loan_acc WHERE status = 1 AND username = ? AND account_id = ? ";
        try {
            ps = connection.prepareStatement(query);
            ps.setString(1, username);
            ps.setString(2, accId);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                status = true;
            }

        } catch (SQLException e) {
            System.out.println(e);
        } finally {
            DbUtilities.closePreparedStatement(ps);
            pool.freeConnection(connection);
        }
        return status;
    }

    public String getInterestRate(String time) {
        ConnectionPool pool = ConnectionPool.getInstance();
        Connection connection = pool.getConnection();
        PreparedStatement ps = null;
        String rsRate = null;

        String query
                = "SELECT rate FROM rates WHERE account_type = 'loan' AND loan_time= ? ";
        System.out.println("select sql");
        try {
            ps = connection.prepareStatement(query);
            ps.setString(1, time);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                rsRate = rs.getString("rate");
            }
        } catch (SQLException e) {
            System.out.println(e);
        } finally {
            DbUtilities.closePreparedStatement(ps);
            pool.freeConnection(connection);
        }
        return rsRate;
    }

    public float getMonthPaidAmount(String username, String accId) {

        String dateNow = dateTime.getDate();

        ConnectionPool pool = ConnectionPool.getInstance();
        Connection connection = pool.getConnection();
        PreparedStatement ps = null;

        float monthPaid = 0;
        String query
                = "SELECT value FROM loan_repayment WHERE username = ? AND account_id= ? ";
        try {
            ps = connection.prepareStatement(query);
            ps.setString(1, username);
            ps.setString(2, accId);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                float rsValue = Float.parseFloat((rs.getString("value")));
                monthPaid += rsValue;
            }
        } catch (SQLException e) {
            System.out.println(e);
        } finally {
            DbUtilities.closePreparedStatement(ps);
            pool.freeConnection(connection);
        }
        return monthPaid;
    }

    public float calcMonthlyDebt(float LoanAmount, float monthPaid, float interst, int time) {

        float pay = LoanAmount * (interst / 100) / 12;
        float monthRaw = LoanAmount / time;
        float fullMonth = monthRaw+pay;
        float remPay = fullMonth - monthPaid;
        System.out.println("fullMonth "+fullMonth);
        return remPay;
    }

    public List<LoanAccModel> viewLoanAcc(String username) {
        List<LoanAccModel> LoanAcc = new ArrayList<>();
        ConnectionPool pool = ConnectionPool.getInstance();
        Connection connection = pool.getConnection();
        PreparedStatement ps = null;
//        System.out.println("hey rate "+rate);

        String dateNow = dateTime.getDate();
        long monthDiff = 0;
        String query
                = "SELECT account_id, value,created_date, loan_time, loan_interest_rate FROM loan_acc WHERE username = ?";
        try {
            ps = connection.prepareStatement(query);
            ps.setString(1, username);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {

                String rsAccoutId = rs.getString("account_id");
                float rsValue = Float.parseFloat((rs.getString("value")));
                int rsLoanTime = Integer.parseInt(rs.getString("loan_time"));
                float rsLoanInt = Float.parseFloat(rs.getString("loan_interest_rate"));
                float finalVal = 0;

                String rsDate = rs.getString("created_date");

                monthDiff = ChronoUnit.MONTHS.between(YearMonth.from(LocalDate.parse(rsDate)), YearMonth.from(LocalDate.parse(dateNow)));
                int intMonthDiff = Math.toIntExact(monthDiff);
                System.out.println("month Diff "+intMonthDiff );

                if (intMonthDiff <= rsLoanTime) {
                    float monthPaidAmount = getMonthPaidAmount(username, rsAccoutId);
                    float calcMonthlyPay = calcMonthlyDebt(rsValue, monthPaidAmount, rsLoanInt,rsLoanTime);
                    finalVal = calcMonthlyPay;
                }
                LoanAcc.add(new LoanAccModel(0, rsLoanTime, username, rsAccoutId, "loan", "date", "time", finalVal));

            }
        } catch (SQLException e) {
            System.out.println(e);
        } finally {
            DbUtilities.closePreparedStatement(ps);
            pool.freeConnection(connection);
        }
        return LoanAcc;
    }
    
    public boolean checkAccAvail(String username, String accId) {
        ConnectionPool pool = ConnectionPool.getInstance();
        Connection connection = pool.getConnection();
        PreparedStatement ps = null;
        boolean status = false;

        String query
                = "SELECT id FROM loan_acc WHERE status = 1 AND username = ? AND account_id = ? ";
        try {
            ps = connection.prepareStatement(query);
            ps.setString(1, username);
            ps.setString(2, accId);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                status = true;
            }

        } catch (SQLException e) {
            System.out.println(e);
        } finally {
            DbUtilities.closePreparedStatement(ps);
            pool.freeConnection(connection);
        }
        return status;
    }
    
    public int insertTransaction(String username, String accId, float amount ) {
        ConnectionPool pool = ConnectionPool.getInstance();
        Connection connection = pool.getConnection();
        PreparedStatement ps = null;
        int generatedkey = 0;
        
        
        String timenow = dateTime.getTime();
        String dateNow = dateTime.getDate();

        String query
                = "INSERT INTO loan_repayment (username, account_id, value, date, time ) "
                + "VALUES (?, ?, ?, ?, ?)";
        System.out.println("Res:        Insert Query done -> UsersDao");
        try {

            connection.setAutoCommit(false); //transaction block start
            ps = connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
            ps.setString(1, username);
            ps.setString(2, accId);
            ps.setFloat(3, amount);
            ps.setString(4, dateNow);
            ps.setString(5, timenow);

            ps.executeUpdate();
            ResultSet rs = ps.getGeneratedKeys();
            if (rs.next()) {
                generatedkey = rs.getInt(1);
            }

            if (generatedkey > 0) {

            } else {
                connection.rollback();
                return 0;
            }
            connection.commit(); //transaction block end

            return generatedkey;

        } catch (SQLException e) {
            System.out.println(e);
            return 0;
        } finally {
            DbUtilities.closePreparedStatement(ps);
            pool.freeConnection(connection);
        }
    }

}
