package handler.registration;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.PropertyAccessor;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SequenceWriter;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.sun.net.httpserver.HttpExchange;
import exceptions.ApplicationExceptions;
import exceptions.GlobalExceptionHandler;
import model.User;
<<<<<<< Updated upstream
=======
import utils.ApiUtils;
>>>>>>> Stashed changes
import utils.Constants;
import utils.Handler;

import java.io.*;
<<<<<<< Updated upstream
=======
import java.nio.charset.StandardCharsets;
>>>>>>> Stashed changes
import java.util.UUID;
import java.util.stream.Collectors;

public class RegistrationHandler extends Handler {

    private final File json = new File(Constants.USERS_PATH);
    private final ObjectMapper mapper = new ObjectMapper().setVisibility(PropertyAccessor.FIELD, JsonAutoDetect.Visibility.ANY);

    public RegistrationHandler(ObjectMapper objectMapper,
                               GlobalExceptionHandler exceptionHandler) {
        super(objectMapper, exceptionHandler);
    }

    @Override
    protected void execute(HttpExchange exchange) throws IOException {
        if ("POST".equals(exchange.getRequestMethod())) {
            String body = new BufferedReader(new InputStreamReader(exchange.getRequestBody()))
<<<<<<< Updated upstream
                    .lines().collect(Collectors.joining());
=======
                    .lines()
                    .collect(Collectors.joining());
>>>>>>> Stashed changes

            exchange.getResponseHeaders().set(Constants.CONTENT_TYPE, Constants.APPLICATION_JSON);
            exchange.sendResponseHeaders(200, 0);

            OutputStream responseBody = exchange.getResponseBody();
<<<<<<< Updated upstream
            responseBody.write(addUser(body).getBytes("UTF-8"));
=======
            responseBody.write(addUser(body).getBytes(StandardCharsets.UTF_8));
>>>>>>> Stashed changes
            responseBody.close();
        } else {
            throw ApplicationExceptions.methodNotAllowed(
                "Method " + exchange.getRequestMethod() + " is not allowed for " + exchange.getRequestURI() + ". Please make a POST request with username&password for registration.").get();
        }
        exchange.close();
    }

    private String addUser(String requestBody) {
        try {
<<<<<<< Updated upstream
            FileWriter output = new FileWriter(json);
=======
            RandomAccessFile output = ApiUtils.makeArrayNotFinished(Constants.USERS_PATH);

>>>>>>> Stashed changes
            JsonNode objectNode = mapper.readTree(requestBody);
            User newUser = mapper.convertValue(objectNode, User.class);
            ((ObjectNode) objectNode).put("id",  UUID.randomUUID().toString());
            ((ObjectNode) objectNode).put("username", newUser.getUsername());
            ((ObjectNode) objectNode).put("password", newUser.getPassword());

            SequenceWriter seqWriter = mapper.configure(JsonGenerator.Feature.AUTO_CLOSE_JSON_CONTENT, false)
                    .writerWithDefaultPrettyPrinter()
                    .writeValuesAsArray(output);
            seqWriter.write(objectNode);
            seqWriter.close();
<<<<<<< Updated upstream
=======

            ApiUtils.jsonPrettyArray(String.valueOf(json));

>>>>>>> Stashed changes
        } catch (IOException | IllegalArgumentException e) {
            e.printStackTrace();
        }
        return requestBody;
    }
}
