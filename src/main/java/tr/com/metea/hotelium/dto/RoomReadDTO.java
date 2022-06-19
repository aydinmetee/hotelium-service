package tr.com.metea.hotelium.dto;

import lombok.Getter;
import lombok.Setter;
import tr.com.metea.hotelium.domain.Room;

import java.util.Date;

/**
 * @author Mete Aydin
 * @date 23.10.2021
 */
@Getter
@Setter
public class RoomReadDTO extends RoomWriteDTO {
    private String id;
    private Room.RoomStatus status;
    private String orgId;
    private Date creDate;
    private String creUser;
    private Date updDate;
    private String updUser;
}
