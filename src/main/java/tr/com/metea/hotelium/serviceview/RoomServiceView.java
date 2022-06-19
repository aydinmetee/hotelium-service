package tr.com.metea.hotelium.serviceview;

import tr.com.metea.hotelium.domain.Room;
import tr.com.metea.hotelium.dto.RoomReadDTO;
import tr.com.metea.hotelium.dto.RoomSearchCriteriaDTO;
import tr.com.metea.hotelium.dto.RoomWriteDTO;

import java.util.List;

/**
 * @author Mete Aydin
 * @date 23.10.2021
 */
public interface RoomServiceView extends BaseServiceView<Room, RoomReadDTO, RoomWriteDTO, RoomSearchCriteriaDTO> {
    RoomReadDTO markAsClean(String id);

    List<RoomReadDTO> find(String rsqlQueryString);

}
