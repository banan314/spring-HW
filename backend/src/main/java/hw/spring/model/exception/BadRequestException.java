package hw.spring.model.exception;

/**
 * Created by kamil on 30.05.17.
 */
public class BadRequestException extends Exception {
    public BadRequestException(String reason) {
        super(reason);
    }
}
