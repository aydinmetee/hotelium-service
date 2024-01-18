package tr.com.metea.hotelium.exception;

import lombok.Getter;
import lombok.Setter;

/**
 * @author Mete Aydin
 * @since 28.10.2021
 */
@Getter
@Setter
public class ServiceExecutionException extends RuntimeException {
    private String message;

    public ServiceExecutionException(String message) {
        this.message = message;
    }
}
