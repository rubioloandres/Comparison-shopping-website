package sdk.clients.exceptions;

public class ClientException extends RuntimeException {

    public ClientException(final Exception ex) {
        super(ex.getMessage(), ex.getCause());
    }

    public ClientException(final String message) {
        super(message);
    }
}