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

    public Message getMessage(Message message) {
        return messageDAO.getMessage(message);
    }

    public Message deleteMessage(Message message) {
        return messageDAO.deleteMessage(message);
    }

    public Message updateMessage(Message message) {
        return messageDAO.updateMessage(message);
    }

    public List<Message> getMessagesByUser(Account account) {
        return messageDAO.getMessagesByUser(account);
    }







}
