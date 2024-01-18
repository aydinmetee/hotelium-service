package tr.com.metea.hotelium.service.impl;

import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import tr.com.metea.hotelium.domain.Room;
import tr.com.metea.hotelium.dto.RoomSearchCriteriaDTO;
import tr.com.metea.hotelium.dto.RoomWriteDTO;
import tr.com.metea.hotelium.repository.RoomRepository;
import tr.com.metea.hotelium.service.RoomService;
import tr.com.metea.hotelium.util.SessionContext;

/**
 * @author Mete Aydin
 * @since 23.10.2021
 */
@Service
@RequiredArgsConstructor
public class RoomServiceImpl extends
        BaseServiceImpl<Room, RoomWriteDTO, RoomSearchCriteriaDTO>
        implements RoomService {
    private final RoomRepository roomRepository;

    @Override
    public Room create(RoomWriteDTO roomWriteDTO) {
        final var roomDB = modelMapper.map(roomWriteDTO, Room.class);
        roomDB.setStatus(Room.RoomStatus.CLEAN);
        return roomRepository.save(roomDB);
    }

    @Override
    public Page<Room> search(RoomSearchCriteriaDTO filter, Pageable pageable) {
        filter.setOrgId(SessionContext.getSessionData().getOrgId());
        return super.search(filter, pageable);
    }

    @Override
    public Room markAsReserved(String id) {
        final var roomDb = getById(id);
        roomDb.setStatus(Room.RoomStatus.RESERVED);
        return roomRepository.save(roomDb);
    }

    @Override
    public Room markAsFilled(String id) {
        final var roomDb = getById(id);
        roomDb.setStatus(Room.RoomStatus.FILLED);
        return roomRepository.save(roomDb);
    }

    @Override
    public Room markAsDirt(String id) {
        final var roomDb = getById(id);
        roomDb.setStatus(Room.RoomStatus.DIRTY);
        return roomRepository.save(roomDb);
    }

    @Override
    public Room markAsClean(String id) {
        final var roomDb = getById(id);
        roomDb.setStatus(Room.RoomStatus.CLEAN);
        return roomRepository.save(roomDb);
    }

    @Override
    public Room convertToEntity(RoomWriteDTO dto) {
        return modelMapper.map(dto, Room.class);
    }

    @Override
    public Room mapDtoToEntity(Room entity, RoomWriteDTO dto) {
        entity.setCode(dto.getCode());
        entity.setCapacity(dto.getCapacity());
        entity.setValid(dto.getValid());
        return entity;
    }
}
