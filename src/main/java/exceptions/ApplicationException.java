package exceptions;

import lombok.Getter;

@Getter
<<<<<<< Updated upstream
public class ApplicationException extends RuntimeException {
=======
class ApplicationException extends RuntimeException {
>>>>>>> Stashed changes

    private final int code;

    ApplicationException(int code, String message) {
        super(message);
        this.code = code;
    }
}