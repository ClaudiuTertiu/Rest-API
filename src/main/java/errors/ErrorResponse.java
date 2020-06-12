package errors;

import lombok.*;

@Value
@Setter
@Getter
public class ErrorResponse {

    private int code;
    private String message;

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
