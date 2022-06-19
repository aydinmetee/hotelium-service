package tr.com.metea.hotelium.serviceview.impl;

import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import tr.com.metea.hotelium.domain.Room;
import tr.com.metea.hotelium.dto.RoomReadDTO;
import tr.com.metea.hotelium.dto.RoomSearchCriteriaDTO;
import tr.com.metea.hotelium.dto.RoomWriteDTO;
import tr.com.metea.hotelium.service.RoomService;
import tr.com.metea.hotelium.serviceview.RoomServiceView;

import java.util.List;
import java.util.stream.Collectors;

/**
 * @author Mete Aydin
 * @date 23.10.2021
 */
@Service
@RequiredArgsConstructor
public class RoomServiceViewImpl extends BaseServiceViewImpl<RoomReadDTO, RoomWriteDTO, Room, RoomSearchCriteriaDTO>
        implements RoomServiceView {
    private final RoomService roomService;
    private final ModelMapper modelMapper;

    @Override
    public RoomReadDTO markAsClean(String id) {
        return convertToDTO(roomService.markAsClean(id));
    }

    @Override
    public List<RoomReadDTO> find(String rsqlQueryString) {
        return roomService.find(rsqlQueryString).stream().map(this::convertToDTO).collect(Collectors.toList());
    }

    @Override
    public RoomReadDTO convertToDTO(Room room) {
        return modelMapper.map(room, RoomReadDTO.class);
    }
}
