package Controller;


import Model.*;
import Service.*;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.util.List;



import io.javalin.Javalin;
import io.javalin.http.Context;

/**
 *
 * Social media controller below is what the front-end interacts with to get resourses to and from the database
 * 
 */
public class SocialMediaController {
    AccountService accountService;
    MessageService messageService;

    public SocialMediaController() {
        this.accountService = new AccountService();
        this.messageService = new MessageService();
    }



    /**
     *
     * 
     * @return a Javalin app object which defines the behavior of the Javalin controller.
     */
    public Javalin startAPI() {
        Javalin app = Javalin.create();
        app.post("/login", this::postLoginInfoHandler);
        app.post("/register", this::postRegisterInfoHandler);
        app.post("/messages", this::postNewMessageHandler);
        app.get("/messages", this::getAllMessagesHandler);
        app.get("/messages/{message_id}", this::getMessageHandler);
        app.delete("/messages/{message_id}", this::deleteMessageHandler);
        app.patch("/messages/{message_id}", this::updateMessageHandler);
        app.get("/accounts/{account_id}/messages", this::getAllMessagesFromUserHandler);


        return app;
    }

    private void postLoginInfoHandler(Context ctx) throws JsonProcessingException {
        ObjectMapper mapper = new ObjectMapper();
        Account account = mapper.readValue(ctx.body(), Account.class);
        Account checkedAccount = accountService.loginAccount(account);
        
        if(checkedAccount != null) {
            ctx.json(mapper.writeValueAsString(checkedAccount));
        } else {
            ctx.status(401);
        }
    }


    private void postRegisterInfoHandler(Context ctx) throws JsonProcessingException {
        ObjectMapper mapper = new ObjectMapper();
        Account account = mapper.readValue(ctx.body(), Account.class);
        Account addedAccount = accountService.registerAccount(account);

        if(addedAccount != null) {
            ctx.json(mapper.writeValueAsString(addedAccount));
        } else {
            ctx.status(400);
        }
    }

    private void postNewMessageHandler(Context ctx) throws JsonProcessingException {
        ObjectMapper mapper = new ObjectMapper();
        Message message = mapper.readValue(ctx.body(), Message.class);

        Message addedMessage = messageService.insertMessage(message);


        if(addedMessage != null) {
            ctx.json(mapper.writeValueAsString(addedMessage));
        } else {
            ctx.status(400);
        }
    
    }

    private void getAllMessagesHandler(Context ctx) throws JsonProcessingException {
        List<Message> messages = messageService.getAllMessages();    
        ctx.json(messages);
    }

    private void getMessageHandler(Context ctx) throws JsonProcessingException {
        int messageId = Integer.parseInt(ctx.pathParam("message_id"));

        Message message = messageService.getMessage(messageId);

        if(message != null)
            ctx.json(message);
    
    }

    private void deleteMessageHandler(Context ctx) throws JsonProcessingException {
        int messageId = Integer.parseInt(ctx.pathParam("message_id"));

        Message message = messageService.deleteMessage(messageId);

        if(message != null) {
            ctx.json(message);
        }
    
    }

    private void updateMessageHandler(Context ctx) throws JsonProcessingException {
        int messageId = Integer.parseInt(ctx.pathParam("message_id"));
        ObjectMapper mapper = new ObjectMapper();
        Message messageText = mapper.readValue(ctx.body(), Message.class);

        Message message = messageService.updateMessage(messageText.getMessage_text(), messageId);

        if(message != null) {
            ctx.json(message);
        } else {
            ctx.status(400);
        }

    }

    private void getAllMessagesFromUserHandler(Context ctx) throws JsonProcessingException {
        int accountId = Integer.parseInt(ctx.pathParam("account_id"));

        List<Message> messages = messageService.getMessagesByUser(accountId);    
        ctx.json(messages);
    
    }

}