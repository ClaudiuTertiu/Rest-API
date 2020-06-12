package exceptions;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.sun.net.httpserver.HttpExchange;
import errors.ErrorResponse;

import java.io.IOException;
import java.io.OutputStream;

import static utils.Constants.APPLICATION_JSON;
import static utils.Constants.CONTENT_TYPE;

public class GlobalExceptionHandler {

    private final ObjectMapper objectMapper;

    public GlobalExceptionHandler(ObjectMapper objectMapper) {
        this.objectMapper = objectMapper;
    }

    public void handle(Throwable throwable, HttpExchange exchange) {
        try {
            throwable.printStackTrace();
            exchange.getResponseHeaders().set(CONTENT_TYPE, APPLICATION_JSON);
            ErrorResponse response = getErrorResponse(throwable, exchange);
            OutputStream responseBody = exchange.getResponseBody();
            responseBody.write(objectMapper.writeValueAsBytes(response));
            responseBody.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private ErrorResponse getErrorResponse(Throwable throwable, HttpExchange exchange) throws IOException {
        ErrorResponse errorResponse;
        if (throwable instanceof InvalidRequestException) {
            InvalidRequestException exc = (InvalidRequestException) throwable;
            errorResponse = new ErrorResponse.ErrorResponseBuilder(exc.getCode(), exc.getMessage())
                    .build();
            exchange.sendResponseHeaders(400, 0);
        } else if (throwable instanceof ResourceNotFoundException) {
            ResourceNotFoundException exc = (ResourceNotFoundException) throwable;
            errorResponse = new ErrorResponse.ErrorResponseBuilder(exc.getCode(), exc.getMessage())
                    .build();
            exchange.sendResponseHeaders(404, 0);
        }
        else if (throwable instanceof MethodNotAllowedException) {
            MethodNotAllowedException exc = (MethodNotAllowedException) throwable;
            errorResponse = new ErrorResponse.ErrorResponseBuilder(exc.getCode(), exc.getMessage())
                    .build();
            exchange.sendResponseHeaders(405, 0);
        } else {
            errorResponse = new ErrorResponse.ErrorResponseBuilder(500, throwable.getMessage())
                    .build();
            exchange.sendResponseHeaders(500, 0);
        }
        return errorResponse;
    }
}
