package tr.com.metea.hotelium.dto;

import lombok.Getter;
import lombok.Setter;
import tr.com.metea.hotelium.domain.ReservationMaster;

import java.util.Date;

/**
 * @author Mete Aydin
 * @date 23.10.2021
 */
@Getter
@Setter
public class ReservationMasterReadDTO extends ReservationMasterWriteDTO {
    private String id;
    private ReservationMaster.ReservationStatus status;
    private Date checkInDate;
    private Date checkOutDate;
    private String roomCode;
    private String orgId;
    private Date creDate;
    private String creUser;
    private Date updDate;
    private String updUser;
    private Boolean isPayed;

}
