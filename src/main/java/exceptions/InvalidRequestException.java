package exceptions;

class InvalidRequestException extends ApplicationException {

<<<<<<< Updated upstream
    public InvalidRequestException(int code, String message) {
=======
    InvalidRequestException(int code, String message) {
>>>>>>> Stashed changes
        super(code, message);
    }
}
