package utils;

import com.sun.net.httpserver.Headers;

import lombok.Value;

@Value
public class ResponseEntity<T> {

<<<<<<< Updated upstream
    private final T body;
    private final Headers headers;
    private final StatusCode statusCode;
=======
    T body;
    Headers headers;
    StatusCode statusCode;
>>>>>>> Stashed changes
}