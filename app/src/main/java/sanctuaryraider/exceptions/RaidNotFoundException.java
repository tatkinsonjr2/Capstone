package sanctuaryraider.exceptions;



public class RaidNotFoundException extends RuntimeException {
    private static final long serialVersionUID = -7246143287769681791L;


    /**
     * Exception with no message or cause.
     */
    public RaidNotFoundException() {
        super();
    }

    /**
     * Exception with a message, but no cause.
     * @param message A descriptive message for this exception.
     */
    public RaidNotFoundException(String message) {
        super(message);
    }

    /**
     * Exception with message and cause.
     * @param message A descriptive message for this exception.
     * @param cause The original throwable resulting in this exception.
     */

    public RaidNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }
    /**
     * Exception with no message, but with a cause.
     * @param cause The original throwable resulting in this exception.
     */
    public RaidNotFoundException(Throwable cause) {
        super(cause);
    }
}
