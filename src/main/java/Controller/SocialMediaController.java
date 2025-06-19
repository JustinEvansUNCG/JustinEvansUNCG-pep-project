package Controller;


import Model.*;
import Service.*;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.util.List;



import io.javalin.Javalin;
import io.javalin.http.Context;

/**
 * TODO: You will need to write your own endpoints and handlers for your controller. The endpoints you will need can be
 * found in readme.md as well as the test cases. You should
 * refer to prior mini-project labs and lecture materials for guidance on how a controller may be built.
 */
public class SocialMediaController {
    AccountService accountService;
    MessageService messageService;

    public SocialMediaController() {
        this.accountService = new AccountService();
        this.messageService = new MessageService();
    }



    /**
     * In order for the test cases to work, you will need to write the endpoints in the startAPI() method, as the test
     * suite must receive a Javalin object from this method.
     * @return a Javalin app object which defines the behavior of the Javalin controller.
     */
    public Javalin startAPI() {
        Javalin app = Javalin.create();
        app.get("example-endpoint", this::exampleHandler);
        app.post("/login", this::postLoginInfoHandler);
        app.post("/register", this::postRegisterInfoHandler);
        app.post("/messages", this::postNewMessageHandler);
        app.get("/messages", this::getAllMessagesHandler);
        app.get("/messages/{message_id}", this::getMessageHandler);
        app.delete("/messages/{message_id}", this::deleteMessageHandler);
        app.put("/messages/{message_id}", this::updateMessageHandler);
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
    
    
    
    
    
    
    }

    private void getMessageHandler(Context ctx) throws JsonProcessingException {
    
    
    
    
    
    
    }

    private void deleteMessageHandler(Context ctx) throws JsonProcessingException {
    
    
    
    
    
    
    }

    private void updateMessageHandler(Context ctx) throws JsonProcessingException {
    
    
    
    
    
    
    }

    private void getAllMessagesFromUserHandler(Context ctx) throws JsonProcessingException {
    
    
    
    
    
    
    }



    /**
     * This is an example handler for an example endpoint.
     * @param context The Javalin Context object manages information about both the HTTP request and response.
     */
    private void exampleHandler(Context context) {
        context.json("sample text");
    }




}