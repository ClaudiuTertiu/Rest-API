package handler.user;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.PropertyAccessor;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.sun.net.httpserver.HttpExchange;
import exceptions.ApplicationExceptions;
import exceptions.GlobalExceptionHandler;
import model.User;
import utils.Constants;
import utils.Handler;

import java.io.File;
import java.io.IOException;
import java.io.OutputStream;

public class UserHandler extends Handler {

    private final File json = new File(Constants.USERS_PATH);
    private final ObjectMapper mapper = new ObjectMapper().setVisibility(PropertyAccessor.FIELD, JsonAutoDetect.Visibility.ANY);

    public UserHandler(ObjectMapper objectMapper,
                       GlobalExceptionHandler exceptionHandler) {
        super(objectMapper, exceptionHandler);
    }

    @Override
    protected void execute(HttpExchange exchange) throws IOException {
        if ("GET".equals(exchange.getRequestMethod())) {
            if(exchange.getResponseBody() == null) {
                throw ApplicationExceptions.notFound("The user with:" + exchange.getRequestBody() + " is not found.").get();
            }

            User[] user = mapper.readValue(json, User[].class);
            String prettyUser = mapper
                    .writerWithDefaultPrettyPrinter()
                    .writeValueAsString(user);
            exchange.sendResponseHeaders(200, prettyUser.getBytes().length);
            OutputStream output = exchange.getResponseBody();
            output.write(prettyUser.getBytes());
            output.flush();
        }
        else {
            throw ApplicationExceptions.methodNotAllowed(
                    "Method " + exchange.getRequestMethod() + " is not allowed for " + exchange.getRequestURI()).get();
        }
        exchange.close();
    }
}
