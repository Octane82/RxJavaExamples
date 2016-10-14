package exam1;

/**
 * Some exception
 */
public class SomeException extends Exception {

    @Override
    public String getMessage() {
        return "This overrided exception ->";
    }
}
