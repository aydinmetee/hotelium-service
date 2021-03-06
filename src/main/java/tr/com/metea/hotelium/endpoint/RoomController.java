package tr.com.metea.hotelium.endpoint;


import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import tr.com.metea.hotelium.domain.Room;
import tr.com.metea.hotelium.dto.RoomReadDTO;
import tr.com.metea.hotelium.dto.RoomSearchCriteriaDTO;
import tr.com.metea.hotelium.dto.RoomWriteDTO;
import tr.com.metea.hotelium.serviceview.RoomServiceView;

/**
 * @author Mete Aydin
 * @date 23.10.2021
 */
@RestController
@RequestMapping("/room")
@RequiredArgsConstructor
@Api(value = "/room")
public class RoomController extends BaseController<Room, RoomWriteDTO, RoomReadDTO, RoomSearchCriteriaDTO> {

    private final RoomServiceView roomServiceView;

    @GetMapping("/{roomId}/mark-as-clean")
    @ApiOperation(value = "Mark as clean", response = RoomReadDTO.class)
    public ResponseEntity<RoomReadDTO> markAsClean(@PathVariable("roomId") String roomId) {
        return ResponseEntity.ok(roomServiceView.markAsClean(roomId));
    }

}
