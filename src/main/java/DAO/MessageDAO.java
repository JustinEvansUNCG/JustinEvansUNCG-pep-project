package DAO;

import Model.*;
import Util.ConnectionUtil;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;


/**
 * 
 * Message DAO class
 * 
 */
public class MessageDAO {


    /**
     * 
     * Inserts a message into the database
     * 
     * @param message
     * @return
     */
    public Message insertMessage(Message message) {

        if (message.message_text.length() > 255 || message.message_text.length() == 0) {
            return null;
        }

        Connection connection = ConnectionUtil.getConnection();

        try {

            String sql = "INSERT INTO Message(posted_by, message_text, time_posted_epoch) VALUES(?,?,?);";
            PreparedStatement ps = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);

            ps.setInt(1, message.getPosted_by());
            ps.setString(2, message.getMessage_text());
            ps.setLong(3, message.getTime_posted_epoch());

            ps.executeUpdate();

            ResultSet pkResultSet = ps.getGeneratedKeys();
            if (pkResultSet.next()) {
                int message_id = (int) pkResultSet.getLong(1);
                return new Message(message_id, message.getPosted_by(), message.getMessage_text(),
                        message.getTime_posted_epoch());
            }

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }

        return null;
    }

    /**
     * 
     * Returns all messages in the database
     * 
     * @return
     */
    public List<Message> getAllMessages() {

        Connection connection = ConnectionUtil.getConnection();
        List<Message> messages = new ArrayList<>();

        try {

            String sql = "SELECT message_id, posted_by, message_text, time_posted_epoch FROM Message;";
            PreparedStatement ps = connection.prepareStatement(sql);

            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                Message message = new Message(rs.getInt("message_id"),
                        rs.getInt("posted_by"),
                        rs.getString("message_text"),
                        rs.getLong("time_posted_epoch"));
                messages.add(message);
            }

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }

        return messages;
    }

    /**
     * 
     * Gets a message by its id
     * 
     * @param messageId
     * @return
     */
    public Message getMessage(int messageId) {

        Connection connection = ConnectionUtil.getConnection();

        try {
            String sql = "SELECT message_id, posted_by, message_text, time_posted_epoch FROM Message WHERE message_id=?;";
            PreparedStatement ps = connection.prepareStatement(sql);

            ps.setInt(1, messageId);

            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                return new Message(rs.getInt("message_id"),
                        rs.getInt("posted_by"),
                        rs.getString("message_text"),
                        rs.getLong("time_posted_epoch"));
            }

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }

        return null;
    }

    /**
     * 
     * Deletes a specified Message
     * 
     * @param messageId
     * @return
     */
    public Message deleteMessage(int messageId) {

        Connection connection = ConnectionUtil.getConnection();

        try {

            // Retrieves the message if it exists
            Message message = getMessage(messageId);

            String sql = "DELETE FROM Message WHERE message_id=?;";
            PreparedStatement ps = connection.prepareStatement(sql);

            ps.setInt(1, messageId);
            ps.executeUpdate();

            // Returns the deleted message
            if (message != null) {
                return message;
            }

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }

        return null;
    }

    /**
     * 
     * Updates a specified message
     * 
     * @param messageText
     * @param messageId
     * @return
     */
    public Message updateMessage(String messageText, int messageId) {
        Connection connection = ConnectionUtil.getConnection();

        try {

            // Retrieves the message if it exists
            Message message = getMessage(messageId);

            if (message != null && messageText.length() < 256 && messageText.length() != 0) {
                String sql = "UPDATE Message SET message_text=? WHERE message_id=?;";
                PreparedStatement ps = connection.prepareStatement(sql);

                ps.setString(1, messageText);
                ps.setInt(2, messageId);
                ps.executeUpdate();

                // Returns the deleted message
                if (message != null) {
                    return new Message(messageId,
                        message.getPosted_by(),
                        messageText,
                        message.getTime_posted_epoch());
                }

            }

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }

        return null;
    }

    /**
     * 
     * Gets all messages from a specified user
     * 
     * @param accountId
     * @return
     */
    public List<Message> getMessagesByUser(int accountId) {
        Connection connection = ConnectionUtil.getConnection();
        List<Message> messages = new ArrayList<>();

        try {

            String sql = "SELECT message_id, posted_by, message_text, time_posted_epoch FROM Message WHERE posted_by=?;";
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setInt(1, accountId);

            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                Message message = new Message(rs.getInt("message_id"),
                        rs.getInt("posted_by"),
                        rs.getString("message_text"),
                        rs.getLong("time_posted_epoch"));
                messages.add(message);
            }

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }

        return messages;
    }
}
