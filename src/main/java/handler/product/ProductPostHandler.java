package handler.product;

import com.fasterxml.jackson.annotation.JsonAutoDetect.Visibility;
import com.fasterxml.jackson.annotation.PropertyAccessor;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SequenceWriter;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.sun.net.httpserver.HttpExchange;
import exceptions.ApplicationExceptions;
import exceptions.GlobalExceptionHandler;
import model.Product;
import utils.ApiUtils;
import utils.Constants;
import utils.Handler;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.UUID;
import java.util.stream.Collectors;

public class ProductPostHandler extends Handler {

    private final File json = new File(Constants.PRODUCTS_PATH);

    private final ObjectMapper mapper = new ObjectMapper().setVisibility(PropertyAccessor.FIELD, Visibility.ANY);

    public ProductPostHandler(ObjectMapper objectMapper, GlobalExceptionHandler exceptionHandler) {
        super(objectMapper, exceptionHandler);
    }

    @Override
    public void execute(HttpExchange exchange) {
        try {
            if ("POST".equals(exchange.getRequestMethod())) {
                String body = new BufferedReader(new InputStreamReader(exchange.getRequestBody()))
                        .lines()
                        .collect(Collectors.joining());

                exchange.getResponseHeaders().set(Constants.CONTENT_TYPE, Constants.APPLICATION_JSON);
                exchange.sendResponseHeaders(200, 0);

                OutputStream responseBody = exchange.getResponseBody();
                responseBody.write(addProduct(body).getBytes(StandardCharsets.UTF_8));
                responseBody.close();
            } else {
                throw ApplicationExceptions.methodNotAllowed(
                        "Method " + exchange.getRequestMethod() + " is not allowed for " + exchange.getRequestURI() + ". Please make a proper POST request for a product.").get();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        exchange.close();
    }

    private String addProduct(String requestBody) {
        try {
            RandomAccessFile output = ApiUtils.makeArrayNotFinished(String.valueOf(json));

            JsonNode objectNode = mapper.readTree(requestBody);
            Product newProduct = mapper.convertValue(objectNode, Product.class);

            LocalDateTime now = LocalDateTime.now();
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
            String formatDateTime = now.format(formatter);


            ((ObjectNode) objectNode).put("id", UUID.randomUUID().toString());
            ((ObjectNode) objectNode).put("name", newProduct.getName());
            ((ObjectNode) objectNode).put("price", newProduct.getPrice());
            ((ObjectNode) objectNode).put("category", newProduct.getCategory());
            ((ObjectNode) objectNode).put("createdDate", formatDateTime);
            ((ObjectNode) objectNode).put("updatedDate", formatDateTime);

            SequenceWriter seqWriter = mapper.configure(JsonGenerator.Feature.AUTO_CLOSE_JSON_CONTENT, false)
                    .writerWithDefaultPrettyPrinter()
                    .writeValuesAsArray(output);

            seqWriter.write(objectNode);
            seqWriter.close();

            ApiUtils.jsonPrettyArray(String.valueOf(json));

        } catch (IOException | IllegalArgumentException e) {
            e.printStackTrace();
        }
        return requestBody;
    }
}