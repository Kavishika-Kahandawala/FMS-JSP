/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.master.controller;

/**
 *
 * @author Kavishika
 */
import com.master.controller.model.UserModel;
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

public class UsersDao {

    public int insertUser(UserModel userModel) {
        ConnectionPool pool = ConnectionPool.getInstance();
        Connection connection = pool.getConnection();
        PreparedStatement ps = null;
        int generatedkey = 0;

        System.out.println("Res:        Came to insertUser -> UsersDao");
        String query
                = "INSERT INTO users (username, full_name, password, email) "
                + "VALUES (?, ?, ?, ?)";
        System.out.println("Res:        Insert Query done -> UsersDao");
        try {
//             Bcrypt
//            String pass1 = "Password";
//            BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
//            String hashed = passwordEncoder.encode(pass1);

//            String plain_pass = userModel.getPassword();
//            System.out.println("getpAss is: " + plain_pass);
//            String pw_hash = BCrypt.hashpw(plain_pass, BCrypt.gensalt(10));
//            System.out.println("Hashed Password is: " + pw_hash);
//            String candi_pass = "$2a$10$upv6Ikyv8WhmKsPpk.w77e9xEgMZPDb4zbz41RUmqj9.cBzwCEADC";
//
//            if (BCrypt.checkpw(candi_pass, pw_hash)) {
//                System.err.println("Password matches");
//            } else {
//
//                System.out.println("Passowrds don't match");
//            }
            String originalPassword = userModel.getPassword();

            String generatedHashPassword = BCrypt.hashpw(originalPassword, BCrypt.gensalt());
            System.out.println("Hashed pass is: " + generatedHashPassword);

//            boolean matched = BCrypt.checkpw(originalPassword, "$2a$12$BSADv1n6dv0ggWJBqG7WzeyJqeu30TXsD3l1vvfuzp0OCBQ8FWQzO");
//            System.out.println(matched);
            connection.setAutoCommit(false); //transaction block start
            ps = connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
            ps.setString(1, userModel.getUsername());
            ps.setString(2, userModel.getFullName());
            ps.setString(3, generatedHashPassword);
            ps.setString(4, userModel.getEmail());

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

            System.out.println("Res:        Guess Done SQLInsert -> UsersDao");
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

    public boolean loginUser(String username, String password) {

        UserModel user = null;

        ConnectionPool pool = ConnectionPool.getInstance();
        Connection connection = pool.getConnection();
        PreparedStatement ps = null;
        ResultSet rs = null;
        boolean status = false;

        String query = "SELECT full_name, password, email FROM users WHERE username= ? ";
        System.out.println("Res:        Search Query done -> UsersDao");
        try {
            ps = connection.prepareStatement(query);
            ps.setString(1, username);
            rs = ps.executeQuery();
            if (rs.next()) {
                System.out.println("hello0000  " + rs.getString("full_name"));
                String rsFullName = rs.getString("full_name");
                String rsPassword = rs.getString("password");
                String rsEmail = rs.getString("email");
                user = new UserModel(username, rsFullName, rsPassword, rsEmail);
                System.out.println("full name " + user.getFullName());
                if (BCrypt.checkpw(password, rsPassword)) {
                    System.out.println("Password matches");
                    status = true;
                } else {

                    System.out.println("Passowrds don't match");
                    status = false;
                }

            }
        } catch (SQLException e) {
            System.out.println(e);
        } finally {
            DbUtilities.closeResultSet(rs);
            DbUtilities.closePreparedStatement(ps);
            pool.freeConnection(connection);
        }
        return status;
    }

    public boolean updateUser(UserModel userModel) {
        ConnectionPool pool = ConnectionPool.getInstance();
        Connection connection = pool.getConnection();
        PreparedStatement ps = null;
        boolean status = false;

        String query
                = "UPDATE users SET username = ?, full_name = ?, password = ?, email = ? WHERE username= ?";
        try {
            connection.setAutoCommit(false); //transaction block start
            ps = connection.prepareStatement(query);
            ps.setString(1, userModel.getUsername());
            ps.setString(2, userModel.getFullName());
            ps.setString(3, userModel.getPassword());
            ps.setString(4, userModel.getEmail());
            ps.setString(5, userModel.getUsername());

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

    public List<UserModel> viewUsers() {
        List<UserModel> userList = new ArrayList<>();
        ConnectionPool pool = ConnectionPool.getInstance();
        Connection connection = pool.getConnection();
        PreparedStatement ps = null;

        String query
                = "SELECT * FROM users";
        System.out.println("select sql");
        try {
            ps = connection.prepareStatement(query);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                String rsUsername = rs.getString("username");
                String rsFullName = rs.getString("full_name");
                String rsPassword = rs.getString("password");
                String rsemail = rs.getString("email");
                userList.add(new UserModel(rsUsername, rsFullName, rsPassword, rsemail));
            }
        } catch (SQLException e) {
            System.out.println(e);
        } finally {
            DbUtilities.closePreparedStatement(ps);
            pool.freeConnection(connection);
        }
        return userList;
    }

    public int validateReg(String username, String password, String repassword) {

        int status = 0;

        if (password.equals(repassword)) {
            if (username.length() <= 25) {
                if (checkUsernameAvail(username)) {
                } else {
                    status = 3;
                }
            } else {
                status = 2;
            }
        } else {
            status = 1;
        }
        return status;
    }

    public boolean checkUsernameAvail(String username) {

        ConnectionPool pool = ConnectionPool.getInstance();
        Connection connection = pool.getConnection();
        PreparedStatement ps = null;
        ResultSet rs = null;
        boolean status = true;

        String query = "SELECT username FROM users WHERE username= ? ";
        try {
            ps = connection.prepareStatement(query);
            ps.setString(1, username);
            rs = ps.executeQuery();
            if (rs.next()) {
                status = false;

            }
        } catch (SQLException e) {
            System.out.println(e);
        } finally {
            DbUtilities.closeResultSet(rs);
            DbUtilities.closePreparedStatement(ps);
            pool.freeConnection(connection);
        }
        return status;
    }
}
