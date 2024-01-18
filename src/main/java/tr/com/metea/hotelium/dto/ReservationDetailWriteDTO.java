package tr.com.metea.hotelium.dto;

import lombok.Getter;
import lombok.Setter;

/**
 * @author Mete Aydin
 * @since 23.10.2021
 */
@Getter
@Setter
public class ReservationDetailWriteDTO {
    private String reservationMasterId;
    private String customerId;
}
