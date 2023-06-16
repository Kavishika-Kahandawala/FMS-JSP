/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.master.controller;

import com.master.controller.model.FDAccModel;
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
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;
import org.springframework.security.crypto.bcrypt.BCrypt;

/**
 *
 * @author Kavishika
 */
public class FdAccDao {

    dateTimeGen dateTime = new dateTimeGen();

    public int insertFdAcc(FDAccModel newFdAccModel) {
        ConnectionPool pool = ConnectionPool.getInstance();
        Connection connection = pool.getConnection();
        PreparedStatement ps = null;
        int generatedkey = 0;

        System.out.println("Res:        Came to insert Saving Acc -> SavingDao");
        String query
                = "INSERT INTO fd_acc (username, account_id, value, created_date, created_time, fd_time,fd_interest_rate, status ) "
                + "VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
        System.out.println("Res:        Insert Query done -> UsersDao");
        try {

            connection.setAutoCommit(false); //transaction block start
            ps = connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
            ps.setString(1, newFdAccModel.getUsername());
            ps.setString(2, newFdAccModel.getAccount_id());
            ps.setFloat(3, newFdAccModel.getValue());
            ps.setString(4, newFdAccModel.getCreatedDate());
            ps.setString(5, newFdAccModel.getCreatedTime());
            ps.setInt(6, newFdAccModel.getFdTime());
            ps.setDouble(7, newFdAccModel.getFdInterstRate());
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
            ps.setString(1, "fd");
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

    public boolean setFdTime(String username, int fdTime, String accId) {
        ConnectionPool pool = ConnectionPool.getInstance();
        Connection connection = pool.getConnection();
        PreparedStatement ps = null;

        String rate = getInterestRate(fdTime);

        String query
                = "UPDATE fd_acc SET fd_time = ?, fd_interest_rate = ?, status = ? WHERE status = '0' AND username = ? AND account_id = ? ";
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

            System.out.println("updated fd_acc with months");
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

    public boolean checkFdTimeAvail(String username, String accId) {
        ConnectionPool pool = ConnectionPool.getInstance();
        Connection connection = pool.getConnection();
        PreparedStatement ps = null;
        boolean status = false;

        String query
                = "SELECT id FROM fd_acc WHERE status = 1 AND username = ? AND account_id = ? ";
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

    public float calcMonthlyEarn(float fdAmount, float interest, int monthDiff) {

        float earnedInt = fdAmount * ((interest) / 100) * monthDiff / 12;

        return earnedInt;
    }

    public List<FDAccModel> viewFdAcc(String username) {
        List<FDAccModel> fdAcc = new ArrayList<>();
        ConnectionPool pool = ConnectionPool.getInstance();
        Connection connection = pool.getConnection();
        PreparedStatement ps = null;

        String dateNow = dateTime.getDate();
        long monthDiff = 0;
        String query
                = "SELECT account_id, value, created_date, fd_time, fd_interest_rate FROM fd_acc WHERE username = ?";
        try {
            ps = connection.prepareStatement(query);
            ps.setString(1, username);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {

                String rsAccoutId = rs.getString("account_id");
                float rsValue = Float.parseFloat((rs.getString("value")));
                int rsFdTime = Integer.parseInt(rs.getString("fd_time"));
                float rsFdInt = Float.parseFloat(rs.getString("fd_interest_rate"));
                String rsDate = rs.getString("created_date");

                float finalVal = 0;

                monthDiff = ChronoUnit.MONTHS.between(YearMonth.from(LocalDate.parse(rsDate)), YearMonth.from(LocalDate.parse(dateNow)));
                int intMonthDiff = Math.toIntExact(monthDiff);
                System.out.println("month Diff " + intMonthDiff);

                LocalDate maturityDate = (LocalDate.parse(rsDate)).plusMonths(rsFdTime);
                DateTimeFormatter formatter = DateTimeFormatter.ISO_DATE;
                String stringmDate = maturityDate.format(formatter);

                if (intMonthDiff <= rsFdTime) {

                    finalVal = calcMonthlyEarn(rsValue, rsFdInt, intMonthDiff);
                } else {
                    finalVal = calcMonthlyEarn(rsValue, rsFdInt, rsFdTime);
                }
                fdAcc.add(new FDAccModel(rsFdInt, rsFdTime, username, rsAccoutId, "fd", stringmDate, rsDate, finalVal));

            }
        } catch (SQLException e) {
            System.out.println(e);
        } finally {
            DbUtilities.closePreparedStatement(ps);
            pool.freeConnection(connection);
        }
        return fdAcc;
    }

    public String checkAccAvail(String username, String accId) {
        ConnectionPool pool = ConnectionPool.getInstance();
        Connection connection = pool.getConnection();
        PreparedStatement ps = null;
        String status = "false";

        String dateNow = dateTime.getDate();
        long monthDiff = 0;

        String query
                = "SELECT created_date, fd_time, value, fd_interest_rate FROM fd_acc WHERE status = 1 AND username = ? AND account_id = ? ";
        try {
            ps = connection.prepareStatement(query);
            ps.setString(1, username);
            ps.setString(2, accId);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                String rsDate = rs.getString("created_date");
                float rsValue = Float.parseFloat((rs.getString("value")));
                float rsFdInt = Float.parseFloat(rs.getString("fd_interest_rate"));
                int rsFdTime = Integer.parseInt(rs.getString("fd_time"));
                
                monthDiff = ChronoUnit.MONTHS.between(YearMonth.from(LocalDate.parse(rsDate)), YearMonth.from(LocalDate.parse(dateNow)));
                int intMonthDiff = Math.toIntExact(monthDiff);
                System.out.println("month Diff " + intMonthDiff);

                LocalDate maturityDate = (LocalDate.parse(rsDate)).plusMonths(rsFdTime);
                DateTimeFormatter formatter = DateTimeFormatter.ISO_DATE;
                String stringmDate = maturityDate.format(formatter);

                if (intMonthDiff >= rsFdTime) {
                    float intVal = calcMonthlyEarn(rsValue, rsFdInt, intMonthDiff);
                    float finalVal = intVal+rsValue;
                    status = Float.toString(finalVal);
                }
            }

        } catch (SQLException e) {
            System.out.println(e);
        } finally {
            DbUtilities.closePreparedStatement(ps);
            pool.freeConnection(connection);
        }
        return status;
    }
    
    public boolean setWithdraw(String username, String accId) {
        ConnectionPool pool = ConnectionPool.getInstance();
        Connection connection = pool.getConnection();
        PreparedStatement ps = null;


        String query
                = "UPDATE fd_acc SET value = 0 WHERE  username = ? AND account_id = ? ";
        try {
            connection.setAutoCommit(false); //transaction block start
            ps = connection.prepareStatement(query);
            ps.setString(1, username);
            ps.setString(2, accId);

            System.out.println("Withdrew");
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
    
    public int insertTransaction(String username, String accId, float amount) {
        ConnectionPool pool = ConnectionPool.getInstance();
        Connection connection = pool.getConnection();
        PreparedStatement ps = null;
        int generatedkey = 0;
        
        String transType="fd-withdraw"; 

        String timenow = dateTime.getTime();
        String dateNow = dateTime.getDate();

        String query
                = "INSERT INTO transactions (username, account_id, type, value, date, time ) "
                + "VALUES (?, ?, ?, ?, ?, ?)";
        System.out.println("Res:        Insert Query done -> UsersDao");
        try {

            connection.setAutoCommit(false); //transaction block start
            ps = connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
            ps.setString(1, username);
            ps.setString(2, accId);
            ps.setString(3, transType);
            ps.setFloat(4, amount);
            ps.setString(5, dateNow);
            ps.setString(6, timenow);

            ps.executeUpdate();
            ResultSet rs = ps.getGeneratedKeys();
            System.out.println("Res:        Resultset Done -> inserted transaction");
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

}
