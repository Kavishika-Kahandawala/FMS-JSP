/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.master.controller;

import com.master.controller.model.SavingAccModel;
import com.master.controller.model.dateTimeGen;
import com.master.databaseLayer.ConnectionPool;
import com.master.databaseLayer.DbUtilities;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import org.springframework.security.crypto.bcrypt.BCrypt;

/**
 *
 * @author Kavishika
 */
public class SavingAccDao {

    dateTimeGen dateTime = new dateTimeGen();

    public int insertSavingAcc(SavingAccModel newSavAccModel) {
        ConnectionPool pool = ConnectionPool.getInstance();
        Connection connection = pool.getConnection();
        PreparedStatement ps = null;
        int generatedkey = 0;

        System.out.println("Res:        Came to insert Saving Acc -> SavingDao");
        String query
                = "INSERT INTO saving_acc (username, account_id, value, created_date, created_time,status ) "
                + "VALUES (?, ?, ?, ?, ?, ?)";
        System.out.println("Res:        Insert Query done -> UsersDao");
        try {

            connection.setAutoCommit(false); //transaction block start
            ps = connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
            ps.setString(1, newSavAccModel.getUsername());
            ps.setString(2, newSavAccModel.getAccount_id());
            ps.setFloat(3, newSavAccModel.getValue());
            ps.setString(4, newSavAccModel.getCreatedDate());
            ps.setString(5, newSavAccModel.getCreatedTime());
            ps.setInt(6, 1);

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

    public List<SavingAccModel> viewAccounts(String username) {
        List<SavingAccModel> savinList = new ArrayList<>();
        ConnectionPool pool = ConnectionPool.getInstance();
        Connection connection = pool.getConnection();
        PreparedStatement ps = null;
        float rate = Float.parseFloat(getInterestRate());

//        System.out.println("hey rate "+rate);
        String query
                = "SELECT username, account_id, value FROM saving_acc WHERE username = ?";
        try {
            ps = connection.prepareStatement(query);
            ps.setString(1, username);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {

                String rsUsername = rs.getString("username");
                String rsAccoutId = rs.getString("account_id");
                float rsValue = Float.parseFloat((rs.getString("value")));

//                float intEarn =calcMonthlyEarn(rsValue, rate);

                String intEarn = String.valueOf(calcMonthlyEarn(rsValue, rate));
                savinList.add(new SavingAccModel(rate, rsUsername, rsAccoutId, "savings", "date", intEarn, rsValue));

                System.out.println();
            }
        } catch (SQLException e) {
            System.out.println(e);
        } finally {
            DbUtilities.closePreparedStatement(ps);
            pool.freeConnection(connection);
        }
        return savinList;
    }

    public String getInterestRate() {
        ConnectionPool pool = ConnectionPool.getInstance();
        Connection connection = pool.getConnection();
        PreparedStatement ps = null;
        String rsRate = null;

        String query
                = "SELECT rate FROM rates WHERE account_type = 'savings' ";
        System.out.println("select sql");
        try {
            ps = connection.prepareStatement(query);
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

    public float calcMonthlyEarn(float savingsAmount, float interest) {

        float earnedInt = savingsAmount * ((interest) / 100) / 12;

        return earnedInt;
    }

    public List<SavingAccModel> viewAccountsd(String username, String accId) {
        List<SavingAccModel> savinAcc = new ArrayList<>();
        ConnectionPool pool = ConnectionPool.getInstance();
        Connection connection = pool.getConnection();
        PreparedStatement ps = null;
        float rate = Float.parseFloat(getInterestRate());
//        System.out.println("hey rate "+rate);

        String query
                = "SELECT username, account_id, value FROM saving_acc WHERE username = ? AND account_id = ?";
        try {
            ps = connection.prepareStatement(query);
            ps.setString(1, username);
            ps.setString(2, accId);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {

                String rsUsername = rs.getString("username");
                String rsAccoutId = rs.getString("account_id");
                float rsValue = Float.parseFloat((rs.getString("value")));
                String intEarn = String.valueOf(calcMonthlyEarn(rsValue, rate));
                savinAcc.add(new SavingAccModel(rate, rsUsername, rsAccoutId, "savings", "date", intEarn, rsValue));

                System.out.println();
            }
        } catch (SQLException e) {
            System.out.println(e);
        } finally {
            DbUtilities.closePreparedStatement(ps);
            pool.freeConnection(connection);
        }
        return savinAcc;
    }

    public boolean checkAccAvail(String username, String accId) {
        ConnectionPool pool = ConnectionPool.getInstance();
        Connection connection = pool.getConnection();
        PreparedStatement ps = null;
        boolean status = false;

        String query
                = "SELECT id FROM saving_acc WHERE status = 1 AND username = ? AND account_id = ? ";
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

    public float checkBalAvail(String username, String accId, float amount) {
        ConnectionPool pool = ConnectionPool.getInstance();
        Connection connection = pool.getConnection();
        PreparedStatement ps = null;
        float status = 0;

        String query
                = "SELECT value FROM saving_acc WHERE status = 1 AND username = ? AND account_id = ? ";
        try {
            ps = connection.prepareStatement(query);
            ps.setString(1, username);
            ps.setString(2, accId);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                float accountBal = Float.parseFloat(rs.getString("value"));

                System.out.println(accountBal);

                if (accountBal >= amount) {
                    status = accountBal;

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

    public boolean updateBal(String username, String accId, float amount, float bal) {
        ConnectionPool pool = ConnectionPool.getInstance();
        Connection connection = pool.getConnection();
        PreparedStatement ps = null;
        boolean status = false;

        float newbal = bal - amount;

        String query
                = "UPDATE saving_acc SET value = ? WHERE username= ? AND account_id = ? ";
        try {
            connection.setAutoCommit(false); //transaction block start
            ps = connection.prepareStatement(query);
            ps.setFloat(1, newbal);
            ps.setString(2, username);
            ps.setString(3, accId);

            int updatesql = ps.executeUpdate();
            if (updatesql > 0) {
                status = true;
            }
            connection.commit();// transaction block ends here

        } catch (SQLException e) {
            System.out.println(e);
        } finally {
            DbUtilities.closePreparedStatement(ps);
            pool.freeConnection(connection);
        }
        return status;
    }

    public int insertTransaction(String username, String accId, String transType, float amount) {
        ConnectionPool pool = ConnectionPool.getInstance();
        Connection connection = pool.getConnection();
        PreparedStatement ps = null;
        int generatedkey = 0;

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
}
