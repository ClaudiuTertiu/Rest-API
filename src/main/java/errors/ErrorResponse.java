package errors;

import lombok.*;

@Value
@Setter
@Getter
public class ErrorResponse {

<<<<<<< Updated upstream
    private int code;
    private String message;
=======
    int code;
    String message;
>>>>>>> Stashed changes

    private ErrorResponse (ErrorResponseBuilder errorResponseBuilder) {
        this.code = errorResponseBuilder.code;
        this.message = errorResponseBuilder.message;
    }

    @Getter
    @Setter
    @AllArgsConstructor
    public static class ErrorResponseBuilder {
        private int code;
        private String message;

        public ErrorResponse build() {
            return new ErrorResponse(this);
        }
    }
}
