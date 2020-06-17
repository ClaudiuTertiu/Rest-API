package exceptions;

class InvalidRequestException extends ApplicationException {
    InvalidRequestException(int code, String message) {
        super(code, message);
    }
}
