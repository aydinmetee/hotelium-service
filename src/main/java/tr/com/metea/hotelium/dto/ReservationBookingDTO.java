package tr.com.metea.hotelium.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;

/**
 * @author Mete Aydin
 * @since 23.10.2021
 */
@Getter
@Setter
public class ReservationBookingDTO {
    private String masterId;
    private Date checkInDate;
}
