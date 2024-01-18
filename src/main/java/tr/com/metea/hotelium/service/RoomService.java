package tr.com.metea.hotelium.service;

import tr.com.metea.hotelium.domain.Room;
import tr.com.metea.hotelium.dto.RoomSearchCriteriaDTO;
import tr.com.metea.hotelium.dto.RoomWriteDTO;

/**
 * @author Mete Aydin
 * @since 23.10.2021
 */
public interface RoomService extends
        BaseService<Room, RoomWriteDTO, RoomSearchCriteriaDTO> {

    Room markAsReserved(String id);

    Room markAsFilled(String id);

    Room markAsDirt(String id);

    Room markAsClean(String id);

}
