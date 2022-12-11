package tr.com.metea.hotelium.service.impl;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.modelmapper.ModelMapper;
import org.springframework.boot.test.context.SpringBootTest;
import tr.com.metea.hotelium.domain.Room;
import tr.com.metea.hotelium.dto.RoomWriteDTO;
import tr.com.metea.hotelium.repository.BaseRepository;
import tr.com.metea.hotelium.repository.RoomRepository;
import tr.com.metea.hotelium.util.MockDataGenerator;
import tr.com.metea.hotelium.util.ReflectionUtil;
import tr.com.metea.hotelium.util.SessionContext;
import tr.com.metea.hotelium.util.SessionData;

import java.lang.reflect.InvocationTargetException;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

/**
 * @author Mete Aydin
 * <p>
 * 11.12.2022
 */
@SpringBootTest
class RoomServiceImplTest {

    @Mock
    private RoomRepository roomRepository;

    @Mock
    private ModelMapper modelMapper;

    @InjectMocks
    private RoomServiceImpl roomService;


    @BeforeEach
    void setUp() {
        roomService.repository = roomRepository;
        roomService.modelMapper = new ModelMapper();
        final var sessionData = SessionData.builder()
                .userName("USER")
                .userId("USER-001")
                .orgId("ORG-001")
                .build();
        SessionContext.setSessionData(sessionData);
    }

    @Test
    void testCreate_return_room_when_send_valid_parameters() throws IllegalAccessException, InvocationTargetException, InstantiationException {
        final var mockDTO = MockDataGenerator.generate(new RoomWriteDTO());
        final var expectedRoom = MockDataGenerator.generate(new Room());
        expectedRoom.setCapacity(mockDTO.getCapacity());
        expectedRoom.setStatus(Room.RoomStatus.CLEAN);
        expectedRoom.setCode(mockDTO.getCode());
        when(modelMapper.map(mockDTO, Room.class)).thenReturn(expectedRoom);
        when(roomRepository.save(any())).thenReturn(expectedRoom);

        final var roomDB = roomService.create(mockDTO);

        assertThat(roomDB).isEqualToComparingFieldByFieldRecursively(expectedRoom);
    }

    @Test
    void testMarkAsReserved_return_room_when_send_valid_parameters() throws IllegalAccessException, InvocationTargetException, InstantiationException {
        final var roomId = "TST-001";
        final var mockRoom = MockDataGenerator.generate(new Room());
        mockRoom.setId(roomId);
        final var expectedRoom = ReflectionUtil.copy(mockRoom);
        expectedRoom.setStatus(Room.RoomStatus.RESERVED);

        when(roomRepository.findByIdAndOrgId(anyString(), anyString())).thenReturn(Optional.of(mockRoom));
        when(roomRepository.save(mockRoom)).thenReturn(expectedRoom);

        final var updatedRoom = roomService.markAsReserved(roomId);

        assertThat(updatedRoom).isEqualToComparingFieldByFieldRecursively(expectedRoom);
        assertThat(updatedRoom.getStatus()).isEqualTo(Room.RoomStatus.RESERVED);
    }

}