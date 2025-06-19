package DAO;

import Model.*;
import Util.ConnectionUtil;

import java.sql.*;
import java.util.List;

public class MessageDAO {
    public Message insertMessage(Message message) {

        if(message.message_text.length() > 255 || message.message_text.length() == 0) {
            return null;
        }

        Connection connection = ConnectionUtil.getConnection();

        try {

            String sql = "INSERT INTO Message(posted_by, message_text, time_posted_epoch) VALUES(?,?,?);";
            PreparedStatement ps = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);

            //Long postTime = System.currentTimeMillis();

            ps.setInt(1, message.getPosted_by());
            ps.setString(2, message.getMessage_text());
            ps.setLong(3, message.getTime_posted_epoch());

            ps.executeUpdate();

            ResultSet pkResultSet = ps.getGeneratedKeys();
            if(pkResultSet.next()) {
                int message_id = (int) pkResultSet.getLong(1);
                return new Message(message_id, message.getPosted_by(), message.getMessage_text(), message.getTime_posted_epoch());
            }

            
            




        } catch(SQLException e) {
            System.out.println(e.getMessage());
        }

        return null;
    }

    public List<Message> getAllMessages() {


        return null;
    }

    public Message getMessage(Message message) {


        return null;
    }

    public Message deleteMessage(Message message) {


        return null;
    }

    public Message updateMessage(Message message) {


        return null;
    }

    public List<Message> getMessagesByUser(Account account) {


        return null;
    }
}
