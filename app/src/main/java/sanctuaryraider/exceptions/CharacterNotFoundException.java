package sanctuaryraider.exceptions;


/**
 * Exception to throw when a given character name is not found in the database.
 */
public class CharacterNotFoundException extends RuntimeException{

    private static final long serialVersionUID = 4527432307748581843L;

    /**
     * Exception with no message or cause.
     */
    public CharacterNotFoundException() {
        super();
    }

    /**
     * Exception with a message, but no cause.
     * @param message A descriptive message for this exception.
     */
    public CharacterNotFoundException(String message) {
        super(message);
    }

    /**
     * Exception with message and cause.
     * @param message A descriptive message for this exception.
     * @param cause The original throwable resulting in this exception.
     */

    public CharacterNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }
    /**
     * Exception with no message, but with a cause.
     * @param cause The original throwable resulting in this exception.
     */
    public CharacterNotFoundException(Throwable cause) {
        super(cause);
    }

}
