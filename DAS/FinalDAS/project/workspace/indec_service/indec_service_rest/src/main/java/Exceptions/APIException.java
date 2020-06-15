package Exceptions;

public class APIException extends RuntimeException {

    public APIException(final Exception ex) {
        super(ex.getMessage(), ex.getCause());
    }

    public APIException(final String exmsg) {
        super(exmsg);
    }
}

