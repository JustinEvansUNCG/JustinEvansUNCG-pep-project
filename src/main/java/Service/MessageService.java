package Service;

import Model.Account;
import Model.Message;
import DAO.AccountDAO;
import DAO.MessageDAO;

import java.util.List;

public class MessageService {
    private MessageDAO messageDAO;


    /**
     * Basic constructor for setting up message services
     */
    public MessageService() {
        this.messageDAO = new MessageDAO();
    }

    public MessageService(MessageDAO messageDAO) {
        this.messageDAO = messageDAO;
    }


    public Message insertMessage(Message message) {
        return messageDAO.insertMessage(message);
    }

    public List<Message> getAllMessages() {
        return messageDAO.getAllMessages();
    }

    public Message getMessage(int messageId) {
        return messageDAO.getMessage(messageId);
    }

    public Message deleteMessage(int messageId) {
        return messageDAO.deleteMessage(messageId);
    }

    public Message updateMessage(String messageText, int messageId) {
        return messageDAO.updateMessage(messageText, messageId);
    }

    public List<Message> getMessagesByUser(int accountId) {
        return messageDAO.getMessagesByUser(accountId);
    }







}
