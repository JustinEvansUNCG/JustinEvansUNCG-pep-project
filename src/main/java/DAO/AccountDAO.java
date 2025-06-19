package DAO;

import Model.Account;
import Util.ConnectionUtil;

import java.sql.*;



public class AccountDAO {
    /**
     * Todo: Add login functionality
     * 
     * 
     */

     public Account loginAccount(Account user) {

        Connection connection = ConnectionUtil.getConnection();

        try {

            String sql = "SELECT account_id, username, password FROM Account WHERE username=? AND password=?;";
            PreparedStatement ps = connection.prepareStatement(sql);

            ps.setString(1, user.getUsername());
            ps.setString(2, user.getPassword());

            ResultSet rs = ps.executeQuery();
            while(rs.next()) {
                Account account = new Account(rs.getInt("account_id"), rs.getString("username"), rs.getString("password"));

                return account;
            }
            




        } catch(SQLException e) {
            System.out.println(e.getMessage());
        }

        return null;
     }



     public String getAccount(Account user) {

        Connection connection = ConnectionUtil.getConnection();

        try {

            String sql = "SELECT account_id FROM Account WHERE username=?;";
            PreparedStatement ps = connection.prepareStatement(sql);

            ps.setString(1, user.getUsername());

            ResultSet rs = ps.executeQuery();
            while(rs.next()) {
                String username = rs.getString("username");

                return username;
            }
            




        } catch(SQLException e) {
            System.out.println(e.getMessage());
        }

        return null;
     }


     public Account registerAccount(Account user) {

        Connection connection = ConnectionUtil.getConnection();

        if(user.getUsername().length() == 0 || user.getPassword().length() < 4) {
            return null;
        }

        try {

            String sql = "INSERT INTO Account(username, password) VALUES(?,?);";
            PreparedStatement ps = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);

            ps.setString(1, user.getUsername());
            ps.setString(2, user.getPassword());

            ps.executeUpdate();

            ResultSet pkResultSet = ps.getGeneratedKeys();
            if(pkResultSet.next()) {
                int account_id = (int) pkResultSet.getLong(1);
                return new Account(account_id, user.getUsername(), user.getPassword());
            }

            
            




        } catch(SQLException e) {
            System.out.println(e.getMessage());
        }

        return null;
     }
}