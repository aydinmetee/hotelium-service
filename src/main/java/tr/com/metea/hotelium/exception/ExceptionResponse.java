package tr.com.metea.hotelium.exception;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

/**
 * @author Mete Aydin
 * @since 28.10.2021
 */
@Getter
@Setter
public class ExceptionResponse {
    private String message;
    private LocalDateTime localDateTime;

    public ExceptionResponse(String message) {
        this.message = message;
        this.localDateTime = LocalDateTime.now();
    }
}
