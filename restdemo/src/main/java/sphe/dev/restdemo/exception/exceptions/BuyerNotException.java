package sphe.dev.restdemo.exception.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class BuyerNotException extends RuntimeException {

    public BuyerNotException(String message) {
        super(message);
    }
}
