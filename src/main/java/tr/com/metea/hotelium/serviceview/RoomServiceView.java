package tr.com.metea.hotelium.serviceview;

import tr.com.metea.hotelium.domain.Room;
import tr.com.metea.hotelium.dto.RoomReadDTO;
import tr.com.metea.hotelium.dto.RoomSearchCriteriaDTO;
import tr.com.metea.hotelium.dto.RoomWriteDTO;

/**
 * @author Mete Aydin
 * @since 23.10.2021
 */
public interface RoomServiceView extends
        BaseServiceView<Room, RoomWriteDTO, RoomReadDTO, RoomSearchCriteriaDTO> {
    RoomReadDTO markAsClean(String id);

}
