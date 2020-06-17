package exceptions;

import lombok.Getter;

@Getter
class ApplicationException extends RuntimeException {

    private final int code;

    ApplicationException(int code, String message) {
        super(message);
        this.code = code;
    }
}