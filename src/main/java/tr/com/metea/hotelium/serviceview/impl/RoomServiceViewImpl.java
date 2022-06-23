package tr.com.metea.hotelium.serviceview.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import tr.com.metea.hotelium.domain.Room;
import tr.com.metea.hotelium.dto.RoomReadDTO;
import tr.com.metea.hotelium.dto.RoomSearchCriteriaDTO;
import tr.com.metea.hotelium.dto.RoomWriteDTO;
import tr.com.metea.hotelium.service.RoomService;
import tr.com.metea.hotelium.serviceview.RoomServiceView;

/**
 * @author Mete Aydin
 * @date 23.10.2021
 */
@Service
@RequiredArgsConstructor
public class RoomServiceViewImpl extends
        BaseServiceViewImpl<Room, RoomWriteDTO, RoomReadDTO, RoomSearchCriteriaDTO>
        implements RoomServiceView {
    private final RoomService roomService;

    @Override
    public RoomReadDTO markAsClean(String id) {
        return convertToDTO(roomService.markAsClean(id));
    }


    @Override
    public RoomReadDTO convertToDTO(Room room) {
        return modelMapper.map(room, RoomReadDTO.class);
    }
}
