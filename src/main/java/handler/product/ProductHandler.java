package handler.product;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.PropertyAccessor;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.sun.net.httpserver.HttpExchange;
import exceptions.ApplicationExceptions;
import exceptions.GlobalExceptionHandler;
import model.Product;
import utils.Constants;
import utils.Handler;

import java.io.File;
import java.io.IOException;
import java.io.OutputStream;

public class ProductHandler extends Handler {

    private final File json = new File(Constants.PRODUCTS_PATH);
    private final ObjectMapper mapper = new ObjectMapper().setVisibility(PropertyAccessor.FIELD, JsonAutoDetect.Visibility.ANY);

    public ProductHandler(ObjectMapper objectMapper,
                          GlobalExceptionHandler exceptionHandler) {
        super(objectMapper, exceptionHandler);
    }

    @Override
    protected void execute(HttpExchange exchange) throws IOException {
        if ("GET".equals(exchange.getRequestMethod())) {
            if(exchange.getResponseBody() == null) {
                throw ApplicationExceptions.notFound("The product with:" + exchange.getRequestBody() + " is not found.").get();
            }
            Product[] product = mapper.readValue(json, Product[].class);
            String prettyUser = mapper.writerWithDefaultPrettyPrinter().writeValueAsString(product);

            exchange.sendResponseHeaders(200, prettyUser.getBytes().length);
            OutputStream output = exchange.getResponseBody();
            output.write(prettyUser.getBytes());
            output.flush();
        }
        else {
            throw ApplicationExceptions.methodNotAllowed(
                    "Method " + exchange.getRequestMethod() + " is not allowed for " + exchange.getRequestURI() + ". Please make a POST request for a product.").get();
        }
        exchange.close();
    }
}
